package quiz.app.project.dias.dias.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.R;

public class QuizFragment extends Fragment {

    private static final String ARG_QUESTION_ID = "questionId";
    private static final String ARG_QUESTIONS_LIST = "questionsList";
    private String selectedAnswer;
    private int questionId;
    private Questions question;
    private List<RadioButton> radioButtons;

    private TextView textViewQuestion;
    private RadioGroup radioGroupAnswers;
    private Button buttonNext;
    private Button buttonPrevious;
    private List<Questions> questionsList;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(List<Questions> questionsList, int questionId) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_ID, questionId);
        args.putSerializable(ARG_QUESTIONS_LIST, (Serializable) questionsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionId = getArguments().getInt(ARG_QUESTION_ID);
            questionsList = (List<Questions>) getArguments().getSerializable(ARG_QUESTIONS_LIST);
            question = getQuestionById(questionId);
        }
    }

    private Questions getQuestionById(int questionId) {
        for (Questions question : questionsList) {
            if (question.getQuestionsId() == questionId) {
                return question;
            }
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewQuestion = view.findViewById(R.id.textViewQuestion);
        radioGroupAnswers = view.findViewById(R.id.radioGroupAnswers);
        buttonNext = view.findViewById(R.id.buttonNext);
        buttonPrevious = view.findViewById(R.id.buttonPrevious);

        radioGroupAnswers.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = getView().findViewById(checkedId);
            selectedAnswer = radioButton.getText().toString();
        });

        loadQuestion();

        buttonNext.setOnClickListener(v -> onButtonNextClicked());
        buttonPrevious.setOnClickListener(v -> onButtonPreviousClicked());
    }

    private void loadQuestion() {
        QuizDatabase quizDatabase = QuizDatabase.getInstance(requireContext());
        question = getQuestionById(questionId);
        radioGroupAnswers.removeAllViews();

        if (question != null) {
            textViewQuestion.setText(question.getQuestionsText());

            radioButtons = new ArrayList<>();

            RadioButton radioButtonAnswer1 = new RadioButton(requireContext());
            radioButtonAnswer1.setText(question.getCorrectAnswer());
            radioButtons.add(radioButtonAnswer1);

            RadioButton radioButtonAnswer2 = new RadioButton(requireContext());
            radioButtonAnswer2.setText(question.getWrongAnswer1());
            radioButtons.add(radioButtonAnswer2);

            RadioButton radioButtonAnswer3 = new RadioButton(requireContext());
            radioButtonAnswer3.setText(question.getWrongAnswer2());
            radioButtons.add(radioButtonAnswer3);

            RadioButton radioButtonAnswer4 = new RadioButton(requireContext());
            radioButtonAnswer4.setText(question.getWrongAnswer3());
            radioButtons.add(radioButtonAnswer4);

            // Shuffle the radio buttons to display answers randomly
            Collections.shuffle(radioButtons);
            for (RadioButton radioButton : radioButtons) {
                radioGroupAnswers.addView(radioButton);
                if (radioButton.getText().toString().equals(question.getSelectedAnswer())) {
                    radioButton.setChecked(true);
                }
            }

            updateButtonVisibility();
        } else {
            // Handle error, question is null
            Toast.makeText(getContext(), R.string.error_Theme, Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        }
    }

    private void onButtonNextClicked() {
        int selectedRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = getView().findViewById(selectedRadioButtonId);
            boolean isCorrect = selectedRadioButton.getText().equals(question.getCorrectAnswer());
            selectedAnswer = selectedRadioButton.getText().toString();
            ((QuizActivity) requireActivity()).onAnswerSelected(questionId, selectedAnswer); // Pass questionId as the first parameter

            int nextQuestionId = getNextQuestionId(questionId);
            if (nextQuestionId != -1) {
                saveSelectedAnswer();
                replaceWithQuestion(nextQuestionId);
            } else {
                // Reached the end of the quiz
                saveSelectedAnswer();
                ((QuizActivity) requireActivity()).onQuizFinished(); // Call the onQuizFinished method in QuizActivity
            }
        } else {
            Toast.makeText(getContext(), R.string.selectAnswer, Toast.LENGTH_SHORT).show();
        }
    }


    private void onButtonPreviousClicked() {
        int previousQuestionId = getPreviousQuestionId(questionId);
        if (previousQuestionId != -1) {
            saveSelectedAnswer();
            replaceWithQuestion(previousQuestionId);
        }
    }

    private int getPreviousQuestionId(int currentQuestionId) {
        int previousQuestionId = -1;
        int currentQuestionIndex = -1;

        for (int i = 0; i < questionsList.size(); i++) {
            Questions question = questionsList.get(i);
            if (question.getQuestionsId() == currentQuestionId) {
                currentQuestionIndex = i;
                break;
            }
        }

        if (currentQuestionIndex > 0) {
            Questions previousQuestion = questionsList.get(currentQuestionIndex - 1);
            previousQuestionId = previousQuestion.getQuestionsId();
        }

        return previousQuestionId;
    }

    private int getNextQuestionId(int currentQuestionId) {
        int nextQuestionId = -1;
        int currentQuestionIndex = -1;

        for (int i = 0; i < questionsList.size(); i++) {
            Questions question = questionsList.get(i);
            if (question.getQuestionsId() == currentQuestionId) {
                currentQuestionIndex = i;
                break;
            }
        }

        if (currentQuestionIndex < questionsList.size() - 1) {
            Questions nextQuestion = questionsList.get(currentQuestionIndex + 1);
            nextQuestionId = nextQuestion.getQuestionsId();
        }

        return nextQuestionId;
    }

    private void updateButtonVisibility() {
        int previousQuestionId = getPreviousQuestionId(questionId);
        if (previousQuestionId == -1) {
            buttonPrevious.setVisibility(View.INVISIBLE);
        } else {
            buttonPrevious.setVisibility(View.VISIBLE);
        }

        if (isLastQuestion()) {
            buttonNext.setText(R.string.Finish);
        } else {
            buttonNext.setText(R.string.Next);
        }
    }

    private boolean isLastQuestion() {
        int currentQuestionIndex = -1;
        for (int i = 0; i < questionsList.size(); i++) {
            Questions question = questionsList.get(i);
            if (question.getQuestionsId() == questionId) {
                currentQuestionIndex = i;
                break;
            }
        }
        return currentQuestionIndex == questionsList.size() - 1;
    }

    private void replaceWithQuestion(int questionId) {
        QuizFragment fragment = QuizFragment.newInstance(questionsList, questionId);
        FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void saveSelectedAnswer() {
        int selectedRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = getView().findViewById(selectedRadioButtonId);
            question.setSelectedAnswer(selectedRadioButton.getText().toString());
        }
    }
}

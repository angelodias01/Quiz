package quiz.app.project.dias.dias.MainMenuUser.QuizPlay;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.Questions;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.R;

public class QuizFragment extends Fragment {

    private static final String ARG_QUESTION_ID = "questionId";

    private int questionId;
    private Questions question;
    private List<RadioButton> radioButtons;

    private TextView textViewQuestion;
    private RadioGroup radioGroupAnswers;
    private Button buttonNext;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(int questionId) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_ID, questionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionId = getArguments().getInt(ARG_QUESTION_ID);
        }
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

        loadQuestion();

        buttonNext.setOnClickListener(v -> onButtonNextClicked());
    }

    private void loadQuestion() {
        QuizDatabase quizDatabase = QuizDatabase.getInstance(requireContext());
        question = quizDatabase.getQuestionsDao().getQuestionById(questionId);
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
            }
        } else {
            // Handle error, question is null
            Toast.makeText(getContext(), "Error: No question found", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        }
    }

    private void onButtonNextClicked() {
        int selectedRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = getView().findViewById(selectedRadioButtonId);
            boolean isCorrect = selectedRadioButton.getText().equals(question.getCorrectAnswer());
            ((QuizActivity) requireActivity()).onAnswerSelected(isCorrect);
        } else {
            Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}

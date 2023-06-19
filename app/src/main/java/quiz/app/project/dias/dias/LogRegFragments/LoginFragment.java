package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class LoginFragment extends Fragment {
    private static final String userId = "userId";
    private EditText tbEmail, tbPassword;
    private String email, password, restoreEmail, restorePassword;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment newInstance(String insertedEmail, String insertedPassword) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(insertedEmail, fragment.restoreEmail);
        args.putString(insertedPassword, fragment.restorePassword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString(restoreEmail);
            password = getArguments().getString(restorePassword);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView lblCreateOne = view.findViewById(R.id.lblCreateOne);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        Handler handler = new Handler();
        //----------------------------------------------------------------------------------------//
        //Database code
        QuizDatabase db = Room.databaseBuilder(this.getContext(), QuizDatabase.class,"QuizDatabase").build();
        UserDao userDao = db.getUserDao();
        //----------------------------------------------------------------------------------------//
        //Event to advance on label click to the register fragment
        lblCreateOne.setOnClickListener(view1 -> {
            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, RegisterFragment.class, null)
                    .setReorderingAllowed(true)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        });
        //----------------------------------------------------------------------------------------//
        tbEmail = view.findViewById(R.id.tbEmail);
        tbPassword = view.findViewById(R.id.tbPassword);

        //Event to verify credentials to execute the login
        btnLogin.setOnClickListener(view12 -> {
            this.email = tbEmail.getText().toString();
            this.password = tbPassword.getText().toString();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                User user = userDao.getUserByEmailAndPassword(email, password);
                // Create a handler associated with the main/UI thread
                Handler handlers = new Handler(Looper.getMainLooper());

                // Post a runnable on the main/UI thread
                handlers.post(() -> {
                    if (user != null) {
                        Toast.makeText(getActivity(), "Login Successful!",
                                Toast.LENGTH_SHORT).show();
                        executor.shutdown();
                        int userId = userDao.getUserByEmailAndPassword(email, password).getUserId();
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userId", userId);
                        editor.apply();

                        intent = new Intent(getActivity(), MainMenuUser.class);
                        bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                        intent.putExtra("userId", userId);
                        getActivity().startActivity(intent, bundle);
                        handler.postDelayed(() -> getActivity().finish(), 500);
                    } else {
                        Toast.makeText(getActivity(), "Email and Password didn't match!",
                                Toast.LENGTH_SHORT).show();
                        if (email.equals("")) {
                            tbEmail.setError("Please insert your email!");
                            tbEmail.requestFocus();
                        } else if (password.equals("")) {
                            tbPassword.setError("Please insert your password!");
                            tbPassword.requestFocus();
                        } else if (!isValidEmail(tbEmail.getText().toString())) {
                            tbEmail.setError("Invalid email address!");
                            tbEmail.requestFocus();
                        } else {
                            tbEmail.setError("Email and password didn't match!");
                            tbPassword.setError("Email and password didn't match!");
                            tbEmail.requestFocus();
                        }
                    }
                });
            });
        });
    }
        //----------------------------------------------------------------------------------------//
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
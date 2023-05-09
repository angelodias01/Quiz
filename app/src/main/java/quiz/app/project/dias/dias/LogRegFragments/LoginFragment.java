package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Dao;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.Users;
import quiz.app.project.dias.dias.QuizDatabase.UsersDao;
import quiz.app.project.dias.dias.R;

public class LoginFragment extends Fragment {
    private static final String userId = "userid";
    private EditText tbEmail, tbPassword;
    private String insertedEmail, insertedPassword, restoreEmail, restorePassword;
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
            insertedEmail = getArguments().getString(restoreEmail);
            insertedPassword = getArguments().getString(restorePassword);
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
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());
        UsersDao dao = db.getUserDao();
        Users users = dao.getUser(insertedEmail, insertedPassword);
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
            insertedEmail = tbEmail.getText().toString();
            insertedPassword = tbPassword.getText().toString();

            if (users == null) {
                Toast.makeText(getActivity(), "Email and Password didn't match!",
                        Toast.LENGTH_SHORT).show();
                if (insertedEmail.equals("")) {
                    tbEmail.setError("Please insert your email!");
                    tbEmail.requestFocus();
                } else if (insertedPassword.equals("")) {
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
            } else {
                Toast.makeText(getActivity(), "Login Successful!",
                        Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(), MainMenuUser.class);
                bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                getActivity().startActivity(intent, bundle);
                handler.postDelayed(() -> getActivity().finish(), 500);
            }
        });
    }
        //----------------------------------------------------------------------------------------//
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
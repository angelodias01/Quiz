package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class RegisterFragment extends Fragment {
    private static final String userId = "userId";
    private EditText tbUsername, tbEmail, tbPassword;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private String insertedUsername, insertedEmail, insertedPassword, restoreUser, restoreMail, restorePass;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String insertedUsername, String insertedEmail, String insertedPassword) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(insertedUsername, fragment.restoreUser);
        args.putString(insertedEmail, fragment.restoreMail);
        args.putString(insertedPassword, fragment.restorePass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restoreUser = getArguments().getString(insertedUsername);
            restoreMail = getArguments().getString(insertedEmail);
            restorePass = getArguments().getString(insertedPassword);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.TVLogIn);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        //----------------------------------------------------------------------------------------//
        //Database code
        QuizDatabase db = Room.databaseBuilder(this.getContext(), QuizDatabase.class,"QuizDatabase").build();
        UserDao userDao = db.getUserDao();
        //----------------------------------------------------------------------------------------//
        //----------------------------------------------------------------------------------------//
        textView.setOnClickListener(view1 -> {
            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, LoginFragment.class, null)
                    .setReorderingAllowed(true)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        });
        //----------------------------------------------------------------------------------------//
        tbUsername = view.findViewById(R.id.tbUsername);
        tbEmail = view.findViewById(R.id.tbEmail);
        tbPassword = view.findViewById(R.id.tbPassword);

        btnRegister.setOnClickListener(view12 -> {
            insertedUsername = tbUsername.getText().toString();
            insertedEmail = tbEmail.getText().toString();
            insertedPassword = tbPassword.getText().toString();
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                // Create a handler associated with the main/UI thread
                Handler handlers = new Handler(Looper.getMainLooper());

                // Post a runnable on the main/UI thread
                handlers.post(() -> {
                    if(Objects.equals(insertedUsername, "") || Objects.equals(insertedEmail, "") || Objects.equals(insertedPassword, "")){
                        if(Objects.equals(insertedUsername, "")){
                            tbUsername.setError("Please insert your username!");
                            tbUsername.requestFocus();
                        }else if(Objects.equals(insertedEmail, "")){
                            tbEmail.setError("Please insert your email!");
                            tbEmail.requestFocus();
                        }else if(Objects.equals(insertedPassword, "")){
                            tbPassword.setError("Please insert your password!");
                            tbPassword.requestFocus();
                        }
                    }else{
                        if (!isValidEmail(tbEmail.getText().toString())){
                            tbEmail.setError("Invalid email address!");
                            tbEmail.requestFocus();
                        }else{
                            Toast.makeText(getActivity(), "Account Created!",
                                    Toast.LENGTH_SHORT).show();
                            User newUser = new User(insertedUsername,insertedEmail,insertedPassword,false);
                            QuizDatabase.getInstance(this.getContext()).getUserDao().insertAll(newUser);
                            fragmentManager = getParentFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainerView3, LoginFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .commit();
                        }
                    }
                });
            });
        });
        //----------------------------------------------------------------------------------------//
    }
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
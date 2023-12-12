/**
 * RegisterFragment.java
 * Represents a fragment where users can create their account.
 */

package quiz.app.project.dias.dias.view;

import static quiz.app.project.dias.dias.viewmodel.Hash.hashPassword;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class RegisterFragment extends Fragment {
    private static final String userId = "userId";
    private EditText tbUsername, tbEmail, tbPassword;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private String insertedUsername, insertedEmail, insertedPassword, restoreUser, restoreMail, restorePass;
    private UserViewModel userViewModel;

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
        userViewModel = new UserViewModel(getActivity().getApplication());
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
        QuizDatabase db = QuizDatabase.getInstance(getContext());
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

            // Observe the user data by username
            userViewModel.getUserByUsername(insertedUsername).observe(getViewLifecycleOwner(), userByUsername -> {
                // Observe the user data by email
                userViewModel.getUserByEmail(insertedEmail).observe(getViewLifecycleOwner(), userByEmail -> {
                    if (TextUtils.isEmpty(insertedUsername) || TextUtils.isEmpty(insertedEmail) || TextUtils.isEmpty(insertedPassword)) {
                        // Check if any of the fields is empty
                        if (TextUtils.isEmpty(insertedUsername)) {
                            tbUsername.setError(getString(R.string.insert_username));
                            tbUsername.requestFocus();
                        } else if (TextUtils.isEmpty(insertedEmail)) {
                            tbEmail.setError(getString(R.string.insert_email));
                            tbEmail.requestFocus();
                        } else if (TextUtils.isEmpty(insertedPassword)) {
                            tbPassword.setError(getString(R.string.insert_password));
                            tbPassword.requestFocus();
                        }
                    } else {
                        // Check if the email is valid
                        if (!isValidEmail(tbEmail.getText().toString())) {
                            tbEmail.setError(getString(R.string.invalid_email));
                            tbEmail.requestFocus();
                        } else if (userByEmail != null) {
                            // Email already exists
                            Toast.makeText(getActivity(), getString(R.string.email_exists), Toast.LENGTH_SHORT).show();
                            tbEmail.setError(getString(R.string.email_exists));
                            tbEmail.requestFocus();
                        } else if (userByUsername != null) {
                            // Username already exists
                            Toast.makeText(getActivity(), getString(R.string.username_exists), Toast.LENGTH_SHORT).show();
                            tbUsername.setError(getString(R.string.username_exists));
                            tbUsername.requestFocus();
                        } else {
                            // All checks passed, create a new user
                            Toast.makeText(getActivity(), getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                            String hashedInputPassword = hashPassword(insertedPassword);
                            User newUser = new User(insertedUsername, insertedEmail, hashedInputPassword);
                            userViewModel.insertAll(newUser);
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
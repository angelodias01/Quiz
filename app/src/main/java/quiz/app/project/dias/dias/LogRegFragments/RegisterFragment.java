package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import quiz.app.project.dias.dias.MainMenuUser;
import quiz.app.project.dias.dias.R;

public class RegisterFragment extends Fragment {

    private EditText tbUsername;
    private EditText tbEmail;
    private EditText tbPassword;
    private TextView textView;
    private Button btnRegister;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private String insertedUsername;
    private String insertedEmail;
    private String insertedPassword;
    private String restoreUser;
    private String restoreMail;
    private String restorePass;

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
        textView = view.findViewById(R.id.TVLogIn);
        btnRegister = view.findViewById(R.id.btnRegister);
        //----------------------------------------------------------------------------------------//
        textView.setOnClickListener(view1 -> {
            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, LoginFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        });
        //----------------------------------------------------------------------------------------//
        tbUsername = (EditText) view.findViewById(R.id.tbUsername);
        tbEmail = (EditText) view.findViewById(R.id.tbEmail);
        tbPassword = (EditText) view.findViewById(R.id.tbPassword);

        btnRegister.setOnClickListener(view12 -> {
            insertedUsername = tbUsername.getText().toString();
            insertedEmail = tbEmail.getText().toString();
            insertedPassword = tbPassword.getText().toString();

        if(Objects.equals(insertedUsername, "") || Objects.equals(insertedEmail, "") || Objects.equals(insertedPassword, "")){
            if(Objects.equals(insertedUsername, "")){
                Toast.makeText(getActivity(), "You need to insert you username!",
                        Toast.LENGTH_SHORT).show();
            } else if(Objects.equals(insertedEmail, "")){
                Toast.makeText(getActivity(), "You need to insert you e-mail!",
                        Toast.LENGTH_SHORT).show();
            } else if(Objects.equals(insertedPassword, "")){
                Toast.makeText(getActivity(), "You need to insert you password!",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "Account Created!",
                    Toast.LENGTH_SHORT).show();

            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, LoginFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            }
        });
    }
}
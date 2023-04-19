package quiz.app.project.dias.dias.LogRegFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quiz.app.project.dias.dias.R;

public class RegisterFragment extends Fragment {

    private static final String Username = "Username";
    private static final String Email = "Email";
    private static final String Password = "Password";

    private String User;
    private String Mail;
    private String Pass;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String Username, String Email, String Password) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(Username, fragment.User);
        args.putString(Email, fragment.Mail);
        args.putString(Password, fragment.Pass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            User = getArguments().getString(Username);
            Mail = getArguments().getString(Email);
            Pass = getArguments().getString(Password);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
package quiz.app.project.dias.dias.LogRegFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quiz.app.project.dias.dias.R;

public class LoginFragment extends Fragment {

    private static final String Username = "Username";
    private static final String Password = "Password";

    private String User;
    private String Pass;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String Username, String Password) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(Username, fragment.User);
        args.putString(Password, fragment.Pass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            User = getArguments().getString(Username);
            Pass = getArguments().getString(Password);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_fragment, container, false);
    }
}
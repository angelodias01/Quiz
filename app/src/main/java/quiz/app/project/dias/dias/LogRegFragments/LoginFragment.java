package quiz.app.project.dias.dias.LogRegFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.mainActivityFragments.TermsFragment;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.TVCreateOne);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, RegisterFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }
}
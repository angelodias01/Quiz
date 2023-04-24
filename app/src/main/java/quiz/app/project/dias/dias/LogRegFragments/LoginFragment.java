package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
import quiz.app.project.dias.dias.mainActivityFragments.MainActivity;
import quiz.app.project.dias.dias.mainActivityFragments.TermsFragment;

public class LoginFragment extends Fragment {


    private EditText tbEmail;
    private EditText tbPassword;
    private String Username = "";
    private String Password = "";

    private final String UserTeste = "";
    private final String PassTeste = "";

    private String User;
    private String Pass;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment newInstance(String Username, String Password) {
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
            Username = getArguments().getString(User);
            Password = getArguments().getString(Pass);
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
        Button btnLogin = view.findViewById(R.id.btnLogin);

        tbEmail = (EditText) view.findViewById(R.id.tbEmail);
        tbPassword = (EditText) view.findViewById(R.id.tbPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = tbEmail.getText().toString();
                Password = tbPassword.getText().toString();

                if(!Objects.equals(Username, UserTeste) || !Password.equals(PassTeste)){
                    Toast.makeText(getActivity(), "Username or Password incorrect!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Login Successful!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainMenuUser.class);
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                    getActivity().startActivity(intent,bundle);
                }
            }
        });
    }
}
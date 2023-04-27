package quiz.app.project.dias.dias.LogRegFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class LoginFragment extends Fragment {

    private EditText tbEmail, tbPassword;
    private String insertedEmail, insertedPassword, restoreEmail, restorePassword;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private final String EmailTeste = "";
    private final String PassTeste = "";

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
        //----------------------------------------------------------------------------------------//
        //Event to advance on label click to the register fragment
        lblCreateOne.setOnClickListener(view1 -> {
            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, RegisterFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
        });
        //----------------------------------------------------------------------------------------//
        tbEmail = (EditText) view.findViewById(R.id.tbEmail);
        tbPassword = (EditText) view.findViewById(R.id.tbPassword);

        //Event to verify credentials to execute the login
        btnLogin.setOnClickListener(view12 -> {
            insertedEmail = tbEmail.getText().toString();
            insertedPassword = tbPassword.getText().toString();

            //when i have a database connected code to change
            //if (editText.getText().toString().trim().equalsIgnoreCase("")) {
            //      editText.setError("This field can not be blank");
            //}

            if(!Objects.equals(insertedEmail, EmailTeste) || !Objects.equals(insertedPassword, PassTeste)){
                Toast.makeText(getActivity(), "Email and Password didn't match!",
                        Toast.LENGTH_SHORT).show();
                tbEmail.setError("Email and Password didn't match!");
                tbPassword.setError("Email and Password didn't match!");
            }else{
                Toast.makeText(getActivity(), "Login Successful!",
                        Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(), MainMenuUser.class);
                bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                getActivity().startActivity(intent,bundle);
            }
        });
        //----------------------------------------------------------------------------------------//
    }
}
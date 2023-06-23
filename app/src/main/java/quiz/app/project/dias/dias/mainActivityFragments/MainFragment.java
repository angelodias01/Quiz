package quiz.app.project.dias.dias.mainActivityFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quiz.app.project.dias.dias.LogRegFragments.LogRegActivity;
import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.R;

public class MainFragment extends Fragment {
    public static final int delay = 1500;

    public static final Handler handler = new Handler();
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean isAccepted = sharedPreferences.getBoolean("isAccepted", false);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        //event do automatic advance to the sign in screen!
        handler.postDelayed(() -> {
            if (isAccepted == true && isLoggedIn == false) {
                Intent intent = new Intent(requireActivity(), LogRegActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
            if (isAccepted == true && isLoggedIn == true) {
                Intent intent = new Intent(requireActivity(), MainMenuUser.class);
                startActivity(intent);
                requireActivity().finish();
            }
            if (isAccepted == false && isLoggedIn == false) {
                // Creating an instance of the TermsFragment class
                TermsFragment termsFragment = new TermsFragment();

                // Creating a fragment manager to change automatically from the main fragment to the terms fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView8, termsFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        }, delay);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if user has already accepted the terms
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
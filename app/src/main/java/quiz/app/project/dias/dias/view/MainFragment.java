/**
 * MainFragment.java
 * Represents the main fragment of the application.
 */

package quiz.app.project.dias.dias.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.view.LogRegActivity;
import quiz.app.project.dias.dias.view.MainMenuUser;
import quiz.app.project.dias.dias.view.TermsFragment;

public class MainFragment extends Fragment {
    public static final int delay = 1500;

    public static final Handler handler = new Handler();

    /**
     * Default constructor for MainFragment.
     */
    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of MainFragment.
     *
     * @return A new instance of MainFragment.
     */
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

        // Event to automatically advance to the sign-in screen
        handler.postDelayed(() -> {
            if (isAccepted && !isLoggedIn) {
                Intent intent = new Intent(requireActivity(), LogRegActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
            if (isAccepted && isLoggedIn) {
                Intent intent = new Intent(requireActivity(), MainMenuUser.class);
                startActivity(intent);
                requireActivity().finish();
            }
            if (!isAccepted && !isLoggedIn) {
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
        // Initialize the Room database
        QuizDatabase db = QuizDatabase.getInstance(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
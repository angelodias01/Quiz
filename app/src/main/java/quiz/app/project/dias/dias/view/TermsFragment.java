/**
 * TermsFragment.java
 * Fragment for displaying and accepting terms and conditions.
 */

package quiz.app.project.dias.dias.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import quiz.app.project.dias.dias.R;

public class TermsFragment extends Fragment {

    /**
     * Default constructor for the TermsFragment.
     * Required empty public constructor.
     */
    public TermsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the user has already accepted the terms
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean isAccepted = sharedPreferences.getBoolean("isAccepted", false);

        // If the terms are already accepted, navigate to the main activity
        if (isAccepted) {
            Intent intent = new Intent(requireActivity(), LogRegActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnAccept = view.findViewById(R.id.btnAccept);
        Handler handler = new Handler();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store boolean value in SharedPreferences indicating acceptance of terms
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isAccepted", true);
                editor.apply();

                // Navigate to the main activity
                Intent intent = new Intent(requireActivity(), LogRegActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle();
                startActivity(intent, bundle);

                // Finish the current activity
                requireActivity().finish();
            }
        });
    }
}
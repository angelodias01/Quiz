package quiz.app.project.dias.dias.mainActivityFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quiz.app.project.dias.dias.R;

public class GoToTermsFragment extends Fragment {


    public GoToTermsFragment() {
        // Required empty public constructor
    }

    public static GoToTermsFragment newInstance() {
        GoToTermsFragment fragment = new GoToTermsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_go_to_terms, container, false);
    }
}
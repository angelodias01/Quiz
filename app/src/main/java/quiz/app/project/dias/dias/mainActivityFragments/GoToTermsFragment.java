package quiz.app.project.dias.dias.mainActivityFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quiz.app.project.dias.dias.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoToTermsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoToTermsFragment extends Fragment {


    public GoToTermsFragment() {
        // Required empty public constructor
    }


    public static GoToTermsFragment newInstance() {
        GoToTermsFragment fragment = new GoToTermsFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_go_to_terms, container, false);
    }
}
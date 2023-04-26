package quiz.app.project.dias.dias.mainActivityFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import quiz.app.project.dias.dias.LogRegFragments.LogRegActivity;
import quiz.app.project.dias.dias.R;

public class TermsFragment extends Fragment {

    public TermsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // Creating the event to accept the terms and change to the next activity
        // And ending this fragment until new app installation
        Button btnAccept= view.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), LogRegActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
            getActivity().startActivity(intent,bundle);
        });
    }
}
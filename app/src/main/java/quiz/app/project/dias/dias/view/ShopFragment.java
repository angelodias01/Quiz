package quiz.app.project.dias.dias.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.ShopViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class ShopFragment extends Fragment {
    private int userId;
    TextView lblUsernameShop, lblCoinsShop;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private ShopViewModel shopViewModel;
    private UserViewModel userViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopViewModel = new ShopViewModel(getActivity().getApplication());
        userViewModel = new UserViewModel(getActivity().getApplication());
        userCurrencyViewModel = new UserCurrencyViewModel(getActivity().getApplication());
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());


        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameShop = rootView.findViewById(R.id.lblUsernameShop);
        lblCoinsShop = rootView.findViewById(R.id.lblCoinsShop);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            userViewModel.getUserById(userId).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    String username = user.getUsername();
                    lblUsernameShop.setText(username);
                }
            });

            userCurrencyViewModel.getUserCurrencyByUserId(userId).observe(getViewLifecycleOwner(), existingUserCurrency -> {
                if (existingUserCurrency != null) {
                    lblCoinsShop.setText(String.valueOf(existingUserCurrency.getAmount()));
                }
            });
        }
        shopViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                adapter = new ShopAdapter(items);
                recyclerView.setAdapter(adapter);
            }
        });
        return rootView;
    }
}


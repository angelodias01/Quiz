package quiz.app.project.dias.dias.MainMenuUser;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrency;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class ShopFragment extends Fragment {
    private int userId;
    TextView lblUsernameShop , lblCoinsShop;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shop, container, false);

        lblUsernameShop = rootView.findViewById(R.id.lblUsernameShop);
        lblCoinsShop = rootView.findViewById(R.id.lblCoinsShop);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();
            UserCurrencyDao userCurrencyDao = quizDB.getUserCurrencyDao();

            User user = userDao.getUserById(userId);
            UserCurrency existingUserCurrency = userCurrencyDao.getUserCurrencyByUserId(userId);


            if (user != null) {
                String username = user.getUsername();
                lblUsernameShop.setText(username);
                lblCoinsShop.setText(String.valueOf(existingUserCurrency.getAmount()));
            }
        }
        return rootView;
  }
}

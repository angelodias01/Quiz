package quiz.app.project.dias.dias.LogRegFragments;

import static quiz.app.project.dias.dias.LogRegFragments.Hash.hashPassword;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUserDao;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.AchievementsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrency;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class LoginFragment extends Fragment {
    private static final String userId = "userId";
    private EditText tbEmail, tbPassword;
    private String email, password, restoreEmail, restorePassword;
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private int userIdValue;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment newInstance(String insertedEmail, String insertedPassword) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("restoreEmail", insertedEmail);
        args.putString("restorePassword", insertedPassword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restoreEmail = getArguments().getString("restoreEmail");
            restorePassword = getArguments().getString("restorePassword");
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
        Handler handler = new Handler();
        //----------------------------------------------------------------------------------------//
        //Database code
        QuizDatabase db = Room.databaseBuilder(this.getContext(), QuizDatabase.class,"QuizDatabase").build();
        UserDao userDao = db.getUserDao();
        UserCurrencyDao userCurrencyDao = db.getUserCurrencyDao();
        AchievementsDao achievementsDao = db.getAchievementsDao();
        AchievementUserDao achievementUserDao = db.getAchievementUserDao();

        //----------------------------------------------------------------------------------------//
        //Event to advance on label click to the register fragment
        lblCreateOne.setOnClickListener(view1 -> {
            fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, RegisterFragment.class, null)
                    .setReorderingAllowed(true)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        });
        //----------------------------------------------------------------------------------------//
        tbEmail = view.findViewById(R.id.tbEmail);
        tbPassword = view.findViewById(R.id.tbPassword);

        //Event to verify credentials to execute the login
        btnLogin.setOnClickListener(view12 -> {
            this.email = tbEmail.getText().toString();
            this.password = tbPassword.getText().toString();
            long currentTimeMillis = System.currentTimeMillis();
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                String hashedPassword = hashPassword(password);
                User user = userDao.getUserByEmailAndPassword(email, hashedPassword);
                if (user != null){
                    userIdValue = user.getUserId();
                    if(userIdValue > 0) {
                        UserCurrency existingUserCurrency = userCurrencyDao.getUserCurrencyByUserId(userIdValue);
                        if (existingUserCurrency == null) {
                            UserCurrency newUserCurrency = new UserCurrency(userIdValue, 0);
                            userCurrencyDao.insertCurrency(newUserCurrency);
                        }
                        AchievementUser existingUserAchievement = achievementUserDao.getUserAchievementByUserId(userIdValue);
                        if (existingUserAchievement == null) {
                            AchievementUser newAchievementUser = new AchievementUser(userIdValue, 1, currentTimeMillis);
                            achievementUserDao.insertAchievementUser(newAchievementUser);
                        }
                    }
                }
                    // Create a handler associated with the main/UI thread
                    Handler handlers = new Handler(Looper.getMainLooper());

                    // Post a runnable on the main/UI thread
                    handlers.post(() -> {
                        if (user != null) {
                            Toast.makeText(getActivity(), "Login Successful!",
                                    Toast.LENGTH_SHORT).show();
                            executor.shutdown();
                            userIdValue = user.getUserId();
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.putInt("userId", userIdValue);
                            editor.apply();

                            intent = new Intent(getActivity(), MainMenuUser.class);
                            bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                            getActivity().startActivity(intent, bundle);
                            handler.postDelayed(() -> getActivity().finish(), 500);
                        } else {
                            Toast.makeText(getActivity(), "Email and Password didn't match!",
                                    Toast.LENGTH_SHORT).show();
                            if (email.equals("")) {
                                tbEmail.setError("Please insert your email!");
                                tbEmail.requestFocus();
                            } else if (password.equals("")) {
                                tbPassword.setError("Please insert your password!");
                                tbPassword.requestFocus();
                            } else if (!isValidEmail(tbEmail.getText().toString())) {
                                tbEmail.setError("Invalid email address!");
                                tbEmail.requestFocus();
                            } else {
                                tbEmail.setError("Email and password didn't match!");
                                tbPassword.setError("Email and password didn't match!");
                                tbEmail.requestFocus();
                            }
                        }
                    });

            });
        });
    }
        //----------------------------------------------------------------------------------------//
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
package quiz.app.project.dias.dias.MainMenuUser;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import quiz.app.project.dias.dias.R;

public class MainMenuUser extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int userId = getUserIdFromSharedPreferences();
        MainPageFragment fragment = MainPageFragment.newInstance(userId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerViewMenuUser, fragment)
                .commit();
    }

    // Helper method to retrieve the userId from SharedPreferences
    private int getUserIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt("userId", -1);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Do You Want To Exit The App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
package quiz.app.project.dias.dias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class quiz_login extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Lock the screen orientation!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_quiz_login);
    }
    public void goToSignUp(View view){
        //Creates a new intent and opens the login class!
        Intent intent = new Intent(this, quiz_signup.class);
        //Adding the transition!
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        Log.i("Activity", "Changing to Sign Up!");
        startActivity(intent, bundle);
    }
}
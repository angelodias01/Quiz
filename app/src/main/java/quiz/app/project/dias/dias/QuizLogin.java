package quiz.app.project.dias.dias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class QuizLogin extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Lock the screen orientation!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_quiz_login);
    }

    protected void onStart() {
        super.onStart();
        Log.i("Current State", "On Start Of Main Activity!");
    }

    protected void onResume() {
        super.onResume();
        Log.i("Current State", "On Resume Of Main Activity!");
    }

    protected void onPause() {
        super.onPause();
        Log.i("Current State", "On Pause Of Main Activity!");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("Current State", "On Restart Of Main Activity!");
    }

    protected void onStop() {
        super.onStop();
        Log.i("Current State", "On Stop Of Main Activity!");
        finishAfterTransition();
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("Current State", "On Destroy Of Main Activity!");
        //Finish of the application!
        finish();
    }

    public void goToSignUp(View view){
        //Creates a new intent and opens the login class!
        Intent intent = new Intent(this, QuizSignUp.class);
        //Adding the transition!
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        Log.i("Activity", "Changing to Sign Up!");
        startActivity(intent, bundle);
    }
}
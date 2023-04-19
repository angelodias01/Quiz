package quiz.app.project.dias.dias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static final int delay = 2000;
    private static final Handler handler = new Handler();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lock the screen orientation!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("Current State:", "On Create Of Main Activity!");

        //event do automatic advance to the sign in screen!
        handler.postDelayed(() -> {
            Intent goToMainScreen = new Intent(MainActivity.this, QuizLogin.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            MainActivity.this.startActivity(goToMainScreen, bundle);
        }, delay);
    }

    protected void onStart() {
        super.onStart();
        Log.i("Current State:", "On Start Of Main Activity!");
    }

    protected void onResume() {
        super.onResume();
        Log.i("Current State:", "On Resume Of Main Activity!");
    }

    protected void onPause() {
        super.onPause();
        Log.i("Current State:", "On Pause Of Main Activity!");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("Current State:", "On Restart Of Main Activity!");

        //event do automatic advance to the sign in screen on the restart of the main screen!
        handler.postDelayed(() -> {
            Intent goToMainScreen = new Intent(MainActivity.this, QuizLogin.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            MainActivity.this.startActivity(goToMainScreen, bundle);
        }, delay);
    }

    protected void onStop() {
        super.onStop();
        Log.i("Current State:", "On Stop Of Main Activity!");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("Current State:", "On Destroy Of Main Activity!");
        //Finish of the application!
        finish();
    }
}
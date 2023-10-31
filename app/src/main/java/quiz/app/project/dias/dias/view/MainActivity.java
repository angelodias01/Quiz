package quiz.app.project.dias.dias.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.R;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuizDatabase db = Room.databaseBuilder(this.getApplicationContext(), QuizDatabase.class,"QuizDatabase").build();
        //Locking the screen orientation!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("Current State:", "On Create Of Main Activity!");

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


    }

    protected void onStop() {
        super.onStop();
        Log.i("Current State:", "On Stop Of Main Activity!");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("Current State:", "On Destroy Of Main Activity!");
        finish();
    }
}
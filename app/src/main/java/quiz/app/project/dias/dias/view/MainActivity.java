/**
 * MainActivity.java
 * Represents the main activity of the application.
 */

package quiz.app.project.dias.dias.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.R;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Room database
        QuizDatabase db = Room.databaseBuilder(this.getApplicationContext(), QuizDatabase.class, "QuizDatabase").build();

        // Locking the screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Overrides the onDestroy method to ensure proper cleanup.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
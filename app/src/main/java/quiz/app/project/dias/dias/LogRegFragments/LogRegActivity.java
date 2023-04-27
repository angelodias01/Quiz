package quiz.app.project.dias.dias.LogRegFragments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import quiz.app.project.dias.dias.R;

public class LogRegActivity extends AppCompatActivity {
    private Boolean credencialsColored;
    private EditText tbEmail;
    private EditText tbPassword;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_log_reg);
    }
}
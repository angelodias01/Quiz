package quiz.app.project.dias.dias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logo_advance(View view) {
        Intent intent = new Intent(this, quiz_login.class);
        startActivity(intent);
    }

    public void layout_advance(View view) {
        Intent intent = new Intent(this, quiz_login.class);
        startActivity(intent);
    }

    public void label_advance(View view) {
        Intent intent = new Intent(this, quiz_login.class);
        startActivity(intent);
    }
}
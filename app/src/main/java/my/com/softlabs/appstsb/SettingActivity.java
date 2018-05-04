package my.com.softlabs.appstsb;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static my.com.softlabs.appstsb.R.id.btn_about;
import static my.com.softlabs.appstsb.R.id.btn_change_pass;
import static my.com.softlabs.appstsb.R.id.title_setting;

public class SettingActivity extends AppCompatActivity {

    TextView title;
    Button btn1, btn2;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        title = (TextView) findViewById(title_setting);
        btn1 = (Button) findViewById(btn_change_pass);
        btn2 = (Button) findViewById(btn_about);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        title.setTypeface(tf1);
        btn1.setTypeface(tf1);
        btn2.setTypeface(tf1);
    }

    public void goToChangePasswordActivity(View view){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToAboutActivity(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }




}

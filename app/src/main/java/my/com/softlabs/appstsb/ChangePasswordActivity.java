package my.com.softlabs.appstsb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static my.com.softlabs.appstsb.R.id.btnConfirmPass;
import static my.com.softlabs.appstsb.R.id.etConfirmPass;
import static my.com.softlabs.appstsb.R.id.etNewPass;
import static my.com.softlabs.appstsb.R.id.etOldPass;
import static my.com.softlabs.appstsb.R.id.title_change_pass;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView title;
    EditText t1, t2, t3;
    Button btn1;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        title = (TextView) findViewById(title_change_pass);
        t1 = (EditText) findViewById(etOldPass);
        t2 = (EditText) findViewById(etNewPass);
        t3 = (EditText) findViewById(etConfirmPass);
        btn1 = (Button) findViewById(btnConfirmPass);


        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        title.setTypeface(tf1);
        t1.setTypeface(tf1);
        t2.setTypeface(tf1);
        t3.setTypeface(tf1);
        btn1.setTypeface(tf1);

    }

    public void confirmPass(View view){

        EditText newPass = (EditText) findViewById(etNewPass);
        EditText conPass = (EditText) findViewById(etConfirmPass);

        if(newPass.getText().toString().equals(conPass.getText().toString())){
            //finish();
            Toast.makeText(this, "Password Change Successfully. Please Login Again.", Toast.LENGTH_SHORT).show();
            //Getting out sharedpreferences
            SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            //Getting editor
            SharedPreferences.Editor editor = preferences.edit();

            //Putting the value false for loggedin
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

            //Putting blank value to staff_id
            editor.putString(Config.STAFFID_SHARED_PREF, "");
            //editor.putString(Config.STAFFNAME_SHARED_PREF, "");
            //Saving the sharedpreferences
            editor.commit();

            //Starting login activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please Check Your Password!", Toast.LENGTH_SHORT).show();
        }



    }


}

package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LeaveActivity extends Activity {
    String staff,address,contact;
    String json_string;
    String strJSONValue;
    JSONObject jsonObject;
    JSONArray jsonArray;

    Typeface tf2;
    Button bstat, bapp;
    TextView ttitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ttitle = (TextView) findViewById(R.id.leave_title);
        bstat = (Button) findViewById(R.id.btnLeaveStat);
        bapp = (Button) findViewById(R.id.btnLeaveApp);

        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        ttitle.setTypeface(tf2);
        bstat.setTypeface(tf2);
        bapp.setTypeface(tf2);

        strJSONValue = getIntent().getExtras().getString("json_data");

        //staff_id = intent.getStringExtra(Config.STAFF_ID);


        try {
            jsonObject = new JSONObject(strJSONValue);
            jsonArray = jsonObject.getJSONArray("result");

            int count = 0;
            //String name,id,ic, confirm, gender, desig;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                address = JO.getString("address");
                contact = JO.getString("contact");
                count++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        staff = getIntent().getExtras().getString("staff");
        new BackgroundTask().execute();



    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.URL_LEAVE_STAT + staff;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView textview = (TextView)findViewById(R.id.tv);
            //textview.setText(result);
            json_string = result;
        }
    }

    public void goToLeaveApplicationActivity(View view){
        Intent intent = new Intent(this, LeaveApplicationActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        String edit_id = "0";
        intent.putExtra("edit_id",edit_id);
        String leave_id = "0";
        intent.putExtra("leave_id",leave_id);
        intent.putExtra("address",address);
        intent.putExtra("contact",contact);
        intent.putExtra("json_data",json_string);
        startActivity(intent);
    }

    public void goToLeaveStatusActivity(View view){
        Intent intent = new Intent(this, LeaveStatusActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("json_data",json_string);
        intent.putExtra("address",address);
        intent.putExtra("contact",contact);
        startActivity(intent);
    }
}

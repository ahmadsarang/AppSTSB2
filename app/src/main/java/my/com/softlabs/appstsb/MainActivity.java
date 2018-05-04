package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
import static my.com.softlabs.appstsb.R.id.staff_id;
import static my.com.softlabs.appstsb.R.id.title_title;
import static my.com.softlabs.appstsb.R.id.tvID;

public class MainActivity extends AppCompatActivity {

    //private TextView current_user;
    private TextView current_id;
    private String strJSONValue,staff_aidi,strJSONValue2,dept;
    private static Button exitButton;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String address,contact,link;
    TextView ttitle;
    Typeface tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setTitle("Softlabs Staff Management System");

        ttitle = (TextView) findViewById(title_title);
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        ttitle.setTypeface(tf2);

        //strJSONValue2 = getIntent().getExtras().getString("json_data");

        /*try {
            jsonObject = new JSONObject(strJSONValue);
            jsonArray = jsonObject.getJSONArray("result");

            int count = 0;
            String  staff_address,contact_no;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                staff_address = JO.getString("staff_address");
                contact_no = JO.getString("contact_no");

                address = staff_address;
                contact = contact_no;


                //Contacts contacts = new Contacts(name,ic,design);
                //contactAdapter.add(contacts);

                count++;
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        //onButtonClickListener();

        //Initializing textview
        current_id = (TextView) findViewById(tvID);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String staff_id = sharedPreferences.getString(Config.STAFFID_SHARED_PREF,"Not Available");
        //String staff_name = sharedPreferences.getString(Config.STAFFNAME_SHARED_PREF,"Not Available");

        //Showing the current logged in email to textview
        //current_user.setText("WELCOME " + staff_id);

        current_id.setText(staff_id);
        staff_aidi = staff_id;


        setTitle(staff_id);
        new BackgroundTask().execute();
        new BackgroundTask2().execute();
    }

    public void exitApp(View v){
        finishAffinity();
    }

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

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
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            //json_url = "http://192.168.1.68:8080/appSTSB/getStaff.php?staff_id=18";
            json_url = Config.URL_GET_STAFF + current_id.getText();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL (json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
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
            //TextView textview = (TextView)findViewById(R.id.textView);
            //textview.setText(result);
            strJSONValue = result;


            try {
                jsonObject = new JSONObject(strJSONValue);
                jsonArray = jsonObject.getJSONArray("result");

                int count = 0;
                //String apply, start, end, stat;
                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    dept = JO.getString("dept");


                    count++;
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

            //Creating editor to store values to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Adding values to editor
            editor.putString(Config.DEPT_SHARED_PREF, dept);
          editor.apply();

        }
    }

    class BackgroundTask2 extends AsyncTask<Void,Void,String> {
        String json_url2;
        String JSON_STRING2;

        @Override
        protected void onPreExecute() {
            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String dept2 = sharedPreferences.getString(Config.DEPT_SHARED_PREF,"no");
            //json_url = "http://192.168.1.68:8080/appSTSB/getStaff.php?staff_id=18";
            json_url2 = Config.URL_LEAVE_STAT_APPROVE + dept2 ;
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL (json_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING2 = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING2+"\n");
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
            //TextView textview = (TextView)findViewById(R.id.textView);
            //textview.setText(result);
            strJSONValue2 = result;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToProfileActivity(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra("json_data", strJSONValue);
        startActivity(intent);
    }

    public void goToSlipActivity(View view){
        Intent intent = new Intent(this, SlipActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToApprovalActivity(View view){
        Intent intent = new Intent(this, ApprovalActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("json_data", strJSONValue);
        intent.putExtra("json_data_2", strJSONValue2);
        startActivity(intent);
    }

    public void goToSettingActivity(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToLeaveActivity(View view){
        Intent intent = new Intent(this, LeaveActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("staff",staff_aidi);
        intent.putExtra("json_data", strJSONValue);


        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}

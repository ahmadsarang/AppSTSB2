package my.com.softlabs.appstsb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener{

    //Defining views
    private EditText editTextStaffID;
    private EditText editTextPassword;
    private AppCompatButton btnLogin;
    private String strJSONValue;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing views
        editTextStaffID = (EditText) findViewById(R.id.etStaff_id);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);

        //Adding click listener
        btnLogin.setOnClickListener(this);


    }



    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String staff_id = editTextStaffID.getText().toString().trim();
        //final String staff_name = editTextStaffID.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        //final String staff_name = tvStaffName.setText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.STAFFID_SHARED_PREF, staff_id);
                            //editor.putString(Config.STAFFNAME_SHARED_PREF, staff_name);

                            //Saving values to editor
                            editor.commit();
                            //new BackgroundTask().execute();

                            //Starting profile activity
                            //String method = editTextStaffID.getText().toString();
                            //BgLoginActivity backgroundTask = new BgLoginActivity(this);
                            //backgroundTask.execute(method);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //intent.putExtra("json_data",strJSONValue);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_STAFFID, staff_id);
                params.put(Config.KEY_PASSWORD, password);
                //params.put(Config.KEY_STAFF_NAME, staff_name);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert =  builder.create();
        alert.show();
    }
}

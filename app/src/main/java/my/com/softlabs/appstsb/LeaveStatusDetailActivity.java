package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
import static my.com.softlabs.appstsb.R.id.tvTodayDate;

/**
 * Created by Muhammad Akmal on 10/3/2017.
 */

public class LeaveStatusDetailActivity extends Activity {
    String json_string,apply, start, end, stat, reason, type, add, no,leave_id,address,contact;
    JSONObject jsonObject;
    JSONArray jsonArray;


    private TextView tvTodayDate;
    private TextView tvFromDate;
    private TextView tvToDate;
    private TextView tvTotalDays;
    private TextView tvReasons;
    private TextView tvTypeOfLeave;
    private TextView tvAddress;
    private TextView tvContact;

    TextView tlhd, tad, tadf, tadt, ttd, tr, tol, ta, tcn;
    Button el;
    Typeface tf1, tf2;


    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_status_detail);

        tlhd = (TextView) findViewById(R.id.titleLeaveHisDet);
        tad = (TextView) findViewById(R.id.ttvTodayDate);
        tadf = (TextView) findViewById(R.id.ttvFromDate);
        tadt = (TextView) findViewById(R.id.ttvToDate);
        ttd = (TextView) findViewById(R.id.ttvTotalDays);
        tr = (TextView) findViewById(R.id.ttvReasons);
        tol = (TextView) findViewById(R.id.ttvTypeOfLeave);
        ta = (TextView) findViewById(R.id.ttvAddress);
        tcn = (TextView) findViewById(R.id.ttvContact);

        tvTodayDate = (TextView) findViewById(R.id.tvTodayDate);
        tvFromDate = (TextView) findViewById(R.id.tvFromDate);
        tvToDate = (TextView) findViewById(R.id.tvToDate);
        tvTotalDays = (TextView) findViewById(R.id.tvTotalDays);
        tvReasons = (TextView) findViewById(R.id.tvReasons);
        tvTypeOfLeave = (TextView) findViewById(R.id.tvTypeOfLeave);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvContact = (TextView) findViewById(R.id.tvContact);

        el = (Button) findViewById(R.id.btnEditLeave);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        tlhd.setTypeface(tf2);
        tad.setTypeface(tf2);
        tadf.setTypeface(tf2);
        tadt.setTypeface(tf2);
        ttd.setTypeface(tf2);
        tr.setTypeface(tf2);
        tol.setTypeface(tf2);
        ta.setTypeface(tf2);
        tcn.setTypeface(tf2);

        tvTodayDate.setTypeface(tf1);
        tvFromDate.setTypeface(tf1);
        tvToDate.setTypeface(tf1);
        tvTotalDays.setTypeface(tf1);
        tvReasons.setTypeface(tf1);
        tvTypeOfLeave.setTypeface(tf1);
        tvAddress.setTypeface(tf1);
        tvContact.setTypeface(tf1);

        el.setTypeface(tf2);


        leave_id = getIntent().getExtras().getString("leave_id");
        address = getIntent().getExtras().getString("address");
        contact = getIntent().getExtras().getString("contact");
        new BackgroundTask().execute();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_leave);


    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.URL_LEAVE_STAT_DETAIL + leave_id;
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
            json_string = result;


            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("server_response");

                int count = 0;
                //String apply, start, end, stat;
                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    apply = JO.getString("apply");
                    start = JO.getString("start");
                    end = JO.getString("end");
                    stat = JO.getString("total");
                    reason = JO.getString("reason");
                    type = JO.getString("type");
                    add = JO.getString("add");
                    no = JO.getString("no");

                    count++;
                }

                tvTodayDate = (TextView) findViewById(R.id.tvTodayDate);
                tvTodayDate.setText(apply);
                tvFromDate = (TextView) findViewById(R.id.tvFromDate);
                tvFromDate.setText(start);
                tvToDate = (TextView) findViewById(R.id.tvToDate);
                tvToDate.setText(end);
                tvTotalDays = (TextView) findViewById(R.id.tvTotalDays);
                tvTotalDays.setText(stat);
                tvReasons = (TextView) findViewById(R.id.tvReasons);
                tvReasons.setText(reason);
                tvTypeOfLeave = (TextView) findViewById(R.id.tvTypeOfLeave);
                tvTypeOfLeave.setText(type);
                tvAddress = (TextView) findViewById(R.id.tvAddress);
                tvAddress.setText(add);
                tvContact = (TextView) findViewById(R.id.tvContact);
                tvContact.setText(no);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

   public void editLeave(View view){
       Intent intent = new Intent(this, LeaveApplicationActivity.class);
       intent.putExtra(EXTRA_MESSAGE, message);
       String edit_id = "1";
       intent.putExtra("edit_id",edit_id);
       intent.putExtra("leave_id",leave_id);
       intent.putExtra("address",address);
       intent.putExtra("contact",contact);
       startActivity(intent);
   }
}

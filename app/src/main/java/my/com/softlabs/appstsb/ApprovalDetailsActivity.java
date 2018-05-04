package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ApprovalDetailsActivity extends Activity {

    Calendar dateTime  = Calendar.getInstance();
    TextView ttv1, ttv2, ttv3, ttv4, ttv5, ttv6, ttv7, ttv8, ttv9, ttv10,ttv11;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9,tv10;
    Button btn1;
    Typeface tf1, tf2;
    Spinner spina,spina2;
    ArrayAdapter<CharSequence> adapter;
    SimpleDateFormat formatToday, formatFrom, formatTo;
    String json_string,json_string2;
    String apply,leave_bal;
    String start;
    String end;
    String stat;
    String reason;
    String type;
    String add;
    String no;
    String leave_id;
    String address;
    String contact;
    String dept;
    String name;
    String spun;
    String hradmin;
    String spun2;
    String name_man;
    String id_man;
    String date_app,staff_id_aa;
    String applyS,startS,endS,statS,reasonS,typeS,addS,noS,sidS,mrS,nameS,id_manS,name_manS,date_appS,leave_idS;
    JSONObject jsonObject,jsonObject2;
    JSONArray jsonArray,jsonArray2;
    private String staff_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_details);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        staff_id = sharedPreferences.getString(Config.STAFFID_SHARED_PREF,"Not Available");

        ttv1 = (TextView) findViewById(R.id.title_approval_details);
        ttv2 = (TextView) findViewById(R.id.ttvName);
        ttv3 = (TextView) findViewById(R.id.ttvTodayDate);
        ttv4 = (TextView) findViewById(R.id.ttvFromDate);
        ttv5 = (TextView) findViewById(R.id.ttvToDate);
        ttv6 = (TextView) findViewById(R.id.ttvTotalDays);
        ttv7 = (TextView) findViewById(R.id.ttvReasons);
        ttv8 = (TextView) findViewById(R.id.ttvTypeOfLeave);
        ttv9 = (TextView) findViewById(R.id.ttvAddress);
        ttv10 = (TextView) findViewById(R.id.ttvContact);
        //ttv11 = (TextView) findViewById(R.id.ttvManRemark);

        tv1 = (TextView) findViewById(R.id.tvName);
        tv2 = (TextView) findViewById(R.id.tvTodayDate);
        tv3 = (TextView) findViewById(R.id.tvFromDate);
        tv4 = (TextView) findViewById(R.id.tvToDate);
        tv5 = (TextView) findViewById(R.id.tvTotalDays);
        tv6 = (TextView) findViewById(R.id.tvReasons);
        tv7 = (TextView) findViewById(R.id.tvTypeOfLeave);
        tv8 = (TextView) findViewById(R.id.tvAddress);
        tv9 = (TextView) findViewById(R.id.tvContact);
        //tv10 = (TextView) findViewById(R.id.tvManRemark);

        btn1 = (Button) findViewById(R.id.btnApproval);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        ttv1.setTypeface(tf2);
        ttv2.setTypeface(tf2);
        ttv3.setTypeface(tf2);
        ttv4.setTypeface(tf2);
        ttv5.setTypeface(tf2);
        ttv6.setTypeface(tf2);
        ttv7.setTypeface(tf2);
        ttv8.setTypeface(tf2);
        ttv9.setTypeface(tf2);
        ttv10.setTypeface(tf2);
        //ttv11.setTypeface(tf2);

        tv1.setTypeface(tf1);
        tv2.setTypeface(tf1);
        tv3.setTypeface(tf1);
        tv4.setTypeface(tf1);
        tv5.setTypeface(tf1);
        tv6.setTypeface(tf1);
        tv7.setTypeface(tf1);
        tv8.setTypeface(tf1);
        tv9.setTypeface(tf1);
        //tv10.setTypeface(tf1);

        btn1.setTypeface(tf2);
        hradmin = getIntent().getExtras().getString("hradmin");
        if (hradmin.equals("1")) {
            spina = (Spinner) findViewById(R.id.spinnerApproval);
            adapter = ArrayAdapter.createFromResource(this, R.array.approval_list_admin, R.layout.spinner_item_approval);
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spina.setAdapter(adapter);
            spina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int pos, long id) {
                    spun = getResources().getStringArray(R.array.approval_list_admin_value)[pos];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else{
            spina = (Spinner) findViewById(R.id.spinnerApproval);
            adapter = ArrayAdapter.createFromResource(this, R.array.approval_list_manager, R.layout.spinner_item_approval);
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spina.setAdapter(adapter);
            spina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG) .show();
                    //spun = parent.getItemAtPosition(position).toString();
                //    spun = parent.getSelectedItem().toString();
                //}
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int pos, long id) {
                    spun = getResources().getStringArray(R.array.approval_list_managers_value)[pos];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        formatToday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_app = formatToday.format(dateTime.getTime());

        leave_id = getIntent().getExtras().getString("leave_id");
        name = getIntent().getExtras().getString("name");
        name_man = getIntent().getExtras().getString("name_man");
        id_man = getIntent().getExtras().getString("id_man");
        dept = getIntent().getExtras().getString("dept");
        staff_id_aa = getIntent().getExtras().getString("staff_id");

        //dept = getIntent().getExtras().getString("dept");
        new BackgroundTask().execute();
        new BackgroundTask2().execute();
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
                tv1 = (TextView) findViewById(R.id.tvName);
                tv1.setText(name);
                tv2 = (TextView) findViewById(R.id.tvTodayDate);
                tv2.setText(apply);
                tv3 = (TextView) findViewById(R.id.tvFromDate);
                tv3.setText(start);
                tv4 = (TextView) findViewById(R.id.tvToDate);
                tv4.setText(end);
                tv5 = (TextView) findViewById(R.id.tvTotalDays);
                tv5.setText(stat);
                tv6 = (TextView) findViewById(R.id.tvReasons);
                tv6.setText(reason);
                tv7 = (TextView) findViewById(R.id.tvTypeOfLeave);
                tv7.setText(type);
                tv8 = (TextView) findViewById(R.id.tvAddress);
                tv8.setText(add);
                tv9 = (TextView) findViewById(R.id.tvContact);
                tv9.setText(no);




            } catch (JSONException e) {
                e.printStackTrace();
            }


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
            json_url2 = Config.URL_LEAVE_STAT + staff_id_aa ;
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
            json_string2 = result;

            try {
                jsonObject2 = new JSONObject(json_string2);
                jsonArray2 = jsonObject2.getJSONArray("server_response");

                int count = 0;
                //String apply, start, end, stat;
                while (count < jsonArray2.length()) {
                    JSONObject JO2 = jsonArray2.getJSONObject(count);
                    leave_bal = JO2.getString("bal");



                    count++;
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void approveLeave(View view){

        if (hradmin.equals("1")){

        nameS = tv1.getText().toString();
        mrS = spun;
        sidS = staff_id;
        date_appS = date_app;
        leave_idS = leave_id;
        //admin_remS =
        //id_manS = id_man;
        //name_manS = name_man;
            int bal_after_int = Integer.parseInt(leave_bal) - Integer.parseInt(stat);
           String bal_after = Integer.toString(bal_after_int);

        String method = "applyhr";
        BgLeaveApprove backgroundTask = new BgLeaveApprove(this);
        //backgroundTask.execute(method, applyS, startS, endS, statS, reasonS, typeS, addS,noS,sidS,nameS);
        backgroundTask.execute(method, name_man, sidS, mrS, date_appS, leave_idS,dept,leave_bal,stat,bal_after);

        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
        else{

        nameS = tv1.getText().toString();
        mrS = spun;
        sidS = staff_id;
        date_appS = date_app;
        leave_idS = leave_id;
        //admin_remS =
        //id_manS = id_man;
        //name_manS = name_man;


        String method = "apply";
        BgLeaveApprove backgroundTask = new BgLeaveApprove(this);
        //backgroundTask.execute(method, applyS, startS, endS, statS, reasonS, typeS, addS,noS,sidS,nameS);
        backgroundTask.execute(method, name_man, sidS, mrS, date_appS, leave_idS,dept);

        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
}


    }
}

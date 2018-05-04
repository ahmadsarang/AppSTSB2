package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
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
import java.util.ArrayList;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ApprovalActivity extends Activity {

    String json_string,position,dept,name,status,name_man,id_man,staff_id;
    String strJSONValue,leave_id,strJSONValue2;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ApproveAdapter approveAdapter;
    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> list3 = new ArrayList<String>();
    Approve approve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        listView = (ListView) findViewById(R.id.lvApprovalList);

        strJSONValue = getIntent().getExtras().getString("json_data");
        strJSONValue2 = getIntent().getExtras().getString("json_data_2");

        //staff_id = intent.getStringExtra(Config.STAFF_ID);


        try {
            jsonObject = new JSONObject(strJSONValue);
            jsonArray = jsonObject.getJSONArray("result");

            int count = 0;
            //String name,id,ic, confirm, gender, desig;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                position = JO.getString("position");
                dept = JO.getString("dept");
                name_man = JO.getString("staff_name");
                id_man = JO.getString("staff_id");
                count++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
            if((position.equals("1")) || (position.equals("2"))){
                if (dept.equals("3")){
                    approveAdapter = new ApproveAdapter(this, R.layout.row_layout_approve);
                    listView.setAdapter(approveAdapter);

                    try {
                        jsonObject = new JSONObject(strJSONValue2);
                        jsonArray = jsonObject.getJSONArray("server_response");

                        int count = 0;
                        //String name,id,ic, confirm, gender, desig;
                        while (count < jsonArray.length()) {
                            JSONObject JO = jsonArray.getJSONObject(count);
                            name = JO.getString("name");
                            status = JO.getString("stat");
                            leave_id = JO.getString("leave_id");
                            staff_id = JO.getString("staff_id");

                            approve = new Approve(name, status);
                            approveAdapter.add(approve);
                            list.add(leave_id);
                            list2.add(name);
                            list3.add(staff_id);

                            count++;
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                            //int item = status.getApply(position);
                                                            String leave = list.get(position);
                                                            String name = list2.get(position);
                                                            Intent intent = new Intent(ApprovalActivity.this, ApprovalDetailsActivity.class);
                                                            intent.putExtra("leave_id", leave);
                                                            intent.putExtra("dept", dept);
                                                            intent.putExtra("name", name);
                                                            intent.putExtra("staff_id", staff_id);
                                                            intent.putExtra("name_man", name_man);
                                                            intent.putExtra("id_man", id_man);
                                                            intent.putExtra("hradmin", "1");
                                                            startActivity(intent);

                                                        }
                                                    }

                    );

                }
                else {
                    approveAdapter = new ApproveAdapter(this, R.layout.row_layout_approve);
                    listView.setAdapter(approveAdapter);

                    try {
                        jsonObject = new JSONObject(strJSONValue2);
                        jsonArray = jsonObject.getJSONArray("server_response");

                        int count = 0;
                        //String name,id,ic, confirm, gender, desig;
                        while (count < jsonArray.length()) {
                            JSONObject JO = jsonArray.getJSONObject(count);
                            name = JO.getString("name");
                            status = JO.getString("stat");
                            leave_id = JO.getString("leave_id");
                            approve = new Approve(name, status);
                            approveAdapter.add(approve);
                            list.add(leave_id);
                            list2.add(name);

                            count++;
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                            //int item = status.getApply(position);
                                                            String leave = list.get(position);
                                                            String name = list2.get(position);
                                                            Intent intent = new Intent(ApprovalActivity.this, ApprovalDetailsActivity.class);
                                                            intent.putExtra("leave_id", leave);
                                                            intent.putExtra("dept", dept);
                                                            intent.putExtra("name", name);
                                                            intent.putExtra("name_man", name_man);
                                                            intent.putExtra("id_man", id_man);
                                                            intent.putExtra("hradmin", "0");
                                                            startActivity(intent);

                                                        }
                                                    }

                    );
                }
        }



            else {
                AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
                myAlert.setMessage("You are not authorized to view this section").create();
                myAlert.show();
                Intent intent = new Intent(ApprovalActivity.this, MainActivity.class);
                startActivity(intent);


            }




    }



    public void goToApprovalDetailsActivity(View view){
        Intent intent = new Intent(this, ApprovalDetailsActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}

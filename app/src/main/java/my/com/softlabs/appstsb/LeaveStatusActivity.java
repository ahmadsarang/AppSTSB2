package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.id.list;


public class LeaveStatusActivity extends Activity {
    String json_string,leave_id,apply,ent,bal,address,contact;
    JSONObject jsonObject;
    JSONArray jsonArray;
    StatusAdapter statusAdapter;
    ListView listView;
    TextView textView;
    Status status;
    ArrayList<String> list = new ArrayList<String>();
    private TextView tvBal,tvEnt;

    TextView tls, tab, tae, tth, tdt, tpen;
    Typeface tf1, tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_status);
        //setContentView(R.layout.dis);
        listView = (ListView) findViewById(R.id.lvLeaveStat);

        tls = (TextView) findViewById(R.id.tvTitleStat);
        tab = (TextView) findViewById(R.id.tvTitleAnnBal);
        tae = (TextView) findViewById(R.id.tvTitleAnnEnt);
        tth = (TextView) findViewById(R.id.tvTitleHis);
        tdt = (TextView) findViewById(R.id.tvTitleDateTime);
        tpen = (TextView) findViewById(R.id.tvTitlePend);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        //listView.set;

        tls.setTypeface(tf2);
        tab.setTypeface(tf2);
        tae.setTypeface(tf2);
        tth.setTypeface(tf2);
        tdt.setTypeface(tf2);
        tpen.setTypeface(tf2);


        statusAdapter = new StatusAdapter(this, R.layout.row_layout);
        listView.setAdapter(statusAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        address = getIntent().getExtras().getString("address");
        contact = getIntent().getExtras().getString("contact");
        try {
            jsonObject = new JSONObject(json_string);
            //jsonArray = new JSONArray(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            //jsonArray  = new JSONObject(json_string).getJSONArray("server_response");


            int count = 0;
            String apply,stat;

            //int finalresultlengt=jsonArray.length();

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                apply = JO.getString("apply");
                //start = JO.getString("start");
                //end = JO.getString("end");
                stat = JO.getString("stat");
                leave_id = JO.getString("leave_id");
                ent = JO.getString("ent");
                bal = JO.getString("bal");
                status = new Status(apply, stat,ent,bal);
                statusAdapter.add(status);
                list.add(leave_id);
                count++;

            }

            tvBal = (TextView) findViewById(R.id.tvBalAnnual);
            tvBal.setText(bal);
            tvEnt = (TextView) findViewById(R.id.tvEntAnnual);
            tvEnt.setText(ent);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //int item = status.getApply(position);
                String leave = list.get(position);
                Intent intent = new Intent(LeaveStatusActivity.this, LeaveStatusDetailActivity.class);
                intent.putExtra("leave_id",leave);
                intent.putExtra("address",address);
                intent.putExtra("contact",contact);
                startActivity(intent);

                                            }
                                        }

        );

    }




}

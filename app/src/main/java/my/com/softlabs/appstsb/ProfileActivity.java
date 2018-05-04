package my.com.softlabs.appstsb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends Activity implements View.OnClickListener {

    String strJSONValue;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String name_value,id_value,ic_value,confirm_value,gender_value,desig_value,add_value,cont_value;
    ImageView imageView;

    private TextView tvStaffID;
    private TextView tvStaffName;
    private TextView tvIC;
    private TextView tvConfirm;
    private TextView tvGender;
    private TextView tvDesig;
    private TextView tvAddress;
    private TextView tvContact;

    private Button btnShowDetail;

    private String staff_id;
    private String staff_name;

    TextView tidd, tic, tconf, tgend, tadd, tcont;

    Typeface tf1, tf2, tf3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String staff_id = sharedPreferences.getString(Config.STAFFID_SHARED_PREF,"Not Available");
        setTitle(staff_id);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.b);
        //imageView.setImageURI();

        //imageView = (ImageView)findViewById(R.id.imageView);
        //imageView.setImageBitmap(BitmapFactory.decodeFile("pic/b.png"));

        tvStaffName = (TextView) findViewById(R.id.tvStaffName);
        tvDesig = (TextView) findViewById(R.id.tvDesig);
        tvStaffID = (TextView) findViewById(R.id.tvStaffID);
        tvIC = (TextView) findViewById(R.id.tvIC);
        tvConfirm = (TextView) findViewById(R.id.tvConfirm);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvContact = (TextView) findViewById(R.id.tvContact);

        tidd = (TextView) findViewById(R.id.tvStaffID_title);
        tic = (TextView) findViewById(R.id.tvIC_title);
        tconf = (TextView) findViewById(R.id.tvConfirm_title);
        tgend = (TextView) findViewById(R.id.tvGender_title);
        tadd = (TextView) findViewById(R.id.tvAddress_title);
        tcont = (TextView) findViewById(R.id.tvContact_title);


        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        tidd.setTypeface(tf3);
        tic.setTypeface(tf3);
        tconf.setTypeface(tf3);
        tgend.setTypeface(tf3);
        tadd.setTypeface(tf3);
        tcont.setTypeface(tf3);

        tvStaffName.setTypeface(tf3);
        tvDesig.setTypeface(tf3);
        tvStaffID.setTypeface(tf2);
        tvIC.setTypeface(tf2);
        tvConfirm.setTypeface(tf2);
        tvGender.setTypeface(tf2);
        tvAddress.setTypeface(tf2);
        tvContact.setTypeface(tf2);


        //Intent intent = getIntent();
        //strJSONValue=null;
        strJSONValue = getIntent().getExtras().getString("json_data");

        //staff_id = intent.getStringExtra(Config.STAFF_ID);


        try {
            jsonObject = new JSONObject(strJSONValue);
            jsonArray = jsonObject.getJSONArray("result");

            int count = 0;
            String name,id,ic, confirm, gender, desig,address,contact;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("staff_name");
                id = JO.getString("staff_id");
                ic = JO.getString("ic_no");
                confirm = JO.getString("confirmation_year");
                gender = JO.getString("gender");
                desig = JO.getString("designation");
                address = JO.getString("address");
                contact = JO.getString("contact");

                name_value = name;
                id_value = id;
                ic_value = ic;
                confirm_value = confirm;
                gender_value = gender;
                desig_value = desig;
                add_value = address;
               cont_value = contact;

                //Contacts contacts = new Contacts(name,ic,design);
                //contactAdapter.add(contacts);

                count++;
            }
            tvStaffID = (TextView) findViewById(R.id.tvStaffID);
            tvStaffID.setText(id_value);
            tvStaffName = (TextView) findViewById(R.id.tvStaffName);
            tvStaffName.setText(name_value);
            tvIC = (TextView) findViewById(R.id.tvIC);
            tvIC.setText(ic_value);
            tvConfirm = (TextView) findViewById(R.id.tvConfirm);
            tvConfirm.setText(confirm_value);
            tvGender = (TextView) findViewById(R.id.tvGender);
            tvGender.setText(gender_value);
            tvDesig = (TextView) findViewById(R.id.tvDesig);
            tvDesig.setText(desig_value);
            tvAddress = (TextView) findViewById(R.id.tvAddress);
            tvAddress.setText(add_value);
            tvContact = (TextView) findViewById(R.id.tvContact);
            tvContact.setText(cont_value);






        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void getDetail(){
        class getDetail extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfileActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_STAFF,staff_id);
                return s;
            }
        }
        getDetail gd = new getDetail();
        gd.execute();
    }

    private void showDetail(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_STAFF_NAME);
            String id = c.getString(Config.TAG_STAFF_ID);

            tvStaffID.setText(id);
            tvStaffName.setText(name);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v){
        if(v == btnShowDetail){
            getDetail();

        }

    }



}

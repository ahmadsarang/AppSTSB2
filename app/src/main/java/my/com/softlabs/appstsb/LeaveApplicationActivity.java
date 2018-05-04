package my.com.softlabs.appstsb;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static my.com.softlabs.appstsb.R.id.btnFromDate;
import static my.com.softlabs.appstsb.R.id.btnToDate;
import static my.com.softlabs.appstsb.R.id.etAddress;
import static my.com.softlabs.appstsb.R.id.etNumber;
import static my.com.softlabs.appstsb.R.id.etReasons;
import static my.com.softlabs.appstsb.R.id.rbYesHalf;
import static my.com.softlabs.appstsb.R.id.tvFromDate;
import static my.com.softlabs.appstsb.R.id.tvLeaveBal;
import static my.com.softlabs.appstsb.R.id.tvToDate;
import static my.com.softlabs.appstsb.R.id.tvTodayDate;
import static my.com.softlabs.appstsb.R.id.tvTotalDays;



public class LeaveApplicationActivity extends Activity implements View.OnClickListener {

    DateFormat formatDate = DateFormat.getDateInstance();
    Calendar dateTime  = Calendar.getInstance();
    SimpleDateFormat formatToday, formatFrom, formatTo;
    JSONObject jsonObject;
    JSONArray jsonArray;

    private Button btnFrom, btnTo;
    private Button btnApplyLeave;
    private TextView tvFrom, tvTo, tvToday, tvTotDay,tvLeave_Bal ;
    private EditText reason,address, number;
    String edit_id,leave_id,address2,contact,json_string,bal;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;

    Typeface tf1, tf2;

    Spinner spin;
    ArrayAdapter<CharSequence> adapter;

    RadioGroup rg;
    RadioButton rb;

    //private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

        t1 = (TextView) findViewById(R.id.title_leave_app);
        t2 = (TextView) findViewById(R.id.ttvbal);
        t3 = (TextView) findViewById(R.id.ttvTodayDate);
        t4 = (TextView) findViewById(R.id.ttvFromDate);
        t5 = (TextView) findViewById(R.id.ttvToDate);
        t6 = (TextView) findViewById(R.id.ttvTotalDays);
        t7 = (TextView) findViewById(R.id.trgHalf);
        t8 = (TextView) findViewById(R.id.tSpinTOL);
        t9 = (TextView) findViewById(R.id.tetReasons);
        t10 = (TextView) findViewById(R.id.tetAddress);
        t11 = (TextView) findViewById(R.id.tetNumber);

        btnApplyLeave = (Button) findViewById(R.id.btnApplyLeaveApp);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        t1.setTypeface(tf2);
        t2.setTypeface(tf2);
        t3.setTypeface(tf2);
        t4.setTypeface(tf2);
        t5.setTypeface(tf2);
        t6.setTypeface(tf2);
        t7.setTypeface(tf2);
        t8.setTypeface(tf2);
        t9.setTypeface(tf2);
        t10.setTypeface(tf2);
        t11.setTypeface(tf2);

        btnApplyLeave.setTypeface(tf2);

        if (getIntent() != null){
            edit_id = getIntent().getExtras().getString("edit_id");
            leave_id = getIntent().getExtras().getString("leave_id");
            address2 = getIntent().getExtras().getString("address");
            contact = getIntent().getExtras().getString("contact");
            json_string = getIntent().getExtras().getString("json_data");

        }
        address2 = getIntent().getExtras().getString("address");
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

                bal = JO.getString("bal");
                count++;

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        formatToday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatFrom = new SimpleDateFormat("yyyy-MM-dd");
        formatTo = new SimpleDateFormat("yyyy-MM-dd");

        rg = (RadioGroup) findViewById(R.id.rgHalf);

        btnFrom = (Button)findViewById(btnFromDate);
        btnTo = (Button)findViewById(btnToDate);

        tvFrom = (TextView)findViewById(tvFromDate);
        tvTo = (TextView)findViewById(tvToDate);
        tvTotDay = (TextView)findViewById(tvTotalDays);
        tvToday = (TextView)findViewById(tvTodayDate);
        tvLeave_Bal = (TextView)findViewById(tvLeaveBal);

        tvFrom.setTypeface(tf1);
        tvTo.setTypeface(tf1);
        tvTotDay.setTypeface(tf1);
        tvToday.setTypeface(tf1);
        tvLeave_Bal.setTypeface(tf1);
        tvLeave_Bal.setText(bal);

        tvToday.setText(formatToday.format(dateTime.getTime()));

        reason = (EditText)findViewById(etReasons);
        address = (EditText)findViewById(etAddress);
        address.setText(address2);
        number = (EditText)findViewById(etNumber);
        number.setText(contact);

        reason.setTypeface(tf1);
        address.setTypeface(tf1);
        number.setTypeface(tf1);

        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromDate();
            }
        });

        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToDate();
            }
        });

        /*btnApplyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });
*/
        spin = (Spinner) findViewById(R.id.spinnerTOL);
        adapter = ArrayAdapter.createFromResource(this, R.array.type_of_leave, R.layout.spinner_item_application);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG) .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





    private void setFromDate(){
        new DatePickerDialog(this, d1, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void setToDate(){
        new DatePickerDialog(this, d2, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //tvFrom.setText(formatDate.format(dateTime.getTime()));
            tvFrom.setText(formatFrom.format(dateTime.getTime()));
            //TextView frDate = (TextView)findViewById(R.id.tvFromDate);
        }
    };

    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //tvTo.setText(formatDate.format(dateTime.getTime()));
            tvTo.setText(formatTo.format(dateTime.getTime()));
            //TextView tooDate = (TextView)findViewById(R.id.tvToDate);
            //String haha = tvTo.toString();
            try {
                Calculate();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    };

    public void rbclick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String kahak = rb.getText().toString();
        if (rb.getText().toString().equals("Yes")){
            tvTotDay.setText("0.5");
            String test = tvFrom.getText().toString();
            tvTo.setText(test);
        }

        //Toast.makeText(getBaseContext(),rb.getText(), Toast.LENGTH_LONG).show();

    }

    public void applyLeave(View v) {

        TextView leaveBal = (TextView)findViewById(R.id.tvLeaveBal);
        TextView todayDate = (TextView)findViewById(R.id.tvTodayDate);
        TextView fromDate = (TextView)findViewById(R.id.tvFromDate);
        TextView toDate = (TextView)findViewById(R.id.tvToDate);
        TextView totDay = (TextView)findViewById(R.id.tvTotalDays);
        TextView reas = (TextView)findViewById(R.id.etReasons);
        TextView add = (TextView)findViewById(R.id.etAddress);
        TextView num = (TextView)findViewById(R.id.etNumber);

        Intent i = new Intent(LeaveApplicationActivity.this, LeaveAppConfirm.class);
        i.putExtra("leaveBal", leaveBal.getText().toString());
        i.putExtra("todayDate", todayDate.getText().toString());
        i.putExtra("fromDate", fromDate.getText().toString());
        i.putExtra("toDate", toDate.getText().toString());
        i.putExtra("totDay", totDay.getText().toString());
        //i.putExtra("half", rb.getText().toString());
        i.putExtra("remark", spin.getSelectedItem().toString());
        i.putExtra("reas", reas.getText().toString());
        i.putExtra("add", add.getText().toString());
        i.putExtra("num", num.getText().toString());
        i.putExtra("edit_id", edit_id);
        i.putExtra("leave_id", leave_id);


        startActivity(i);
    }

    @Override
    public void onClick(View v){

    }

    private void Calculate() throws ParseException {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Date date1 = formatFrom.parse(formatFrom.format(dateTime.getTime()));
        //Date date2 = formatTo.parse(formatTo.format(dateTime.getTime()));

        Date date1 = formatFrom.parse(tvFrom.getText().toString());
        Date date2 = formatTo.parse(tvTo.getText().toString());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))&&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
                cal1.add(Calendar.DATE,1);
            }else {
                cal1.add(Calendar.DATE,1);
            }
        }
        numberOfDays=numberOfDays+1;
        String num = Integer.toString(numberOfDays);
        tvTotDay.setText(num);

        //return(numberOfDays);
    }
}

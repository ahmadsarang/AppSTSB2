package my.com.softlabs.appstsb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import static my.com.softlabs.appstsb.R.id.tvStaffID;

public class LeaveAppConfirm extends Activity {

    private TextView staff, today,from, to, tot, rem, rea,add, num;
    String staff_idS, todayS,fromS, toS, totS, remS, reaS, edit_id,leave_id,addS,numS;

    TextView tlc, tsic, tdoa, tlf, tlt, tld, ttol, tr,ta, tc;
    Button bconf;
    Typeface tf1, tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_app_confirm);

        staff = (TextView) findViewById(tvStaffID);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String staff_id = sharedPreferences.getString(Config.STAFFID_SHARED_PREF,"Not Available");

        staff.setText(staff_id);

         //staff_id = (TextView) findViewById(tvStaffID);
         today = (TextView) findViewById(R.id.todayDate);
         from = (TextView) findViewById(R.id.fromLeave);
         to = (TextView) findViewById(R.id.toLeave);
         tot = (TextView) findViewById(R.id.dayTot);
         rem = (TextView) findViewById(R.id.remarksConf);
         rea = (TextView) findViewById(R.id.reasonConf);
        add = (TextView) findViewById(R.id.tvAddress);
        num = (TextView) findViewById(R.id.tvContact) ;

        tlc = (TextView) findViewById(R.id.titleLeaveConf);
        tsic = (TextView) findViewById(R.id.ttvStaffID);
        tdoa = (TextView) findViewById(R.id.ttodayDate);
        tlf = (TextView) findViewById(R.id.tfromLeave);
        tlt = (TextView) findViewById(R.id.ttoLeave);
        tld = (TextView) findViewById(R.id.tdayTot);
        ttol = (TextView) findViewById(R.id.tremarksConf);
        tr = (TextView) findViewById(R.id.treasonConf);
        ta = (TextView) findViewById(R.id.ttvAddress);
        tc = (TextView) findViewById(R.id.ttvContact);

        bconf = (Button) findViewById(R.id.button2);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        tlc.setTypeface(tf2);
        tsic.setTypeface(tf2);
        tdoa.setTypeface(tf2);
        tlf.setTypeface(tf2);
        tlt.setTypeface(tf2);
        tld.setTypeface(tf2);
        ttol.setTypeface(tf2);
        tr.setTypeface(tf2);
        ta.setTypeface(tf2);
        tc.setTypeface(tf2);

        staff.setTypeface(tf1);
        today.setTypeface(tf1);
        from.setTypeface(tf1);
        to.setTypeface(tf1);
        tot.setTypeface(tf1);
        rem.setTypeface(tf1);
        rea.setTypeface(tf1);
        add.setTypeface(tf1);
        num.setTypeface(tf1);

        bconf.setTypeface(tf2);


        Bundle bun;

        bun = getIntent().getExtras();

        today.setText(bun.getString("todayDate"));
        from.setText(bun.getString("fromDate"));
        to.setText(bun.getString("toDate"));
        tot.setText(bun.getString("totDay"));
        rem.setText(bun.getString("remark"));
        rea.setText(bun.getString("reas"));
        add.setText(bun.getString("add"));
        num.setText(bun.getString("num"));
        edit_id = bun.getString("edit_id");
        leave_id = bun.getString("leave_id");

        //staff_id = (TextView) findViewById(tvStaffID);
        //staff_id.setText(staff_idS);

    }

    public void applyLeave(View view)
    {
        staff_idS = staff.getText().toString();
        todayS = today.getText().toString();
        fromS = from.getText().toString();
        toS = to.getText().toString();
        totS = tot.getText().toString();
        remS = rem.getText().toString();
        reaS = rea.getText().toString();
        addS = add.getText().toString();
        numS = num.getText().toString();

        String method = "apply";
        BgLeaveAppConf backgroundTask = new BgLeaveAppConf(this);
        backgroundTask.execute(method, staff_idS, todayS, fromS, toS, totS, remS, reaS,edit_id,leave_id,addS,numS);

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

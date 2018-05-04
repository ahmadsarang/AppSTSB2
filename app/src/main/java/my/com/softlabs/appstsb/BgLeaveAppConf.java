package my.com.softlabs.appstsb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class BgLeaveAppConf extends AsyncTask <String, Void, String>{

    String applyLeave_url;

    AlertDialog alertDialog;
    Context ctx;
    BgLeaveAppConf(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        //applyLeave_url = Config.URL_LEAVE_APP + tvStaffID;
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        //String applyLeave_url = "http://192.168.1.68:8080/appSTSB/leaveApplication.php";
        applyLeave_url = Config.URL_LEAVE_APP;

        String method = params[0];
        if (method.equals("apply")) {

            String staff_id = params[1];
            String todayDate = params[2];
            String fromDate = params[3];
            String toDate = params[4];
            String totDay = params[5];
            String remark  = params[6];
            String reason = params[7];
            String edit_id = params[8];
            String leave_id = params[9];
            String add = params[10];
            String num = params[11];
            try {
                URL url = new URL(applyLeave_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        URLEncoder.encode("staff_id", "UTF-8") + "=" + URLEncoder.encode(staff_id, "UTF-8") + "&" +
                        URLEncoder.encode("apply_date", "UTF-8") + "=" + URLEncoder.encode(todayDate, "UTF-8") + "&" +
                        URLEncoder.encode("leave_start_date", "UTF-8") + "=" + URLEncoder.encode(fromDate, "UTF-8") + "&" +
                        URLEncoder.encode("leave_end_date", "UTF-8") + "=" + URLEncoder.encode(toDate, "UTF-8") + "&" +
                        URLEncoder.encode("total_day_leave", "UTF-8") + "=" + URLEncoder.encode(totDay, "UTF-8") + "&" +
                        URLEncoder.encode("remarks", "UTF-8") + "=" + URLEncoder.encode(remark, "UTF-8") + "&" +
                        URLEncoder.encode("reason", "UTF-8") + "=" + URLEncoder.encode(reason, "UTF-8") + "&" +
                        URLEncoder.encode("edit_id", "UTF-8") + "=" + URLEncoder.encode(edit_id, "UTF-8") + "&" +
                                URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(add, "UTF-8") + "&" +
                                URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(num, "UTF-8") + "&" +
                                URLEncoder.encode("leave_id", "UTF-8") + "=" + URLEncoder.encode(leave_id, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Your Leave Application Successful!";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Your Leave Application Successful!"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}

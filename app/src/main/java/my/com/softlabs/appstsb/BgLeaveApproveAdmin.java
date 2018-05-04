package my.com.softlabs.appstsb;

import android.app.AlertDialog;
import android.content.Context;
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

/**
 * Created by Muhammad Akmal on 14/4/2017.
 */

public class BgLeaveApproveAdmin extends AsyncTask<String, Void, String> {

    String approveLeave_url;

    AlertDialog alertDialog;
    Context ctx;
    BgLeaveApproveAdmin(Context ctx)
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
        approveLeave_url = Config.URL_LEAVE_APPROVE;

        String method = params[0];
        if (method.equals("apply")) {

            String name = params[1];
            String staff_id = params[2];
            String date_app = params[4];
            String manRem = params[3];
            String leave_id = params[5];
            String dept = params[5];
            try {
                URL url = new URL(approveLeave_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        URLEncoder.encode("man_name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                                URLEncoder.encode("man_id", "UTF-8") + "=" + URLEncoder.encode(staff_id, "UTF-8") + "&" +
                                URLEncoder.encode("date_app", "UTF-8") + "=" + URLEncoder.encode(date_app, "UTF-8") + "&" +
                                URLEncoder.encode("manager_remark", "UTF-8") + "=" + URLEncoder.encode(manRem, "UTF-8") + "&" +
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


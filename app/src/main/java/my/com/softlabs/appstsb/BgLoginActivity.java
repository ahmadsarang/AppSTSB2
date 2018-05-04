package my.com.softlabs.appstsb;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Muhammad Akmal on 30/3/2017.
 */



public class BgLoginActivity extends AsyncTask<String,Void,String> {
    String json_url;
    String JSON_STRING;
    String strJSONValue;

    AlertDialog alertDialog;
    Response.Listener<String> ctx;
    BgLoginActivity(Response.Listener<String> ctx)
    {
        this.ctx =ctx;
    }


    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder((Context) ctx).create();
        alertDialog.setTitle("Login Information....");

    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        json_url = Config.URL_LOGIN_CONT + method;
        try{
            URL url = new URL (json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while((JSON_STRING = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(JSON_STRING+"\n");
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
        strJSONValue = result;

    }

}
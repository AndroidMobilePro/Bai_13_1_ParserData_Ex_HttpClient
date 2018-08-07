package com.proteam.bai_13_1_parserdata_ex_httpclient.task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by CuongNV.
 */

public class HttpClientTask extends AsyncTask<String, String, String> {

    private TextView textView;

    public HttpClientTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String textUrl = params[0];

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(textUrl);
        BufferedReader br = null;
        HttpResponse response = null;

        try {
            response = httpclient.execute(request);

            br = new BufferedReader((new InputStreamReader(response.getEntity().getContent())));

            StringBuilder sb = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            this.textView.setText(result);
        } else {
            Log.e("MyMessage", "Failed to fetch data!");
        }
    }
}

package com.proteam.bai_13_1_parserdata_ex_httpclient;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.proteam.bai_13_1_parserdata_ex_httpclient.task.HttpClientTask;
import com.proteam.bai_13_1_parserdata_ex_httpclient.task.HttpURLConnectionTask;

import static com.proteam.bai_13_1_parserdata_ex_httpclient.R.id.contentHTML;

public class MainActivity extends AppCompatActivity {

    public static final String URL_CONNECT = "http://webservice.recruit.co.jp/hotpepper/genre/v1/?key=5b71415094f1f910&format=json";

    TextView contentHtml;
    AppCompatButton btnGetHttpURLConnection;
    AppCompatButton btnGetHttpClient;
    AppCompatButton mBtnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentHtml = (TextView) findViewById(contentHTML);
        btnGetHttpURLConnection = (AppCompatButton) findViewById(R.id.getHttpURLConnection);
        btnGetHttpClient = (AppCompatButton) findViewById(R.id.getHttpClient);
        mBtnClear = (AppCompatButton) findViewById(R.id.btnClearText);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info.isConnected()) {
            Log.v("cuongnv", "Connect");
        }

        btnGetHttpURLConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttpURLConnection();
            }
        });

        btnGetHttpClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttpClient();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentHtml.setText("");
            }
        });

    }

    private void getHttpURLConnection() {
        String jsonUrl = URL_CONNECT;
        HttpURLConnectionTask task = new HttpURLConnectionTask(contentHtml);
        task.execute(jsonUrl);
    }

    private void getHttpClient() {
        String jsonUrl = URL_CONNECT;
        HttpClientTask task = new HttpClientTask(contentHtml);
        task.execute(jsonUrl);
    }
}

package com.example.formattimezone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView tvOriTime,tvForTime;
    Button btnFormat;
    String respTime;
    String TAG = getPackageName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RFC 3339  Date & time receive from server
        respTime = "2016-10-03T11:02:45.282Z";

        tvOriTime = (TextView)findViewById(R.id.oriTime);
        tvForTime = (TextView) findViewById(R.id.formatTime);
        btnFormat = (Button) findViewById(R.id.btnFormat);
        tvOriTime.setText(respTime);
        btnFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formatTime(respTime);
            }
        });

    }

    // format the Date&time to specific TimeZone
    public void formatTime(String respTime){
        String DATE_FORMAT = "yyyy-M-dd'T'hh:mm:ss.SSS'Z'";
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date = df.parse(respTime);
            df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            df.setTimeZone(TimeZone.getDefault());
            Log.i(TAG,"Local time: "+df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

package com.example.alarmapp;
import androidx.appcompat.app.AppCompatActivity;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
        import java.util.Date;
public class Alarm extends AppCompatActivity {
    TimePicker timePicker;
    TextView textView;
    Button btn;
    int mHour,mMin;
    Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        textView = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.button);
        logoutbtn = (Button)findViewById(R.id.button4);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMin = minute;
                textView.setText("");
                textView.setText(textView.getText().toString()+" "+mHour+":"+mMin);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alarm.this,Login.class);
                startActivity(intent);
                Toast.makeText(Alarm.this,"You have logged out...",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void setTimer(View v){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(date);
        cal_alarm.setTime(date);
        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMin);
        cal_alarm.set(Calendar.SECOND,0);
        if(cal_alarm.before(cal_now))
        {
            cal_alarm.add(Calendar.DATE,1);
        }
        Intent i =new Intent(Alarm.this, MyBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarm.this,24,i,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);
        Toast.makeText(this,"Alarm has been set...",Toast.LENGTH_SHORT).show();
    }
}

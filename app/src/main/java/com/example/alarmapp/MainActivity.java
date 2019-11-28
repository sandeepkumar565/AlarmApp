package com.example.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText e1,e2,e3;
    Button b1,b2;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pass);
        e3 = (EditText) findViewById(R.id.cpass);
        b1 = (Button) findViewById(R.id.register);
        b2 = (Button) findViewById(R.id.button2);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(MainActivity.this,R.id.email,android.util.Patterns.EMAIL_ADDRESS,R.string.email);
        awesomeValidation.addValidation(MainActivity.this,R.id.pass,regexPassword,R.string.pass);
        awesomeValidation.addValidation(MainActivity.this,R.id.cpass,R.id.pass,R.string.cpass);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    String s3 = e3.getText().toString();
                    if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fields can't be empty",Toast.LENGTH_LONG).show();
                    } else{
                        if(s2.equals(s3)){
                            Boolean chkmail = db.chkmail(s1);
                            if(chkmail==true){
                                Boolean insert = db.insert(s1,s2);
                                if(insert==true){
                                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(MainActivity.this,Login.class);
                                    startActivity(i);
                                }
                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }


}

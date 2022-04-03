package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.*;
import java.io.*;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {
    String str = "test";
    String str2 = "";
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button a = (Button) findViewById(R.id.button);
        Button b = (Button) findViewById(R.id.button2);
        EditText c = (EditText) findViewById(R.id.textsnd);
        TextView d = (TextView) findViewById(R.id.tv3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str=c.getText().toString();
                d.setText(str);
                try {
                    send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    a.setVisibility(View.INVISIBLE);
                    b.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);
                    try {
                        connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        });

    }

    public void connect() throws Exception {


        Toast.makeText(getApplicationContext(), "CONNECTED", Toast.LENGTH_SHORT).show();


    }

    public void send() throws Exception{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Socket s = new Socket("192.168.1.14", 12340);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF(str);
        dout.flush();
        str2 = din.readUTF();
        Toast.makeText(getApplicationContext(), str2, Toast.LENGTH_SHORT).show();
        dout.close();
        s.close();





    }
}



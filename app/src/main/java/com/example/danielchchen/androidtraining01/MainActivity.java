package com.example.danielchchen.androidtraining01;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends ActionBarActivity {

    private TextView texviewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        texviewMessage = (TextView)findViewById(R.id.textVeiwMessage);

        /*Toast.makeText(getBaseContext(),
                "Build.VERSION.SDK_INT: "+
                        String.valueOf(Build.VERSION.SDK_INT)+
                        ", Build.VERSION_CODES.HONEYCOMB: "+
                        String.valueOf(Build.VERSION_CODES.HONEYCOMB),
                Toast.LENGTH_SHORT).show();*/
        Toast.makeText(getBaseContext(),telephonyManager.getDeviceId(),Toast.LENGTH_SHORT).show();
        Process p;
        try {
            // Preform su to get root privledges
            p = Runtime.getRuntime().exec("su");
            //fileCopy("/data/data/jp.naver.line.android/databases/naver_line","/data/naver_line");
        }
        catch (IOException e) {
            Toast.makeText(getBaseContext(),"not root",Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean fileCopy(String oldFilePath,String newFilePath) throws IOException {
        //如果原文件不存在
        if(fileExists(oldFilePath) == false){
            return false;
        }
        //?得原文件流
        FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
        byte[] data = new byte[1024];
        //?出流
        FileOutputStream outputStream =new FileOutputStream(new File(newFilePath));
        //?始?理流
        while (inputStream.read(data) != -1) {
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
        return true;
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getBaseContext(),
                "Leave!",
                Toast.LENGTH_SHORT).show();
        super.onDestroy();  // Always call the superclass

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }

    @Override
    public void onPause() {
        Toast.makeText(getBaseContext(),
                "Pause!",
                Toast.LENGTH_SHORT).show();
        super.onPause();  // Always call the superclass method first
    }

    @Override
    public void onResume() {
        Toast.makeText(getBaseContext(),
                "Come Back!",
                Toast.LENGTH_SHORT).show();
        super.onResume();  // Always call the superclass method first
    }
}

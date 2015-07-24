package com.dygame.myrestartapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity
{
    protected static SecondActivity _instance = null;
    protected static Activity instance = null ;
    //
    public static SecondActivity getInstance()
    {
        if(_instance != null)
        {
            return _instance;
        }
        synchronized (SecondActivity.class)
        {
            if(_instance == null)
            {
                _instance = new SecondActivity();
            }
        }
        return _instance;
    }
    public static void finishInstance()
    {
        if(instance != null) instance.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        instance = this ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

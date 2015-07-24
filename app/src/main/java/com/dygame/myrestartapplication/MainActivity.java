package com.dygame.myrestartapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    protected static String TAG ;
    protected Button testButton1 ;
    protected Button testButton2 ;
    protected Button testButton3 ;
    protected Button testButton4 ;
    protected Button testButton5 ;
    protected Button testButton6 ;
    protected int iSelected = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Uncaught Exception Handler(Crash Exception)
        MyCrashHandler pCrashHandler = MyCrashHandler.getInstance();
        pCrashHandler.init(getApplicationContext());
        TAG = pCrashHandler.getTag() ;
        //find resource
        testButton1 = (Button)findViewById(R.id.button) ;
        testButton2 = (Button)findViewById(R.id.button2) ;
        testButton3 = (Button)findViewById(R.id.button3) ;
        testButton4 = (Button)findViewById(R.id.button4) ;
        testButton5 = (Button)findViewById(R.id.button5) ;
        testButton6 = (Button)findViewById(R.id.button6) ;
        //set OnClickListener
        testButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 1 ;
                restartAppliction() ;
            }
        });
        testButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 2 ;
                restartAppliction() ;
            }
        });
        testButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 3 ;
                restartAppliction() ;
            }
        });
        testButton4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 4 ;
                restartAppliction() ;
            }
        });
        testButton5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 5 ;
                restartAppliction() ;
            }
        });
        testButton6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iSelected = 6 ;
                restartAppliction() ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    //
    public void restartAppliction()
    {
        boolean isInstall = isInstalledPackege(MainActivity.this , "com.dygame.myasynctaskprogressbarsample") ;
        if (isInstall == false) cloneApk() ;
        //
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Log.i(TAG, "thread run..");
                //顯示畫面之後, 過 1 秒鐘,
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Log.i(TAG, "thread run after 1 sec..");
                //打開其它的Activity
                if ( iSelected == 1)
                {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent) ;
                }
                else
                {
                    //打開其它的 App ,
                    launcherApk(MainActivity.this, "com.dygame.myasynctaskprogressbarsample");
                }
                //再過 10 秒鐘, 想辦法讓自己的畫面到前面
                try
                {
                    Thread.sleep(10000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Log.i(TAG, "thread run after 10 sec..");
                //Test !!
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        //Log.i(TAG, "test UIthread run..");
                    }
                });
                //
                if ( iSelected == 1)
                {
                    //SecondActivity.getInstance().finish();//沒效果
                    SecondActivity.finishInstance();
                }
                if ( iSelected == 2)
                {
                    //自我了斷@20150723
                    Intent mStartActivity = new Intent(MainActivity.this, MainActivity.class);
                    int mPendingIntentId = 123456;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this , mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    System.exit(0);//殺死這個進程
                }
                if ( iSelected == 3)
                {
/*
                            //沒效果
                            Intent intent = new Intent(mActivity , MainActivity.class);
	            	    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            	    startActivity(intent);

                            //沒效果
                            Intent intent2 = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
*/
/*
	            		//error:Unable to start activity ComponentInfo
	            		Intent intent3 = new Intent("com.dygame.myrestartapplication");
	            		//Note: context.startActivity(it) would NOT work when the context object is same as the activity one wants to bring up.
	            		intent3.setComponent(new ComponentName(getApplicationContext().getPackageName(), MainActivity.class.getName()));
	            		intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            		getApplicationContext().startActivity(intent3);
*/
/*
		            	//error:Unable to start activity ComponentInfo
		            	Intent intent4 = new Intent(getApplicationContext() , MainActivity.class);
	            		intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
	            		getApplicationContext().startActivity(intent4);
*/
/*
		            	//沒效果
		            	Intent intent4Ex = new Intent(mActivity , MainActivity.class);
	            		intent4Ex.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
	            		startActivity(intent4Ex);
*/
	            	//有效
            		Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
            		intent5.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
            		getApplicationContext().startActivity(intent5);
/*
		            	//沒效果
	            		Intent intent5Ex = new Intent(mActivity , MainActivity.class);
	            		intent5Ex.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
	            		startActivity(intent5Ex);
*/
                }
                if ( iSelected == 4)
                {
                    //有效
                    Intent intent6 = new Intent(getApplicationContext() , MainActivity.class);
                    intent6.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent6);
                }
                if ( iSelected == 5)
                {
                    //有效
                    Intent intent6Ex = new Intent(getApplicationContext() , MainActivity.class);
                    intent6Ex.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent6Ex);
                    /*
		            	//沒效果//error:Unable to start activity ComponentInfo
	            		Intent intent6Ex2 = new Intent(getApplicationContext() , MainActivity.class);
	            		intent6Ex2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            		getApplicationContext().startActivity(intent6Ex2);
*/
                }
                if ( iSelected == 6)
                {

    	            //有效
	            	Intent intent6Ex3 = new Intent(getApplicationContext() , MainActivity.class);
	            	intent6Ex3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	            	getApplicationContext().startActivity(intent6Ex3);
/*
		            	//沒效果
	            		Intent intent7 = new Intent(Intent.ACTION_MAIN);
	            		intent7.addCategory(Intent.CATEGORY_HOME);
	            		startActivity(intent7);
*/
/*
		            	//error:Unable to start activity ComponentInfo
	            		Intent intent8 = new Intent(getApplicationContext(), MainActivity.class);
	            		int mPendingIntentId = 123456;
	            		PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext() , mPendingIntentId, intent8 , PendingIntent.FLAG_CANCEL_CURRENT);
	            		AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	            		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
*/
                }
            }
        }).start();
    }
    //檢查 App 是否存在
    public boolean isInstalledPackege(Context inContext , String sPackage)
    {
        if ( (sPackage == null) || (sPackage.isEmpty()) ) return false ;

        List<PackageInfo> packages = inContext.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packages.size();i++)
        {
            PackageInfo packageInfo = packages.get(i);
            if (packageInfo.packageName.equals(sPackage))
            {
                return true;
            }
        }
        return false;
    }
    //下載與安裝 Apk
    public void cloneApk()
    {
        File file = null;
        Log.i(TAG, "clone Apk : MyAsyncTaskProgressBar.apk ");
        try
        {
            InputStream is = MainActivity.this.getAssets().open("MyAsyncTaskProgressBar.apk");
            //轉移到可讀寫文件目錄下
            file = new File(Environment.getExternalStorageDirectory().getPath() + "/temp.apk");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0)
            {
                fos.write(temp, 0, i);
            }
            is.close();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //安裝
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);//安裝
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        Log.i(TAG, "clone Apk and install Done");
    }
    //開啟指定的APK
    public void launcherApk(Context inContext, String sPackage)
    {
        Intent intent = new Intent();
        PackageManager manager = inContext.getPackageManager();
        intent = manager.getLaunchIntentForPackage(sPackage);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        inContext.startActivity(intent);
    }
}

package com.cs246.nathanael.multithread;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    ProgressBar bar;
    ListView list;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progress);
        list = (ListView) findViewById(R.id.listView);
        filename = "saveFile";
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

    public void create(View view) {
        CreateFile newFile = new CreateFile();
        newFile.execute();

        bar.setProgress(0);
    }

    public void load(View view) {
        LoadFile newFile = new LoadFile();
        newFile.execute();

        bar.setProgress(0);
    }

    public void clear(View view) {
    }

    // will create a file and update the bar
    private class CreateFile extends AsyncTask<Void,Integer,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                FileOutputStream fOut = openFileOutput(filename,Context.MODE_PRIVATE);
                //loop and fill file, post updates
                for (Integer i = 1; i < 11; i++) {
                    String data = i.toString();
                    fOut.write(data.getBytes());

                    //update bar
                    publishProgress(i);
                    Thread.sleep(250);
                }
                fOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate( progress[0]);
            bar.setProgress(progress[0]);
        }
    }

    // will open the file and fill the list view
    private class LoadFile extends AsyncTask<Void,Integer,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate( progress[0]);
            bar.setProgress(progress[0]);
        }
    }
}

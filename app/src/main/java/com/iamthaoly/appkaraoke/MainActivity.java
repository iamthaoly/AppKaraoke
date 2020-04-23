package com.iamthaoly.appkaraoke;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    public static String DATABASE_NAME = "Arirang.sqlite";
    public static SQLiteDatabase database = null;
    public static String TableName = "ArirangSongList";
    String DB_PATH_SUFFIX = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        addControls();
    }

    private void addControls() {

    }

    //copy database
    private  void processCopy()
    {
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            if(!dbFile.exists())
            {
                copyDatabaseFromAsset();
            }
        }
        catch(Exception ex)
        {
            Log.e("ERROR", ex.toString());
        }
    }
    private String getDatabasePath()
    {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
    private void copyDatabaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
            {
                f.mkdir();
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while((length = myInput.read(buffer)) > 0)
                {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }

        }
        catch (Exception ex)
        {
            Log.e("ERROR", ex.toString());
        }
    }
}

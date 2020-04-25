package com.iamthaoly.appkaraoke;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.iamthaoly.adapter.BaiHatAdapter;
import com.iamthaoly.model.BaiHat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    public static String DATABASE_NAME = "Arirang.sqlite";
    public static SQLiteDatabase database = null;
    public static String TableName = "ArirangSongList";
    String DB_PATH_SUFFIX = "/databases/";
    ListView lvAll;
    BaiHatAdapter adapterAll;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        setUpTabHost();
        addControls();
        hienThiToanBo();
    }

    private void setUpTabHost() {
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("All");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Favourite");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
    }

    private void hienThiToanBo() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor c = database.query(TableName, null, null, null, null, null, null);
        adapterAll.clear();
        while(c.moveToNext())
        {
            String ma = c.getString(0);
            String ten = c.getString(1);
            String casy = c.getString(3);
            int thich = c.getInt(5);
            BaiHat baiHat = new BaiHat(ma, ten, casy, thich);
            adapterAll.add(baiHat);

        }
        c.close();
    }

    private void addControls() {
        lvAll = findViewById(R.id.lvAll);
        adapterAll = new BaiHatAdapter(MainActivity.this, R.layout.item);
        lvAll.setAdapter(adapterAll);
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
            if(!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex)
        {
            Log.e("ERROR", ex.toString());
        }
    }
}

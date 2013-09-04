package com.anton.mosaique;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity {
    private MosaiqueView mMosaique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File root = Environment.getExternalStorageDirectory();

        String p = "/DCIM/Camera/Testmosaique.jpg";

        File f = new File(root, p);
        mMosaique = (MosaiqueView) findViewById(R.id.mosaique);
        List<String> list = new ArrayList<String>();
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        list.add(f.getAbsolutePath() );
        mMosaique.init(list);
    }

}

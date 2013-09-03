package com.anton.mosaique;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
private MosaiqueView mMosaique;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMosaique = (MosaiqueView) findViewById(R.id.mosaique);
		List<Integer> list = new ArrayList<Integer>();
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		list.add(R.drawable.ic_launcher);
		mMosaique.init(list);

	}


}

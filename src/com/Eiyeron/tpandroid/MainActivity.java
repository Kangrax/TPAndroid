package com.Eiyeron.tpandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void ajoutContact(View v) {
		Intent intentAjout = new Intent(this, AjoutContact.class);
		startActivity(intentAjout);
	}

	public void accederRepertoire(View v) {
		Intent intentRepertoire = new Intent(this, ListContact.class);
		startActivity(intentRepertoire);
	}

}

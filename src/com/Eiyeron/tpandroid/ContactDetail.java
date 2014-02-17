package com.Eiyeron.tpandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.Eiyeron.tpandroid.SQLHelper.SQLContact;

public class ContactDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_detail);
		populateDetails();
	}

	private void populateDetails() {
		Intent intent = getIntent();
		SQLContact contact = (SQLContact) intent.getSerializableExtra("contact");
		
		TextView firstName = (TextView) findViewById(R.id.contact_detail_first_name);
		TextView lastName = (TextView) findViewById(R.id.contact_detail_last_name);
		
		firstName.setText(contact.getFirst_name());
		lastName.setText(contact.getLast_name());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_detail, menu);
		return true;
	}

}

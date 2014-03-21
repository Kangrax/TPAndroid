package com.Eiyeron.tpandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	private final int ADD_CONTACT_PHOTO = 1;
	private final int ADD_CONTACT = 2;
	private final String TMP_FILE = Environment.getExternalStorageDirectory()
			.getPath();
	private View dialogView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// Problème avec les photos.

	public void ajoutContact(View v) {
		// Inflation du layout du Dialog, puis affichage de
		dialogView = View.inflate(this, R.layout.activity_ajout_contact, null);
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setView(dialogView);
		dialog.show();
	}

	public void addContactPhoto(View v) {
		// On appelle
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, ADD_CONTACT_PHOTO);
	}

	public void accederRepertoire(View v) {
		Intent intentRepertoire = new Intent(this, ListContact.class);
		startActivity(intentRepertoire);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_CONTACT_PHOTO) {
			// Changement de l'image du contact à ajouter
			if (resultCode == RESULT_CANCELED)
				return;

			Uri uri = data.getData();
			String[] filePathColumn = { android.provider.MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(uri, filePathColumn,
					null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			Log.d("Meh", picturePath);
			ImageButton iButton = (ImageButton) dialogView
					.findViewById(R.id.contact_add_photo);

			iButton.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			Log.e("Meh", "NPE");
		}

	}
}

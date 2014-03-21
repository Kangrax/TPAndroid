package com.Eiyeron.tpandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int ADD_CONTACT_PHOTO_FROM_GALLERY = 1;
	private static final int ADD_CONTACT_PHOTO_FROM_CAMERA = 2;
	private static final int ADD_CONTACT = 3;
	private View dialogView;
	private String currentFilePath;

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

	public void addContactPhotoFromGallery(View v) {
		// On appelle
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, ADD_CONTACT_PHOTO_FROM_GALLERY);
	}

	public void addContactPhotoFromCamera(View v) {
		// On appelle
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File imageFile = new File(getFilePath());
		Uri outputFileUri = Uri.fromFile(imageFile);
		i.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		startActivityForResult(i, ADD_CONTACT_PHOTO_FROM_CAMERA);
	}

	private String getFilePath() {
		// TODO Auto-generated method stub
		currentFilePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
				+ "/IMG_"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
				+ ".jpg";
		return currentFilePath;
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

	private int getScaleFactor(ImageButton iButton) {
		// On récupère les dimensions de la vue qui va afficher notre photo
		int targetW = iButton.getWidth()*2;
		int targetH = iButton.getHeight()*2;

		// On récupère les dimensions originales de la photo
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(currentFilePath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// On calcule le ratio a appliquer à la taille de l'image pour qu'elle
		// se rapproche de la taille de la vue, valeur arrondi par le type int.
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		return scaleFactor;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_CONTACT_PHOTO_FROM_GALLERY) {
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
		if (requestCode == ADD_CONTACT_PHOTO_FROM_CAMERA) {
			if (resultCode == RESULT_CANCELED)
				return;
			Toast.makeText(this, "Saved in : " + currentFilePath,
					Toast.LENGTH_SHORT).show();
			ImageButton iButton = (ImageButton) dialogView
					.findViewById(R.id.contact_add_photo);

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = getScaleFactor(iButton);
			try {

				Bitmap imageBitmap = BitmapFactory.decodeFile(currentFilePath,
						options);
				imageBitmap.compress(CompressFormat.JPEG, 100,
						new FileOutputStream(new File(currentFilePath)));
				iButton.setImageBitmap(imageBitmap);
				Log.e("Meh", "NPE");
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

	}
}

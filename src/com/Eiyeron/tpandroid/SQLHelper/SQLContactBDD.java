package com.Eiyeron.tpandroid.SQLHelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class SQLContactBDD {

	
	public static final String COLUMN_NAME_ID = "contact_entry_id";
	public static final String COLUMN_NAME_FIRST_NAME = "contact_first_name";
	public static final String COLUMN_NAME_LAST_NAME = "contact_last_name";
	public static final String COLUMN_NAME_PHONE_NUMBER = "contact_phone_number";
	public static final String COLUMN_NAME_MAIL = "contact_email";
	public static final String COLUMN_NAME_LABEL = "contact_label";
	public static final String COLUMN_NAME_ADDRESS = "contact_address";
	public static final String COLUMN_NAME_PICTURE = "contact_picture";
	
	public static final String TABLE_NAME = "contacts";
	
	private SQLDatabaseHelper bd;
	
	public SQLContactBDD(SQLDatabaseHelper bd)
	{
		this.bd=bd;
	}
	
	public ArrayList<SQLContact> getContactList() {
		ArrayList<SQLContact> list = new ArrayList<SQLContact>();
		SQLiteDatabase db = bd.getReadableDatabase();

		String[] projection = { COLUMN_NAME_ID, COLUMN_NAME_FIRST_NAME,
				COLUMN_NAME_LAST_NAME, COLUMN_NAME_PHONE_NUMBER,
				COLUMN_NAME_MAIL,COLUMN_NAME_ADDRESS,
				COLUMN_NAME_LABEL ,COLUMN_NAME_PICTURE};

		String sortOrder = COLUMN_NAME_LAST_NAME +" "+"DESC";

		Cursor cur = db.query(TABLE_NAME, projection, null, null, null, null,
				sortOrder);
		cur.moveToFirst();
		while (!cur.isLast()) {
			list.add(new SQLContact(cur.getLong(cur.getColumnIndex(COLUMN_NAME_ID)), cur
					.getString(cur.getColumnIndex(COLUMN_NAME_FIRST_NAME)), cur
					.getString(cur.getColumnIndex(COLUMN_NAME_LAST_NAME)), cur
					.getString(cur.getColumnIndex(COLUMN_NAME_PHONE_NUMBER)), cur
					.getString(cur.getColumnIndex(COLUMN_NAME_MAIL)),cur
					.getString(cur.getColumnIndex(COLUMN_NAME_ADDRESS)),cur
					.getString(cur.getColumnIndex(COLUMN_NAME_LABEL)),cur
					.getString(cur.getColumnIndex(COLUMN_NAME_PICTURE))));
			
			cur.moveToNext();

		}
		return list;
	}

	public long insertContact(SQLContact contact) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_ID, contact.getId());
		values.put(COLUMN_NAME_FIRST_NAME, contact.getFirst_name());
		values.put(COLUMN_NAME_LAST_NAME, contact.getLast_name());
		values.put(COLUMN_NAME_PHONE_NUMBER, contact.getPhone_number());
		values.put(COLUMN_NAME_MAIL, contact.getMail());
		values.put(COLUMN_NAME_ADDRESS, contact.getAddress());
		values.put(COLUMN_NAME_LABEL, contact.getLabel());
		values.put(COLUMN_NAME_PICTURE, contact.getPicture());
		
		return bd.getReadableDatabase().insert(TABLE_NAME, null,
				values);
	}

}

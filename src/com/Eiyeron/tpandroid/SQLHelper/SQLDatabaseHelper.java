package com.Eiyeron.tpandroid.SQLHelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.TextView;

public class SQLDatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 13;
	public static final String DATABASE_NAME = "TpAndroid.db";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	public static final String TABLE_NAME = "contacts";

	public static final String COLUMN_NAME_ID = "contact_entry_id";
	public static final String COLUMN_NAME_FIRST_NAME = "contact_first_name";
	public static final String COLUMN_NAME_LAST_NAME = "contact_last_name";
	public static final String COLUMN_NAME_PHONE_NUMBER = "contact_phone_number";
	public static final String COLUMN_NAME_MAIL = "contact_email";
	public static final String COLUMN_NAME_LABEL = "contact_label";
	public static final String COLUMN_NAME_ADDRESS = "contact_address";
	public static final String COLUMN_NAME_PICTURE = "contact_picture";

	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ TABLE_NAME + " (" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY" + COMMA_SEP
			+ COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP
			+ COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP
			+ COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP
			+ COLUMN_NAME_MAIL + TEXT_TYPE + COMMA_SEP
			+COLUMN_NAME_ADDRESS+ TEXT_TYPE+ COMMA_SEP
			+ COLUMN_NAME_LABEL + TEXT_TYPE+ COMMA_SEP 
			+COLUMN_NAME_PICTURE+ TEXT_TYPE

			+ " )";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	public SQLDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
		System.out.println("ici onCreate");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy
		// is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	public void onDowngrade(Context C, SQLiteDatabase db, int oldVersion,
			int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

	
}

package com.gvit.gims.attendance.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase _db) {
		_db.execSQL(LoginDBContentProvider.CREATE_LOGIN_TABLE);
		
		ContentValues supercredential = new ContentValues();
		supercredential.put("USERNAME", "superuser");
		supercredential.put("PASSWORD", "supernova");
		_db.insert(LoginDBContentProvider.TABLE_NAME_LOGIN, "", supercredential);
		
		supercredential.put("USERNAME", "Ajay");
		supercredential.put("PASSWORD", "supernova");
		_db.insert(LoginDBContentProvider.TABLE_NAME_LOGIN, "", supercredential);
		
		_db.execSQL(LoginDBContentProvider.CREATE_STATUS_TABLE);
		
		_db.execSQL(LoginDBContentProvider.CREATE_STUDENT_TABLE);
		ContentValues students = new ContentValues();
		students.put("YEAR", "I");
		students.put("BRANCH", "CSE");
		students.put("SECTION", "B");
		students.put("REGNO", "1");
		students.put("NAME", "BOB thebuilder");
		_db.insert(LoginDBContentProvider.TABLE_NAME_STUDENT, "", students);
	}

	@Override
	public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
		// Log the version upgrade.
		Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
				+ _newVersion + ", which will destroy all old data");
		_db.execSQL("DROP TABLE IF EXISTS " + LoginDBContentProvider.TABLE_NAME_LOGIN);
		_db.execSQL("DROP TABLE IF EXISTS " + LoginDBContentProvider.TABLE_NAME_ABSENTEES);
		onCreate(_db);
	}
}

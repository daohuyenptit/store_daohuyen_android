package com.daohuyen.dell.store_cosmetics.services.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.daohuyen.dell.store_cosmetics.model.Profile;

import java.util.ArrayList;


public class UserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Store";
    private static final int DATABASE_VERSION = 2;


    private static final String TABLE_USER = "users";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";

    private static final String TABLE_PROFILE = "profile";
    private static final String KEY_PROFILE_ID = "id";
    private static final String KEY_PROFILE_EMAIL = "email";
    private static final String KEY_PROFILE_NAME = "name";
    private static final String KEY_PROFILE_ADDRESS = "address";
    private static final String KEY_PROFILE_AVATAR = "avatarUrl";


    private static final String TABLE_TYPE= "types";
    private static final String KEY_TYPE_ID="id";
    private static final String KEY_TYPE_NAME="name";

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_user_table = String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT)", TABLE_USER, KEY_EMAIL, KEY_PASS);
        String create_prfile_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", TABLE_PROFILE, KEY_PROFILE_ID, KEY_PROFILE_EMAIL, KEY_PROFILE_NAME, KEY_PROFILE_ADDRESS, KEY_PROFILE_AVATAR);

        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_prfile_table);
    }
    //class nay can k e c co thao tac voi sqli k, chuathe thi cu de day

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_user_table = String.format("DROP TABLE IF EXISTS %s", TABLE_USER);
        String drop_profile_table = String.format("DROP TABLE IF EXISTS %s", TABLE_PROFILE);
        sqLiteDatabase.execSQL(drop_user_table);
        sqLiteDatabase.execSQL(drop_profile_table);
    }
    public boolean registerUser(Profile user){
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, user.getUser().getUsername());
            values.put(KEY_PASS, user.getUser().getPassword());
            sqLiteDatabase.insert(TABLE_USER, null, values);
            ContentValues values1 = new ContentValues();
            values1.put(KEY_PROFILE_NAME, user.getName());
            values1.put(KEY_PROFILE_ADDRESS, user.getAddress());
            values1.put(KEY_PROFILE_EMAIL, user.getUser().getUsername());
            values1.put(KEY_PROFILE_AVATAR, "1");
            sqLiteDatabase.insert(TABLE_PROFILE, null, values1);
            sqLiteDatabase.close();
        } catch (Exception e){
            return false;
        }
        return true;
    }
    public Profile getProfile(String email) {
        Profile profile = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_PROFILE, null, KEY_PROFILE_EMAIL + "= ?", new String[]{email}, null, null, null);
        if(cursor.moveToNext()) {
            profile = new Profile(cursor.getInt(0), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        return profile;
    }
    public boolean checkLogin(String email, String pass){
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.query(TABLE_USER, null, KEY_EMAIL +"= ? and "+KEY_PASS +"= ?",new String[]{email, pass},null,null,null );
        if (cursor!=null && cursor.moveToNext()){
            return true;
        }
        return false;
    }
    public boolean updateProfile(Profile profile){
        try{
            SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put(KEY_PROFILE_AVATAR, profile.getSrcAvatar());

            sqLiteDatabase.update(TABLE_PROFILE, values, KEY_PROFILE_EMAIL +"= ?",new String[]{profile.getUser().getUsername()});
            sqLiteDatabase.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }
}

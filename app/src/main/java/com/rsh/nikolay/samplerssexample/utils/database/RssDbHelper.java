package com.rsh.nikolay.samplerssexample.utils.database;

/** Created by Tkachenko Nikolay */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class RssDbHelper extends SQLiteOpenHelper implements BaseColumns {


    public static final String DB_CONTACTS = "rss_data_list.db";
    public static final String TABLE_NAME_CHANNEL = "channel_rss";
    public static final String TABLE_NAME_ITEM_CHANNEL = "item_records_rss";
    public static final int VERSION = 1;

    private Context context;

    //Таблица TABLE_NAME_CHANNEL
    public static final String TABLE_CHANNEL_TITLE = "title";
    public static final String TABLE_CHANNEL_LINK = "link";
    public static final String TABLE_CHANNEL_DESCRIPTION = "description";
    public static final String TABLE_CHANNEL_LANGUAGE = "language";
    public static final String TABLE_CHANNEL_MANAGING_EDITOR = "managing_editor";
    public static final String TABLE_CHANNEL_GENERATOR = "generator";
    public static final String TABLE_CHANNEL_PUBDATE = "pubdate";
    public static final String TABLE_CHANNEL_LASTBUILDDATE = "lastbuilddate";
    public static final String TABLE_CHANNEL_IMAGE = "image";


    //Таблица TABLE_NAME_ITEM_CHANNEL
    public static final String TABLE_ITEM_CHANNEL_TITLE = "title";
    public static final String TABLE_ITEM_CHANNEL_GUID = "guid";
    public static final String TABLE_ITEM_CHANNEL_LINK = "link";
    public static final String TABLE_ITEM_CHANNEL_DESCRIPTION = "description";
    public static final String TABLE_ITEM_CHANNEL_PUBDATE = "pubdate";
    public static final String TABLE_ITEM_CHANNEL_DCCREATOR = "dccreator";
    public static final String TABLE_ITEM_CHANNEL_CATEGORY = "category";
    public static final String TABLE_ITEM_CHANNEL_ID = "id_channel";

    public static final String[] ITEM_RSS_PROJECTION = new String[] {
            TABLE_ITEM_CHANNEL_TITLE, TABLE_ITEM_CHANNEL_GUID,
            TABLE_ITEM_CHANNEL_LINK, TABLE_ITEM_CHANNEL_DESCRIPTION,
            TABLE_ITEM_CHANNEL_PUBDATE, TABLE_ITEM_CHANNEL_DCCREATOR,
            TABLE_ITEM_CHANNEL_CATEGORY, TABLE_ITEM_CHANNEL_ID
    };

    public static final String[] CHANNEL_RSS_PROJECTION = new String[] {
            TABLE_CHANNEL_TITLE, TABLE_CHANNEL_LINK,
            TABLE_CHANNEL_DESCRIPTION, TABLE_CHANNEL_LANGUAGE,
            TABLE_CHANNEL_MANAGING_EDITOR, TABLE_CHANNEL_GENERATOR,
            TABLE_CHANNEL_PUBDATE, TABLE_CHANNEL_LASTBUILDDATE,
            TABLE_CHANNEL_IMAGE
    };

    public RssDbHelper(Context context) {
        super(context, DB_CONTACTS, null, VERSION);
        this.context = context;
    }


    public static String createTableChannel = "CREATE TABLE "+TABLE_NAME_CHANNEL
            + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_CHANNEL_TITLE  + " TEXT, "
            + TABLE_CHANNEL_LINK + " TEXT, "
            + TABLE_CHANNEL_DESCRIPTION  + " TEXT, "
            + TABLE_CHANNEL_LANGUAGE  + " TEXT, "
            + TABLE_CHANNEL_MANAGING_EDITOR  + " TEXT, "
            + TABLE_CHANNEL_GENERATOR  + " TEXT, "
            + TABLE_CHANNEL_PUBDATE  + " TEXT, "
            + TABLE_CHANNEL_LASTBUILDDATE  + " TEXT, "
            + TABLE_CHANNEL_IMAGE + " TEXT);";

    public static String createTableItem = "CREATE TABLE "+TABLE_NAME_ITEM_CHANNEL
            + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_ITEM_CHANNEL_TITLE + " TEXT, "
            + TABLE_ITEM_CHANNEL_GUID + " TEXT, "
            + TABLE_ITEM_CHANNEL_LINK + " TEXT, "
            + TABLE_ITEM_CHANNEL_DESCRIPTION + " TEXT, "
            + TABLE_ITEM_CHANNEL_PUBDATE + " TEXT, "
            + TABLE_ITEM_CHANNEL_DCCREATOR + " TEXT, "
            + TABLE_ITEM_CHANNEL_CATEGORY + " TEXT, "
            + TABLE_ITEM_CHANNEL_ID + " TEXT);";

    public static String dropTableChannel = "DROP TABLE IF EXISTS " + TABLE_NAME_CHANNEL;
    public static String dropTableItem = "DROP TABLE IF EXISTS " + TABLE_NAME_ITEM_CHANNEL;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createTableChannel);
        sqLiteDatabase.execSQL(createTableItem);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(dropTableChannel);
        sqLiteDatabase.execSQL(dropTableItem);
        onCreate(sqLiteDatabase);
    }



}

package com.rsh.nikolay.samplerssexample.utils.database;

/** Created by Tkachenko Nikolay */

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.rsh.nikolay.samplerssexample.utils.database.RssDbHelper.TABLE_NAME_CHANNEL;
import static com.rsh.nikolay.samplerssexample.utils.database.RssDbHelper.TABLE_NAME_ITEM_CHANNEL;

public class AppContentProvider extends ContentProvider {

    static final String PROVIDER_PATH
            = "com.rsh.nikolay.samplerssexample.utils.database.AppContentProvider";

    static final String URL_CHANNEL_RSS_ITEM_TABLE
            = "content://" + PROVIDER_PATH + "/"+ TABLE_NAME_ITEM_CHANNEL;
    static final String URL_CHANNEL_CHANNEL_TABLE
            = "content://" + PROVIDER_PATH + "/"+ TABLE_NAME_CHANNEL;

    // Типы данных
    // набор строк
    static final String CHANNEL_RSS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + PROVIDER_PATH + "." + TABLE_NAME_CHANNEL;
    static final String ITEM_RSS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + PROVIDER_PATH + "." + TABLE_NAME_ITEM_CHANNEL;

    // одна строка
    static final String CHANNEL_RSS_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + PROVIDER_PATH + "." + TABLE_NAME_CHANNEL;
    static final String ITEM_RSS_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + PROVIDER_PATH + "." + TABLE_NAME_ITEM_CHANNEL;

    public static final Uri CONTENT_URI_CHANNEL_RSS = Uri.parse(URL_CHANNEL_CHANNEL_TABLE);
    public static final Uri CONTENT_URI_ITEM_RSS = Uri.parse(URL_CHANNEL_RSS_ITEM_TABLE);

    private SQLiteDatabase db;
    private UriMatcher mUriMatcher;

    private static final int RSS_ITEM_RECORD = 1;
    private static final int RSS_ITEM_RECORD_ID = 2;
    private static final int RSS_CHANNEL = 3;
    private static final int RSS_CHANNEL_ID = 4;

    @Override
    public boolean onCreate() {
        db = new RssDbHelper(getContext()).getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (getUriMatcher().match(uri)) {
            case RSS_ITEM_RECORD:
                return ITEM_RSS_CONTENT_TYPE;  //DatabaseHelper.VEHICLES_CONTENT_TYPE;
            case RSS_ITEM_RECORD_ID:
                return ITEM_RSS_CONTENT_ITEM_TYPE;  //DatabaseHelper.VEHICLES_CONTENT_TYPE;
            case RSS_CHANNEL:
                return CHANNEL_RSS_CONTENT_TYPE;   //DatabaseHelper.VEHICLES_CONTENT_ITEM_TYPE;
            case RSS_CHANNEL_ID:
                return CHANNEL_RSS_CONTENT_ITEM_TYPE;  //DatabaseHelper.VEHICLES_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] columns, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sort) {
        int path = getPath(uri);
        switch (path) {
            case RSS_ITEM_RECORD:
                return getListRssItemsCursor();
            case RSS_CHANNEL:
                return getRssChannelCursor();
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (contentValues == null) return null;
        Uri operationUri;
        long id;
        switch (getUriMatcher().match(uri)) {
            case RSS_ITEM_RECORD:
                Log.d("APPRSSEXAMPLE", "AppContentProvider RSS_ITEM_RECORD insert");

                id = db.insert(TABLE_NAME_ITEM_CHANNEL, null, contentValues);
                operationUri = ContentUris.withAppendedId(CONTENT_URI_ITEM_RSS, id);
                break;
            case RSS_CHANNEL:
                Log.d("APPRSSEXAMPLE", "AppContentProvider RSS_ITEM_RECORD insert");

                id = db.insert(TABLE_NAME_CHANNEL, null ,contentValues);
                operationUri = ContentUris.withAppendedId(CONTENT_URI_CHANNEL_RSS, id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(operationUri, null);
        return operationUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        //В данном методе удаляем таблицы со всеми данными
        switch (getUriMatcher().match(uri)) {
            case RSS_ITEM_RECORD:
                db.execSQL(RssDbHelper.dropTableItem);
                db.execSQL(RssDbHelper.createTableItem);
                break;
            case RSS_CHANNEL:
                db.execSQL(RssDbHelper.dropTableChannel);
                db.execSQL(RssDbHelper.createTableChannel);
                break;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String where,
                      @Nullable String[] whereArgs) {
        //На данный момент не используется, т.к. не планируем обновлять


        return 0;
    }


    private UriMatcher getUriMatcher() {
        if (mUriMatcher == null) {
            UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            uriMatcher.addURI(PROVIDER_PATH, TABLE_NAME_CHANNEL, RSS_CHANNEL);
            uriMatcher.addURI(PROVIDER_PATH, TABLE_NAME_CHANNEL+"/#", RSS_CHANNEL_ID);
            uriMatcher.addURI(PROVIDER_PATH, TABLE_NAME_ITEM_CHANNEL, RSS_ITEM_RECORD);
            uriMatcher.addURI(PROVIDER_PATH, TABLE_NAME_ITEM_CHANNEL+"/#", RSS_ITEM_RECORD_ID);
            mUriMatcher = uriMatcher;
        }
        return mUriMatcher;
    }

    private int getPath(Uri uri) {
        int path = getUriMatcher().match(uri);
        if (path == -1) throw new IllegalArgumentException("Unknown URI " + uri);
        return path;
    }


    private Cursor getRssChannelCursor() {
        String queryRssChannelOne = "SELECT * FROM " + TABLE_NAME_CHANNEL + " WHERE _ID = 1";

        Cursor cursor  = db.rawQuery(queryRssChannelOne, null);
        cursor.setNotificationUri(getContext().getContentResolver(), CONTENT_URI_CHANNEL_RSS);

        return cursor;
    }

    private Cursor getListRssItemsCursor() {

        String queryItemRssAll = "SELECT * FROM " + TABLE_NAME_ITEM_CHANNEL;

        Cursor cursor  = db.rawQuery(queryItemRssAll, null);
        cursor.setNotificationUri(getContext().getContentResolver(), CONTENT_URI_ITEM_RSS);

        return cursor;
    }


}

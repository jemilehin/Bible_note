package jem.temidayo.bible_note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class BibleNoteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BibleNote.db";
    public static  final int DATABASE_VERSION = 1;
    private static final String TAG = ListNoteActivity.class.getSimpleName();

    public BibleNoteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BibleNoteDatabaseContract.BibleNoteEntry.SQL_CREATE_TABLE);
        DatabaseDataWorker worker = new DatabaseDataWorker(db);
        worker.insertSampleNotes();
//        Log.e(TAG, "worker:"+ db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

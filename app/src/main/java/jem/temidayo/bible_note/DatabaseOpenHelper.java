package jem.temidayo.bible_note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BibleNote.db";
    public static  final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BibleNoteDatabaseContract.BibleNoteEntry.SQL_CREATE_TABLE);
        DatabaseDataWorker worker = new DatabaseDataWorker(db);
        worker.insertSampleNotes();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

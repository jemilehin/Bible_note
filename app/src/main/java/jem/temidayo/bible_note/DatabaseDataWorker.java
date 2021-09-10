package jem.temidayo.bible_note;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class  DatabaseDataWorker {
    private SQLiteDatabase nDb;

    public DatabaseDataWorker(SQLiteDatabase db){
        nDb = db;
    }

    private void insertNote(String mPreacher, String nTitle, String nText) {
        ContentValues values = new ContentValues();
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_SERMONER, mPreacher);
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE, nTitle);
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT, nText);

        long newRowId = nDb.insert(BibleNoteDatabaseContract.BibleNoteEntry.TABLE_NAME, null, values);
    }
}

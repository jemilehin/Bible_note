package jem.temidayo.bible_note;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class  DatabaseDataWorker {
    private SQLiteDatabase nDb;

    public DatabaseDataWorker(SQLiteDatabase db){
        nDb = db;
    }

    public void insertSampleNotes(){
        insertNote("Mr Rafeal", "God do Wonders", "God is kind, " +
                "he is marvelous, gave his onlyson " +
                "jesus christ.Example: Gen 3:1-4");
        insertNote("Prophet King James", "Serving God", "God is Mighty, he is " +
                "Brave, serving God is good " +
                "Example: Psalm 3:1-4");
        insertNote("Mr Jacob", "Brothers Keeper", "it is what God wants " +
                "show love to one another" +
                "created us in his kind. " +
                "Example: Gen 1:1-to the end");
        insertNote("Mr Ayo Gabriel", "God's Mercy", "God is kind, he is " +
                "marvelous, gave his only " +
                "son jesus christ. " +
                "Example: Gen 3:1-4");
        insertNote("Mr Isreal", "God do Wonders", "God is kind, he is " +
                "marvelous, gave his only " +
                "son jesus christ. " +
                "Example: Gen 3:1-4");
        insertNote("Prophet Exodus", "Glorify God", "God is Mighty, he is " +
                "Brave, serving God is good " +
                "Example: Psalm 3:1-4");
        insertNote("Prophet Isiah", "Serving God", "God is Mighty, he is " +
                "Brave, serving God is good " +
                "Example: Psalm 3:1-4");
        insertNote("Pastor James", "God Saves", "God is Mighty, he is " +
                "Brave, serving God is good " +
                "Example: Psalm 3:1-4");
    }

    private void insertNote(String mPreacher, String nTitle, String nText) {
        ContentValues values = new ContentValues();
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_SERMONER, mPreacher);
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE, nTitle);
        values.put(BibleNoteDatabaseContract.BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT, nText);

        long newRowId = nDb.insert(BibleNoteDatabaseContract.BibleNoteEntry.TABLE_NAME, null, values);
    }
}

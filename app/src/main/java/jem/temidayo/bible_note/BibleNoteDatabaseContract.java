package jem.temidayo.bible_note;

import android.provider.BaseColumns;

public class BibleNoteDatabaseContract {
    private  BibleNoteDatabaseContract() {}

    public static final class BibleNoteEntry implements BaseColumns{
        public static final String TABLE_NAME = "bible_note";
        public static final String COLUMN_BIBLE_NOTE_TITLE = "note_tilte";
        public static final String COLUMN_BIBLE_NOTE_TEXT = "note_text";
        public static final String COLUMN_SERMONER = "sermoner";

        public static final String SQL_CREATE_TABLE = "CREAT TABLE" + TABLE_NAME + "(" + _ID
                + "INTEGER PRIMARY KEY," + COLUMN_BIBLE_NOTE_TITLE + "TEXT NOT NULL," +
                COLUMN_BIBLE_NOTE_TEXT + "TEXT NOT NULL," + COLUMN_SERMONER + "TEXT)";
    }
}

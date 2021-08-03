package jem.temidayo.bible_note;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static jem.temidayo.bible_note.BibleNoteDatabaseContract.*;

public class NoteManager {
    private static NoteManager noteInstance = null;
    private List<BibleNote> mNotes = new ArrayList<>();


    public static NoteManager getNoteInstance() {
        if(noteInstance == null) {
            noteInstance = new NoteManager();
//            noteInstance.loadNotesFromDatabase();
        }
        return noteInstance;
    }

    public List<BibleNote> getmNotes() {
        return mNotes;
    }

    public int createNewNote(){
        BibleNote note = new BibleNote(null,null,null);
        mNotes.add(note);
        return mNotes.size() - 1;
    }

//    public static  void loadFromDatabase(DatabaseOpenHelper dbHelper){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        final String[] noteColumns = {
//                BibleNoteEntry.COLUMN_SERMONER,
//                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT,
//                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT
//        };
//        final Cursor noteCursor = db.query(BibleNoteEntry.TABLE_NAME, noteColumns, null,null, null,null, BibleNoteEntry._ID);
//        loadNotesFromDatabase(noteCursor);
//    }

//    private static void loadNotesFromDatabase(Cursor cursor) {
//        int nPreacher = cursor.getColumnIndex(BibleNoteEntry.COLUMN_SERMONER);
//        int noteTitlePos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE);
//        int noteTextPos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT);
//
//        NoteManager nm = getNoteInstance();
//        nm.mNotes.clear();
//        while (cursor.moveToNext()){
//            String nPreacherName = cursor.getString(nPreacher);
//            String nTitle = cursor.getString(noteTitlePos);
//            String nText = cursor.getString(noteTextPos);
//
//            BibleNote note = new BibleNote(nPreacherName,nTitle,nText);
//            nm.mNotes.add(note);
//        }
//        cursor.close();
//    }

    public int findNote(BibleNote note) {
        for(int index = 0; index < mNotes.size(); index++) {
            if(note.equals(mNotes.get(index)))
                return index;
        }
        return -1;
    }

    public void removeNote(int index) {
        mNotes.remove(index);
    }
}

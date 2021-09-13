package jem.temidayo.bible_note;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jem.temidayo.bible_note.BibleNoteDatabaseContract.BibleNoteEntry;

public class NoteManager {
    private static NoteManager noteInstance = null;
    private List<BibleNote> notes = new ArrayList<>();


    public static NoteManager getNoteInstance() {
        if(noteInstance == null) {
            noteInstance = new NoteManager();
        }
        return noteInstance;
    }

    public List<BibleNote> getNotes() {
        return notes;
    }

    public static void loadFromDatabase(BibleNoteOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String[] noteColumns = {
                BibleNoteEntry._ID,
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE,
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT,
                BibleNoteEntry.COLUMN_SERMONER
        };

        final Cursor noteCursor = db.query(BibleNoteEntry.TABLE_NAME, noteColumns,
                null, null, null, null, null
        );

        loadNotesFromDatabase(noteCursor);
    }

    private static void loadNotesFromDatabase(Cursor cursor) {
        int noteTitlePos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE);
        int noteTextPos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT);
        int noteSermonerPos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_SERMONER);
        int idPos = cursor.getColumnIndex(BibleNoteEntry._ID);

        NoteManager nm = getNoteInstance();
        nm.notes.clear();
        while(cursor.moveToNext()){
            String noteTitle = cursor.getString(noteTitlePos);
            String noteText = cursor.getString(noteTextPos);
            String noteSermoner = cursor.getString(noteSermonerPos);
            int nId = cursor.getInt(idPos);

            BibleNote note = new BibleNote(nId,noteSermoner,noteTitle,noteText);
            nm.notes.add(note);
        }
        cursor.close();
    }


    public int createNewNote(){
        BibleNote note = new BibleNote(null,null,null, null);
        notes.add(note);
        return notes.size() - 1;
    }

    public void removeNote(int index) {
        notes.remove(index);
    }
}

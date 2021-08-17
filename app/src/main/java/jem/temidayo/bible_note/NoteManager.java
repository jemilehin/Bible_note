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
//            noteInstance.intilizeExampleNotes();
        }
        return noteInstance;
    }

    public List<BibleNote> getNotes() {
        return notes;
    }

    public static void loadFromDatabase(BibleNoteOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String[] noteColumns = {BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE,
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT,
                BibleNoteEntry.COLUMN_SERMONER};

        final Cursor noteCursor = db.query(BibleNoteEntry.TABLE_NAME, noteColumns,
                null, null, null, null, null
        );

        loadNotesFromDatabase(noteCursor);
    }

    private static void loadNotesFromDatabase(Cursor cursor) {
        int noteTitlePos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE);
        int noteTextPos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT);
        int noteSermonerPos = cursor.getColumnIndex(BibleNoteEntry.COLUMN_SERMONER);

        NoteManager nm = getNoteInstance();
        nm.notes.clear();
        while(cursor.moveToNext()){
            String noteTitle = cursor.getString(noteTitlePos);
            String noteText = cursor.getString(noteTextPos);
            String noteSermoner = cursor.getString(noteSermonerPos);

            BibleNote nwNote = new BibleNote(noteSermoner,noteTitle,noteText);
            nm.notes.add(nwNote);
        }
        cursor.close();
    }


    public int createNewNote(){
        BibleNote note = new BibleNote(null,null,null);
        notes.add(note);
        return notes.size() - 1;
    }


    private void intilizeExampleNotes() {
        final NoteManager nm = getNoteInstance();
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Prophet King James",
                "Serving God",
                "God is Mighty, he is " +
                        "Brave, serving God is good " +
                        "Example: Psalm 3:1-4"
        ));
        notes.add(new BibleNote("Mr Jacob",
                "Brothers Keeper",
                "it is what God wants " +
                        "show love to one another" +
                        "created us in his kind. " +
                        "Example: Gen 1:1-to the end"
        ));
        notes.add(new BibleNote("Mr Ayo Gabriel",
                "God's Mercy",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
        notes.add(new BibleNote("Mr Isreal",
                "God do Wonders",
                "God is kind, he is " +
                        "marvelous, gave his only " +
                        "son jesus christ. " +
                        "Example: Gen 3:1-4"
        ));
    }

    public void removeNote(int index) {
        notes.remove(index);
    }
}

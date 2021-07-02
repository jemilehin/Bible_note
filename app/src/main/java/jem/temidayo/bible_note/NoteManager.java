package jem.temidayo.bible_note;


import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private static NoteManager noteInstance = null;
    private List<BibleNote> notes = new ArrayList<>();


    public static NoteManager getNoteInstance() {
        if(noteInstance == null) {
            noteInstance = new NoteManager();
            noteInstance.intilizeExampleNotes();
        }
        return noteInstance;
    }

    public List<BibleNote> getNotes() {
        return notes;
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
}

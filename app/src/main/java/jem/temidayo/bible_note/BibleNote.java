package jem.temidayo.bible_note;

public class BibleNote {
    private String pName,  nTitle,  nText;
    private int noteId;
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int id) {
        this.noteId = id;
    }



    public BibleNote(Integer nId, String pName, String nTitle, String nText) {
        this.pName = pName;
        this.nTitle = nTitle;
        this.nText = nText;
        this.noteId = nId;
    }

    public BibleNote(String sermoner, String title, String text){
        nTitle = title;
        nText = text;
        pName = sermoner;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public String getnText() {
        return nText;
    }

    public void setnText(String nText) {
        this.nText = nText;
    }
}

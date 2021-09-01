package jem.temidayo.bible_note;

public class BibleNote {
    private String pName,  nTitle,  nText;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    private Integer noteId;

    public BibleNote(Integer nId, String pName, String nTitle, String nText) {
        this.pName = pName;
        this.nTitle = nTitle;
        this.nText = nText;
        this.noteId = nId;
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

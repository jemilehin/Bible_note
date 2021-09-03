package jem.temidayo.bible_note;

public class BibleNote {
    private String pName,  nTitle,  nText;

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    private int nId;

    public BibleNote(String pName, String nTitle, String nText, Integer nId) {
        this.pName = pName;
        this.nTitle = nTitle;
        this.nText = nText;
        this.nId = nId;
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

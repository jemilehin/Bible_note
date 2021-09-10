package jem.temidayo.bible_note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jem.temidayo.bible_note.BibleNoteDatabaseContract.BibleNoteEntry;

public class BibleNoteActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    public static  final String NOTE_ID = "jem.temidayo.bible_note.NOTE_ID";
    public static final String BIBLE_NOTE_PREACHER = "jem.temidayo.bible_note.BIBLE_NOTE_PREACHER";
    public static final String BIBLE_NOTE_TITLE = "jem.temidayo.bible_note.BIBLE_NOTE_TITLE";
    public static final String BIBLE_NOTE_TEXT = "jem.temidayo.bible_note.BIBLE_NOTE_TEXT";
    public static final int ID_NOT_SET = -1;
    private EditText mPreacherName, mNoteTitle, mNoteText;
    private String mPutPreacherName, mPutNoteTitle, mPutNoteText;
    private boolean mIsNewNote;
    private boolean mIsCancelling;
//    private MenuItem menu;
    private Button sButton, cButton;
    private BibleNoteOpenHelper mDbHelper;
    private Cursor mBibleNoteCursor;
    private int mNoteTextPos;
    private int mNoteTitlePos;
    private int mSermornerPos;
    private int noteId;
    private BibleNote mNote = new BibleNote("", "","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bible_note_activity);

        mDbHelper = new BibleNoteOpenHelper(this);

        readDisplayStateValues();
        if(savedInstanceState == null){
            saveNoteValues();
        }else{
            restoreOriginalNoteValues(savedInstanceState);
        }

        mPreacherName = findViewById(R.id.edit_text_pname);
        mNoteTitle = findViewById(R.id.edit_text_title);
        mNoteText = findViewById(R.id.edit_text_note);

        if(!mIsNewNote){
            loadNoteData();
        }

        sButton = findViewById(R.id.save_note);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                startActivity(new Intent(BibleNoteActivity.this, ListNoteActivity.class));
            }
        });

        cButton = findViewById(R.id.cancle_note);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mIsCancelling){
                    if(mIsNewNote){
                        NoteManager.getNoteInstance().removeNote(noteId);
                    }else {
                        storePreviousNoteValues();
                    }
//                }
                finish();
            }
        });
    }

    private void loadNoteData() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = BibleNoteEntry._ID + " = ?";
        String[] selectionArgs = {Integer.toString(noteId)};
//        Log.v("selected: ", Integer.toString(noteId));
        String[] bibleNoteColumn= {
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE,
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT,
                BibleNoteEntry.COLUMN_SERMONER
        };

        mBibleNoteCursor = db.query(BibleNoteEntry.TABLE_NAME,bibleNoteColumn,selection, selectionArgs,
                null, null, null);

        mNoteTitlePos = mBibleNoteCursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE);
        mNoteTextPos = mBibleNoteCursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT);
        mSermornerPos = mBibleNoteCursor.getColumnIndex(BibleNoteEntry.COLUMN_SERMONER);
        mBibleNoteCursor.moveToNext();
        displayNote();
    }

    private void restoreOriginalNoteValues(Bundle savedInstanceState) {
        mPutPreacherName = savedInstanceState.getString(BIBLE_NOTE_PREACHER);
        mPutNoteTitle = savedInstanceState.getString(BIBLE_NOTE_TITLE);
        mPutNoteText = savedInstanceState.getString(BIBLE_NOTE_TEXT);
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        noteId = intent.getIntExtra(NOTE_ID, ID_NOT_SET);
        mIsNewNote = noteId == ID_NOT_SET;
        if(mIsNewNote)
            createNewNote();
        Log.i(TAG, "noteId: " + noteId);
//        mNote = NoteManager.getNoteInstance().getNotes().get(noteId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mIsCancelling){
            if(mIsNewNote){
                NoteManager.getNoteInstance().removeNote(noteId);
            }else{
                storePreviousNoteValues();
            }
        }
    }

    private void storePreviousNoteValues() {
        mNote.setpName(mPutPreacherName);
        mNote.setnTitle(mPutNoteTitle);
        mNote.setnText(mPutNoteText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BIBLE_NOTE_PREACHER, mPutPreacherName);
        outState.putString(BIBLE_NOTE_TITLE, mPutNoteTitle);
        outState.putString(BIBLE_NOTE_TEXT, mPutNoteText);
    }

    private void saveNote() {
        mNote.setpName(mPreacherName.getText().toString());
        mNote.setnTitle(mNoteTitle.getText().toString());
        mNote.setnText(mNoteText.getText().toString());
    }

    private void createNewNote() {
        NoteManager nm = NoteManager.getNoteInstance();
        noteId = nm.createNewNote();
    }

    private void displayNote() {
        String noteTitle = mBibleNoteCursor.getString(mNoteTitlePos);
        String noteText = mBibleNoteCursor.getString(mNoteTextPos);
        String sermorner = mBibleNoteCursor.getString(mSermornerPos);
        mNoteTitle.setText(noteTitle);
        mPreacherName.setText(sermorner);
        mNoteText.setText(noteText);
    }

    private void saveNoteValues(){
        if(mIsNewNote)
            return;
        mPutPreacherName = mNote.getpName();
        mPutNoteTitle = mNote.getnTitle();
        mPutNoteText = mNote.getnText();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_forward:
                moveNext();
                return true;
            case R.id.action_backward:
                movePrev();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void movePrev() {
        saveNote();
        int id = --noteId;
        mNote= NoteManager.getNoteInstance().getNotes().get(id);
        saveNoteValues();
        displayNote();
        invalidateOptionsMenu();
    }

    private void moveNext() {
        saveNote();

        ++noteId;
        mNote= NoteManager.getNoteInstance().getNotes().get(noteId);

        Log.d("noteId: ", String.valueOf(mNote));
        saveNoteValues();
        displayNote();
        invalidateOptionsMenu();
    }
}
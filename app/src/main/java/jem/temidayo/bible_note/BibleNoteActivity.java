package jem.temidayo.bible_note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class BibleNoteActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    public static  final String NOTE_POSITION = "jem.temidayo.bible_note.NOTE_POSITION";
    public static final String BIBLE_NOTE_PREACHER = "jem.temidayo.bible_note.BIBLE_NOTE_PREACHER";
    public static final String BIBLE_NOTE_TITLE = "jem.temidayo.bible_note.BIBLE_NOTE_TITLE";
    public static final String BIBLE_NOTE_TEXT = "jem.temidayo.bible_note.BIBLE_NOTE_TEXT";
    public static  final int POSITION_NOT_SET = -1;
    private EditText mPreacherName, mNoteTitle, mNoteText;
    private String mPutPreacherName, mPutNoteTitle, mPutNoteText;
    private boolean mIsNewNote;
    private boolean mIsCancelling;
    private int mNotePosition;
    private BibleNote mNote;
    private MenuItem menu;
    private Button sButton, cButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bible_note_activity);


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
            displayNote(mPreacherName,mNoteTitle,mNoteText);
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
                finish();
            }
        });
    }

    private void restoreOriginalNoteValues(Bundle savedInstanceState) {
        mPutPreacherName = savedInstanceState.getString(BIBLE_NOTE_PREACHER);
        mPutNoteTitle = savedInstanceState.getString(BIBLE_NOTE_TITLE);
        mPutNoteText = savedInstanceState.getString(BIBLE_NOTE_TEXT);
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        mNotePosition = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        mIsNewNote = mNotePosition == POSITION_NOT_SET;
        if(mIsNewNote)
            createNewNote();
//        Log.i(TAG, "mNotePosition: " + mNote);
        mNote = NoteManager.getNoteInstance().getNotes().get(mNotePosition);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mIsCancelling){
            if(mIsNewNote){
                NoteManager.getNoteInstance().removeNote(mNotePosition);
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
        mNotePosition = nm.createNewNote();
    }

    private void displayNote(EditText mPreacherName, EditText mNoteTitle, EditText mNoteText) {
        mNoteTitle.setText(mNote.getnTitle());
        mPreacherName.setText(mNote.getpName());
        mNoteText.setText(mNote.getnText());
    }

    private void saveNoteValues(){
        if(mIsNewNote)
            return;
        mPutPreacherName = mNote.getpName();
        mPutNoteTitle = mNote.getnTitle();
        mPutNoteText = mNote.getnText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }
}
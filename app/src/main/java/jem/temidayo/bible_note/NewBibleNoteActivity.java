package jem.temidayo.bible_note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class NewBibleNoteActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    public static  final String NOTE_POSITION = "jem.temidayo.bible_note.NOTE_POSITION";
    public static  final int POSITION_NOT_SET = -1;
    private EditText mPreacherName, mNoteTitle, mNoteText;
    private boolean mIsNewNote;
    private int mNotePosition;
    private BibleNote mNote;
    private MenuItem menu;
    private Button button;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bible_note_activity);


        readDisplayStateValues();

        mPreacherName = findViewById(R.id.edit_text_pname);
        mNoteTitle = findViewById(R.id.edit_text_title);
        mNoteText = findViewById(R.id.edit_text_note);

        if(!mIsNewNote){
            displayNote(mPreacherName,mNoteTitle,mNoteText);
        }
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        mNotePosition = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        mIsNewNote = mNotePosition == POSITION_NOT_SET;
        if(mIsNewNote)
            createNewNote();
        Log.i(TAG, "mNotePosition: " + mNote);
        mNote = NoteManager.getNoteInstance().getNotes().get(mNotePosition);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }
}
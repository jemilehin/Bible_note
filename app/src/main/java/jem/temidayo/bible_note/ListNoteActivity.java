package jem.temidayo.bible_note;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class ListNoteActivity extends AppCompatActivity {

//    private List<BibleNote> bibleNotes = new ArrayList<>();
    private RecyclerView recyclerNotes;
    private NoteListRecyclerAdapter noteListRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListNoteActivity.this, BibleNoteActivity.class);
                startActivity(intent);
            }
        });

        initiateView();
    }

    private void initiateView() {
        recyclerNotes = (RecyclerView) findViewById(R.id.note_recyclerview);
        final LinearLayoutManager noteLayoutManager = new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(noteLayoutManager);

        List<BibleNote> notes = NoteManager.getNoteInstance().getNotes();
        noteListRecyclerAdapter = new NoteListRecyclerAdapter(this, notes);
        recyclerNotes.setItemAnimator(new DefaultItemAnimator());
        recyclerNotes.setAdapter(noteListRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteListRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_backward) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package jem.temidayo.bible_note;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import android.view.Menu;

import jem.temidayo.bible_note.BibleNoteDatabaseContract.BibleNoteEntry;

public class ListNoteActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static  final int LOADER_NOTES = 0;
    private RecyclerView recyclerNotes;
    private NoteListRecyclerAdapter noteListRecyclerAdapter;
    private BibleNoteOpenHelper mOpenHelper;
    private static final String TAG = ListNoteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mOpenHelper = new BibleNoteOpenHelper(this);

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

    @Override
    protected void onDestroy() {
        mOpenHelper.close();
        super.onDestroy();
    }

    private void initiateView() {
         NoteManager.loadFromDatabase(mOpenHelper);
        recyclerNotes = (RecyclerView) findViewById(R.id.note_recyclerview);
        LinearLayoutManager noteLayoutManager = new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(noteLayoutManager);

//        List<BibleNote> mNotes = NoteManager.getNoteInstance().getNotes();
        noteListRecyclerAdapter = new NoteListRecyclerAdapter(this, null);
        recyclerNotes.setItemAnimator(new DefaultItemAnimator());
        recyclerNotes.setAdapter(noteListRecyclerAdapter);

        ItemTouchHelper.SimpleCallback itemTouchCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(recyclerNotes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
        noteListRecyclerAdapter.notifyDataSetChanged();
    }

    private void loadNotes() {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final String[] noteColumns = {
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE,
                BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT,
                BibleNoteEntry.COLUMN_SERMONER,
                BibleNoteEntry._ID
        };
        final Cursor noteCursor = db.query(BibleNoteEntry.TABLE_NAME, noteColumns,null,null,
                null,null,null);
        noteListRecyclerAdapter.changeCursor(noteCursor);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_backward) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof NoteListRecyclerAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
//            String name = mNotes.get(viewHolder.getAdapterPosition()).getnTitle();

            // backup of removed item for undo purpose
//            final BibleNote deletedItem = cartList.get(viewHolder.getAdapterPosition());
//            final int deletedIndex = viewHolder.getAdapterPosition();
            Log.d(TAG, "the id:"+ viewHolder.getItemId());

            // remove the item from recycler view
            noteListRecyclerAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {

            // undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedItem, deletedIndex);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = null;
        if(id == LOADER_NOTES){
           loader = new CursorLoader(this){
                @Override
                public Cursor loadInBackground() {
                    SQLiteDatabase db = mOpenHelper.getReadableDatabase();
                    final String[] noteColumns = {
                            BibleNoteEntry._ID, BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE,
                            BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT, BibleNoteEntry.COLUMN_SERMONER
                    };

                    return db.query(BibleNoteEntry.TABLE_NAME, noteColumns,null,null,null,
                            null,null);
                }
            };
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == LOADER_NOTES)
            noteListRecyclerAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(loader.getId() == LOADER_NOTES)
            noteListRecyclerAdapter.changeCursor(null);
    }
}
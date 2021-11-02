package jem.temidayo.bible_note;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jem.temidayo.bible_note.BibleNoteDatabaseContract.BibleNoteEntry;

public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private int mTitleNotePos;
    private int mTextNotePos;
    private int mSermornerPos;
    private Cursor mCursor;
    private int mIdPos;
    private SQLiteOpenHelper mDbHelper;

    public NoteListRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();
    }

    private void populateColumnPositions() {
        if(mCursor == null)
            return;
        mTitleNotePos = mCursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TITLE);
        mTextNotePos = mCursor.getColumnIndex(BibleNoteEntry.COLUMN_BIBLE_NOTE_TEXT);
        mSermornerPos = mCursor.getColumnIndex(BibleNoteEntry.COLUMN_SERMONER);
        mIdPos = mCursor.getColumnIndex(BibleNoteEntry._ID);
    }

    public void changeCursor(Cursor cursor){
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnPositions();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.list_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String title = mCursor.getString(mTitleNotePos);
        String text = mCursor.getString(mTextNotePos);
        String sermoner = mCursor.getString(mSermornerPos);
        int Id = mCursor.getInt(mIdPos);

        holder.nTitle.setText(title);
        holder.nText.setText(text);
        holder.pName.setText(sermoner);
        holder.mId = Id;
        holder.itemView.setTag(Id);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

//    public static void removeItem(int id) {
//        // notify the item removed by position
//        // to perform recycler view delete animations
//        // NOTE: don't call notifyDataSetChanged()
//        String selection = BibleNoteEntry._ID + "= ?";
//        String[] selectionArgs = {Integer.toString(id)};
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//        db.delete(BibleNoteEntry.TABLE_NAME, selection, selectionArgs);
//
//        notifyItemRemoved(id);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView nTitle, nText, pName;
        public int mId;
//        public CardView viewForeground;

        public ViewHolder(View view) {
            super(view);
            nTitle = (TextView) view.findViewById(R.id.note_title_text);
            nText = (TextView)  view.findViewById(R.id.note_bible_text);
            pName = (TextView) view.findViewById(R.id.preacher_name);
//            viewForeground = view.findViewById(R.id.note_item);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, BibleNoteActivity.class);
                intent.putExtra(BibleNoteActivity.NOTE_ID, mId);
                mContext.startActivity(intent);
            });
        }
    }
}

package jem.temidayo.bible_note;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//        if (viewHolder instanceof NoteListRecyclerAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
//            String name = bibleNotelist.get(viewHolder.getAdapterPosition()).getnTitle();

            // backup of removed item for undo purpose
//            final Item deletedItem = cartList.get(viewHolder.getAdapterPosition());
//            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
//            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {

                    // undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedItem, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();
//        }
//    }

    public void removeItem(int adapterPosition) {
//        bibleNotelist.remove(adapterPosition);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView nTitle, nText, pName;
        public int mId;
        public CardView viewForeground;

        public ViewHolder(View view) {
            super(view);
            nTitle = (TextView) view.findViewById(R.id.note_title_text);
            nText = (TextView)  view.findViewById(R.id.note_bible_text);
            pName = (TextView) view.findViewById(R.id.preacher_name);
            viewForeground = view.findViewById(R.id.note_item);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, BibleNoteActivity.class);
                intent.putExtra(BibleNoteActivity.NOTE_ID, mId);
                mContext.startActivity(intent);
            });
        }
    }
}

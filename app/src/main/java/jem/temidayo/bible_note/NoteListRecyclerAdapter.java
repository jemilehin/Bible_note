package jem.temidayo.bible_note;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder>{

    private final List<BibleNote> bibleNotelist;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
//    private NoteListRecyclerAdapter mAdapter;

    public NoteListRecyclerAdapter(Context context, List<BibleNote> bibleNotes) {
        mContext = context;
        bibleNotelist = bibleNotes;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.list_note_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BibleNote bibleNote = bibleNotelist.get(position);
        holder.nTitle.setText(bibleNote.getnTitle());
        holder.nText.setText(bibleNote.getnText());
        holder.pName.setText(bibleNote.getpName());
        holder.mCurrentPosition =  holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return bibleNotelist.size();
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
        bibleNotelist.remove(adapterPosition);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nTitle, nText, pName;
        public int mCurrentPosition;
        public CardView viewForeground;

        public ViewHolder(View view) {
            super(view);
            nTitle = (TextView) view.findViewById(R.id.note_title_text);
            nText = (TextView)  view.findViewById(R.id.note_bible_text);
            pName = (TextView) view.findViewById(R.id.preacher_name);
            viewForeground = view.findViewById(R.id.note_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BibleNoteActivity.class);
                    intent.putExtra(BibleNoteActivity.NOTE_POSITION, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

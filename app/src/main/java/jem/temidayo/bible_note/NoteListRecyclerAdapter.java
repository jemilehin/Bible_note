package jem.temidayo.bible_note;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder> {

    private List<BibleNote> bibleNotelist;

    public NoteListRecyclerAdapter(List<BibleNote> bibleNotes) {
        bibleNotelist = bibleNotes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_note_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BibleNote bibleNote = bibleNotelist.get(position);
        holder.nTitle.setText(bibleNote.getnTitle());
        holder.nText.setText(bibleNote.getnText());
        holder.pName.setText(bibleNote.getpName());
    }

    @Override
    public int getItemCount() {
        return bibleNotelist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nTitle, nText, pName;
        public ViewHolder(View view) {
            super(view);
            nTitle = (TextView) view.findViewById(R.id.note_title_text);
            nText = (TextView)  view.findViewById(R.id.note_bible_text);
//            pName = (TextView) view.findViewById(R.id.pname);
        }
    }
}

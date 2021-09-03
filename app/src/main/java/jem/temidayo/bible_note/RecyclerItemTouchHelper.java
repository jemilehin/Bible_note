package jem.temidayo.bible_note;

import android.graphics.Canvas;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener Listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        listener = Listener;
    }

    @Override
    public void onChildDraw( Canvas c,  RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View view = ((NoteListRecyclerAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
//        return true;
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View view = ((NoteListRecyclerAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder != null){
            final View view = ((NoteListRecyclerAdapter.ViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(view);
        }
    }

    @Override
    public boolean onMove( RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,  RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void clearView( RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder) {
//        super.clearView(recyclerView, viewHolder);
        final View view = ((NoteListRecyclerAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(view);
    }

    @Override
    public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
        Listener.onItemSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onItemSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}

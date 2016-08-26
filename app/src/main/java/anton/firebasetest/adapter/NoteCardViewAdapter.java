package anton.firebasetest.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anton.firebasetest.R;
import anton.firebasetest.model.Note;

public class NoteCardViewAdapter extends RecyclerView.Adapter<NoteCardViewAdapter.ViewHolder>{

    private List<Note> mNoteList;
    private Context mContext;
    private LayoutInflater mInflater;

    public NoteCardViewAdapter(Context context, ArrayList<Note> noteList) {
        mNoteList = noteList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public Note getItem(int position) {
        return mNoteList.get(position);
    }

    public void setNewItems(List<Note> newList) {
        mNoteList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_note_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNoteList.get(position);

        holder.mTitle.setText(carer.getFullName());
        holder.mContent.setText(Utils.getStringFromList(carer.getCareCategories()));
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelRequest();
    }

    @Override
    public int getItemCount() {
        return mCarerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mContent;
        private Button mEdit;
        private Button mSetAlarm;

        public ViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.note_title_tv);
            mContent = (TextView) view.findViewById(R.id.note_content_tv);
        }

        /**
         * Cancels photo loading request
         * Avoids wrong item photo displaying on scrolling
         */
        public void cancelRequest() {
            PicassoUtils.cancelRequest(mIcon);
            mIcon.setImageResource(R.drawable.user_photo);
        }
    }

}
}

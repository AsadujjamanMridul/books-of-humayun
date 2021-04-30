package com.example.booksofhumayun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksofhumayun.R;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ChapterViewHolder> {


    private static ClickListener clickListener;

    Context context;
    String[] chapters;

    public ChapterListAdapter(Context context, String[] chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.chapter_list_layout,parent,false);

        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {

        holder.chapterTextView.setText(chapters[position]);

    }

    @Override
    public int getItemCount() {
        return chapters.length;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView chapterTextView;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            chapterTextView = itemView.findViewById(R.id.bookNameTextView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            clickListener.onItemClick(getAdapterPosition(), view);

        }
    }

    public interface ClickListener{

        void onItemClick(int position, View view);

    }

    public void setOnItemClickListener(ClickListener clickListener)
    {
        ChapterListAdapter.clickListener = clickListener;
    }

}

package com.example.booksofhumayun.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksofhumayun.customclass.CustomClass;
import com.example.booksofhumayun.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoverAdapter extends RecyclerView.Adapter<CoverAdapter.CoverViewHolder> implements Filterable{

    private List<CustomClass> customList;
    private List<CustomClass> customListFull;
    private SelectedUser selectedUser;

    public CoverAdapter(List<CustomClass> customList, SelectedUser selectedUser) {
        this.customList = customList;
        customListFull = new ArrayList<>(customList);

        this.selectedUser = selectedUser;
    }

    public class CoverViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView bookName;
        TextView publishYear;
        TextView pageNumber;

        public CoverViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.coverImageView);
            bookName = itemView.findViewById(R.id.bookNameTextView);
            publishYear = itemView.findViewById(R.id.publishYearTextView);
            pageNumber = itemView.findViewById(R.id.pageNumberTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(customList.get(getAdapterPosition()));
                }
            });
        }
    }


    @NonNull
    @Override
    public CoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_list_layout,parent,false);

        return new CoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoverViewHolder holder, int position) {

        CustomClass currentItem = customList.get(position);

        holder.imageView.setImageResource(currentItem.getCover());
        holder.bookName.setText(currentItem.getBookName());
        holder.publishYear.setText(currentItem.getPublishYear());
        holder.pageNumber.setText(currentItem.getPageNumber());

    }

    @Override
    public int getItemCount() {
        return customList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CustomClass> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(customListFull);
            } else {
                String filterPattern = charSequence.toString();

                for (CustomClass item : customListFull) {
                    if (item.getBookName().contains(filterPattern)
                            || item.getPublishYear().contains(filterPattern)
                            || item.getPageNumber().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            customList.clear();
            customList.addAll((Collection<? extends CustomClass>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public interface SelectedUser{
        void selectedUser(CustomClass customList);
    }

}

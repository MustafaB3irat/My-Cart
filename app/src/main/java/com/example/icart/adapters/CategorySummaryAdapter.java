package com.example.icart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.databinding.CategorySummaryCardviewBinding;
import com.example.icart.models.data.CategorySummary;

import java.util.List;

public class CategorySummaryAdapter extends RecyclerView.Adapter<CategorySummaryAdapter.CategorySummaryViewHolder> {


    List<com.example.icart.models.data.CategorySummary> categorySummaries;


    public CategorySummaryAdapter(List<CategorySummary> categorySummaries) {
        this.categorySummaries = categorySummaries;
    }


    @NonNull
    @Override
    public CategorySummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategorySummaryCardviewBinding categorySummaryCardviewBinding = CategorySummaryCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        CategorySummaryViewHolder categorySummaryViewHolder = new CategorySummaryViewHolder(categorySummaryCardviewBinding);

        return categorySummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySummaryViewHolder holder, int position) {

        com.example.icart.models.data.CategorySummary categorySummary = categorySummaries.get(position);

        holder.categorySummaryCardviewBinding.setCategory(categorySummary);

        holder.categorySummaryCardviewBinding.categoryAvatar.setImageResource(R.drawable.ic_question);

    }

    @Override
    public int getItemCount() {
        return categorySummaries.size();
    }

    public class CategorySummaryViewHolder extends RecyclerView.ViewHolder {

        private CategorySummaryCardviewBinding categorySummaryCardviewBinding;

        public CategorySummaryViewHolder(@NonNull CategorySummaryCardviewBinding itemView) {
            super(itemView.getRoot());
            this.categorySummaryCardviewBinding = itemView;
        }
    }


}

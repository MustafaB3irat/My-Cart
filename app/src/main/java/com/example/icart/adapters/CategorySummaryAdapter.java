package com.example.icart.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.databinding.CategorySummaryCardviewBinding;
import com.example.icart.models.data.CategorySummary;
import com.example.icart.views.MainActivity;
import com.example.icart.views.fragments.CategorySummaryFragment;

import java.util.List;

public class CategorySummaryAdapter extends RecyclerView.Adapter<CategorySummaryAdapter.CategorySummaryViewHolder> {


    List<com.example.icart.models.data.CategorySummary> categorySummaries;
    private final String FONT_SIZE = "fontsize";
    private final String SHARED_PREF = "sharedpref";
    private SharedPreferences sharedPreferences;


    public CategorySummaryAdapter(List<CategorySummary> categorySummaries, CategorySummaryFragment fragment) {
        this.categorySummaries = categorySummaries;
        sharedPreferences = fragment.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
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

        recursiveLoopChildren(holder.categorySummaryCardviewBinding.cardview);

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


    public void recursiveLoopChildren(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child);
            } else {
                if (child != null && child instanceof TextView) {

                    if (sharedPreferences != null) {


                        float textSize = sharedPreferences.getFloat(FONT_SIZE, -1);

                        if (textSize != -1)
                        ((TextView) child).setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                    }
                }
            }
        }
    }


}

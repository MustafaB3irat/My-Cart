package com.example.icart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.databinding.CategoriesCardviewBinding;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.fragments.CategoriesFragment;

import java.util.List;

public class CataegoryAdpater extends RecyclerView.Adapter<CataegoryAdpater.CategoryViewHolder> {

    List<Catagory> catagories;
    private CategoriesFragment fragment;

    public CataegoryAdpater(List<Catagory> catagories, CategoriesFragment fragment) {
        this.catagories = catagories;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategoriesCardviewBinding categoriesCardviewBinding = CategoriesCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoriesCardviewBinding);

        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Catagory catagory = catagories.get(position);

        holder.categoriesCardviewBinding.setCategory(catagory);

        if (catagory.getCatagory_avatar().equals("default")) {
            holder.categoriesCardviewBinding.categoryAvatar.setImageResource(R.drawable.ic_question);
        }

        initCardViewClickable(holder.categoriesCardviewBinding);


    }

    private void initCardViewClickable(CategoriesCardviewBinding categoriesCardviewBinding) {

        categoriesCardviewBinding.cardview.setOnClickListener(view -> {


        });

    }

    @Override
    public int getItemCount() {
        return catagories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoriesCardviewBinding categoriesCardviewBinding;

        public CategoryViewHolder(@NonNull CategoriesCardviewBinding itemView) {
            super(itemView.getRoot());
            this.categoriesCardviewBinding = itemView;
        }
    }
}

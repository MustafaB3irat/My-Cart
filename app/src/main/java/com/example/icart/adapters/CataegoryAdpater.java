package com.example.icart.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.icart.R;
import com.example.icart.databinding.CategoriesCardviewBinding;
import com.example.icart.models.DatabaseHelpers;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.ElementsActivity;
import com.example.icart.views.fragments.CategoriesFragment;

import java.util.List;

public class CataegoryAdpater extends RecyclerView.Adapter<CataegoryAdpater.CategoryViewHolder> {

    List<Catagory> catagories;
    private CategoriesFragment fragment;
    private DatabaseHelpers databaseHelpers;

    public CataegoryAdpater(List<Catagory> catagories, CategoriesFragment fragment) {
        this.catagories = catagories;
        this.fragment = fragment;
        databaseHelpers = new DatabaseHelpers(fragment.getContext());
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

            String category = categoriesCardviewBinding.categoryName.getText().toString();

            Intent intent = new Intent(fragment.getContext(), ElementsActivity.class);
            intent.putExtra("category", category);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            fragment.getActivity().startActivity(intent);
            fragment.getActivity().overridePendingTransition(0, 0);

        });

    }

    @Override
    public int getItemCount() {
        return catagories.size();
    }

    public Context getContext() {

        return fragment.getContext();
    }

    public void deleteItem(int position) {


        new AlertDialog.Builder(fragment.getContext())
                .setTitle(fragment.getResources().getString(R.string.alert))
                .setMessage(fragment.getResources().getString(R.string.delete_category_confirmation))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {


                    if (databaseHelpers.deleteCategory(catagories.get(position).getName())) {
                        catagories.remove(position);
                        this.notifyDataSetChanged();
                    } else {
                        Toast.makeText(fragment.getContext(), fragment.getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                    }

                })
                .setNegativeButton(android.R.string.no, null).show();


    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoriesCardviewBinding categoriesCardviewBinding;

        public CategoryViewHolder(@NonNull CategoriesCardviewBinding itemView) {
            super(itemView.getRoot());
            this.categoriesCardviewBinding = itemView;
        }
    }
}

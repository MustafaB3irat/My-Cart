package com.example.icart.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.customCallbacks.CategoryActionModeCallBack;
import com.example.icart.databinding.CategoriesCardviewBinding;
import com.example.icart.models.DatabaseHelpers;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.ElementsActivity;
import com.example.icart.views.MainActivity;
import com.example.icart.views.dialogs.AddCategoryDialog;
import com.example.icart.views.fragments.CategoriesFragment;

import java.util.List;

public class CataegoryAdpater extends RecyclerView.Adapter<CataegoryAdpater.CategoryViewHolder> {

    List<Catagory> catagories;
    private CategoriesFragment fragment;
    private DatabaseHelpers databaseHelpers;
    private ActionMode actionMode;
    private final String FONT_SIZE = "fontsize";
    private final String SHARED_PREF = "sharedpref";
    private SharedPreferences sharedPreferences;

    public CataegoryAdpater(List<Catagory> catagories, CategoriesFragment fragment) {
        this.catagories = catagories;
        this.fragment = fragment;
        databaseHelpers = new DatabaseHelpers(fragment.getContext());
        sharedPreferences = fragment.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
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

        recursiveLoopChildren(holder.categoriesCardviewBinding.cardview);


        holder.categoriesCardviewBinding.categoryAvatar.setAnimation(AnimationUtils.loadAnimation(fragment.getContext(), R.anim.fade_in_transition));
        holder.categoriesCardviewBinding.cardview.setAnimation(AnimationUtils.loadAnimation(fragment.getContext(), R.anim.fade_in_scale));


        if (catagory.getCatagory_avatar().equals("default")) {
            holder.categoriesCardviewBinding.categoryAvatar.setImageResource(R.drawable.ic_question);
        }

        initCardViewClickable(holder.categoriesCardviewBinding);
        initCardViewLongClick(holder.categoriesCardviewBinding, holder);


    }


    private void initCardViewLongClick(CategoriesCardviewBinding categoriesCardviewBinding, CategoryViewHolder categoryViewHolder) {
        categoriesCardviewBinding.cardview.setOnLongClickListener(view -> {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

            CategoryActionModeCallBack categoryActionModeCallBack = new CategoryActionModeCallBack(this, categoryViewHolder);

            actionMode = ((MainActivity) fragment.getActivity()).toolbar.startActionMode(categoryActionModeCallBack);
            if (actionMode == null)
                return false;


            return true;
        });
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
                .setIcon(android.R.drawable.ic_delete)
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

    public void editItem(int position) {

        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
        addCategoryDialog.show(fragment.getFragmentManager(), "Update Category");
        addCategoryDialog.setCategoryName(catagories.get(position).getName());

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoriesCardviewBinding categoriesCardviewBinding;

        public CategoryViewHolder(@NonNull CategoriesCardviewBinding itemView) {
            super(itemView.getRoot());
            this.categoriesCardviewBinding = itemView;
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

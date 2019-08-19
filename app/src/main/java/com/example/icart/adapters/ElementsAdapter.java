package com.example.icart.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.customCallbacks.ElementActionModeCallBack;
import com.example.icart.databinding.ElementCardviewBinding;
import com.example.icart.models.DatabaseHelpers;
import com.example.icart.models.data.Element;
import com.example.icart.views.ElementsActivity;
import com.example.icart.views.dialogs.AddElementDialog;

import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementsViewHolder> {


    private List<Element> elements;

    private ElementsActivity activity;
    private DatabaseHelpers databaseHelpers;
    private ActionMode actionMode;
    private final String FONT_SIZE = "fontsize";
    private final String SHARED_PREF = "sharedpref";
    private SharedPreferences sharedPreferences;

    public ElementsAdapter(List<Element> elements, ElementsActivity elementsActivity) {
        this.elements = elements;
        this.activity = elementsActivity;
        this.databaseHelpers = new DatabaseHelpers(activity);
        sharedPreferences=elementsActivity.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ElementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ElementCardviewBinding elementCardviewBinding = ElementCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ElementsViewHolder(elementCardviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementsViewHolder holder, int position) {

        holder.cardviewBinding.setElement(elements.get(position));

        initCardViewLongClickListener(holder);

        recursiveLoopChildren(holder.cardviewBinding.elementMainLayout);


    }

    private void initCardViewLongClickListener(ElementsViewHolder elementsViewHolder) {

        elementsViewHolder.cardviewBinding.elementMainLayout.setOnLongClickListener(view -> {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

            ElementActionModeCallBack elementActionModeCallBack = new ElementActionModeCallBack(this, elementsViewHolder);

            actionMode = activity.startActionMode(elementActionModeCallBack);
            if (actionMode == null)
                return false;

            return true;
        });

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }


    public void editItem(int adapterPosition) {


        AddElementDialog addCategoryDialog = new AddElementDialog();
        addCategoryDialog.show(activity.getSupportFragmentManager(), "Update Element");
        addCategoryDialog.setOldElementForEdit(elements.get(adapterPosition), activity.getIntent().getStringExtra("category"));


    }

    public void deleteItem(int adapterPosition) {

        new AlertDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.alert))
                .setMessage(activity.getResources().getString(R.string.are_you_sure))
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {


                    if (databaseHelpers.deleteElement(elements.get(adapterPosition).getEid())) {
                        elements.remove(adapterPosition);
                        this.notifyDataSetChanged();
                    } else {
                        Toast.makeText(activity, activity.getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                    }

                })
                .setNegativeButton(android.R.string.no, null).show();
    }


    public class ElementsViewHolder extends RecyclerView.ViewHolder {


        private ElementCardviewBinding cardviewBinding;

        public ElementsViewHolder(@NonNull ElementCardviewBinding itemView) {
            super(itemView.getRoot());

            this.cardviewBinding = itemView;
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

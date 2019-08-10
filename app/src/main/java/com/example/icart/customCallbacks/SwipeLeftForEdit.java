package com.example.icart.customCallbacks;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.R;
import com.example.icart.adapters.CataegoryAdpater;

public class SwipeLeftForEdit extends ItemTouchHelper.SimpleCallback {


    private CataegoryAdpater cataegoryAdpater;
    private Drawable icon;


    public SwipeLeftForEdit(CataegoryAdpater cataegoryAdpater) {

        super(0, ItemTouchHelper.LEFT);


        this.cataegoryAdpater = cataegoryAdpater;
        icon = ContextCompat.getDrawable(cataegoryAdpater.getContext(),
                R.drawable.edit);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        int position = viewHolder.getAdapterPosition();

        cataegoryAdpater.editItem(position);

    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX,
                dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        if (dX > 0) {

            icon.setBounds(itemView.getLeft(), itemView.getTop(), (itemView.getLeft() + ((int) dX) + backgroundCornerOffset),
                    itemView.getBottom());

        } else if (dX < 0) {

            icon.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else {
            icon.setBounds(0, 0, 0, 0);
        }
        icon.draw(c);
    }
}

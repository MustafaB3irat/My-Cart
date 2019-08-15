package com.example.icart.customCallbacks;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.icart.adapters.CataegoryAdpater;

public class SwipeRightForDelete extends ItemTouchHelper.SimpleCallback {


    private CataegoryAdpater cataegoryAdpater;



    public SwipeRightForDelete(CataegoryAdpater cataegoryAdpater) {

        super(0, ItemTouchHelper.RIGHT);

        this.cataegoryAdpater = cataegoryAdpater;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        int position = viewHolder.getAdapterPosition();


        cataegoryAdpater.deleteItem(position);

        cataegoryAdpater.notifyItemChanged(viewHolder.getAdapterPosition());


    }

}

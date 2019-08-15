package com.example.icart.customCallbacks;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.icart.R;
import com.example.icart.adapters.CataegoryAdpater;

public class CategoryActionModeCallBack implements ActionMode.Callback {


    private CataegoryAdpater cataegoryAdpater;
    private CataegoryAdpater.CategoryViewHolder categoryViewHolder;


    public CategoryActionModeCallBack(CataegoryAdpater cataegoryAdpater, CataegoryAdpater.CategoryViewHolder categoryViewHolder) {
        this.cataegoryAdpater = cataegoryAdpater;
        this.categoryViewHolder = categoryViewHolder;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

        actionMode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
        actionMode.setTitle("pick one option");



        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.edit_menuitem: {
                cataegoryAdpater.editItem(categoryViewHolder.getAdapterPosition());

                actionMode.finish();

                break;
            }

            case R.id.delete_menuitem: {
                cataegoryAdpater.deleteItem(categoryViewHolder.getAdapterPosition());
                actionMode.finish();

                break;
            }

            default:
                return false;

        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

        actionMode = null;
    }
}

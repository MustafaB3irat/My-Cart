package com.example.icart.customCallbacks;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.icart.R;
import com.example.icart.adapters.ElementsAdapter;

public class ElementActionModeCallBack implements ActionMode.Callback {

    private ElementsAdapter element;
    private ElementsAdapter.ElementsViewHolder elementsViewHolder;


    public ElementActionModeCallBack(ElementsAdapter element, ElementsAdapter.ElementsViewHolder elementsViewHolder) {
        this.element = element;
        this.elementsViewHolder = elementsViewHolder;
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
                element.editItem(elementsViewHolder.getAdapterPosition());

                actionMode.finish();

                break;
            }

            case R.id.delete_menuitem: {
                element.deleteItem(elementsViewHolder.getAdapterPosition());
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

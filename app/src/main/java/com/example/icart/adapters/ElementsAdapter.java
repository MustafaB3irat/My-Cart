package com.example.icart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.databinding.ElementCardviewBinding;
import com.example.icart.models.data.Element;

import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementsViewHolder> {


    private List<Element> elements;

    public ElementsAdapter(List<Element> elements) {
        this.elements = elements;
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


    }

    @Override
    public int getItemCount() {
        return elements.size();
    }


    public class ElementsViewHolder extends RecyclerView.ViewHolder {


        private ElementCardviewBinding cardviewBinding;

        public ElementsViewHolder(@NonNull ElementCardviewBinding itemView) {
            super(itemView.getRoot());

            this.cardviewBinding = itemView;
        }
    }


}

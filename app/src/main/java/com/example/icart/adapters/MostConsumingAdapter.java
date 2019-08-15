package com.example.icart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.databinding.MostConsumingCardviewBinding;
import com.example.icart.models.data.MostConsuming;

import java.util.List;

public class MostConsumingAdapter extends RecyclerView.Adapter<MostConsumingAdapter.MostConsumingViewHolder> {


    List<MostConsuming> mostConsumings;

    public MostConsumingAdapter(List<MostConsuming> mostConsumings) {
        this.mostConsumings = mostConsumings;
    }

    @NonNull
    @Override
    public MostConsumingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        MostConsumingCardviewBinding mostConsumingCardviewBinding = MostConsumingCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        MostConsumingViewHolder mostConsumingViewHolder = new MostConsumingViewHolder(mostConsumingCardviewBinding);

        return mostConsumingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostConsumingViewHolder holder, int position) {

        MostConsuming mostConsuming = mostConsumings.get(position);

        holder.mostConsumingCardviewBinding.setElement(mostConsuming);

    }

    @Override
    public int getItemCount() {
        return mostConsumings.size();
    }

    public class MostConsumingViewHolder extends RecyclerView.ViewHolder {

        private MostConsumingCardviewBinding mostConsumingCardviewBinding;

        public MostConsumingViewHolder(@NonNull MostConsumingCardviewBinding itemView) {
            super(itemView.getRoot());

            this.mostConsumingCardviewBinding = itemView;
        }
    }
}

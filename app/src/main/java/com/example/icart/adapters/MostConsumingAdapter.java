package com.example.icart.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icart.databinding.MostConsumingCardviewBinding;
import com.example.icart.models.data.MostConsuming;
import com.example.icart.views.fragments.MostConsumingFragment;

import java.util.List;

public class MostConsumingAdapter extends RecyclerView.Adapter<MostConsumingAdapter.MostConsumingViewHolder> {


    List<MostConsuming> mostConsumings;
    private final String FONT_SIZE = "fontsize";
    private final String SHARED_PREF = "sharedpref";
    private SharedPreferences sharedPreferences;

    public MostConsumingAdapter(List<MostConsuming> mostConsumings, MostConsumingFragment fragment) {
        this.mostConsumings = mostConsumings;
        sharedPreferences = fragment.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
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

        recursiveLoopChildren(holder.mostConsumingCardviewBinding.elementMainLayout);

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

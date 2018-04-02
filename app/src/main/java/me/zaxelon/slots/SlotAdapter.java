package me.zaxelon.slots;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {
    private List<Drawable> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.image_view);
        }
    }

    public SlotAdapter(List<Drawable> myDataset) {
        mDataset = new ArrayList<>(myDataset);
        Collections.shuffle(mDataset);
    }

    @Override
    public SlotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setImageDrawable(mDataset.get(position % mDataset.size()));

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
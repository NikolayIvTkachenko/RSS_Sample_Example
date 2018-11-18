package com.rsh.nikolay.samplerssexample.View;

/** Created by Tkachenko Nikolay */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsh.nikolay.samplerssexample.R;
import com.rsh.nikolay.samplerssexample.models.ItemRss;

import java.util.ArrayList;

public class RecyclerViewRssAdapter extends RecyclerView.Adapter<RecyclerViewRssAdapter.ItemRssHolder> {

    ArrayList<ItemRss> listItems;

    public RecyclerViewRssAdapter(ArrayList<ItemRss> listItems){
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ItemRssHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View viewHolder
                = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item,
                viewGroup, false);
        ItemRssHolder itemVehicleHolder = new ItemRssHolder(viewHolder);
        return itemVehicleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRssHolder itemRssHolder, int position) {
        itemRssHolder.tvDataRssItem.setText(listItems.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ItemRssHolder extends RecyclerView.ViewHolder {

        protected TextView tvDataRssItem;

        public ItemRssHolder(View view){
            super(view);
            tvDataRssItem = (TextView) view.findViewById(R.id.txt_rss_item);
        }

    }
}

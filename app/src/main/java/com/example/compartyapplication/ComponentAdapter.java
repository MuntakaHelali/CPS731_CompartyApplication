package com.example.compartyapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ComponentHolder>
{
    private List<Component> component = new ArrayList<>();

    @NonNull
    @Override
    public ComponentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_item, parent, false);
        return new ComponentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentHolder holder, int position)
    {
        Component currentComponent = component.get(position);
        holder.title.setText(currentComponent.getName());
        holder.manufacturer.setText(currentComponent.getManufacturer());
        holder.price.setText(String.valueOf(currentComponent.getPrice()));
    }

    @Override
    public int getItemCount() {
        return component.size();
    }

    public void setComponent(List<Component> component)
    {
        this.component = component;
        notifyDataSetChanged();
    }

    public Component getComponentAt(int position){
        return component.get(position);
    }

    class ComponentHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView manufacturer;
        private TextView price;

        public ComponentHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            manufacturer = itemView.findViewById(R.id.text_view_manufacturer);
            price = itemView.findViewById(R.id.text_view_price);
        }
    }
}

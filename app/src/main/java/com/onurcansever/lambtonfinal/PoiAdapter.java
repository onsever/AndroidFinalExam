package com.onurcansever.lambtonfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PoiAdapter extends BaseAdapter {

    Country country;
    LayoutInflater inflater;

    public PoiAdapter(Context context, Country country) {
        this.country = country;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return country.getPlaces().length;
    }

    @Override
    public Object getItem(int i) {
        return country.getPlaces()[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.poi_row, null);
            holder = new ViewHolder();
            holder.placeImageView = view.findViewById(R.id.placeImageView);
            holder.placeNameText = view.findViewById(R.id.placeNameText);
            holder.placePriceText = view.findViewById(R.id.placePriceText);
            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();
        holder.placeImageView.setImageResource(country.getPlaces()[i].getPlaceImage());
        holder.placeNameText.setText(country.getPlaces()[i].getPlaceName());
        holder.placePriceText.setText(String.valueOf(country.getPlaces()[i].getVisitPrice()) + "$");

        return view;
    }

    private static class ViewHolder {
        private ImageView placeImageView;
        private TextView placeNameText, placePriceText;
    }
}

package com.iot.smarthomemasterandroid.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.smarthomemasterandroid.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ElectricAdapter extends RecyclerView.Adapter<ElectricAdapter.MyViewHolder> {


    private List<ElectricData> list;
    private Context context;


    public ElectricAdapter( Context con , List<ElectricData> list) {
        this.list = list;
        this.context = con;
    }

    public void setList(List<ElectricData> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public ElectricAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.electric_meter_item,
                        parent, false);
        return new ElectricAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectricAdapter.MyViewHolder holder, int position) {
        ElectricData electricData = list.get(position);

        holder.timeTxt.setText(electricData.getReadingTime());
        holder.consumptionTxt.setText(electricData.getConsumption());
        holder.readingTxt.setText(electricData.getReading());
        holder.costTxt.setText(electricData.getCost());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView itemView;
        private TextView readingTxt, consumptionTxt,timeTxt,costTxt;

        private MyViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            itemView.setBackgroundResource(R.drawable.border);

            readingTxt = view.findViewById(R.id.txt_reading_id);
            consumptionTxt = view.findViewById(R.id.txt_consumption_id);
            costTxt = view.findViewById(R.id.txt_cost_id);
            timeTxt = view.findViewById(R.id.txt_time);

        }
    }
}


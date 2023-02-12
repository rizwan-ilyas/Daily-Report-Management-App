package com.example.dailyreportmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class reportViewAdapter extends RecyclerView.Adapter<reportViewAdapter.ViewHolder> {
    List<Report> reports;

    public int [] PSP = {
            1,
            150,
            261,
            387,
            520,
            644,
            756,
            905,
            1048,
            1208,
            1336,
            1488,
            1659,
            1817,
            2044,
            2231,
            2502,
            2694,
            2899,
            3244,
            3412,
            3595,
            3761,
            4127,
            4304,
            4554,
            4755,
            5160,
            5306,
            5748,
    };



    public reportViewAdapter(List<Report> rep){
        reports=rep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reports_view,parent,false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtSabak.setText("Sabak : "+String.valueOf(reports.get(position).lines));
        holder.txtSabki.setText("Sabaki : "+String.valueOf(reports.get(position).sabki));
        holder.txtManzal.setText("Manzal : "+String.valueOf(reports.get(position).manzal));
        holder.txtDate.setText(reports.get(position).date);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public int getPara(int l){
        for (int i=0;i<PSP.length;i++){
            if(l<=PSP[i]){
                return i;
            }
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtDate;
        TextView txtSabak;
        TextView txtSabki;
        TextView txtManzal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtSabak=itemView.findViewById(R.id.txtSabak);
            txtSabki=itemView.findViewById(R.id.txtSabki);
            txtManzal=itemView.findViewById(R.id.txtManzal);

        }
    }

}

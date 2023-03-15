package com.example.triolingo_mobile.Units;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolingo_mobile.Model.UnitModel;
import com.example.triolingo_mobile.R;

import java.util.List;

public class UnitAdapter extends RecyclerView.Adapter<UnitHolder>{
    private List<UnitModel> list;
    public UnitAdapter(List<UnitModel> _list) {
        list=_list;
    }

    @NonNull
    @Override
    public UnitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_unit, parent, false);
        UnitHolder viewHolder = new UnitHolder(v, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UnitHolder holder, int position) {
        UnitModel a = list.get(position);
        holder.setView(a);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

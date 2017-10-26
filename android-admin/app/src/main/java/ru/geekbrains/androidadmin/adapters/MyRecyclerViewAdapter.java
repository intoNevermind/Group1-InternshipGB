package ru.geekbrains.androidadmin.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.model.PersonInfo;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<?> list;

    public MyRecyclerViewAdapter(List<?> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object o = list.get(position);
        if (o instanceof PersonInfo){
            PersonInfo pi = (PersonInfo) o;
            holder.tvName.setText(pi.getName());
            holder.tvPopularity.setText(String.valueOf(pi.getPopularity()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPopularity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPopularity = itemView.findViewById(R.id.tv_popularity);
        }
    }
}

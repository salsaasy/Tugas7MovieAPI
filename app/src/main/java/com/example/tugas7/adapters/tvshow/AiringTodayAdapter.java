package com.example.tugas7.adapters.tvshow;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugas7.R;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.tvshow.AiringToday;
import com.example.tugas7.networks.Const;

import java.util.List;

public class AiringTodayAdapter extends RecyclerView.Adapter<AiringTodayAdapter.ViewHolder>{
    private List<AiringToday> airingTodayList;
    private OnItemClickListener<Integer> clickListener;

    public void setAiringTodayList(List<AiringToday> airingTodayList) {
        this.airingTodayList = airingTodayList;
    }

    public void setClickListener(OnItemClickListener<Integer> clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(airingTodayList.get(position));
    }

    @Override
    public int getItemCount() {
        return airingTodayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AiringToday airingToday;
        TextView tvTitle;
        ImageView photo;
        Integer tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tv_title);
            photo = itemView.findViewById(R.id.iv_poster);
        }

        public void onBind(AiringToday airingToday) {
            String uri = Const.IMAGEBASEURL + airingToday.getPosterImage();
            this.airingToday = airingToday;
            tvId = airingToday.getId();
            tvTitle.setText(airingToday.getName());
            Glide.with(this.itemView.getContext()).load(uri).into(photo);
        }

        @Override
        public void onClick(View v) {
            Log.d("DEBUG", tvId.toString());
            clickListener.onClick(tvId);
        }

    }
}

package com.example.triolingo_mobile.Units;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolingo_mobile.Units.LessonHolder;
import com.example.triolingo_mobile.Model.LessonModel;
import com.example.triolingo_mobile.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonHolder>{
    private List<LessonModel> list;
    private RecyclerView recyclerView;
    private boolean leftToRight = true;
    private int itemWidth,firstItemPosition;

    public LessonAdapter(List<LessonModel> _list, RecyclerView _recyclerView) {
        list=_list;
        recyclerView=_recyclerView;
        itemWidth = (int) (recyclerView.getResources().getDisplayMetrics().widthPixels * 0.8);
        firstItemPosition = (recyclerView.getResources().getDisplayMetrics().widthPixels - itemWidth) / 2;
    }

    @NonNull
    @Override
    public LessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lesson, parent, false);
        LessonHolder viewHolder = new LessonHolder(v, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessonHolder holder, int position) {

        LessonModel a = list.get(position);
        holder.setView(a);
        // Nếu là holder đầu tiên, đặt vị trí giữa màn hình
        if (position == 0) {
            holder.itemView.setX((recyclerView.getResources().getDisplayMetrics().widthPixels - itemWidth) / 2);
        } else {
            // Nếu ViewHolder đang hiển thị bên phải ngoài cùng của màn hình
            if (!leftToRight && holder.itemView.getLeft() >= recyclerView.getWidth() - itemWidth) {
                // Di chuyển ViewHolder sang bên trái
                holder.itemView.setTranslationX(-itemWidth);
                // Đặt ViewHolder ở vị trí kế tiếp bên trái
//                notifyItemMoved(position, position - 1);
                // Đặt lại vị trí của ViewHolder
                holder.itemView.setX(firstItemPosition);
                // Giảm biến leftToRight
                leftToRight = true;
            }
            // Nếu ViewHolder đang hiển thị bên trái ngoài cùng của màn hình
            else if (leftToRight && holder.itemView.getRight() <= 0) {
                // Di chuyển ViewHolder sang bên phải
                holder.itemView.setTranslationX(itemWidth);
                // Đặt ViewHolder ở vị trí kế tiếp bên phải
//                notifyItemMoved(position, position + 1);
                // Đặt lại vị trí của ViewHolder
                holder.itemView.setX(firstItemPosition);
                // Tăng biến leftToRight
                leftToRight = false;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

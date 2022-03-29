package com.example.crud_tablayout.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_tablayout.MainActivity;
import com.example.crud_tablayout.R;
import com.example.crud_tablayout.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> mList;
    private CatItemListener itemListener;
    private MainActivity mainActivity;

    public CatAdapter(MainActivity mainActivity) {
        mList = new ArrayList<>();
        this.mainActivity = mainActivity;
    }

    public void setItemListener(CatItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Cat getItem(int position) {
        return mList.get(position);
    }

    public List<Cat> getListCat() {
        return mList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);

        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice() + "");
        holder.infor.setText(cat.getInfor());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan xoa" + cat.getName() + "nay khong?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mList.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        mainActivity.list = mList;
                    }
                });

                builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView name, price, infor;
        Button btnRemove;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.editName);
            price = itemView.findViewById(R.id.editPrice);
            infor = itemView.findViewById(R.id.editDesc);
            btnRemove = itemView.findViewById(R.id.item_btnRemove);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }

    public void add(Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }

    public void update(int postion, Cat cat) {
        mList.set(postion, cat);
        mainActivity.list = mList;
    }
}

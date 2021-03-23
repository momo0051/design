package com.zoolife.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.zoolife.app.R;
import com.zoolife.app.interfaces.OnItemClickListener;
import com.zoolife.app.models.CategoryModel;

import java.util.List;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.MyViewHolder>{

    Context context;
    List<CategoryModel> data;
    private OnItemClickListener listener;





    public SearchCategoryAdapter(Context context, List<CategoryModel> data,OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_search_item, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CategoryModel categoryModel=data.get(position);

        holder.categoryBtn.setText(categoryModel.name);

        holder.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(categoryModel);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton categoryBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryBtn=itemView.findViewById(R.id.catItemTxt);
        }
    }
}

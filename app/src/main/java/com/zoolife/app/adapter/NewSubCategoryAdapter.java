package com.zoolife.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoolife.app.R;
import com.zoolife.app.fragments.HomeFragment;
import com.zoolife.app.models.SubCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class NewSubCategoryAdapter extends RecyclerView.Adapter<NewSubCategoryAdapter.Holder> {

    Context context;
    List<SubCategoryModel> subCategoryList ;
    int row_index = -1;
    HomeFragment homeFragment;
    int cat_id;

    public NewSubCategoryAdapter(Context context, ArrayList<SubCategoryModel> subCategoryList, HomeFragment homeFragment, int cat_id)
    {
        this.context = context;
        this.subCategoryList = subCategoryList;
        this.homeFragment = homeFragment;
        this.cat_id = cat_id;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_subcategory,parent,false);

        return new Holder(searchResultView);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.tvSubCategory.setText(subCategoryList.get(position).getSubCategoryName());

        holder.subCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                homeFragment.getAllPostBySubCategory(cat_id, Integer.parseInt(subCategoryList.get(position).getSubCategoryId()));
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.subCategoryLayout.setSelected(true);
//            holder.subCategoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue_yellow));
//            holder.subCategoryLayout.setBackground(context.getResources().getDrawable(R.drawable.white_round_boder));
//            holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.appColor));
        }
        else
        {
            holder.subCategoryLayout.setSelected(false);
//            holder.subCategoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue));
//            holder.subCategoryLayout.setBackground(context.getResources().getDrawable(R.drawable.blue_round_boder));
//            holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.white));
        }

    }



    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView tvSubCategory;
        LinearLayout subCategoryLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvSubCategory = itemView.findViewById(R.id.tvSubCategory);
            subCategoryLayout = itemView.findViewById(R.id.subCategoryLayout);
        }
    }
}

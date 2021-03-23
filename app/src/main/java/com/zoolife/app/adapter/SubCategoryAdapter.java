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
import com.zoolife.app.models.SubCategoryModel;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.Holder> {

    Context context;
    List<SubCategoryModel> subCategoryList ;
    int row_index = -1;

    public SubCategoryAdapter(Context context,List<SubCategoryModel> subCategoryList)
    {
        this.context = context;
        this.subCategoryList = subCategoryList;
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
                row_index=position;
                notifyDataSetChanged();
            }
        });
//        if(row_index==position){
//            holder.subCategoryLayout.setBackgroundColor(context.getResources().getColor(R.color.bg_welcome_screen_4));
//            holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.white));
//        }
//        else
//        {
//            holder.subCategoryLayout.setBackground(context.getResources().getDrawable(R.drawable.selected_category_attribute));
//            holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.bg_welcome_screen_4));
//        }

    }

    public void changeColor(LinearLayout linearLayout, TextView textView){
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.bg_welcome_screen_4));
        textView.setTextColor(context.getResources().getColor(R.color.white));
    }

    public void resetColor(LinearLayout linearLayout, TextView textView){
        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.selected_category_attribute));
        textView.setTextColor(context.getResources().getColor(R.color.bg_welcome_screen_4));
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

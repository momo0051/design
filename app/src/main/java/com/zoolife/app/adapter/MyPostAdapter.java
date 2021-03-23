package com.zoolife.app.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.NoDataResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.activity.AddAdActivity;
import com.zoolife.app.activity.MyPostsActivity;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.TimeShow;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.MyViewHolder> {
    MyPostsActivity activity;
    List<HomeModel> data;
    ProgressBar activityProgressBar;
    Session session;
    HomeModel current;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public MyPostAdapter(MyPostsActivity activity, List<HomeModel> data, ProgressBar activityProgressBar, Session session) {
        this.activity = activity;
        this.data = data;
        this.activityProgressBar = activityProgressBar;
        this.session = session;

        viewBinderHelper.setOpenOnlyOne(true);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_mypost, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        HomeModel current = data.get(position);

        viewBinderHelper.bind(holder.swipeRevealLayout, current.id);

        holder.setIsRecyclable(true);
        /*if(position % 2 == 0){
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white_smoke));

        }else{
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/
        holder.itemTitle.setText(current.title);
        TimeShow timeShow = new TimeShow();
        holder.itemPOstedDate.setText(timeShow.covertTimeToText(activity, current.postedDate));
        holder.itemPostedBy.setText(current.username);
        holder.itemLocation.setText(current.location);

        RequestOptions requestOptions = new RequestOptions();
        Resources r = activity.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                18,
                r.getDisplayMetrics()
        );
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners((int) px));

        Glide.with(activity)
                .load(current.image)
                .centerCrop()
                .apply(requestOptions)
                .placeholder(R.drawable.placeholder)
                .into(holder.itemImage);

        holder.singleCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
//                    Intent intent = new Intent(context, AddDetailsActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("id", current.id);
//                    intent.putExtra("from", "Home");
//                    context.startActivity(intent);

                    Intent intent = new Intent(activity, AddAdActivity.class);
                    intent.putExtra("edit", true);
                    intent.putExtra("editId", current.id);
                    intent.putExtra("priority", current.priority);

                    activity.position = position;

                    activity.startActivityForResult(intent, MyPostsActivity.EDIT_POST_REQUEST_CODE);
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteApi(current.id);

                data.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SwipeRevealLayout swipeRevealLayout;
        ImageView itemImage, itemFavImage;
        TextView itemTitle, itemPOstedDate, itemPostedBy, itemLocation, delete;
        LinearLayout singleCLick;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            singleCLick = itemView.findViewById(R.id.single_click);
            itemImage = itemView.findViewById(R.id.item_image);
            itemFavImage = itemView.findViewById(R.id.item_fav_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemPOstedDate = itemView.findViewById(R.id.item_posted_on);
            itemPostedBy = itemView.findViewById(R.id.item_user_postedby);
            itemLocation = itemView.findViewById(R.id.item_location);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteApi(String id) {
        ApiService apiService = ApiClient.getClient(activity).create(ApiService.class);
        Call<NoDataResponseModel> call = apiService.deleteItem(session.getUserId(), id);
        call.enqueue(new Callback<NoDataResponseModel>() {
            @Override
            public void onResponse(Call<NoDataResponseModel> call, Response<NoDataResponseModel> response) {
                NoDataResponseModel responseModel = response.body();
                activityProgressBar.setVisibility(View.GONE);
                Toast.makeText(activity, responseModel.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NoDataResponseModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                activityProgressBar.setVisibility(View.GONE);
            }
        });
    }
}

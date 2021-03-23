package com.zoolife.app.fragments;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabLayout;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.DataItem;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.ResponseModel.SearchPost.SearchResponseModel;
import com.zoolife.app.ResponseModel.SubCategory.SubCategoryResponseModel;
import com.zoolife.app.SortedPostActivity;
import com.zoolife.app.activity.AddAdActivity;
import com.zoolife.app.activity.AppBaseActivity;
import com.zoolife.app.activity.FavouriteActivity;
import com.zoolife.app.activity.ImageDetailActivity;
import com.zoolife.app.activity.LoginActivity;
import com.zoolife.app.activity.MainActivity;
import com.zoolife.app.adapter.AdSliderAdapter;
import com.zoolife.app.adapter.CategoryAdapter;
import com.zoolife.app.adapter.HomeAdapter;
import com.zoolife.app.adapter.NewSubCategoryAdapter;
import com.zoolife.app.adapter.SubCategoryAdapter;
import com.zoolife.app.models.CategoryModel;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.models.ImageData;
import com.zoolife.app.models.SliderModel;
import com.zoolife.app.models.SubCategoryModel;
import com.zoolife.app.models.related_ad_home.Datum;
import com.zoolife.app.models.related_ad_home.RelatedAdHomeModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.ItemOffsetDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zoolife.app.activity.AppBaseActivity.categories;
import static com.zoolife.app.activity.AppBaseActivity.session;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressBar progress_circular;

    ImageView searchPost;

    FrameLayout searchingIcon;

    HomeAdapter homeAdapter;
    CategoryAdapter categoryAdapter;
    List<HomeModel> homeModelList;
    List<SubCategoryModel> subCategoryList;
    List<Datum> relatedAdsList;
    RecyclerView recyclerView, category_rv;
    ViewPager adSliderViewPager;
    AdSliderAdapter adSliderAdapter;
    List<ImageData> items = new ArrayList<>();
    TabLayout tabLayout, tabLayout2;
    SubCategoryAdapter subCategoryAdapter;
    NewSubCategoryAdapter newSubCategoryAdapter;
    RelativeLayout citiesCv;
    Spinner spinner;
    EditText searchET;
    RecyclerView subCategoryRecyclerView, newSubCategoryRecyclerView;
    LinearLayout linSubCategory, dotsLayout;
    String[] cities = {"", "كل المدن", "الرياض", "الشرقية", "جدة", "مكة", "ينبع", "حفر الباطن", "المدينة", "الطائف", "تبوك", "القصيم", "حائل", "ابها", "الباحة", "جيزان", "نجران", "الجوف", "عرعر", "الكويت", "الأمارات", "البحرين"};
    ImageSlider imageSlider;
    private static Retrofit retrofit = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);




        //forceRTLIfSupported();


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_data_rv);
        imageSlider = view.findViewById(R.id.slider);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(2, spacingInPixels, true, 0);
        recyclerView.addItemDecoration(itemDecoration);

//        searchET = view.findViewById(R.id.searchET);
        searchingIcon = view.findViewById(R.id.searching_icon);
        searchingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SortedPostActivity.class);
                intent.putExtra("activity", "home");
                startActivity(intent);
            }
        });

        searchPost = view.findViewById(R.id.search_post);
        searchPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FavouriteActivity.class);
                startActivity(intent);

//                Fragment someFragment = new FavouriteFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_container, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });

        category_rv = view.findViewById(R.id.category_rv);
//        citiesCv = view.findViewById(R.id.citiesCv);
        linSubCategory = view.findViewById(R.id.linSubCategory);
        //  subCategoryRecyclerView = view.findViewById(R.id.subCategoryRecyclerView);
        newSubCategoryRecyclerView = view.findViewById(R.id.new_sub_category_rv);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);

        getCategory();
        getAllPost();
        //getSubCategory(1);
        getRelatedAdds();

        getSliderData();

        initMe(view);
        newSubCategoryRecyclerView.setVisibility(View.GONE);


        view.findViewById(R.id.add_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!session.isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), AddAdActivity.class));

                }
            }
        });

        ViewPager viewPager = view.findViewById(R.id.view_pager);
//        viewPager.setBackgroundResource(R.drawable.ripple_effect_white_bg);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
        lp.bottomMargin = 100;
        viewPager.setLayoutParams(lp);


        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        Objects.requireNonNull(getActivity()).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    private void initMe(View view) {

        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!searchET.getText().toString().isEmpty()) {
                    getSearch(searchET.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "Please Enter something to search", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public List<ImageData> getAdSliderData() {
        List<ImageData> items = new ArrayList<>();

        ImageData item = new ImageData(R.drawable.ic_item1);
        items.add(item);

        item = new ImageData(R.drawable.ic_item3);
        items.add(item);

        item = new ImageData(R.drawable.ic_item4);
        items.add(item);

        return items;
    }


    public void getAllPost() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllPost();
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    Log.d(TAG, "getallpost: " + response.body().getData().toString());
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    for (int i = 0; i < responseModel.getData().size(); i++) {
                        DataItem dataItem = responseModel.getData().get(i);
                        arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));
                    }

                    if (arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList, session);
                        recyclerView.setAdapter(homeAdapter);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
                    }

                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    public void getAllPostByCategory(int cat_id) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllItembyCategory(cat_id, session.getCity(), 1, 0);
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    Log.d(TAG, "getallpost: " + response.body().getData().toString());
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    for (int i = 0; i < responseModel.getData().size(); i++) {
                        if (responseModel.getData().get(i).getCategory() == cat_id) {
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));
                        }
                    }

                    if (arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList,session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                } else {
                    // infoDialog("Server Error.");

                    recyclerView.removeAllViews();
                    Toast.makeText(getContext(), "No Ad for this category", Toast.LENGTH_SHORT).show();
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    public void getAllPostBySubCategory(int cat_id, int sub_cat_id) {
        progress_circular.setVisibility(View.VISIBLE);
        Log.d(TAG, "getAllPostBySubCategory: " + cat_id + " " + sub_cat_id);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllPost();
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    Log.d(TAG, "getallpost: " + response.body().getData().toString());
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    try {
                        for (int i = 0; i < responseModel.getData().size(); i++) {
                            if (responseModel.getData().get(i).getCategory() == cat_id) {
                                if (responseModel.getData().get(i).getSubCategory() == sub_cat_id) {
                                    DataItem dataItem = responseModel.getData().get(i);
                                    arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));

                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("TAG", "Exception at List Fetch " + e.getMessage());
                    }

                    if (arrayList.size() > 0) {







                        homeAdapter = new HomeAdapter(getActivity(), arrayList,session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    } else {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList,session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        homeAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "No Ad for this category", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    // infoDialog("Server Error.");

                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getCategory() {
        progress_circular.setVisibility(View.VISIBLE);
        Log.e("TAG", "Get Category Category called");
//        ApiService apiService = ApiClient.getClientZoo().create(ApiService.class);
        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<CategoryResponseModel> call = apiService.getCategory();
        call.enqueue(new Callback<CategoryResponseModel>() {
            @Override
            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {
                CategoryResponseModel responseModel = response.body();
                categories = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<CategoryModel> arrayList = new ArrayList<>();

                    for (int i = 0; i < responseModel.getData().size(); i++) {

                        com.zoolife.app.ResponseModel.Category.DataItem categoryModel = responseModel.getData().get(i);
                        arrayList.add(new CategoryModel(categoryModel.getTitle(), categoryModel.getImgUnSelected(), String.valueOf(categoryModel.getId())));
                    }


                    Collections.reverse(arrayList);

                    categoryAdapter = new CategoryAdapter(getActivity(), arrayList, HomeFragment.this);
                    category_rv.setAdapter(categoryAdapter);
                    category_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));


                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getSearch(String searchText) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<SearchResponseModel> call = apiService.itemSearch(session.getUserId(), searchText);
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                SearchResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    if (responseModel.getData() != null) {
                        for (int i = 0; i < responseModel.getData().size(); i++) {

                            com.zoolife.app.ResponseModel.SearchPost.DataItem searchResponseModel = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(searchResponseModel.getItemTitle(), searchResponseModel.getCreateAt(), searchResponseModel.getCity(), searchResponseModel.getFromUserId(), searchResponseModel.getImgUrl(), "0", searchResponseModel.getPriority()));
                        }
                    } else {
                        Toast.makeText(getActivity(), "No Record Found!", Toast.LENGTH_LONG).show();
                    }

                    homeAdapter = new HomeAdapter(getActivity(), arrayList,session);
                    recyclerView.setAdapter(homeAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
                    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
                    ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(2, spacingInPixels, true, 0);
                    recyclerView.addItemDecoration(itemDecoration);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    public void getSubCategory(int cat_id) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<SubCategoryResponseModel> call = apiService.getSubCategory(cat_id);
        call.enqueue(new Callback<SubCategoryResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryResponseModel> call, Response<SubCategoryResponseModel> response) {
                SubCategoryResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<SubCategoryModel> arrayList = new ArrayList<>();

                    if (responseModel.getData() != null) {
                        for (int i = 0; i < responseModel.getData().size(); i++) {
                            arrayList.add(new SubCategoryModel(responseModel.getData().get(i).getTitle(), responseModel.getData().get(i).getId()));

                        }
                    } else {
                        Toast.makeText(getActivity(), "No Record Found!", Toast.LENGTH_LONG).show();
                    }

                    if (arrayList.size() > 0) {
                        newSubCategoryRecyclerView.setVisibility(View.VISIBLE);
                        newSubCategoryAdapter = new NewSubCategoryAdapter(getActivity(), arrayList, HomeFragment.this, cat_id);
                        newSubCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        newSubCategoryRecyclerView.setAdapter(newSubCategoryAdapter);

                    } else {

                        newSubCategoryRecyclerView.setVisibility(View.GONE);
                    }


                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<SubCategoryResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }


    /*public Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor); // read timeout

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES) // read timeout
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL5)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }*/


    private void getRelatedAdds() {
        try {
            progress_circular.setVisibility(View.VISIBLE);

            ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);


            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);


            builder.addFormDataPart("pass", "all_sliders");


            MultipartBody requestBody = builder.build();

            relatedAdsList = new ArrayList<>();
            Call<RelatedAdHomeModel> call = apiService.sliders("all_sliders");
            call.enqueue(new Callback<RelatedAdHomeModel>() {
                @Override
                public void onResponse(Call<RelatedAdHomeModel> call, Response<RelatedAdHomeModel> response) {
                    RelatedAdHomeModel responseModel = response.body();
                    if (responseModel != null && !responseModel.getError()) {
                        progress_circular.setVisibility(View.GONE);
//                        Toast.makeText(getContext(), responseModel.getMessage(), Toast.LENGTH_LONG).show();
//                        finish();
//                        relatedAdsList.addAll(responseModel.getData());
                        List<SlideModel> slideModels = new ArrayList<>();

                        for (int j = 0; j < responseModel.getData().size(); j++) {
                            slideModels.add(new SlideModel(responseModel.getData().get(j).getImage1()));
                        }
                        imageSlider.setImageList(slideModels, true);


                        imageSlider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Intent intent=new Intent(getContext(), ImageDetailActivity.class);
                                intent.putExtra("image_detail",responseModel.getData().get(i));

                                startActivity(intent);
                            }
                        });


                    } else {
                        // infoDialog("Server Error.");
                        progress_circular.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<RelatedAdHomeModel> call, Throwable t) {
                    t.printStackTrace();
                    String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    progress_circular.setVisibility(View.GONE);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getSliderData(){




        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<SliderModel> call = apiService.getSliders("all_sliders");

        call.enqueue(new Callback<SliderModel>() {
            @Override
            public void onResponse(Call<SliderModel> call, Response<SliderModel> response) {

                if(response.isSuccessful() && response.body()!=null){

                    Log.i("ashfbweg","hey "+response.body().toString());
                } else {
                    Log.i("ashfbweg","pey ");
                }
            }

            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {

                Log.i("ashfbweg","ley "+t.getMessage());

            }
        });




    }

}
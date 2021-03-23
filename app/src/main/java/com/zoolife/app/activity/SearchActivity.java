package com.zoolife.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.DataItem;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.ResponseModel.CityNameResponseModel.CityNameResponseModel;
import com.zoolife.app.ResponseModel.SubCategory.SubCategoryResponseModel;
import com.zoolife.app.SortedPostActivity;
import com.zoolife.app.adapter.HomeAdapter;
import com.zoolife.app.adapter.SearchCategoryAdapter;
import com.zoolife.app.interfaces.OnItemClickListener;
import com.zoolife.app.models.CategoryModel;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.models.SubCategoryModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.ItemOffsetDecoration;
import com.zoolife.app.utility.LocaleHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppBaseActivity implements OnItemClickListener {

    ImageView btnSearchBack;
    RelativeLayout dropdown_btn;
    ProgressBar progress_circular;


    String[] cities = new String[]{};
    String[] mainCategory;
    String[] subCategory;

    HomeAdapter homeAdapter;
    RecyclerView recyclerView;

    List<HomeModel> dataList;
    List<HomeModel> dataListFiltered;
    private EditText etSearch;
    private LinearLayout llSpinner;
    private RecyclerView searchCategoryRv;
    private SearchCategoryAdapter searchCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);


        dropdown_btn = findViewById(R.id.dropdown_btn);
        llSpinner = findViewById(R.id.ll_spinners);
        recyclerView = findViewById(R.id.search_recyclerview);
        progress_circular = findViewById(R.id.search_pd);
        etSearch = findViewById(R.id.et_search);


        searchCategoryRv=findViewById(R.id.searchCategoryRv);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(2, spacingInPixels, true, 0);
        recyclerView.addItemDecoration(itemDecoration);
        getAllCityNames();
        getCategory();

        getAllPost();

        dataListFiltered = new ArrayList<>();
        dataListFiltered.clear();
        etSearch.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0) {
                    llSpinner.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

//                    dataListFiltered = dataList.forEach(articlesModel -> );
                    if (dataList.size() > 0) {
                        dataListFiltered.clear();
                        for (int m = 0; m < dataList.size(); m++) {
                            if (dataList.get(m).title.toLowerCase().contains(s.toString().toLowerCase())) {
                                dataListFiltered.add(dataList.get(m));
                            }
                        }
                        if (dataListFiltered.size() > 0) {
                            homeAdapter = new HomeAdapter(SearchActivity.this, dataListFiltered,session);
                            recyclerView.setAdapter(homeAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                            homeAdapter.notifyDataSetChanged();


                        } else {
                            homeAdapter = new HomeAdapter(SearchActivity.this, dataList,session);
                            recyclerView.setAdapter(homeAdapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2, LinearLayoutManager.VERTICAL, false));

                            homeAdapter.notifyDataSetChanged();

//                            articlesAdapter.notifyDataSetChanged();
//                        Toast.makeText(this,"Nothing found",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        dataListFiltered.clear();

                    }
//                    field2.setText("");
                } else {
                    dataListFiltered.clear();
                    homeAdapter = new HomeAdapter(SearchActivity.this, dataList,session);
                    recyclerView.setAdapter(homeAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2, LinearLayoutManager.VERTICAL, false));
                    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
                    ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(2, spacingInPixels, true, 0);
                    recyclerView.addItemDecoration(itemDecoration);

                    homeAdapter.notifyDataSetChanged();

                    llSpinner.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        btnSearchBack = (ImageView) findViewById(R.id.btnSearchBack);

        btnSearchBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }


    private void getCategory() {

//        ApiService apiService = ApiClient.getClientZoo().create(ApiService.class);
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<CategoryResponseModel> call = apiService.getCategory();
        call.enqueue(new Callback<CategoryResponseModel>() {
            @Override
            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {
                CategoryResponseModel responseModel = response.body();
                categories = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    ArrayList<CategoryModel> arrayList = new ArrayList<>();

                    mainCategory = new String[responseModel.getData().size() + 1];
                    mainCategory[0] = "اختر الفئة";

                    for (int i = 0; i < responseModel.getData().size(); i++) {

                        com.zoolife.app.ResponseModel.Category.DataItem categoryModel = responseModel.getData().get(i);
                        arrayList.add(new CategoryModel(categoryModel.getTitle(), categoryModel.getImgUnSelected(), String.valueOf(categoryModel.getId())));
                        mainCategory[i + 1] = categoryModel.getTitle();

                    }

                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa2 = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_spinner_item, mainCategory);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner


                    searchCategoryAdapter=new SearchCategoryAdapter(SearchActivity.this,arrayList,SearchActivity.this::onItemClick);

                    searchCategoryRv.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                    searchCategoryRv.setAdapter(searchCategoryAdapter);






//                    Collections.reverse(arrayList);


//                    categoryAdapter = new CategoryAdapter(getActivity(),arrayList, HomeFragment.this);
//                    category_rv.setAdapter(categoryAdapter);
//                    category_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));


                } else {
                    // infoDialog("Server Error.");
//                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getAllCityNames() {

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<CityNameResponseModel> call = apiService.getAllCityNames();
        call.enqueue(new Callback<CityNameResponseModel>() {
            @Override
            public void onResponse(Call<CityNameResponseModel> call, Response<CityNameResponseModel> response) {
                CityNameResponseModel responseModel = response.body();
                cities = new String[responseModel.getData().size()];
                for (int i = 0; i < responseModel.getData().size(); i++) {
                    cities[i] = responseModel.getData().get(i).getName();
                }

            }

            @Override
            public void onFailure(Call<CityNameResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getSubCategory(int cat_id) {

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<SubCategoryResponseModel> call = apiService.getSubCategory(cat_id);
        call.enqueue(new Callback<SubCategoryResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryResponseModel> call, Response<SubCategoryResponseModel> response) {
                SubCategoryResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    ArrayList<SubCategoryModel> arrayList = new ArrayList<>();
                    subCategory = new String[responseModel.getData().size() + 1];
                    subCategory[0] = "حدد فئة فرعية";

                    if (responseModel.getData() != null) {
                        for (int i = 0; i < responseModel.getData().size(); i++) {
                            arrayList.add(new SubCategoryModel(responseModel.getData().get(i).getTitle(), responseModel.getData().get(i).getId()));
                            subCategory[i + 1] = responseModel.getData().get(i).getTitle();
                        }

                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa3 = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_spinner_item, subCategory);
                        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner

                    } else {
                        Toast.makeText(getApplicationContext(), "No Record Found!", Toast.LENGTH_LONG).show();
                    }


                }

            }

            @Override
            public void onFailure(Call<SubCategoryResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAllPost(int cat, int sub_cat) {

        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllPost();
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {


                    ArrayList<HomeModel> arrayList = new ArrayList<>();


                    progress_circular.setVisibility(View.GONE);




//

                    if (arrayList.size() > 0) {
//                        homeAdapter = new HomeAdapter(getApplicationContext(), arrayList);
//                        recyclerView.setAdapter(homeAdapter);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        Intent intent = new Intent(getApplicationContext(), SortedPostActivity.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", arrayList);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra("activity", "search");
                        startActivity(intent);
                    } else {
                        Toast.makeText(SearchActivity.this, "No Post found!", Toast.LENGTH_SHORT).show();
//                        homeAdapter.notifyDataSetChanged();
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
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    public void getAllPost() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllPost();
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {

                try {

                    AllPostResponseModel responseModel = response.body();
                    if (responseModel != null && !responseModel.isError()) {

                        Log.d("SEARCH_TAG", "getallpost: " + response.body().getData().toString());
                        progress_circular.setVisibility(View.GONE);

                        ArrayList<HomeModel> arrayList = new ArrayList<>();

                        for (int i = 0; i < responseModel.getData().size(); i++) {
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));
                        }

                        if (arrayList.size() > 0) {
                            dataList = arrayList;
                            homeAdapter = new HomeAdapter(SearchActivity.this, arrayList,session);
                            recyclerView.setAdapter(homeAdapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2, LinearLayoutManager.VERTICAL, false));
                        }

                    } else {
                        // infoDialog("Server Error.");
                        progress_circular.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onItemClick(CategoryModel categoryModel) {

        int cat_id=Integer.parseInt(categoryModel.id);



        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(SearchActivity.this).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllItembyCategory(cat_id, session.getCity(), 1, 0);
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {

                    progress_circular.setVisibility(View.GONE);
                    llSpinner.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    for (int i = 0; i < responseModel.getData().size(); i++) {
                        if (responseModel.getData().get(i).getCategory() == cat_id) {
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));
                        }
                    }

                    if (arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(SearchActivity.this, arrayList,session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        homeAdapter.notifyDataSetChanged();
                        Log.i("askjdvweavd",":dffww");
                    } else {
                        Log.i("askjdvweavd",":ppp");
                        homeAdapter = new HomeAdapter(SearchActivity.this, new ArrayList<>(),session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        homeAdapter.notifyDataSetChanged();

                    }

                } else {
                    // infoDialog("Server Error.");

                    recyclerView.removeAllViews();
                    Toast.makeText(SearchActivity.this, "No Ad for this category", Toast.LENGTH_SHORT).show();
                    progress_circular.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });

    }
}
package com.zoolife.app.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zoolife.app.R;
import com.zoolife.app.adapter.NotificationAdapter;
import com.zoolife.app.models.notification.NotificationModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zoolife.app.activity.AppBaseActivity.session;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    TextView textNotif;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressBar progress_circular;

    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    List<NotificationModel> notificationModelList;
    private static Retrofit retrofit = null ;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        progress_circular = view.findViewById(R.id.progress_circular);
        recyclerView = view.findViewById(R.id.notification_rv);
        textNotif = view.findViewById(R.id.textNotif);
//        notificationAdapter = new NotificationAdapter(getActivity(),populateDate());
//        recyclerView.setAdapter(notificationAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        notificationAdapter.notifyDataSetChanged();

        //forceRTLIfSupported();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getNotifications(session.getEmail());
    }


   /* public Retrofit getClient(){
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


    public void getNotifications(String username) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
        Call<NotificationModel> call = apiService.getAllNotify(session.getUserId());
        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                NotificationModel responseModel = response.body();
                if (responseModel!=null ) {
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<com.zoolife.app.models.NotificationModel> arrayList = new ArrayList<>();

                    for(int i=0 ;i<responseModel.getData().size() ; i++)
                    {
//                        if(responseModel.getData().get(i).getItem()!=null && responseModel.getData().get(i).getItem().size()>0){
                            arrayList.add(new com.zoolife.app.models.NotificationModel(responseModel.getData().get(i).getContent(),responseModel.getData().get(i).getCreatedAt(),String.valueOf(responseModel.getData().get(i).getAdsId())));
//                        }
                    }
                    if(arrayList.size()>0) {
                        notificationAdapter = new NotificationAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(notificationAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        notificationAdapter.notifyDataSetChanged();
                        textNotif.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        textNotif.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
               // Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void forceRTLIfSupported()
    {
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
}
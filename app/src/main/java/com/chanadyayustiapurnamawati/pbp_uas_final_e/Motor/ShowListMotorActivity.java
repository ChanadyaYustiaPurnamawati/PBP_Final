package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowListMotorActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private MotorRecyclerAdapter recyclerAdapter;
    private List<motorDAO> user = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_motorcycle);
        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        searchView = findViewById(R.id.searchMotor);
        swipeRefresh = findViewById(R.id.swipeRefreshMotor);
        swipeRefresh.setRefreshing(true);
        loadUser();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUser();
            }
        });
    }
    public void loadUser() {
        ApiInterfaceMotor apiService = ApiClient.getClient().create(ApiInterfaceMotor.class);
        Call<MotorResponse> call = apiService.getAllUser("data");
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                generateDataList(response.body().getUsers());
                swipeRefresh.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(ShowListMotorActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }
    private void generateDataList(List<motorDAO> motorList) {
        recyclerView = findViewById(R.id.motorRecyclerView);
        recyclerAdapter = new MotorRecyclerAdapter(this, motorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowListMotorActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String queryString) {
//                recyclerAdapter.getFilter().filter(queryString);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String queryString) {
//                recyclerAdapter.getFilter().filter(queryString);
//                return false;
//            }
//        });
    }
}

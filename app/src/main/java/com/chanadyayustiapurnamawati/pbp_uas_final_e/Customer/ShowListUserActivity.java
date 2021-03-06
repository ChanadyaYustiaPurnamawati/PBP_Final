package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.os.Bundle;
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

public class ShowListUserActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private UserRecyclerAdapter recyclerAdapter;
    private List<userDAOCustomer> user = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefresh;
    private EditUserActivity editUserActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        ibBack = findViewById(R.id.ibBack);
//        this.editUserActivity = new EditUserActivity(this);
        searchView = findViewById(R.id.searchUser);
        swipeRefresh = findViewById(R.id.swipeRefresh);
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
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiService.getAllUser("data");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                generateDataList(response.body().getUsers());
                swipeRefresh.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ShowListUserActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }
    private void generateDataList(List<userDAOCustomer> customerList) {
        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerAdapter = new UserRecyclerAdapter(this,customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowListUserActivity.this);
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

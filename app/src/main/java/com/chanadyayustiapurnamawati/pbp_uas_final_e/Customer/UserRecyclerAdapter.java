package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<userDAOCustomer> dataList;
    private List<userDAOCustomer> filteredDataList;
    private Context context;
    public UserRecyclerAdapter( Context context,List<userDAOCustomer> dataList) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()) {
                    filteredDataList = dataList;
                } else {
                    List<userDAOCustomer> filteredList = new ArrayList<>();
                    for (userDAOCustomer userDAOCustomer : dataList) {
                        if(userDAOCustomer.getNama().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(userDAOCustomer);
                        }
                        filteredDataList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataList = (List<userDAOCustomer>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_adapter_customer, parent, false);
        return new RoomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.RoomViewHolder holder, int position) {
        final userDAOCustomer brg = filteredDataList.get(position);
        holder.twNama.setText(brg.getNama());
       // holder.twNim.setText(brg.getNim());
        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailUserFragment dialog = new DetailUserFragment();
                dialog.show(manager, "dialog");
                Bundle args = new Bundle();
                args.putString("id", brg.getId());
                dialog.setArguments(args);
            }
        });
    }
    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }
    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twNama, twNim;
        private LinearLayout mParent;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            twNama = itemView.findViewById(R.id.tvNama);
          //  twNim = itemView.findViewById(R.id.twNim);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }

}
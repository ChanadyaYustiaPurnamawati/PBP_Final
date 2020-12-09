package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

import java.util.ArrayList;
import java.util.List;

public class MotorRecyclerAdapter extends RecyclerView.Adapter<MotorRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<motorDAO> dataList;
    private List<motorDAO> filteredDataList;
    private Context context;

    public MotorRecyclerAdapter( Context context,List<motorDAO> dataList) {
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
                    List<motorDAO> filteredList = new ArrayList<>();
                    for (motorDAO motorDAO : dataList) {
                        if(motorDAO.getMerk().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(motorDAO);
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
                filteredDataList = (List<motorDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @NonNull
    @Override
    public MotorRecyclerAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_motorcycle, parent, false);
        return new MotorRecyclerAdapter.RoomViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MotorRecyclerAdapter.RoomViewHolder holder, int position) {
        final motorDAO brg = filteredDataList.get(position);
        holder.tvMerk.setText(brg.getMerk());
        // holder.twNim.setText(brg.getNim());
        Glide.with(holder.foto.getContext())
                .load(brg.getFoto())
                .into(holder.foto);
//        holder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
//                DetailUserFragment dialog = new DetailUserFragment();
//                dialog.show(manager, "dialog");
//                Bundle args = new Bundle();
//                args.putString("id", brg.getId());
//                dialog.setArguments(args);
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }
    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMerk, twNim;
        private ImageView foto;
        private LinearLayout mParent;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMerk = itemView.findViewById(R.id.tvMerk);
            foto = itemView.findViewById(R.id.image_view);
            //  twNim = itemView.findViewById(R.id.twNim);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}

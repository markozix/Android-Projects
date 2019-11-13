package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.homework4.MainActivity;
import com.example.homework4.MoreInfoActivity;
import com.example.homework4.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import model.Lokacija;
import util.LokacijaDiffCallback;

public class LokacijaAdapter extends RecyclerView.Adapter<LokacijaAdapter.LokacijaHolder> {

    private List<Lokacija> mDataSet;
    private OnItemClickedCallback monItemClicked;

    public LokacijaAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public LokacijaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);

        return new LokacijaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LokacijaHolder holder, int position) {

        Lokacija lok = mDataSet.get(position);

        holder.tv_locationName.setText(lok.getLocationName());
        holder.tv_date.setText(lok.getDate());
        holder.tv_description.setText(lok.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Lokacija> lokacije){

        LokacijaDiffCallback callback = new LokacijaDiffCallback(mDataSet,lokacije);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(lokacije);
        result.dispatchUpdatesTo(this);
    }


    public class LokacijaHolder extends RecyclerView.ViewHolder{

        TextView tv_locationName;
        TextView tv_date;
        TextView tv_description;
        Button btnMore;

        public LokacijaHolder(@NonNull View itemView) {
            super(itemView);

            tv_locationName = itemView.findViewById(R.id.locationName);
            tv_date = itemView.findViewById(R.id.date);
            tv_description = itemView.findViewById(R.id.description);
            btnMore = itemView.findViewById(R.id.btnMore);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(monItemClicked != null){
                            monItemClicked.onItemClicked(position);
                        }
                    }
                }
            });

        }
    }





    public void setOnItemClickedCallback(OnItemClickedCallback onItemClickedCallback){
        monItemClicked = onItemClickedCallback;
    }

    public interface OnItemClickedCallback{
        void onItemClicked(int position);
    }
}

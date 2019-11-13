package adapteri;

import android.app.Activity;
import android.content.Intent;
import android.os.DeadObjectException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekat1.DetalisActivity;
import com.example.projekat1.MainActivity;
import com.example.projekat1.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CallBack.TrosakDiffCallBack;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import fragmenti.FirstFragment;
import fragmenti.SecondFragment;
import model.Trosak;

public class TrosakAdapter extends RecyclerView.Adapter<TrosakAdapter.TrosakHolder> {

    List<Trosak> mDataSet;
    private OnItemRemoveCallback mOnItemRemoveCallback;
    private OnDetailsClicked mOnDetailsClcked;
    private AdapterView.OnItemClickListener mItemClicked;


    public TrosakAdapter(){
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public TrosakHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new TrosakHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull TrosakHolder holder, int position) {
        Trosak trosak = mDataSet.get(position);

        holder.name.setText(trosak.getName());
        holder.category.setText(trosak.getCategory());
        String str = Integer.toString(trosak.getCost());
        holder.cost.setText(str);
        holder.date.setText(trosak.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void setData(List<Trosak> troskovi){
        TrosakDiffCallBack callback = new TrosakDiffCallBack(mDataSet, troskovi);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(troskovi);
        result.dispatchUpdatesTo(this);
    }

    public class TrosakHolder extends RecyclerView.ViewHolder {


       TextView name;
       TextView cost;
       TextView category;
       TextView date;
       Button btnRemove;
       Button btnDetails;

        public TrosakHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameLabel);
            cost = itemView.findViewById(R.id.costLabel);
            category = itemView.findViewById(R.id.categoryLabel);
            date = itemView.findViewById(R.id.dateLabel);

            btnRemove = itemView.findViewById(R.id.btnRemove);
            btnDetails = itemView.findViewById(R.id.btnDetails);

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        if (mOnItemRemoveCallback != null) {
                            mOnItemRemoveCallback.onItemRemove(position);
                        }
                    }
                }
            });

            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Trosak trosak = mDataSet.get(position);
                    if (position != RecyclerView.NO_POSITION){
                        if (mOnDetailsClcked != null) {
                            mOnDetailsClcked.onDetailsClicked(trosak,position);
                        }
                    }

                }
            });



        }
    }

    public void removeItem(int position){
        mDataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDataSet.size());

    }



    public void setOnItemRemoveCallback (OnItemRemoveCallback onItemRemoveCallback) {
        mOnItemRemoveCallback = onItemRemoveCallback;
    }


    public interface OnItemRemoveCallback {
        void onItemRemove(int position);
    }

    public interface OnDetailsClicked{
        void onDetailsClicked(Trosak trosak, int position);
    }

    public void setOnDetailsClicked(OnDetailsClicked onDetailsClicked){
        mOnDetailsClcked = onDetailsClicked;
    }


}

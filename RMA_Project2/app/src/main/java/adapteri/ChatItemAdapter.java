package adapteri;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rma_project2.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import model.ChatItem;
import repository.db.entity.RasporedEntity;
import util.ChatDiffCallBack;
import util.RasporedDiffCallBack;

public class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ItemHolder> {

    List<ChatItem> mDataSet;
    private OnItemClickedCallback monItemClicked;

    public ChatItemAdapter(){
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_fr2,parent,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        ChatItem item = mDataSet.get(position);

        holder.img.setImageResource(R.drawable.logo2);
        holder.name.setText(item.getName());



    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<ChatItem> items){
        ChatDiffCallBack callback = new ChatDiffCallBack(mDataSet, items);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(items);
        result.dispatchUpdatesTo(this);
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView name;

        ImageView img;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);



            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!= RecyclerView.NO_POSITION){
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

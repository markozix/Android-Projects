package adapteri;

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
import model.ChatMessage;
import util.ChatDiffCallBack;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ItemHolder> {

    List<ChatMessage> mDataSet;


    public ChatAdapter(){
        mDataSet = new ArrayList<>();
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.msg1,parent,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        ChatMessage msg = mDataSet.get(position);
        holder.date.setText(msg.getDate());
        holder.msg.setText(msg.getMsg());

    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<ChatItem> items){
        ChatMsgDiffCallBack callback = new ChatMsgDiffCallBack(mDataSet, items);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(items);
        result.dispatchUpdatesTo(this);
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView date;

        TextView msg;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);



            msg = itemView.findViewById(R.id.tv_msg1);
            date = itemView.findViewById(R.id.tv_date);




        }



    }

}

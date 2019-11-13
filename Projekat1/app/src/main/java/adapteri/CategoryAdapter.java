package adapteri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projekat1.R;

import java.util.ArrayList;
import java.util.List;

import CallBack.ListaCallBack;
import CallBack.TrosakDiffCallBack;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import model.Trosak;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    List<String> mDataSet;


    public CategoryAdapter(){
        mDataSet = new ArrayList<>();
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_category, parent, false);
        return new CategoryHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        String category = mDataSet.get(position);

        holder.name.setText(category);



    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void setData(List<String> kategorije){
        ListaCallBack callback = new ListaCallBack(mDataSet, kategorije);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(kategorije);
        result.dispatchUpdatesTo(this);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {


        TextView name;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.labelName);

        }
    }






}

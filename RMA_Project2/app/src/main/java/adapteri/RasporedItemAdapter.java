package adapteri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rma_project2.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import model.Raspored;
import repository.db.entity.RasporedEntity;
import util.RasporedDiffCallBack;

public class RasporedItemAdapter extends RecyclerView.Adapter<RasporedItemAdapter.ItemHolder> {


    List<RasporedEntity> mDataSet;


    public RasporedItemAdapter(){
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public RasporedItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);

        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {


        RasporedEntity raspored = mDataSet.get(position);

        holder.dan.setText(raspored.getDan());
        holder.predmet.setText(raspored.getPredmet());
        holder.termin.setText(raspored.getTermin());
        holder.tip.setText(raspored.getTip());
        holder.nastavnik.setText(raspored.getNastavnik());
        holder.grupe.setText(raspored.getGrupe());
        holder.ucionica.setText(raspored.getUcionica());



    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<RasporedEntity> rasporedi){
        RasporedDiffCallBack callback = new RasporedDiffCallBack(mDataSet, rasporedi);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(rasporedi);
        result.dispatchUpdatesTo(this);
    }


    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView predmet;

        TextView tip;

        TextView nastavnik;

        TextView grupe;

        TextView dan;

        TextView termin;

        TextView ucionica;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            dan = itemView.findViewById(R.id.dan);
            predmet = itemView.findViewById(R.id.predmet);
            ucionica = itemView.findViewById(R.id.ucionica);
            nastavnik = itemView.findViewById(R.id.profesor);
            termin = itemView.findViewById(R.id.termin);
            grupe = itemView.findViewById(R.id.grupe);
            tip = itemView.findViewById(R.id.tip);


        }



    }






}

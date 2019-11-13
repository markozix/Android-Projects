package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domaci2_marko_vukicevic_rn2916.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import diff.StudentCallBack;
import model.Student;

public class Adapter extends RecyclerView.Adapter<Adapter.StudentHolder> {

    private List<Student> listaStudenata;
    public Adapter(){
        listaStudenata = new ArrayList<>();
    }


    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_u_listi,parent,false);
        return new StudentHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {

        Student s = listaStudenata.get(position);

        holder.img.setImageResource(R.drawable.logo);

        holder.tv.setText(s.getName() + "\n" + s.getMail() + "\n" + s.getUserId());

    }

    @Override
    public int getItemCount() {
        return listaStudenata.size();
    }

    public void setData(List<Student> studenti){
        StudentCallBack callback = new StudentCallBack(listaStudenata,studenti);
        DiffUtil.DiffResult rez = DiffUtil.calculateDiff(callback);

        listaStudenata.clear();
        listaStudenata.addAll(studenti);
        rez.dispatchUpdatesTo(this);
    }

    public class StudentHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tv;

        public StudentHolder(View itemView){
            super(itemView);

            img = itemView.findViewById(R.id.iv_studenta);
            tv = itemView.findViewById(R.id.tv_info);

        }

    }

}

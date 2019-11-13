package diff;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.Student;

public class StudentCallBack extends DiffUtil.Callback {

    private List<Student> mstaraLista;
    private List<Student> mnovaLista;


    public StudentCallBack(List<Student> staraLista, List<Student> novaLista){


        mstaraLista = staraLista;
        mnovaLista = novaLista;

    }

    @Override
    public int getOldListSize() {
        return mstaraLista.size();
    }

    @Override
    public int getNewListSize() {
        return mnovaLista.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Student stariStudent = mstaraLista.get(oldItemPosition);
        Student noviStudent = mnovaLista.get(newItemPosition);
        return stariStudent.getUserId() == noviStudent.getUserId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Student stariStudent = mstaraLista.get(oldItemPosition);
        Student noviStudent = mnovaLista.get(newItemPosition);
        return stariStudent.getName().equals(noviStudent.getName());
    }
}

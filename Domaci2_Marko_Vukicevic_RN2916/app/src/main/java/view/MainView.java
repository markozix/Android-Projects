package view;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import model.Student;

public class MainView extends ViewModel {


    private MutableLiveData<List<Student>> studentMLData;

    public MainView(){

        studentMLData = new MutableLiveData<>();
        List<Student> studenti = new ArrayList<>();

        for(int i = 0; i < 50; i++){
            studenti.add(new Student("Student" + i, "MailLista" + i + "@gmail.com"));
        }
        studentMLData.setValue(studenti);

    }

    public LiveData<List<Student>> getStudents(){
        return studentMLData;
    }

    public void setStudents(List<Student> studenti){
        this.studentMLData.setValue(new ArrayList<>(studenti));
    }

}

package CallBack;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class ListaCallBack extends DiffUtil.Callback {

    private List<String> mOldList;
    private List<String> mNewList;

    public ListaCallBack(List<String> stara, List<String> nova){
        mOldList = stara;
        mNewList = nova;

    }



    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        String oldString = mOldList.get(oldItemPosition);
        String newString = mNewList.get(newItemPosition);

        return oldString.equals(newString);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String oldString = mOldList.get(oldItemPosition);
        String newString = mNewList.get(newItemPosition);

        return oldString.equals(newString);
    }


}

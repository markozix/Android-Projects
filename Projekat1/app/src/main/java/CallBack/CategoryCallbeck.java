package CallBack;

import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.Trosak;

public class CategoryCallbeck extends DiffUtil.Callback {


    private HashMap<String, String> mOldList;
    private HashMap<String, String> mNewList;


    public CategoryCallbeck(HashMap<String,String> oldList, HashMap<String,String> newList){
        mOldList = oldList;
        mNewList = newList;
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
        Object oldItem = mOldList.keySet().toArray()[oldItemPosition];
        Object newItem = mNewList.keySet().toArray()[newItemPosition];

        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String oldItem = mOldList.get(oldItemPosition);
        String newItem = mNewList.get(newItemPosition);
        return oldItem.startsWith(newItem);
    }


}

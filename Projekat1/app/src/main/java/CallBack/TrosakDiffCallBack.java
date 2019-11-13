package CallBack;

import android.widget.AdapterView;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.Trosak;

public class TrosakDiffCallBack extends DiffUtil.Callback {



    private List<Trosak> mOldList;
    private List<Trosak> mNewList;

    public TrosakDiffCallBack(List<Trosak> oldList, List<Trosak> newList){
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
        Trosak oldItem = mOldList.get(oldItemPosition);
        Trosak newItem = mNewList.get(newItemPosition);
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Trosak oldItem = mOldList.get(oldItemPosition);
        Trosak newItem = mNewList.get(newItemPosition);
        return oldItem.getCost() == newItem.getCost();
    }




}





package util;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.Raspored;
import repository.db.RasporedDatabase;
import repository.db.entity.RasporedEntity;

public class RasporedDiffCallBack extends DiffUtil.Callback {

    private List<RasporedEntity> mOldList;
    private List<RasporedEntity> mNewList;

    public RasporedDiffCallBack(List<RasporedEntity> oldList, List<RasporedEntity> newList){
        mNewList = newList;
        mOldList = oldList;
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
        RasporedEntity oldItem = mOldList.get(oldItemPosition);
        RasporedEntity newItem = mNewList.get(newItemPosition);
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        RasporedEntity oldItem = mOldList.get(oldItemPosition);
        RasporedEntity newItem = mNewList.get(newItemPosition);
        return oldItem.getPredmet().equals(newItem.getPredmet());
    }
}

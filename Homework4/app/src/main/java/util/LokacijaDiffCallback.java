package util;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.Lokacija;

public class LokacijaDiffCallback extends DiffUtil.Callback {

    private List<Lokacija> mOldList;
    private List<Lokacija> mNewList;

    public LokacijaDiffCallback(List<Lokacija> mOldList, List<Lokacija> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
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
        Lokacija oldLokacija = mOldList.get(oldItemPosition);
        Lokacija newLokacija = mNewList.get(newItemPosition);
        return oldLokacija.getId().equals(newLokacija.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Lokacija oldLokacija = mOldList.get(oldItemPosition);
        Lokacija newLokacija = mNewList.get(newItemPosition);
        return oldLokacija.getLocationName().equals(newLokacija.getLocationName());
    }
}

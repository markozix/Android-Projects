package util;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.ChatItem;

public class ChatDiffCallBack extends DiffUtil.Callback {

    private List<ChatItem> mOldList;
    private List<ChatItem> mNewList;

    public ChatDiffCallBack(List<ChatItem> oldList, List<ChatItem> newList){
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
        ChatItem oldItem = mOldList.get(oldItemPosition);
        ChatItem newItem = mNewList.get(newItemPosition);
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ChatItem oldItem = mOldList.get(oldItemPosition);
        ChatItem newItem = mNewList.get(newItemPosition);
        return oldItem.getName().equals(newItem.getName());
    }
}

package util;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import model.ChatItem;
import model.ChatMessage;

public class ChatMsgDiffCallBack extends DiffUtil.Callback {

    private List<ChatMessage> mOldList;
    private List<ChatMessage> mNewList;

    public ChatMsgDiffCallBack(List<ChatMessage> oldList, List<ChatMessage> newList){
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
        ChatMessage oldItem = mOldList.get(oldItemPosition);
        ChatMessage newItem = mNewList.get(newItemPosition);
        return oldItem.getDate().equals(newItem.getDate());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ChatMessage oldItem = mOldList.get(oldItemPosition);
        ChatMessage newItem = mNewList.get(newItemPosition);
        return oldItem.getMsg().equals(newItem.getMsg());
    }
}

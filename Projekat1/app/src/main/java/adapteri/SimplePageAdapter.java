package adapteri;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import fragmenti.FirstFragment;
import fragmenti.FourthFragment;
import fragmenti.SecondFragment;
import fragmenti.ThirdFragment;

public class SimplePageAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;

    public SimplePageAdapter(FragmentManager fm, Context context){
        super(fm);
        initTitles(context);


    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return FirstFragment.newInstance("From adapter");
            case 1:
                return SecondFragment.newInstance();
            case 2:
                return ThirdFragment.newInstance();
            case 3:
                return FourthFragment.newInstance();

        }



        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


    public CharSequence getPageTitle(int position){
        return mTitles.get(position);
    }

    public void initTitles(Context context){

        mTitles = new ArrayList<>();
        mTitles.add("First fragment");
        mTitles.add("Second fragment");
        mTitles.add("Third fragment");
        mTitles.add("Fourth fragment");

    }

}

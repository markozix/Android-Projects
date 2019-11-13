package adapteri;

import android.content.Context;

import com.example.rma_project2.FirstFragment;
import com.example.rma_project2.SecondFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


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
               return SecondFragment.newInstance("Str");
           // case 2:
            //    return ThirdFragment.newInstance();


        }



        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    public CharSequence getPageTitle(int position){
        return mTitles.get(position);
    }

    public void initTitles(Context context){

        mTitles = new ArrayList<>();
        mTitles.add("Raspored");
        mTitles.add("Chat");
        ///mTitles.add("Wall");

    }

}

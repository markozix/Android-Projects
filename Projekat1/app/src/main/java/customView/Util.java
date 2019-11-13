package customView;

import android.content.Context;

import java.util.Random;

public class Util {

    public static float PxToDp(Context context, float px){
        return px/context.getResources().getDisplayMetrics().density;
    }
    public static float DpToPx(Context context, float dp){
        return dp*context.getResources().getDisplayMetrics().density;
    }

    private static Random rnd = new Random();

    public static int generateId(){
        return rnd.nextInt(Integer.MAX_VALUE);
    }








}

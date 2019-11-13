package customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.projekat1.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

public class PercentageTextView extends AppCompatTextView {

    private List<String> kategorije;

    public PercentageTextView(Context context) {
        super(context);
    }

    public PercentageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setKategorije(List<String> kategorijeLista){
        kategorije = kategorijeLista;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float strokeWidth = Util.DpToPx(getContext(),10);

        float top = strokeWidth;
        float left = strokeWidth;

        float right = canvas.getWidth()-strokeWidth;
        float bottom = canvas.getHeight() - strokeWidth;


        RectF rect = new RectF(left,top,right,bottom);
        Paint paint = new Paint();

        int color = getContext().getResources().getColor(R.color.colorAccent);

        paint.setColor(color);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        canvas.drawOval(rect,paint);


        float sweepAngle = 0;
        float startAngle = 270;

        paint.setColor(getContext().getResources().getColor(R.color.blue));

        sweepAngle = (25/100f) * 360;

        canvas.drawArc(rect,startAngle,sweepAngle,false,paint);
        startAngle += sweepAngle;

        sweepAngle = (38/100f) * 360;
        paint.setColor(getContext().getResources().getColor(R.color.colorPrimary));

        canvas.drawArc(rect,startAngle,sweepAngle,false,paint);
        startAngle+=sweepAngle;

        paint.setColor(getContext().getResources().getColor(R.color.black));

        sweepAngle = (25/100f) * 360;

        canvas.drawArc(rect,startAngle,sweepAngle,false,paint);


    }
}

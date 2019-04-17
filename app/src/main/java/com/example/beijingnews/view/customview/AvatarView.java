package com.example.beijingnews.view.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/16 0016
 * Time: 19:53
 * Describe: ${as}
 */
public class AvatarView extends AppCompatImageView {

    private Paint paint;
    private Bitmap bitmap;
    private BitmapShader shader;
    private Matrix matrix;

    public AvatarView(Context context) {
        this(context,null);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        notifyViewChange();
    }

    public void notifyViewChange() {
        BitmapDrawable drawable = (BitmapDrawable) this.getDrawable();
        bitmap = drawable.getBitmap();
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        matrix = new Matrix();
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int widthBitmap = bitmap.getWidth();
        int heightBitmap = bitmap.getHeight();
        int widthView = getMeasuredWidth();
        int heightView = getMeasuredHeight();
        float precent = (float) ((widthView*1.0)/widthBitmap);
        float precent2 = (float) ((heightView*1.0)/heightBitmap);
        float scale = Math.max(precent,precent2);

        matrix.setScale(scale,scale);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

        int r = Math.min(getMeasuredHeight(),getMeasuredWidth());
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,r/2,paint);
    }
}

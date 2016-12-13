package com.daomaidaomai.islandtrading.maker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.daomaidaomai.islandtrading.R;

public class PictureComposition extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 图片合成-画布 先去画A 再去画B
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.pxdingsi); // bitmap为只读的
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.local);

        Bitmap alterBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), bitmap2.getConfig());

        Canvas canvas = new Canvas(alterBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawBitmap(bitmap1,36,72, paint);
        canvas.drawBitmap(bitmap2,0,0, paint);

        Drawable drawable = new BitmapDrawable(alterBitmap);
        
    }

}
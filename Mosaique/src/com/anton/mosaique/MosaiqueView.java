package com.anton.mosaique;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import com.anton.mosaique.asynctask.MosaiqueBitmapLoader;
import com.anton.mosaique.asynctask.MosaiqueBitmapLoader.OnBitmapComputedListener;

public class MosaiqueView extends View implements OnBitmapComputedListener {

    private static final int     MAX_IMAGE_DISPLAYED = 21;
    private static final int     MAX_COL             = 8;
    private static final int     MAX_ROW             = 6;

    private static final int[]   arrayX              = { 0, 4, 6, 4, 6, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7 };
    private static final int[]   arrayY              = { 0, 0, 0, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5 };

    private List<String>         mListId;
    private List<Bitmap>         mBitmaps;
    private int                  mColWidth, mRowHeight;
    private int                  width, height;
    private MosaiqueBitmapLoader mAsyncLoader;

    public MosaiqueView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBitmaps = new ArrayList<Bitmap>(MAX_IMAGE_DISPLAYED);
        for (int i = 0; i < MAX_IMAGE_DISPLAYED; ++i) {
            mBitmaps.add(null);
        }

        if (isInEditMode()) {
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        mColWidth = width / MAX_COL;
        mRowHeight = height / MAX_ROW;
        init(mListId);
    }

    public void init(List<String> list) {
        mListId = list;
        loadBitmaps();
    }

    private void loadBitmaps() {

        for (Bitmap b : mBitmaps) {
            if (b != null) {
                b.recycle();
            }
        }
        if (mAsyncLoader != null) {
            mAsyncLoader.cancel(true);
        }
        mAsyncLoader = new MosaiqueBitmapLoader(mColWidth, mRowHeight, MAX_IMAGE_DISPLAYED, MAX_COL, MAX_ROW);
        String tabString[] = new String[mListId.size()];
        for (int i = 0; i < tabString.length; ++i) {
            tabString[i] = mListId.get(i);
        }
        // mBitmaps.clear();
        mAsyncLoader.setOnProgressListener(this);
        mAsyncLoader.execute(tabString);
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable back = getBackground();
        if (back != null) {
            back.draw(canvas);
        }
        for (int i = 0; i < mBitmaps.size() && i < MAX_IMAGE_DISPLAYED; ++i) {
            if (mBitmaps.get(i) != null && !mBitmaps.get(i).isRecycled()) {
                canvas.drawBitmap(mBitmaps.get(i), arrayX[i] * mColWidth, arrayY[i] * mRowHeight, null);
            }
        }
    }

    public static int computeOptimalSampleSize(String url, int requestedHeight, int requestedWidth) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        // This allow to decode only the bounds : the method will return only bitmap's height and width
        // in the options object (so the method returns null as result).
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(url, options);

        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        // Now that we have the bitmap bounds, we can compare it to the requested bounds, and decide
        // how much we should decrease the image size. All this process is done to reduce memory heap use and
        // avoid unexpected OutOfMemoryError while handling bitmaps.
        if (height > requestedHeight || width > requestedWidth) {

            int heightRatio = Math.round((float) height / (float) requestedHeight);
            int widthRatio = Math.round((float) width / (float) requestedWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

        }
        return inSampleSize;
    }

    @Override
    public void onBitmapComputed(Pair<Bitmap, Integer> pair) {
        mBitmaps.set(pair.second, pair.first);
        invalidate();

    }
}

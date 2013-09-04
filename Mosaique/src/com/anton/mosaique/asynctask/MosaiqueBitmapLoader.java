package com.anton.mosaique.asynctask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Pair;
import framework.androidapp.Utils.BitmapUtils;

public class MosaiqueBitmapLoader extends AsyncTask<String, Pair<Bitmap, Integer>, List<Bitmap>> {

    @SuppressWarnings("unused")
    private static final String                     TAG = "MosaiqueBitmapLoader";

    private int                                     mWidthCol, mHeightRow;
    private int                                     mMaxImageDisplayed;
    private int                                     mMaxCol, mMaxRow;
    private List<Bitmap>                            mListRes;
    private WeakReference<OnBitmapComputedListener> mWeakListener;

    public MosaiqueBitmapLoader(int widthCol, int heightRow, int maxImageDisplayed, int maxCol, int maxRow) {
        super();
        mWidthCol = widthCol;
        mHeightRow = heightRow;
        mMaxImageDisplayed = maxImageDisplayed;
        mMaxCol = maxCol;
        mMaxRow = maxRow;
        mListRes = new ArrayList<Bitmap>(mMaxImageDisplayed);
    }

    public void setOnProgressListener(OnBitmapComputedListener mListener) {
        mWeakListener = new WeakReference<MosaiqueBitmapLoader.OnBitmapComputedListener>(mListener);
    }

    public int getWidthCol() {
        return mWidthCol;
    }

    public void setWidthCol(int widthCol) {
        mWidthCol = widthCol;
    }

    public int getHeightRow() {
        return mHeightRow;
    }

    public void setHeightRow(int heightRow) {
        mHeightRow = heightRow;
    }

    public int getMaxImageDisplayed() {
        return mMaxImageDisplayed;
    }

    public void setMaxImageDisplayed(int maxImageDisplayed) {
        mMaxImageDisplayed = maxImageDisplayed;
    }

    public int getMaxCol() {
        return mMaxCol;
    }

    public void setMaxCol(int maxCol) {
        mMaxCol = maxCol;
    }

    public int getMaxRow() {
        return mMaxRow;
    }

    public void setMaxRow(int maxRow) {
        mMaxRow = maxRow;
    }

    @Override
    protected List<Bitmap> doInBackground(String... params) {
        for (int i = 0; i < mMaxImageDisplayed; ++i) {

            if (i < params.length) {
                int w, h;
                if (i == 0) {
                    w = 3 * mWidthCol;
                    h = 3 * mHeightRow;
                } else if (i == 1 || i == 2 || i == 3 || i == 4) {
                    w = 2 * mWidthCol;
                    h = 2 * mHeightRow;
                } else {
                    w = mWidthCol;
                    h = mHeightRow;
                }
                BitmapFactory.Options opts = new Options();
                int sampleSize = BitmapUtils.computeOptimalSampleSize(params[i], h, w);

                opts.inPurgeable = true;
                opts.inSampleSize = sampleSize;

                Bitmap b = BitmapFactory.decodeFile(params[i], opts);
                mListRes.add(b);
                for (int yo = 0; yo < 5000000; yo++)
                    ;
                publishProgress(new Pair<Bitmap, Integer>(b, new Integer(i)));
            }
        }
        return mListRes;
    }

    @Override
    protected void onProgressUpdate(Pair<Bitmap, Integer>... values) {

        super.onProgressUpdate(values);
        OnBitmapComputedListener listener = mWeakListener.get();
        if (listener != null) {
            listener.onBitmapComputed(values[0]);
        }
    }

    public interface OnBitmapComputedListener {
        void onBitmapComputed(Pair<Bitmap, Integer> b);
    }
}

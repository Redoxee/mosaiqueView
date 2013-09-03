package com.anton.mosaique;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MosaiqueView extends View {

	private static final int MAX_IMAGE_DISPLAYED = 21;
	private static final int MAX_COL = 8;
	private static final int MAX_ROW = 5;

	private static final int[] arrayX = { 0, 4, 6, 4, 6, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7 };
	private static final int[] arrayY = { 0, 0, 0, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4 };

	private List<Integer> mListId;
	private Bitmap[] mBitmaps;
	private int mColWidth, mRowHeight;
	private int width, height;

	public MosaiqueView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mBitmaps = new Bitmap[MAX_IMAGE_DISPLAYED];

		if (isInEditMode()) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			list.add(R.drawable.ic_launcher);
			init(list);
		}

	}

	public void init(List<Integer> list) {
		mListId = list;
		loadBitmaps();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		mColWidth = width / MAX_COL;
		mRowHeight = height / MAX_ROW;
	}

	private void loadBitmaps() {

		for (int i = 0; i < MAX_IMAGE_DISPLAYED; ++i) {
			if (mBitmaps[i] != null) {
				mBitmaps[i].recycle();
				mBitmaps[i] = null;
			}
			if (mListId != null && i < mListId.size()) {
				int w, h;
				if (i == 1) {
					w = 4 * mColWidth;
					h = 4 * mRowHeight;
				} else if (i == 1) {
					w = 2 * mColWidth;
					h = 2 * mRowHeight;
				} else {
					w = mColWidth;
					h = mRowHeight;
				}
				mBitmaps[i] = BitmapFactory.decodeResource(getResources(), mListId.get(i), null);
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		Drawable back = getBackground();
		back.draw(canvas);
		for (int i = 0; i < MAX_IMAGE_DISPLAYED; ++i) {
			if (mBitmaps[i] != null) {
				canvas.drawBitmap(mBitmaps[i], arrayX[i] * mColWidth, arrayY[i] * mRowHeight, null);
			}
		}
	}
}

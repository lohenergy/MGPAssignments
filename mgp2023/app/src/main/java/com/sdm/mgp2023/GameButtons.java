package com.sdm.mgp2023;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
public class GameButtons {
    private int left, top, right, bottom;
    private String label;

    public GameButtons(int left, int top, int right, int bottom, String label) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.label = label;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        float textX = left + (right - left) / 2 - paint.measureText(label) / 2;
        float textY = top + (bottom - top) / 2 + paint.getTextSize() / 2;
        canvas.drawText(label, textX, textY, paint);
    }

    public boolean isPressed(int x, int y) {
        return x >= left && x <= right && y >= top && y <= bottom;
    }

}

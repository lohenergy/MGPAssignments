package com.sdm.mgp2023;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase {

	//7. Render a scrolling background
    //Image in Android


    private Bitmap bmp = null; //bmp is the name of the Bitmap tp load

    private boolean isDone = false;

    private float xPos = 0, yPos = 0;

    int ScreenWidth, ScreenHeight;

    private Bitmap scaledbmp = null; //Stored the scaled version is scaled based on Screen

   
    @Override
    public boolean IsDone(){
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view){
       //Load image from the resource
                bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.cyber);

        //Screen Size
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);

    }

    @Override
    public void Update(float _dt){
        if(GameSystem.Instance.GetIsPaused())
            return;

        xPos -= _dt * 500;//To deal with the speed of the scrolling
        if(xPos < -ScreenWidth)
        {
            xPos = 0;
        }
        //Check if xPos is decreasing - screenWidth

    }

    @Override
    public void Render(Canvas _canvas){
      //Draw 2 images of the same kind
        //Once the  1st image reaches 0 based on scrolling left to right
        _canvas.drawBitmap(scaledbmp, xPos,yPos, null);
        _canvas.drawBitmap(scaledbmp,xPos+ ScreenWidth,yPos,null);
    }

    @Override
    public boolean IsInit(){
        return bmp != null;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_DEFAULT;}

    public static RenderBackground Create(){
        RenderBackground result = new RenderBackground();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}

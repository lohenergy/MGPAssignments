//package com.sdm.mgp2023;
//
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.view.SurfaceView;
//
//import java.util.Random;
//
//public class PlayerEntity implements EntityBase, Collidable{
//    private Bitmap[] playerFrames;
//    private int currentFrame;
//    private long frameTimer;
//    private static final int FRAME_DURATION = 100;
//
//    private boolean isDone = false;
//    private boolean isInit = false;
//
//    private float xPos, yPos, xDir, yDir;
//
//    // For touch interaction
//    private boolean hasTouched = false;
//
//    @Override
//    public boolean IsDone() {
//        return isDone;
//    }
//
//    @Override
//    public void SetIsDone(boolean _isDone) {
//        isDone = _isDone;
//    }
//
//    @Override
//    public void Init(SurfaceView _view) {
//        Resources res = _view.getResources();
//        // Load the sprite sheet
//        Bitmap spriteSheet = BitmapFactory.decodeResource(res, R.drawable.player);
//
//        int frameWidth = spriteSheet.getWidth() / 5;
//        int frameHeight = spriteSheet.getHeight();
//
//        playerFrames = new Bitmap[5];
//        for (int i = 0; i < 5; i++) {
//            playerFrames[i] = Bitmap.createBitmap(spriteSheet, i * frameWidth, 0, frameWidth, frameHeight);
//        }
//
//        currentFrame = 0;
//        frameTimer = System.currentTimeMillis();
//
//        // Set initial position randomly
//        Random ranGen = new Random();
//        xPos = ranGen.nextFloat() * _view.getWidth();
//        yPos = ranGen.nextFloat() * _view.getHeight();
//        xDir = ranGen.nextFloat() * 100.0f - 50.0f;
//        yDir = ranGen.nextFloat() * 100.0f - 50.0f;
//
//        isInit = true;
//    }
//
//    @Override
//    public void Update(float _dt) {
//        // Update the frame based on the elapsed time
//        if (System.currentTimeMillis() - frameTimer >= FRAME_DURATION) {
//            currentFrame = (currentFrame + 1) % 5;
//            frameTimer = System.currentTimeMillis();
//        }
//
//        // Deal with touch for interaction
//        if (TouchManager.Instance.HasTouch()) {
//            float imgRadius = playerFrames[0].getWidth() * 0.5f;
//
//            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f,
//                    xPos, yPos, imgRadius) || hasTouched) {
//                hasTouched = true;
//
//                // Drag the sprite around the screen
//                xPos = TouchManager.Instance.GetPosX();
//                yPos = TouchManager.Instance.GetPosY();
//                xDir += xDir * _dt;
//                yDir += yDir * _dt;
//            }
//
//        }
//    }
//
//    @Override
//    public void Render(Canvas _canvas) {
//        // Draw the current frame on the canvas
//        _canvas.drawBitmap(playerFrames[currentFrame], xPos, yPos, null);
//    }
//
//    @Override
//    public boolean IsInit() {
//        return isInit;
//    }
//
//    @Override
//    public int GetRenderLayer() {
//        return LayerConstants.PLAYER_LAYER;
//    }
//
//    @Override
//    public void SetRenderLayer(int _newLayer) {
//
//    }
//    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_PLAYER;}
//    public static PlayerEntity Create()
//    {
//        PlayerEntity result = new PlayerEntity();
//        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PLAYER);
//        return result;
//    }
//
//    @Override
//    public String GetType() {
//        return null;
//    }
//
//    @Override
//    public float GetPosX() {
//        return 0;
//    }
//
//    @Override
//    public float GetPosY() {
//        return 0;
//    }
//
//    @Override
//    public float GetRadius() {
//        return 0;
//    }
//
//    @Override
//    public void OnHit(Collidable _other) {
//
//    }
//}

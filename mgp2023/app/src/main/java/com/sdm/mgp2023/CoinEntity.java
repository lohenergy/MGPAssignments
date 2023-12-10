package com.sdm.mgp2023;

import android.graphics.Canvas;
import android.view.SurfaceView;
import java.util.Random;

public class CoinEntity implements EntityBase, Collidable{

    // 1. Declare the use of spritesheet using Sprite class.
    // public Bitmap bmp = null; // Usual method of loading a bmp/image
    private PlayerEntity player;


    private Sprite spritesheet = null; // Define.

    private boolean isDone = false;
    private boolean isInit = false;

    // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;

    // For use with the TouchManager.class
    private boolean hasTouched = false;

    int ScreenWidth, ScreenHeight;
    private float spawnTimer = 3.0f;
    private float elapsedTime = 0.0f;
    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }


    public void SetPlayer(PlayerEntity _player) {
        this.player = _player;
    }
    @Override
    public void Init(SurfaceView _view) {
        // New method using our own resource manager : Returns pre-loaded one if exists
        // 2. Loading spritesheet
        spritesheet =  new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.flystar),1,5,16,1.5f);
        //In the Sprite Class, there is SetAnimationFrames
        //spritesheet.SetAnimationFrames(1,15);


        // 3. Get some random position of x and y
        //This part can have different ways to move or interact with your character

        //in java utility library, random generator
        Random ranGen = new Random();
        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() * 100.0f - 50.f;
        yDir = ranGen.nextFloat() * 100.0f - 50.f;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;
        // 4. Update spritesheet
        spritesheet.Update(_dt);
        // Update timer
        elapsedTime += _dt;

        // Check if it's time to spawn a new coin
        if (elapsedTime >= spawnTimer) {
            SpawnCoin();
            elapsedTime = 0.0f; // Reset the timer
        }
        // 5. Deal with the touch on screen for interaction of the image using collision check
//        if(TouchManager.Instance.HasTouch())
//        {
//            float imgRadius = spritesheet.GetWidth() * 0.5f;
//
//            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f,xPos,yPos,imgRadius) || hasTouched)
//
//
//                hasTouched = true;
//
//            xPos = TouchManager.Instance.GetPosX();
//            yPos = TouchManager.Instance.GetPosY();
//            xDir += xDir * _dt;
//            yDir += yDir * _dt;
//        }
    }
    private void SpawnCoin() {
        if (player != null) {
            Random ranGen = new Random();

            // Ensure the coin spawns a bit forward of the player's x-position
            float playerXPos = player.GetPosX();
            float spawnOffset = 50.0f; // Adjust the offset as needed
            xPos = playerXPos + spawnOffset;

            // Use the player's y-position for the coin's y-position
            yPos = player.GetPosY();

            xDir = ranGen.nextFloat() * 100.0f - 50.f;
            yDir = ranGen.nextFloat() * 100.0f - 50.f;
        }
    }
    @Override
    public void Render(Canvas _canvas) {

        // This is for our sprite animation!
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.COIN_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static CoinEntity Create()
    {
        CoinEntity result = new CoinEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_COIN);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_COIN;}

    @Override
    public String GetType() {
        return "CoinEntity";
    } // other entities will have their own type

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return spritesheet.GetHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other instanceof PlayerEntity) {
            // Handle collision with SmurfEntity
            PlayerEntity smurf = (PlayerEntity) _other;

            // Perform actions like increasing the score or playing a sound
            // For example:
            // ScoreManager.Instance.IncreaseScore(10);
            // AudioManager.Instance.PlayCoinSound();


        }
    }

}

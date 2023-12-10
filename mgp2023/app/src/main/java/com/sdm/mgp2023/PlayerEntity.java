package com.sdm.mgp2023;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PlayerEntity implements EntityBase, Collidable{

    // 1. Declare the use of spritesheet using Sprite class.
    // public Bitmap bmp = null; // Usual method of loading a bmp/image

    private Sprite spritesheet = null; // Define.

    private boolean isDone = false;
    private boolean isInit = false;

    // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;

    private float speed = 50.0f;
    private float jumpDuration = 2.0f;
    // For use with the TouchManager.class
    private boolean hasTouched = false;
    // public final static SmurfEntity Instance = new SmurfEntity();
    int ScreenWidth, ScreenHeight;
    public boolean isMoving = false;
    // Constructor to initialize SmurfEntity with a reference to LButtonEntity
    private boolean isFacingRight = true;
    private float yVelocity; // Vertical velocity for jumping
    public boolean isJumping = false;
    private float gravity = 2.0f; // Gravity to bring the player back down
    private float jumpStrength = 30.0f; // Strength of the jump
    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }



    @Override
    public void Init(SurfaceView _view) {
        // New method using our own resource manager : Returns pre-loaded one if exists

        // 2. Loading spritesheet
        spritesheet =  new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player),9,9,16, 2.0f);
        //In the Sprite Class, there is SetAnimationFrames
        spritesheet.SetAnimationFrames(0,9);



        // 3. Get some random position of x and y
        //This part can have different ways to move or interact with your character

        // Get the dimensions of the screen
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        // Set the smurf position to the left middle side of the screen
        xPos = 0; // Leftmost side
        yPos = ScreenHeight / 2.0f - spritesheet.GetHeight() / 2.0f; // Centered vertically

        // Set initial direction (you can adjust this based on your requirements)
        xDir = 50.0f; // Move to the right
        yDir = 0.0f; // No vertical movement initially
        yVelocity = 0.0f; // Initialize yVelocity to 0
        isJumping = false;
        isInit = true;
    }
    public void jump(float _dt) {

        if (!isJumping) {
            yVelocity = -jumpStrength;
            isJumping = true;
            jumpDuration = 0.5f; // Set the desired jump duration
        }
    }
    // Move the player up
    public void moveUp() {
        yPos -= speed;

    }

    public void moveDown() {
        yPos += speed;

    }

    public void moveLeft() {
        xPos -= speed;
        isFacingRight = false; // Player is now facing left
        spritesheet.SetAnimationFrames(55, 63);

    }

    public void moveRight() {
        xPos += speed;
        isFacingRight = true; // Player is now facing right
        spritesheet.SetAnimationFrames(19, 27);

    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;
        // 4. Update spritesheet
        spritesheet.Update(_dt);
        yPos += yVelocity;
        yVelocity += gravity;

        float groundLevel = ScreenHeight / 1.5f ;
        if (yPos >= groundLevel) {
            yPos = groundLevel;
            isJumping = false; // Set isJumping to false when player is on the ground
            yVelocity = 0.0f;
        }
        else
        if (isJumping) {
            // Decrease jump duration
            jumpDuration -= _dt;

            // Check if jump duration has elapsed
            if (jumpDuration <= 0.0f) {
                isJumping = false;
            }
        }
        // 5. Deal with the touch on screen for interaction of the image using collision check
//        if (TouchManager.Instance.HasTouch()) {
//            float imgRadius = spritesheet.GetWidth() * 0.5f;
//
//            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched)
//                hasTouched = true;
//
//            // Change animation frames based on touch location
//            if (TouchManager.Instance.GetPosX() > ScreenWidth / 2.0f) {
//                // Right side of the screen, set animation frames for right movement
//                spritesheet.SetAnimationFrames(19, 27);
//                xDir += xDir * _dt;  // Adjust the movement logic as needed
//            } else {
//                // Left side of the screen, set animation frames for left movement
//                spritesheet.SetAnimationFrames(55, 63);
//                xDir -= xDir * _dt;  // Adjust the movement logic as needed
//            }
//
//            // Update position based on touch
//            xPos = TouchManager.Instance.GetPosX();
//            yPos = TouchManager.Instance.GetPosY();
//        }
        // Check if the left button is pressed

    }

    @Override
    public void Render(Canvas _canvas) {

        // This is for our sprite animation!
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);

    }
    public boolean isFacingRight() {
        return isFacingRight;
    }
    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.PLAYER_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static PlayerEntity Create()
    {
        PlayerEntity result = new PlayerEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_SMURF;}

    @Override
    public String GetType() {
        return "SmurfEntity";
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
        if (_other instanceof CoinEntity) {
            // Handle collision with CoinEntity
            CoinEntity coin = (CoinEntity) _other;

            // Perform actions like increasing the score or playing a sound
            // For example:
            // ScoreManager.Instance.IncreaseScore(10);
            // AudioManager.Instance.PlayCoinSound();

            // Despawn the coin
            coin.SetIsDone(true);



        }
        else if (_other instanceof EnemyEntity) {
            // Handle collision with EnemyEntity
            // Optionally perform actions, e.g., decrease player's health

            // Despawn the player
            SetIsDone(true);
        }
    }

}

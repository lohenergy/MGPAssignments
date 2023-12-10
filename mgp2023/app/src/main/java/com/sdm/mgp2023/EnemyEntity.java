package com.sdm.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class EnemyEntity implements EntityBase, Collidable {
    private boolean isDone = false;
    private boolean isInit = false;
    private Bitmap bmEnemy = null;

    public float ExPos, EyPos, ExDir, EyDir, lifeTime;

    private int ScreenWidth, ScreenHeight;

    // Reference to the player (SmurfEntity)
    private PlayerEntity player;

    public void SetPlayer(PlayerEntity _player) {
        this.player = _player;
    }

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
        bmEnemy = ResourceManager.Instance.GetBitmap(R.drawable.enemy_icon); // Replace with your enemy image
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        // Scale the enemy bitmap (Adjust the scale as needed)
        bmEnemy = Bitmap.createScaledBitmap(bmEnemy, ScreenWidth / 10, ScreenHeight / 10, true);

        // Set the enemy position to the middle right of the screen
        ExPos = ScreenWidth - bmEnemy.getWidth();
        EyPos = ScreenHeight / 2.0f - bmEnemy.getHeight() / 2.0f;

        // Set initial direction to move left
        ExDir = -50.0f;
        EyDir = 0.0f;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        // Update enemy's position based on direction
        ExPos += ExDir * _dt;
        EyPos += EyDir * _dt;

        // Add logic to handle enemy boundaries to prevent them from moving off-screen
        // Example:
        if (ExPos < 0) {
            ExPos = 0;     // Reset to leftmost position
            ExDir = -ExDir; // Reverse direction on reaching left screen boundary
        }
        if (ExPos + bmEnemy.getWidth() > ScreenWidth) {
            ExPos = ScreenWidth - bmEnemy.getWidth(); // Reset to rightmost position
            ExDir = -ExDir;                           // Reverse direction on reaching right screen boundary
        }

        if (EyPos < 0 || EyPos + bmEnemy.getHeight() > ScreenHeight) {
            EyDir = -EyDir; // Reverse direction on reaching top or bottom screen boundary
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        if (bmEnemy != null && !bmEnemy.isRecycled()) {
            _canvas.drawBitmap(bmEnemy, ExPos, EyPos, null);
        }
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    public int GetRenderLayer(){
        return LayerConstants.ENEMY_LAYER;
    }
    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }
    public static EnemyEntity Create()
    {
        EnemyEntity result = new EnemyEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_ENEMY;
    }
    @Override
    public String GetType() {
        return "EnemyEntity";
    } // other entities will have their own type

    @Override
    public float GetPosX() {
        return ExPos;
    }

    @Override
    public float GetPosY() {
        return EyPos;
    }

    @Override
    public float GetRadius() {
        return 0.f;
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
            smurf.SetIsDone(true);


        }
    }
}

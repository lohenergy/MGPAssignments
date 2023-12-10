package com.sdm.mgp2023;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private TouchManager touchManager;
    private PlayerEntity player;
    private GameButtons upButton, downButton, leftButton, rightButton,jumpButton;
    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // 3. Create Background
        RenderBackground.Create();
        player = new PlayerEntity();
        player.Init(_view);
        EntityManager.Instance.AddEntity(player,EntityBase.ENTITY_TYPE.ENT_SMURF);
        EnemyEntity.Create();
        touchManager = TouchManager.Instance;
        upButton = new GameButtons(100, 700, 200, 800, "UP");
        downButton = new GameButtons(100, 850, 200, 950, "DOWN");
        leftButton = new GameButtons(0, 775, 100, 875, "LEFT");
        rightButton = new GameButtons(200, 775, 300, 875, "RIGHT");
        jumpButton = new GameButtons(2000, 775, 2100, 875, "JUMP");
        CoinEntity.Create();
        PauseButtonEntity.Create();
        RenderTextEntity.Create();



    }

    @Override
    public void OnExit() {
			// 4. Clear any instance instantiated via EntityManager.
        EntityManager.Instance.Clean();


			// 5. Clear or end any instance instantiated via GamePage.
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
        upButton.draw(_canvas);
        downButton.draw(_canvas);
        leftButton.draw(_canvas);
        rightButton.draw(_canvas);
        jumpButton.draw(_canvas);

    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);
        player.Update(_dt);
        if (touchManager.IsDown()) {
            if (upButton.isPressed(touchManager.GetPosX(), touchManager.GetPosY())) {
                player.moveUp();
                player.isMoving = true;
            } else if (downButton.isPressed(touchManager.GetPosX(), touchManager.GetPosY())) {
                player.moveDown();
                player.isMoving = true;
            } else if (leftButton.isPressed(touchManager.GetPosX(), touchManager.GetPosY())) {
                player.moveLeft();
                player.isMoving = true;
            } else if (rightButton.isPressed(touchManager.GetPosX(), touchManager.GetPosY())) {
                player.moveRight();
                player.isMoving = true;
            }
            else if (jumpButton.isPressed(touchManager.GetPosX(), touchManager.GetPosY())) {
                player.jump(_dt);
                player.isJumping = true;
            }
        }
        else{
            player.isMoving = false;
            player.isJumping = false;
        }


    }
}




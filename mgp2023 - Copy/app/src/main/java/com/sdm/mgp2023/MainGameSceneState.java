package com.sdm.mgp2023;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // 3. Create Background 
        RenderBackground.Create();
        SmurfEntity.Create();
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

    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);

        if (TouchManager.Instance.IsDown()) {
			
            //6. Example of touch on screen in the main game to trigger back to Main menu
            //StateManager.Instance.ChangeState("MainMenu");
        }
    }
}




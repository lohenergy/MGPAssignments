package com.sdm.mgp2023;

// Created by TanSiewLan2023
// Create a GamePage is an activity class used to hold the GameView which will have a surfaceview

import androidx.fragment.app.FragmentActivity;

import android.graphics.Canvas;

import android.os.Bundle;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class GamePage extends FragmentActivity implements OnClickListener, StateBase {

    public static GamePage Instance = null;

    private Button btn_options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Instance = this;

        setContentView(new GameView(this)); // Surfaceview = GameView

        //setContentView(R.layout.gamepage);
        //Do not import R.R is a registry that stores information of what is inside mainmenu.xml

//        btn_options = (Button)findViewById(R.id.btn_options);
//        btn_options.setOnClickListener((this));
        StateManager.Instance.AddState(new GamePage());


        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.ChangeState("Mainmenu");



    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

//        if (v == btn_options) {
//            intent.setClass(this,Nextpage.class);
//            StateManager.Instance.ChangeState("Nextpage");
//            finish();
//        }
//        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // WE are hijacking the touch event into our own system
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());

        return true;
    }



    @Override
    public String GetName() {
        return "GamePage";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {

    }
}


package com.sdm.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Mainmenu extends Activity implements OnClickListener, StateBase {


    public static Mainmenu Instance = null;
    //Define Button
    private Button btn_start;
    private Button btn_next;

    private Button btn_exit;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.mainmenu);
        //Do not import R.R is a registry that stores information of what is inside mainmenu.xml

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener((this));
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener((this));
        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener((this));

        StateManager.Instance.AddState(new Mainmenu());

        Instance = this;
        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.ChangeState("MainMenu");


    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent();

        if(v == btn_start)
        {
            intent.setClass(this,GamePage.class);
            StateManager.Instance.ChangeState("MainGameState");
        }
        if(v== btn_next)
        {
           intent.setClass(this,Nextpage.class);
           StateManager.Instance.ChangeState("NextPage");

        }
        if(v == btn_exit)
        {
            this.finishAffinity();
        }
        startActivity(intent);
    };

    @Override
    public String GetName() {
        return "MainMenu";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas)
    {}

    @Override
    public void Update(float _dt) {

    }


    @Override
    protected void onPause(){super.onPause();}

    @Override
    protected void onStop(){super.onStop();}

    @Override
    protected void onDestroy(){super.onDestroy();}





}

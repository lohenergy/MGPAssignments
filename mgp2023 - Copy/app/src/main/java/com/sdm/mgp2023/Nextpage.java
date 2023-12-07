package com.sdm.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class Nextpage extends Activity implements OnClickListener,StateBase{

    public static Nextpage Instance = null;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.nextpage);
        //Do not import R.R is a registry that stores information of what is inside mainmenu.xml

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener((this));


        StateManager.Instance.AddState(new Mainmenu());

        Instance = this;
        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.ChangeState("Nextpage");


    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        if (v == btn_back) {
            // Handle the back button click to return to the MainMenu
            intent.setClass(this,Mainmenu.class);
            StateManager.Instance.ChangeState("Mainmenu");

            finish(); // Finish the current activity (NextPage) to go back to the MainMenu
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "NextPage";
    }

    @Override
    public void OnEnter(SurfaceView _view) {
        // Add any initialization code if needed
    }

    @Override
    public void OnExit() {
        // Add any cleanup code if needed
    }

    @Override
    public void Render(Canvas _canvas) {
        // Add rendering code if needed
    }

    @Override
    public void Update(float _dt) {
        // Add update code if needed
    }



}

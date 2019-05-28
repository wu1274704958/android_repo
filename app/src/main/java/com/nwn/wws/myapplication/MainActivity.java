package com.nwn.wws.myapplication;

import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.lang.annotation.Annotation;

public class MainActivity extends AppCompatActivity {
    TriangleGesture triangleGesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IpDialog.setOnGetIp(new IpDialog.OnGetIp() {
            @Override
            public void onGetIp(String ip, String port) {
                Log.e("===",ip + ":" +port);
                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        triangleGesture = new TriangleGesture(new Gesture.OnAppear() {
            @Override
            public void onAppear() {
                Toast.makeText(MainActivity.this,"三角形手势 ok!",Toast.LENGTH_LONG).show();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog dialog = IpDialog.getDialog(MainActivity.this,"保存并重启");
                        dialog.show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                triangleGesture.begin(new Vec2(event.getX(),event.getY()));
                break;
            case MotionEvent.ACTION_UP:
                triangleGesture.end(new Vec2(event.getX(),event.getY()));
                break;
            case MotionEvent.ACTION_MOVE:
                triangleGesture.move(new Vec2(event.getX(),event.getY()));
                break;
        }
        return super.onTouchEvent(event);
    }
}

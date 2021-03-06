package com.nwn.wws.myapplication;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

public abstract class Gesture {
    public interface OnAppear{
        void onAppear();
    }
    private OnAppear onAppear;
    public Gesture(OnAppear onAppear)
    {
        this.onAppear = onAppear;
        this.points = new ArrayList<>();
    }

    public void appear(){
        if(onAppear != null)
        {
            onAppear.onAppear();
        }
    }
    protected ArrayList<Vec2> points;
    public void begin(Vec2 v)
    {
        if(!points.isEmpty())
            points.clear();
        points.add(v);
    }

    public void move(Vec2 v)
    {
        if(points.size() != 1)
            Log.e("Gesture","call move point num should be 1!");
        points.add(v);
    }

    public void end(Vec2 v)
    {
        points.add(v);
        onEnd();
    }

    public abstract void onEnd();
}

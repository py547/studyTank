package com.mashibing.tank;

import java.awt.*;

public class Explode extends GameObject{
    public static final int Width = ResourceMgr.explodes[0].getWidth(),Height = ResourceMgr.explodes[0].getHeight();
    private  int x,y;
    private  GameModel gm=null;

    private  int step = 0;
    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length){
            gm.remove(this);
        };
    }

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public int getHeight() {
        return Height;
    }
}

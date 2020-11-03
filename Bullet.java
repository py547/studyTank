package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 1;
    public static final int Width = ResourceMgr.bulletD.getWidth(),Height = ResourceMgr.bulletD.getHeight();

    private  int x,y;
    private Dir dir;
    boolean live= true;
    private  TankFrame tf=null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf =tf;
    }

    void paint(Graphics g){
        if (!live){
            tf.bulletList.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);break;
        }
        move();
    }

    private void move() {
        switch (dir) {

            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (x<0 || x>TankFrame.Game_width||y<0||y>TankFrame.Game_height){
            live=false;
        }
    }
}

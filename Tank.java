package com.mashibing.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    final int SPEED = 5;
    private boolean living = true;

    public static final int Width = ResourceMgr.tankD.getWidth(),Height = ResourceMgr.tankD.getHeight();


    private boolean moveing =false;

    private  TankFrame tf=null;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf =tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMoveing(boolean moveing) {
        this.moveing = moveing;
    }

    void paint(Graphics g){
        if (!living){
            tf.tanks.remove(this);
        }
        System.out.println("move +"+x+" +"+y);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);break;
        }

       move();
    }

    private void move() {
        if(!moveing){ return;}
        switch(dir){

            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }

    public void fire(){
        int bx = this.x+Tank.Width/2-Bullet.Width/2;
        int by = this.y+Tank.Height/2-Bullet.Height/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,this.tf));
    }

    public void die(){
        living=false;
    }
}

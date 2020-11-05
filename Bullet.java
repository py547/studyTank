package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    public static final int Width = ResourceMgr.bulletD.getWidth(),Height = ResourceMgr.bulletD.getHeight();

    private  int x,y;
    private Dir dir;
    boolean living = true;
    private  TankFrame tf=null;
    private Group group = Group.Bad;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf =tf;
        this.group =group;
    }

    void paint(Graphics g){
        if (!living){
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
            living =false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) {return;}
        Rectangle rect1 = new Rectangle(this.x,this.y,Width,Height);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.Width,Tank.Height);
        if (rect1.intersects(rect2)){
            tank.die();
            this.die();
            tf.explodes.add(new Explode(x,y,tf));
        }
    }
    private void die() {
        this.living = false;
    }
}

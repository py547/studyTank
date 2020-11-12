package com.mashibing.tank;

import java.awt.*;

public class Bullet extends GameObject{
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));
    public static final int Width = ResourceMgr.bulletD.getWidth(),Height = ResourceMgr.bulletD.getHeight();



    private  int x,y;
    private Dir dir;
    boolean living = true;
    private GameModel gm=null;
    private Group group = Group.Bad;
    Rectangle rect=new Rectangle();
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

    public Bullet(int x, int y, Dir dir,Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm =gm;
        this.group =group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = Width;
        rect.height = Height;

        gm.add(this);
    }

    @Override
    public void paint(Graphics g){
        if (!living){
            gm.remove(this);
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
        //update rect
        rect.x = this.x;
        rect.y = this.y;
        if (x<0 || x>TankFrame.Game_width||y<0||y>TankFrame.Game_height){
            living =false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) {return;}
        if (tank.rect.intersects(rect)){
            tank.die();
            this.die();
            int ex = tank.getX()+Tank.Width/2-Explode.Width/2;
            int ey = tank.getY()+Tank.Height/2-Explode.Height/2;
            gm.add(new Explode(ex,ey,gm));
        }
    }
    private void die() {
        this.living = false;
    }
}

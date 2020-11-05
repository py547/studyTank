package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    final int SPEED = 2;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.Bad;

    public static final int Width = ResourceMgr.tankD.getWidth(),Height = ResourceMgr.tankD.getHeight();


    private boolean moveing =true;

    private  TankFrame tf=null;

    public Tank(int x, int y, Dir dir,TankFrame tf,Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf =tf;
        this.group =group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        if (this.group == Group.Bad && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.Bad && random.nextInt(100) > 95) {
            randomDir();
        }
    }

    private void randomDir() {
            this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(){
        int bx = this.x+Tank.Width/2-Bullet.Width/2;
        int by = this.y+Tank.Height/2-Bullet.Height/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,this.group,this.tf));
    }

    public void die(){
        living=false;
    }
}

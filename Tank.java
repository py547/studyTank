package com.mashibing.tank;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank {
     int x,y;
     Dir dir = Dir.DOWN;
    final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    private boolean living = true;
    private Random random = new Random();
     Group group = Group.Bad;
    Rectangle rect=new Rectangle();

    public static final int Width = ResourceMgr.goodTankD.getWidth(),Height = ResourceMgr.goodTankD.getHeight();


    private boolean moveing =true;

    TankFrame tf=null;
    FireStrategy fs;

    public Tank(int x, int y, Dir dir,TankFrame tf,Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf =tf;
        this.group =group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = Width;
        rect.height = Height;

        if (group==Group.Good) {
            String name = (String)PropertyMgr.get("goodFs");
            System.out.println(name);
            try {
                fs=(FireStrategy)Class.forName(name).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* fs=new FourDirFireStrategy();*/
        }
        else  {fs=new DefaultFireStrategy();}
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
        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.Good? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);break;
            case UP:
                g.drawImage(this.group == Group.Good? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);break;
            case RIGHT:
                g.drawImage(this.group == Group.Good? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);break;
            case DOWN:
                g.drawImage(this.group == Group.Good? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);break;
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
        boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x<0) {x=0;}
        if (this.y<30) {x=0;}
        if (this.x>TankFrame.Game_width-Tank.Width) {x=TankFrame.Game_width-Tank.Width;}
        if (this.y>TankFrame.Game_height-Tank.Height) {y=TankFrame.Game_height-Tank.Height;}
    }

    private void randomDir() {
            this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(){
        fs.fire(this);
    }

    public void die(){
        living=false;
    }
}

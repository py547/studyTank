package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
     int x,y;
     int oldX,oldY;
     Dir dir = Dir.DOWN;
    final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    private boolean living = true;
    private Random random = new Random();
     Group group = Group.Bad;
    public Rectangle rect=new Rectangle();

    public static final int Width = ResourceMgr.goodTankD.getWidth(),Height = ResourceMgr.goodTankD.getHeight();


    private boolean moveing =true;

    FireStrategy fs;

    public Tank(int x, int y, Dir dir,Group group) {
        super();
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.dir = dir;
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
        GameModel.getInstance().add(this);
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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
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

    @Override
    public void paint(Graphics g){
        if (!living){
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    private void move() {
        if(!moveing){ return;}
        oldX=x;oldY=y;
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
        if (this.y<30) {y=30;}
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

    public void stop(){
      x=oldX;
      y=oldY;
    }
}

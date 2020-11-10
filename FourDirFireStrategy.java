package com.mashibing.tank;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x+Tank.Width/2-Bullet.Width/2;
        int by = t.y+Tank.Height/2-Bullet.Height/2;

        Dir[] dIrs = Dir.values();
        for(Dir dir:dIrs){
            new Bullet(bx,by,dir,t.group,t.tf);
        }
    }
}

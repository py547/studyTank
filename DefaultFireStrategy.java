package com.mashibing.tank;

public class DefaultFireStrategy implements FireStrategy {
    private static DefaultFireStrategy defaultFireStrategy = new  DefaultFireStrategy();

    public static DefaultFireStrategy get(){
        return defaultFireStrategy;
    }
    @Override
    public void fire(Tank t) {
        int bx = t.x+Tank.Width/2-Bullet.Width/2;
        int by = t.y+Tank.Height/2-Bullet.Height/2;
        new Bullet(bx,by,t.dir,t.group,t.gm);
    }
}

package com.mashibing.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank=new Tank(200,400,Dir.DOWN,this,Group.Good);

    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));        //初始化敌方坦克
        for (int i = 0; i <initTankCount ; i++) {
            tanks.add(new Tank(50+i*80,200,Dir.DOWN,this,Group.Bad));
        }
    }

    public Tank getMyTank(){
        return myTank;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);

        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i <bulletList.size() ; i++) {
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i <tanks.size() ; i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i <explodes.size() ; i++) {
            explodes.get(i).paint(g);
        }
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j <tanks.size() ; j++) {
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }
           /* x +=10;
            y +=10;*/
    }
}

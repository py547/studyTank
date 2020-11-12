package com.mashibing.tank;

import com.mashibing.cor.BulletTankCollider;
import com.mashibing.cor.CollideChain;
import com.mashibing.cor.Collider;
import com.mashibing.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank=new Tank(200,400,Dir.DOWN,this,Group.Good);

/*    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();*/

    CollideChain collideChain = new CollideChain();

    List<GameObject> objects = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));        //初始化敌方坦克
        for (int i = 0; i <initTankCount ; i++) {
            add(new Tank(50+i*80,200,Dir.DOWN,this,Group.Bad));
        }
    }

    public Tank getMyTank(){
        return myTank;
    }

    public void add (GameObject go){
        this.objects.add(go);
    }
    public void remove (GameObject go){
        this.objects.remove(go);
    }
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
/*        g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);*/

        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i <objects.size() ; i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j <objects.size() ; j++) {
                collideChain.collide(objects.get(i),objects.get(j));
            }
        }
           /* x +=10;
            y +=10;*/
    }
}

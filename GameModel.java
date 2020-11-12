package com.mashibing.tank;

import com.mashibing.cor.BulletTankCollider;
import com.mashibing.cor.CollideChain;
import com.mashibing.cor.Collider;
import com.mashibing.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final GameModel Instance = new GameModel();

    public static GameModel getInstance(){
        return Instance;
    }

    static {
        Instance.init();
    }
    Tank myTank;
/*  List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();*/
    CollideChain collideChain = new CollideChain();

    List<GameObject> objects = new ArrayList<>();

    private GameModel(){ }
    private void init() {
        //初始化主战坦克
        myTank=new Tank(200,400,Dir.DOWN,Group.Good);
        //初始化敌方坦克
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i <initTankCount ; i++) {
            new Tank(50+i*80,200,Dir.DOWN,Group.Bad);
        }
        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
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

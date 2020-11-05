package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    Tank myTank=new Tank(200,400,Dir.DOWN,this);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();

    Bullet bullet = new Bullet(300,300,myTank.getDir(),this);
    static final int Game_width = 800 , Game_height = 600;
    public TankFrame() {
        this.setSize(Game_width, Game_height);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(Game_width, Game_height);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, Game_width, Game_height);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

        @Override
        public void paint(Graphics g){
            Color c = g.getColor();
            g.setColor(Color.WHITE);
            g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
            g.drawString("敌人的数量:" + tanks.size(), 10, 80);

            g.setColor(c);

            myTank.paint(g);
            for (int i = 0; i <bulletList.size() ; i++) {
                bulletList.get(i).paint(g);
            }
            for (int i = 0; i <tanks.size() ; i++) {
                tanks.get(i).paint(g);
            }
            for (int i = 0; i < bulletList.size(); i++) {
                for (int j = 0; j <tanks.size() ; j++) {
                    bulletList.get(i).collideWith(tanks.get(j));
                }
            }
           /* x +=10;
            y +=10;*/
          }
        class MyKeyListener extends KeyAdapter{
            Boolean BL = false;
            Boolean BR = false;
            Boolean BU = false;
            Boolean BD = false;
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch(key){
                    case KeyEvent.VK_LEFT:
                        BL=true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        BR=true;
                        break;
                    case KeyEvent.VK_UP:
                        BU=true;
                        break;
                    case KeyEvent.VK_DOWN:
                        BD=true;
                        break;
                }
                setMainTankDir();
                // x +=200;
              // repaint();
            }
            @Override
            public void keyReleased(KeyEvent e){
                int key = e.getKeyCode();
                switch(key){
                    case KeyEvent.VK_LEFT:
                        BL=false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        BR=false;
                        break;
                    case KeyEvent.VK_UP:
                        BU=false;
                        break;
                    case KeyEvent.VK_DOWN:
                        BD=false;
                        break;

                    case KeyEvent.VK_CONTROL:
                        myTank.fire();
                        break;
                }
                setMainTankDir();
            }

            private void setMainTankDir() {
                myTank.setMoveing(true);

                if(BL) {myTank.setDir(Dir.LEFT);}
                if(BR) {myTank.setDir(Dir.RIGHT);}
                if(BU) {myTank.setDir(Dir.UP);}
                if(BD) {myTank.setDir(Dir.DOWN);}

                if (!BL && !BR && !BU && !BD){
                    myTank.setMoveing(false);
                }
            }


    }
    }


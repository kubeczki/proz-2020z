/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.proz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub
 */
public class Player extends Sprite{
    
    private List<Bullet> firedBullets = new ArrayList<Bullet>();

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }
    
    public void move(){
        x += dx;
        if(x < 0) x = 0;
        if(x >= Commons.SCREEN_WIDTH) x = Commons.SCREEN_WIDTH - Commons.QUANT_SIZE;
    }
    
    public void shoot(){
        Bullet bullet = new Bullet(x, y);
        firedBullets.add(bullet);
    }
    
    public List<Bullet> getFiredBullets(){
        return firedBullets;
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -Commons.QUANT_SIZE;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = Commons.QUANT_SIZE;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            shoot();
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
    
    
    @Override
    void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, Commons.QUANT_SIZE, Commons.QUANT_SIZE);
    }
    
}

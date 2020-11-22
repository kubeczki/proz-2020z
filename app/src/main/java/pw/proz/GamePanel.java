/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.proz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Jakub
 */
public class GamePanel extends JPanel implements ActionListener{

    boolean running = false;
    Timer timer;
    int aliveEnemies = 0;
    private List<Enemy> enemies;
    private Player player;
    
    GamePanel(){
        this.setPreferredSize(new Dimension(Commons.SCREEN_WIDTH, Commons.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    public void startGame(){
        running = true;
        timer = new Timer(Commons.DELAY, this);
        timer.start();
        enemies = new ArrayList<>();
        for(int i = 0; i < Commons.MAX_ENEMIES; i++){
            Enemy enemy = new Enemy((i+1)*5*Commons.QUANT_SIZE, 50);
            enemies.add(enemy);
        }
        player = new Player();
    }
    
    private void drawPlayer(Graphics g){
        
        if(player.isVisible()){
            player.draw(g);
        }
        if(player.isDying()){
            player.die();
            running = false;
        }
    }
    
    private void drawEnemies(Graphics g) {

        for (Enemy enemy : enemies) {
            if(enemy.isVisible()){
                enemy.draw(g);
            }
            if(enemy.isDying()){
                enemy.die();
            }
        }
    }
    
    private void drawBullets(Graphics g){
        
        for(Bullet bullet : player.getFiredBullets()){
             if(bullet.isVisible()){
                 bullet.draw(g);
             }
             if(bullet.isDying()){
                 bullet.die();
             }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawPlayer(g);
        drawEnemies(g);
        drawBullets(g);
        //drawLines(g);
    }
    
    public void drawLines(Graphics g){
        g.setColor(Color.GRAY);
        for(int i = 0; i < Commons.SCREEN_HEIGHT/Commons.QUANT_SIZE; i++){
            g.drawLine(i*Commons.QUANT_SIZE, 0, i*Commons.QUANT_SIZE, Commons.SCREEN_HEIGHT);
            g.drawLine(0, i*Commons.QUANT_SIZE, Commons.SCREEN_WIDTH, i*Commons.QUANT_SIZE);
        }
    }
    
    
    private void update(){
        
        player.move();
        
        for(Bullet bullet : player.getFiredBullets()){
            
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            
            for(Enemy enemy : enemies){
                
                int enemyX = enemy.getX();
                int enemyY = enemy.getY();
                
                if(enemy.isVisible() && bullet.isVisible()){
                    if(bulletX >= enemyX - Commons.QUANT_SIZE
                            && bulletX <= enemyX + 2*Commons.QUANT_SIZE
                            && bulletY >= enemyY - Commons.QUANT_SIZE
                            && bulletY <= enemyY + 2*Commons.QUANT_SIZE){
                        enemy.setDying(true);
                        bullet.setDying(true);
                    }
                }
            }
            bullet.move();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }
    
    
}
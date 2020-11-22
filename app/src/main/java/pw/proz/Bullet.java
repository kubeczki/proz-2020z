/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.proz;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jakub
 */
public class Bullet extends Sprite{
    
    public Bullet(int x, int y){
        initBullet(x, y);
    }

    private void initBullet(int x, int y){
        this.x = x;
        this.y = y;
        this.dy = 2*Commons.QUANT_SIZE;
    }
    
    public void move(){
        y -= dy;
    }
    
    @Override
    void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, Commons.QUANT_SIZE, Commons.QUANT_SIZE);
    }
    
}

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
public class Enemy extends Sprite{
    
    public Enemy(int x, int y){
        initEnemy(x, y);
    }
    
    private void initEnemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void move(){
        
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 2*Commons.QUANT_SIZE, 2*Commons.QUANT_SIZE);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.proz;

import java.awt.Graphics;

/**
 *
 * @author Jakub
 */
public abstract class Sprite {
    private boolean visible;
    private boolean dying;
    
    int x;
    int y;
    int dx;
    int dy;
    
    public Sprite(){
        visible = true;
    }
    public void die(){
        visible = false;
    }
    public boolean isVisible(){
        return visible;
    }
    protected void setVisible(boolean visible){
        this.visible = visible;
    }
    public boolean isDying(){
        return dying;
    }
    public void setDying(boolean dying){
        this.dying = dying;
    }
    
    abstract void draw(Graphics g);
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}

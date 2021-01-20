package pw.proz.objects;

import java.awt.Graphics;
import java.awt.Image;


public abstract class Sprite {
    protected boolean visible;
    private boolean dying;
    private Image image;
    
    protected int x;
    protected int y;
    protected float dx;
    protected float dy;
    
    protected int width;
    protected int height;
    
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
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getImage() {
        return image;
    }
    
    public abstract void draw(Graphics g);
    
    
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
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}

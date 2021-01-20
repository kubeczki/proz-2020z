package pw.proz.objects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Bonus extends Sprite {
    
    protected String powerUpText;
    
    public void flash(Graphics g){
        g.setColor(Color.red);
        g.fillRect(this.x - 24, this.y - 24, this.width + 24, this.height + 24);
    }
    
    public abstract void move();
    
    @Override
    public abstract void draw(Graphics g);
    
}

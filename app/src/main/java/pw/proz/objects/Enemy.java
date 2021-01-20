package pw.proz.objects;

import pw.proz.window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;


public abstract class Enemy extends Sprite{
    
    long startTime = System.currentTimeMillis();
    long elapsedTime = 0L;
    long lastShot = startTime;
    int bulletCooldown;
    
    public abstract void move();

    public abstract void shoot();
    
    public abstract void shoot(int playerX, int playerY);
    
    public abstract void dive();

    @Override
    public abstract void draw(Graphics g);
    
}

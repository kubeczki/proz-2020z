package pw.proz.objects;

import pw.proz.window.GamePanel;
import java.awt.Graphics;
import javax.swing.ImageIcon;


public class Bullet extends Sprite{
    
    public enum Direction{
        UP, DOWN;
    }
    
    public enum Size{
        STANDARD, BIG;
    }
    
    private Direction direction;
    
    public Bullet(int x, int y, Direction dir, int speed){
        initBullet(x, y, dir, speed);
    }
    
    public Bullet(int x, int y, Direction dir, float dx, float dy){
        initBullet(x, y, dir, dx, dy);
    }

    private void initBullet(int x, int y, Direction dir, int speed){
        String path = "src/main/resources/images/default_bullet.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        this.direction = dir;
        
        if(direction == Direction.UP){
            this.dy = speed;
        }
        else{
            this.dy = -speed;
        }
    }
    
    private void initBullet(int x, int y, Direction dir, float dx, float dy){
        String path = "src/main/resources/images/default_bullet.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.direction = dir;
    }
    
    public void move(){
        x += dx;
        y -= dy;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }
    
}

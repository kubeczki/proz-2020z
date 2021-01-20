package pw.proz.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import pw.proz.window.GamePanel;
import static pw.proz.window.GamePanel.SCREEN_WIDTH;

public class EnemyJet extends Enemy{
    
    
    private static List<Bullet> firedBullets = new ArrayList<Bullet>();
    
    public EnemyJet(int x, int y){
        initEnemyJet(x, y);
    }
    
    private void initEnemyJet(int x, int y){
        String path = "src/main/resources/images/reddie.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        

        if(x%2 == 0){
            this.dx = 3;
        }
        else{
            this.dx = -3;
        }
        this.dy = 3;
        
        bulletCooldown = 1000;
    }
    
    @Override
    public void move(){
        x += dx;
        if(x < 0 || x >= GamePanel.SCREEN_WIDTH - width) dx = -dx;
        
        y += dy;
        if(y < 0 || y > GamePanel.SCREEN_HEIGHT/2) dy = -dy;
    }
    
    @Override
    public void shoot(){
        elapsedTime = (new Date()).getTime() - lastShot;
        if(visible && elapsedTime > bulletCooldown){
            Bullet bullet = new Bullet(x, y, Bullet.Direction.DOWN, 12);
            firedBullets.add(bullet);
            lastShot = (new Date()).getTime();
            elapsedTime = 0;
        }
    }
    
    public static List<Bullet> getFiredBullets(){
        return firedBullets;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void dive() {
        
    }

    @Override
    public void shoot(int playerX, int playerY) {
        
    }
    
}

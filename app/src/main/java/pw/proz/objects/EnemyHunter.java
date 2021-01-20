package pw.proz.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import pw.proz.window.GamePanel;


public class EnemyHunter extends Enemy{

    private static List<Bullet> firedBullets = new ArrayList<Bullet>();
    
    public EnemyHunter(int x, int y){
        initEnemyHunter(x, y);
    }
    
    private void initEnemyHunter(int x, int y){
        String path = "src/main/resources/images/agent.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        
        this.dx = 3;
        this.dy = 0;
        
        bulletCooldown = 2000;
    }
    
    @Override
    public void move() {
        x += dx;
        if(x < 0 || x >= GamePanel.SCREEN_WIDTH - width) dx = -dx;
        
        y += dy;
        if(y < 0 || y > GamePanel.SCREEN_HEIGHT - height) dy = -dy;
    }

    @Override
    public void shoot(int playerX, int playerY) {
        float horizontalSpeed = -(x - playerX)/24;
        float verticalSpeed = (y - playerY)/24;
        elapsedTime = (new Date()).getTime() - lastShot;
        if(visible && elapsedTime > bulletCooldown){
            Bullet bullet = new Bullet(x, y, Bullet.Direction.DOWN, horizontalSpeed, verticalSpeed);
            firedBullets.add(bullet);
            lastShot = (new Date()).getTime();
            elapsedTime = 0;
        }
    }

    @Override
    public void dive() {
        
    }
    
    public static List<Bullet> getFiredBullets(){
        return firedBullets;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void shoot() {
        
    }
    
}

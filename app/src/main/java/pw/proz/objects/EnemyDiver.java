package pw.proz.objects;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import pw.proz.window.GamePanel;


public class EnemyDiver extends Enemy{

    private boolean charging = false;
    
    public EnemyDiver(int x, int y){
        initEnemyDiver(x, y);
    }
    
    private void initEnemyDiver(int x, int y){
        String path = "src/main/resources/images/maddie.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        
        this.dx = 2;
        this.dy = 2;
    }
    
    @Override
    public void move() {
        x += dx;
        if(x < 0 || x >= GamePanel.SCREEN_WIDTH - width) dx = -dx;
        
        y += dy;
    }
    
    @Override
    public void dive(){
        /*if(!charging){
            charging = true;
            int startingY = getY();
            dx = 0;
            dy = -4;
        }
        */
        dx = 0;
        dy = 24;
        
    }

    @Override
    public void shoot() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void shoot(int playerX, int playerY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

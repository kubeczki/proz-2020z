package pw.proz.objects;

import pw.proz.window.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;


public class Player extends Sprite{

    private List<Bullet> firedBullets = new ArrayList<Bullet>();

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        String path = "src/main/resources/images/boo.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        int START_X = 270;
        setX(START_X);

        int START_Y = 450;
        setY(START_Y);
    }
    
    public void move(){
        x += dx;
        if(x < 0) x = 0;
        if(x >= GamePanel.SCREEN_WIDTH - width) x = GamePanel.SCREEN_WIDTH - width;
        
        y += dy;
        if(y > GamePanel.SCREEN_HEIGHT - height) y = GamePanel.SCREEN_HEIGHT - height;
        if(y < GamePanel.SCREEN_HEIGHT/2) y = GamePanel.SCREEN_HEIGHT/2;
    }
    
    public void shoot(){
        Bullet bullet = new Bullet(x, y, Bullet.Direction.UP, 24);
        firedBullets.add(bullet);
    }
    
    public List<Bullet> getFiredBullets(){
        return firedBullets;
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -GamePanel.QUANT_SIZE;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = GamePanel.QUANT_SIZE;
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = -GamePanel.QUANT_SIZE;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = GamePanel.QUANT_SIZE;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            shoot();
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }
    
    
}

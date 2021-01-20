package pw.proz.objects;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class PointsBonus extends Bonus{
    
    public PointsBonus(int x, int y){
        initPointsBonus(x, y);
    }
    
    private void initPointsBonus(int x, int y){
        String path = "src/main/resources/images/points.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        this.x = x;
        this.y = y;
        
        dy = 10;
        
        powerUpText = "+10 POINTS";
    }
    
    @Override
    public void die(){
        visible = false;
    }
    
    @Override
    public void move() {
        y += dy;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }
}

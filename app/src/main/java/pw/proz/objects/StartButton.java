package pw.proz.objects;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class StartButton extends Sprite{

    public StartButton(){
        initStartButton();
    }
    
    private void initStartButton(){
        String path = "src/main/resources/images/start.png";
        ImageIcon ii = new ImageIcon(path);
        
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        
        int START_X = 250;
        setX(START_X);

        int START_Y = 200;
        setY(START_Y);
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }
    
}

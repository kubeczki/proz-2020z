package pw.proz.window;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class Popup extends JLabel{
    
    public Popup(String message){
        this.setPreferredSize(new Dimension(100,25));
        this.setText(message);
        this.setForeground(Color.white);
    }
}

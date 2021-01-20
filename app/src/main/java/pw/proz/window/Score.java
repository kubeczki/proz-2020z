package pw.proz.window;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class Score extends JLabel{
    
    public Score(){
        this.setPreferredSize(new Dimension(100,25));
        this.setText("Wynik: 0");
        this.setForeground(Color.red);
    }
    
    public void updateScore(int newScore){
        this.setText("Wynik: " + String.valueOf(newScore));
    }
}

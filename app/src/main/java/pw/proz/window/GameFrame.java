package pw.proz.window;

import javax.swing.JFrame;


public class GameFrame extends JFrame{
    public GameFrame(GamePanel gamePanel){
        this.add(gamePanel);
        this.setTitle("Galaga");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

package pw.proz;

import pw.proz.window.GameFrame;
import pw.proz.window.GamePanel;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        new GameFrame(new GamePanel());
    }
}

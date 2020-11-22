/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.proz;

/**
 *
 * @author Jakub
 */
public interface Commons {
    
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int QUANT_SIZE = 12;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/QUANT_SIZE;
    static final int DELAY = 40;
    static final int MAX_ENEMIES = 10;
    static final int MAX_BULLETS = 100;
}

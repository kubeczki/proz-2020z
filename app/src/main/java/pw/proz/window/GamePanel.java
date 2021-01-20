package pw.proz.window;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import pw.proz.objects.Bonus;
import pw.proz.objects.Bullet;
import pw.proz.objects.Enemy;
import pw.proz.objects.EnemyDiver;
import pw.proz.objects.EnemyHunter;
import pw.proz.objects.EnemyJet;
import pw.proz.objects.Player;
import java.util.concurrent.ThreadLocalRandom;
import pw.proz.objects.PointsBonus;
import pw.proz.objects.StartButton;


public class GamePanel extends JPanel implements ActionListener{
    
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int QUANT_SIZE = 12;
    public static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/QUANT_SIZE;
    public static final int DELAY = 40;
    public static final int MAX_ENEMIES = 3;
    public static final int MAX_BULLETS = 100;

    private boolean running = false;
    private boolean gameStarted = false;
    private Timer timer;
    private List<Enemy> enemies;
    private List<Bonus> bonuses;
    private Player player;
    private StartButton start;
    private int currentScore = 0;
    private Score score = new Score();

    long startTime = System.currentTimeMillis();
    long elapsedTimeJet = 0L;
    long elapsedTimeDiver = 0L;
    long elapsedTimeHunter = 0L;
    long elapsedTimeBonus = 0L;
    long lastSpawnJet = startTime;
    long lastSpawnDiver = startTime;
    long lastSpawnHunter = startTime;
    long lastSpawnBonus = startTime;
    
    int enemiesCooldown = 2000;
    int bonusCooldown = 15000;
    
    int min = 1;
    int max = 5;
    int cooldown = 1000*(ThreadLocalRandom.current().nextInt(min, max + 1));
    
    

    
    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.add(score);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    private void startGame(){
        
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        enemies = new ArrayList<>();
        bonuses = new ArrayList<>();
        player = new Player();
        start = new StartButton();
    }
    
    
    private void drawPlayer(Graphics g){
        
        if(player.isVisible()){
            player.draw(g);
        }
        if(player.isDying()){
            player.die();
            running = false;
        }
    }
    
    private void drawStartButton(Graphics g){
        
        if(start.isVisible()){
            start.draw(g);
        }
        if(start.isDying()){
            start.die();
            gameStarted = true;
        }
    }
    
    private void drawEnemies(Graphics g) {

        for (Enemy enemy : enemies) {
            if(enemy.isVisible()){
                enemy.draw(g);
            }
            if(enemy.isDying()){
                enemy.die();
            }
        }
    }
    
    private void drawBonuses(Graphics g) {
        
        for (Bonus bonus : bonuses) {
            if(bonus.isVisible()){
                bonus.draw(g);
            }
            if(bonus.isDying()){
                bonus.die();
            }
        }
    }
    
    private void drawBullets(Graphics g){
        
        for(Bullet bullet : player.getFiredBullets()){
             if(bullet.isVisible()){
                 bullet.draw(g);
             }
             if(bullet.isDying()){
                 bullet.die();
             }
        }
        
        for(Bullet bullet : EnemyJet.getFiredBullets()){
             if(bullet.isVisible()){
                 bullet.draw(g);
             }
             if(bullet.isDying()){
                 bullet.die();
             }
        }
        for(Bullet bullet : EnemyHunter.getFiredBullets()){
             if(bullet.isVisible()){
                 bullet.draw(g);
             }
             if(bullet.isDying()){
                 bullet.die();
             }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(running){
            super.paintComponent(g);
            drawPlayer(g);
            drawStartButton(g);
            drawEnemies(g);
            drawBullets(g);
            drawBonuses(g);
        }
        else{
            gameOver(g);
        }
    }
    
    
    private void update(){
        player.move();
        enemiesAct();
        bonusesAct();
        checkCollisions();
        if(gameStarted && running){
            generateJetEnemies();
            generateDiverEnemies();
            generateHunterEnemies();
            generateBonus();
        }
    }
    
    private void enemiesAct(){
        for(Enemy enemy : enemies) {
            enemy.move();
            if(enemy instanceof EnemyJet){
                if(abs(enemy.getX() - player.getX()) < 50){
                    enemy.shoot();
                }
            }
            if(enemy instanceof EnemyHunter){
                enemy.shoot(player.getX(), player.getY());
            }
            if(enemy instanceof EnemyDiver){
                if(abs(enemy.getX() + enemy.getWidth()/2 - player.getX() - player.getWidth()/2) < 10){
                    enemy.dive();
                }
            }
        }
    }
    
    private void bonusesAct(){
        for(Bonus bonus : bonuses) {
            bonus.move();
        }
    }
    
    private void generateJetEnemies(){
        
        elapsedTimeJet = (new Date()).getTime() - lastSpawnJet;
        if(elapsedTimeJet > cooldown){
            int randomPosition = ThreadLocalRandom.current().nextInt(0, SCREEN_WIDTH - 30);
            enemies.add(new EnemyJet(randomPosition, 0));
            lastSpawnJet = (new Date()).getTime();
            elapsedTimeJet = 0;
            cooldown = 1000*(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
    }
    
    private void generateDiverEnemies(){
        
        elapsedTimeDiver = (new Date()).getTime() - lastSpawnDiver;
        if(elapsedTimeDiver > cooldown){
            if((cooldown/1000)%2 == 0){
                enemies.add(new EnemyDiver(0, 60));
            }
            else{
                enemies.add(new EnemyDiver(SCREEN_WIDTH - 60, 60));
            }
            lastSpawnDiver = (new Date()).getTime();
            elapsedTimeDiver = 0;
            cooldown = 1000*(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
    }
    
    private void generateHunterEnemies(){
        
        elapsedTimeHunter = (new Date()).getTime() - lastSpawnHunter;
        if(elapsedTimeHunter > cooldown){
            enemies.add(new EnemyHunter(400, 0));
            lastSpawnHunter = (new Date()).getTime();
            elapsedTimeHunter = 0;
            cooldown = 1000*(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
    }
    
    private void generateBonus(){
        elapsedTimeBonus = (new Date()).getTime() - lastSpawnBonus;
        if(elapsedTimeBonus > bonusCooldown){
            int randomPosition = ThreadLocalRandom.current().nextInt(0, SCREEN_WIDTH - 50);
            bonuses.add(new PointsBonus(randomPosition, 0));
            lastSpawnBonus = (new Date()).getTime();
            elapsedTimeBonus = 0;
            cooldown = 1000*(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
    }
    
    private void checkCollisions(){
        for(Bullet bullet : player.getFiredBullets()){
            
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            
            int bulletWidth = bullet.getWidth();
            int bulletHeight = bullet.getHeight();
            
            bullet.move();
            
            if(start.isVisible() && bullet.isVisible()){
                    if(bulletX >= start.getX() - bulletWidth
                            && bulletX <= start.getX() + start.getWidth()
                            && bulletY >= start.getY() - bulletHeight
                            && bulletY <= start.getY() + start.getHeight()){
                        start.setDying(true);
                        bullet.setDying(true);
                    }
            }
            
            for(Enemy enemy : enemies) {
                
                int enemyX = enemy.getX();
                int enemyY = enemy.getY();
                int enemyWidth = enemy.getWidth();
                int enemyHeight = enemy.getHeight();
                
                
                if(enemy.isVisible() && bullet.isVisible()){
                    if(bulletX >= enemyX - bulletWidth
                            && bulletX <= enemyX + enemyWidth
                            && bulletY >= enemyY - bulletHeight
                            && bulletY <= enemyY + enemyHeight){
                        enemy.setDying(true);
                        bullet.setDying(true);
                        
                        currentScore++;
                        score.updateScore(currentScore);
                    }
                }
            }
        }
        
        for(Bullet bullet : EnemyJet.getFiredBullets()){
            
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            
            int bulletWidth = bullet.getWidth();
            int bulletHeight = bullet.getHeight();
            
            bullet.move();
            
            if(player.isVisible() && bullet.isVisible()){
                if(bulletX >= player.getX() - bulletWidth
                        && bulletX <= player.getX() + player.getWidth()
                        && bulletY >= player.getY() - bulletHeight
                        && bulletY <= player.getY() + player.getHeight()){
                    player.setDying(true);
                    running = false;
                    bullet.setDying(true);
                }
            }
        }
        for(Bullet bullet : EnemyHunter.getFiredBullets()){
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            
            int bulletWidth = bullet.getWidth();
            int bulletHeight = bullet.getHeight();
            
            bullet.move();
            
            if(player.isVisible() && bullet.isVisible()){
                if(bulletX >= player.getX() - bulletWidth
                        && bulletX <= player.getX() + player.getWidth()
                        && bulletY >= player.getY() - bulletHeight
                        && bulletY <= player.getY() + player.getHeight()){
                    player.setDying(true);
                    running = false;
                    bullet.setDying(true);
                }
            }
        }
        for(Enemy enemy : enemies){
            if(enemy instanceof EnemyDiver && player.isVisible() && enemy.isVisible()){
                int enemyX = enemy.getX();
                int enemyY = enemy.getY();
            
                int enemyWidth = enemy.getWidth();
                int enemyHeight = enemy.getHeight();
                
                if(enemyX >= player.getX() - enemyWidth
                        && enemyX <= player.getX() + player.getWidth()
                        && enemyY >= player.getY() - enemyHeight
                        && enemyY <= player.getY() + player.getHeight()){
                    player.setDying(true);
                    running = false;
                    enemy.setDying(true);
                }
            }
        }
        
        for(Bonus bonus : bonuses){
                int bonusX = bonus.getX();
                int bonusY = bonus.getY();
                int bonusWidth = bonus.getWidth();
                int bonusHeight = bonus.getHeight();
                
                
                if(bonus.isVisible() && player.isVisible()){
                    if(player.getX() >= bonusX - player.getWidth()
                            && player.getX() <= bonusX + bonusWidth
                            && player.getY() >= bonusY - player.getHeight()
                            && player.getY() <= bonusY + bonusHeight){
                        bonus.setDying(true);
                        
                        currentScore += 10;
                        score.updateScore(currentScore);
                    }
                }
            }
    }
    
    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, SCREEN_WIDTH / 2 - 30, SCREEN_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, SCREEN_WIDTH / 2 - 30, SCREEN_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Game Over", (SCREEN_WIDTH - this.getFontMetrics(small).stringWidth("Game Over")) / 2,
                SCREEN_WIDTH / 2);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }
    
    public List<Enemy> getEnemies(){
        return enemies;
    }
    
    public List<Bonus> getBonuses(){
        return bonuses;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public boolean getRunning(){
        return running;
    }
}
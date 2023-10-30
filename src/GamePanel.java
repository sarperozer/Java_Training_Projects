import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable{

    private final int TILE_SIZE = 10;
    private final int NUMBER_OF_TILES = 50;
    private final int SCREEN_WIDTH = TILE_SIZE * NUMBER_OF_TILES;
    private final int SCREEN_HEIGHT = TILE_SIZE * NUMBER_OF_TILES;
    private final int FPS = 10;
    private Snake snake = new Snake(this);
    private Food food = new Food(this);
    private Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if((e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getxVel() >= 0) || (e.getKeyCode() == KeyEvent.VK_RIGHT && !snake.hasTail())){
                    snake.setXVel(snake.getVEL_CONST());
                    snake.setYVel(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT && snake.getxVel() <= 0 || (e.getKeyCode() == KeyEvent.VK_LEFT && !snake.hasTail())){
                    snake.setXVel(-snake.getVEL_CONST());
                    snake.setYVel(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_UP && snake.getyVel() <= 0 || (e.getKeyCode() == KeyEvent.VK_UP && !snake.hasTail())) {
                    snake.setYVel(-snake.getVEL_CONST());
                    snake.setXVel(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN && snake.getyVel() >= 0 || (e.getKeyCode() == KeyEvent.VK_DOWN && !snake.hasTail())){
                    snake.setYVel(snake.getVEL_CONST());
                    snake.setXVel(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.setFocusable(true);
        this.setBackground(Color.darkGray);
    }

    public void update(){
        snake.update();
        food.update(snake);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.white);
        snake.draw(g2);
        food.draw(g2);
    }

    public void StartGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long now = System.nanoTime();
        long last = System.nanoTime();
        double timePerFrame = 1000000000 / FPS;

        while(true){
            now = System.nanoTime();

            if(now - last > timePerFrame){

                update();

                repaint();

                last = now;
            }
        }
    }

    public int getTILE_SIZE(){
        return TILE_SIZE;
    }

    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }
}

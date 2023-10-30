import java.awt.*;
import java.util.Random;

public class Food {
    private int posX, posY;
    private GamePanel gamePanel;
    public Food(GamePanel gp){
        gamePanel = gp;

        pickLocation();
    }
    public void update(Snake snake){
        if(snake.getHeadX() == posX && snake.getHeadY() == posY) {
            pickLocation();
            snake.addTail();
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(posX, posY, gamePanel.getTILE_SIZE(), gamePanel.getTILE_SIZE());
    }

    public void pickLocation(){
        int newX, newY;

        Random rand = new Random();
        newX = rand.nextInt(gamePanel.getSCREEN_WIDTH() - gamePanel.getTILE_SIZE()) / 10 * 10;
        newY = rand.nextInt(gamePanel.getSCREEN_HEIGHT() - gamePanel.getTILE_SIZE()) / 10 * 10;

        posX = newX;
        posY = newY;
    }
}

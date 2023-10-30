import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private int headX = 10, headY = 10;
    private final int VEL_CONST = 10;
    private int xVel = 0, yVel = 0;
    private ArrayList<Tail> tail = new ArrayList<>();
    private GamePanel gamePanel;
    private boolean hasTail;

    public Snake(GamePanel gp){
        gamePanel = gp;
    }

    public void draw(Graphics2D g2){
        g2.fillRect(headX, headY, WIDTH, HEIGHT);

        for (int i = 0; i < tail.size(); i++) {
            if(tail.get(i).tailUpdated) g2.fillRect(tail.get(i).posX, tail.get(i).posY, WIDTH, HEIGHT);
        }
    }

    public void update(){

        int newPosX = headX + xVel;
        int newPosY = headY + yVel;
        int lastheadX = headX;
        int lastheadY = headY;


        if(newPosX >= 0 && newPosX + WIDTH <= gamePanel.getWidth() && newPosY >= 0 && newPosY + HEIGHT <= gamePanel.getHeight()) {
            headX = newPosX;
            headY = newPosY;
        }
        else if(newPosX == -10 && xVel < 0)
            headX = 490;
        else if(newPosX + WIDTH == 510 && xVel > 0)
            headX = 0;
        else if(newPosY == -10 && yVel < 0)
            headY = 490;
        else if(newPosY + HEIGHT == 510 && yVel > 0)
            headY = 0;

        for(int i = tail.size(); i > 0; i--){
            if(i == 1){
                tail.get(i - 1).posX = lastheadX;
                tail.get(i - 1).posY = lastheadY;
                tail.get(i-1).tailUpdated = true;
                break;
            }

            tail.get(i-1).posX = tail.get(i-2).posX;
            tail.get(i-1).posY = tail.get(i-2).posY;
            tail.get(i-1).tailUpdated = true;
        }

        for(int i = 0; i < tail.size(); i++){
            if(tail.get(i).posX == headX && tail.get(i).posY == headY)
                tail.clear();
        }
    }

    public void setXVel(int xVel) {
        this.xVel = xVel;
    }

    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    public int getVEL_CONST() {
        return VEL_CONST;
    }

    public int getHeadX() {
        return headX;
    }

    public int getHeadY() {
        return headY;
    }

    public void addTail(){
        Tail t = new Tail();
        tail.add(t);
        hasTail = true;
    }
    public int getxVel() {
        return xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public boolean hasTail() {
        return hasTail;
    }

    private class Tail{
        int posX, posY;
        boolean tailUpdated = false;
    }
}



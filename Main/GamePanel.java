package Main;// Noah TerBest
// Github.com/noahterbest/StoryGame

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile size
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48 tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;
    int frameCount = 0;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastFPSCheck = System.nanoTime();

        while (gameThread != null) {
            update();
            repaint();
            frameCount++;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    System.out.println("Frame took longer than expected to render");
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

                // Calculate and output FPS every second
                if (System.nanoTime() - lastFPSCheck >= 1000000000) {
                    System.out.println("Current FPS: " + frameCount);
                    frameCount = 0;
                    lastFPSCheck = System.nanoTime();
                }

            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        }else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize, tileSize);
        g2.dispose();
    }

}

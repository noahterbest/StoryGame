import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile size
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48 tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);

    }
}

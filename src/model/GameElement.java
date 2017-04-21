package model;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Interface untuk semua game element yang memiliki lokasi.
 */
public abstract class GameElement {

    public GameElement(int x, int y, String img_path) {
        position.setLocation(x,y);
        try {
            Image = ImageIO.read(new File(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
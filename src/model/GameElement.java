package model;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Kelas abstrak GameElement berisi dasar dari semua elemen game.
 */
public abstract class GameElement {

  private BufferedImage Image;

  public GameElement(String img_path) {
    try {
      Image = ImageIO.read(new File(img_path));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
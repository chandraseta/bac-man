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

  public GameElement(String imgPath) {
    try {
      Image = ImageIO.read(new File(imgPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setNewImage(String imgPath) {
    try {
      Image = ImageIO.read(new File(imgPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
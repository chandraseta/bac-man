package model;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Interface untuk semua game element yang memiliki lokasi.
 */
public abstract class GameElement {

  protected Point position;
  protected BufferedImage Image;

  public GameElement(int x, int y, String img_path) {
    position.setLocation(x, y);
    Image = ImageIO.read(new File(img_path));
  }

  public Point getPosition() {
    return position;
  }

  public int getX() {
    return position.getX();
  }

  public int getY() {
    return position.getY();
  }

  public void setPosition(int x, int y) {
    position.setLocation(x, y);
  }
}
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

  private String imgPath;

  public GameElement(String imgPath) {
    this.imgPath = imgPath;
  }

  public void setNewImage(String newImg) {
    imgPath = newImg;
  }

  public String getImgPath() {
    return imgPath;
  }
}
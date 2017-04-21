package model;

import java.awt.Point;

/**
 * Kelas abstrak GameStructure sebagai dasar objek struktur dalam game.
 */
public abstract class GameStructure extends GameElement {

  private Point position;

  public GameStructure(int x, int y, String img_path) {
    super(img_path);
    position.setLocation(x, y);
  }

  public void setPosition(int x, int y) {
    position.setLocation(x, y);
  }

  public int getX() {
    return (int)position.getX();
  }

  public int getY() {
    return (int)position.getY();
  }
}

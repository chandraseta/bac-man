package model;

import java.awt.Point;

/**
 * Kelas abstrak GameCharacter sebagai dasar dari semua karakter dalam game.
 */
public class GameCharacter extends GameElement {
  private Point position;

  public GameCharacter(int x, int y, String img_path) {
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

  public void moveUp() {
    position.setLocation(getX(), getY() - 1);
  }

  public void moveRight() {
    position.setLocation(getX()+1, getY());
  }

  public void moveDown() {
    position.setLocation(getX(), getY()+1);
  }

  public void moveLeft() {
    position.setLocation(getX()-1, getY());
  }
}

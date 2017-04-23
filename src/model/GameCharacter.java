package model;

import java.awt.Point;

/**
 * Kelas abstrak GameCharacter sebagai dasar dari semua karakter dalam game.
 */
public class GameCharacter extends GameElement {
  private Point position;

  public GameCharacter(int i, int j, String img_path) {
    super(img_path);
    position.setLocation(i, j);
  }

  public void setPosition(int i, int j) {
    position.setLocation(i, j);
  }

  public int getI() {
    return (int)position.getX();
  }

  public int getJ() {
    return (int)position.getY();
  }

  public void moveUp() {
    position.setLocation(getI()-1, getJ());
  }

  public void moveRight() {
    position.setLocation(getI(), getJ()+1);
  }

  public void moveDown() {
    position.setLocation(getI()+1, getJ());
  }

  public void moveLeft() {
    position.setLocation(getI(), getJ()-1);
  }
}

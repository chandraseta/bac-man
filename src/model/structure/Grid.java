package model.structure;

import model.GameStructure;

/**
 * Kelas berisi Grid yang merupakan komponen dari Arena.
 */
public class Grid extends GameStructure {
  private Cookie cookie;
  private boolean accessible;

  public Grid(int x, int y, String sprite) {
    super(x, y, sprite);
  }
}

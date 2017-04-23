package model.element;

import model.GameElement;

/**
 * Kelas berisi Grid yang merupakan komponen dari Arena.
 */
public class Grid extends GameElement {
  private boolean accessible;

  public Grid() {
    super("src/view/assets/grid.png");
    accessible = true;
  }

  public Grid(String sprite, boolean _accessible) {
    super(sprite);
    accessible = _accessible;
  }

  public boolean isAccessible() {
    return accessible;
  }
}

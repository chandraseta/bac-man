package model.element;

import model.GameElement;

/**
 * Kelas berisi Grid yang merupakan komponen dari Arena.
 */
public class Grid extends GameElement {
  private Cookie cookie;
  private boolean accessible;

  public Grid() {
    super("src/view/assets/grid.png");
    cookie = new Cookie();
    accessible = true;
  }

  public Grid(boolean containCookie) {
    super("src/view/assets/grid.png");
    cookie = new Cookie(containCookie);
    accessible = true;
  }

  public Grid(String sprite, boolean accessible) {
    super(sprite);
    this.accessible = accessible;
  }

  public Cookie getCookie() {
    return cookie;
  }

  public boolean isAccessible() {
    return accessible;
  }
}

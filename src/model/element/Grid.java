package model.element;

import model.GameElement;

/**
 * Kelas berisi Grid yang merupakan komponen dari Arena.
 */
public class Grid extends GameElement {
  private Cookie cookie;
  private boolean accessible;

  /**
   * Constructor Grid yang tidak memiliki Cookie
   */
  public Grid() {
    super("\\assets\\grid.png");
    cookie = new Cookie();
    removeCookie();
    accessible = true;
  }

  public Grid(boolean containCookie) {
    super("\\assets\\cookie.png");
    cookie = new Cookie();
    if (!containCookie) {
      removeCookie();
    }
    accessible = true;
  }

  public Grid(boolean containCookie, boolean isSuperCookie) {
    super("\\assets\\super_cookie.png");
    if (isSuperCookie) {
      cookie = new SuperCookie();
    }
    else {
      cookie = new Cookie();
    }
    if (!containCookie) {
      removeCookie();
    }
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

  public void removeCookie() {
    cookie.remove();
    setNewImage("\\assets\\grid.png");
  }
}

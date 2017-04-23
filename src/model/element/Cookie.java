package model.element;

import model.GameElement;

/**
 * Kelas berisi Cookie yang tersebar pada Grid dalam Arena.
 */
public class Cookie extends GameElement {
  private boolean available;
  private int value;

  public Cookie() {
    super("src/view/assets/cookie.png");
    available = true;
    value = 10;
  }

  public Cookie(boolean available) {
    super("src/view/assets/cookie.png");
    this.available = available;
    value = 10;
  }

  public Cookie(String sprite, int value) {
    super(sprite);
    available = true;
    this.value = value;
  }

  public boolean isAvailable() {
    return available;
  }

  public int getValue() {
    return value;
  }

  public void removeCookie() {
    available = false;
  }

  public void setValue(int value) {
    this.value = value;
  }
}

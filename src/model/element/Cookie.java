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

  public Cookie(boolean _available) {
    super("src/view/assets/cookie.png");
    available = _available;
    value = 10;
  }

  public Cookie(String sprite, int _value) {
    super(sprite);
    available = true;
    value = _value;
  }

  public boolean isAvailable() {
    return available;
  }

  public int getValue() {
    return value;
  }

  public void remove() {
    available = false;
  }

  public void setValue(int _val) {
    value = _val;
  }
}

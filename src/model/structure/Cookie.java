package model.structure;

import model.GameElement;

/**
 * Kelas berisi Cookie yang tersebar pada Grid dalam Arena.
 */
public class Cookie extends GameElement {
  private boolean available;
  private int value;

  public Cookie(String sprite) {
    super(sprite);
    available = true;
    value = 10;
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

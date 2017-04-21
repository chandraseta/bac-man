package model;

/**
 * Kelas berisi Cookie yang tersebar pada Grid dalam Arena.
 */
public class Cookie {
  private boolean available;
  private int value;

  public Cookie() {
    available = true;
    value = 10;
  }

  public boolean isAvailable() {
    return available;
  }

  public int getValue() {
    return value;
  }

  public void eatCookie() {
    available = false;
  }

  public void setValue(int _val) {
    value = _val;
  }
}

package model.element;

/**
 * Kelas berisi SuperCookie yang memberikan player status Super ketika dimakan.
 */
public class SuperCookie extends Cookie {

  public SuperCookie() {
    super("\\assets\\super_cookie.png", 100);
  }

  public SuperCookie(String sprite, int value) {
    super(sprite, value);
  }

  @Override
  public void remove() {
    available = false;
  }
}

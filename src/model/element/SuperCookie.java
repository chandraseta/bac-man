package model.element;

/**
 * Kelas berisi SuperCookie yang memberikan player status Super ketika dimakan.
 *
 * @author
 */
public class SuperCookie extends Cookie {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan SuperCookie pada Grid.
   * </p>
   */
  public SuperCookie() {
    super("\\assets\\super_cookie.png", 100);
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan SUperCookie pada Grid dengan parameter sprite dan value.
   * </p>
   *
   * @param sprite Path direktori sprite SuperCookie.
   * @param value Nilai pada SuperCookie.
   */
  public SuperCookie(String sprite, int value) {
    super(sprite, value);
  }

  @Override
  /**
   * Menghapus SuperCookie pada Grid.
   */
  public void remove() {
    available = false;
  }
}

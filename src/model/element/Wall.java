package model.element;

/**
 * Kelas berisi Wall yang merupakan Grid yang secara normal
 * tidak dapat dilewati oleh Player maupun Ghost.
 *
 * @author Oktavianus Handika - 13515035
 */
public class Wall extends Grid {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Wall pada Grid.
   * </p>
   */
  public Wall() {
    super("\\assets\\wall.png", false);
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Wall pada Grid dengan parameter sprite dan accessible.
   * </p>
   *
   * @param sprite Path direktori sprite Wall.
   * @param accessible Wall pada Grid dapat diakses oleh karakter atau tidak.
   */
  public Wall(String sprite, boolean accessible) {
    super(sprite, accessible);
  }
}

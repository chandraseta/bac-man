package model.element;

/**
 * Kelas berisi Wall yang merupakan Grid yang secara normal
 * tidak dapat dilewati oleh Player maupun Ghost.
 */
public class Wall extends Grid {

  public Wall() {
    super("src\\view\\assets\\wall.png", false);
  }

  public Wall(String sprite, boolean accessible) {
    super(sprite, accessible);
  }
}

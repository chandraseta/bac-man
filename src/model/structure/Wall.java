package model.structure;

/**
 * Kelas berisi Wall yang merupakan Grid yang secara normal
 * tidak dapat dilewati oleh Player maupun Ghost.
 */
public class Wall extends Grid {
  private final boolean accessible = false;

  public Wall(int x, int y, String sprite) {
    super(x, y, sprite);
  }
}

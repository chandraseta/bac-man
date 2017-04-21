package model;

/**
 * Kelas berisi karakter Player yang dapat dikendalikan pemain.
 */
public class Player extends GameElement {

  /**
   * Berisi informasi status Player.
   * 0 = Normal
   * 1 = Super
   * 2 = Dead
   */
  private int state;

  public Player(int x, int y) {
    super(x, y, "STUB_Player_Sprite");
    state = 0;
  }

  public boolean isNormal() {
    return state == 0;
  }

  public boolean isSuper() {
    return state == 1;
  }

  public boolean isDead() {
    return state == 2;
  }
}

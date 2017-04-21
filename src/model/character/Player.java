package model.character;

import model.GameCharacter;

/**
 * Kelas berisi karakter Player yang dapat dikendalikan pemain.
 */
public class Player extends GameCharacter {

  /**
   * Berisi informasi status Player.
   * 0 = Normal
   * 1 = Super
   * 2 = Dead
   */
  private int state;

  public Player(int x, int y) {
    // TODO: REPLACE STUB WITH PLAYER SPRITE
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

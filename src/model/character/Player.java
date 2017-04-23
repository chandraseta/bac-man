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

  /**
   *
   */
  private static int playerI = 20;
  /**
   *
   */
  private static int playerJ = 12;

  public Player() {
    super(20, 12, "src/view/assets/player.png");
    state = 0;
    playerI = 20;
    playerJ = 12;
  }

  public Player(int i, int j, String sprite) {
    // TODO: REPLACE STUB WITH PLAYER SPRITE
    super(i, j, "src/view/assets/player.png");
    state = 0;
    playerI = i;
    playerJ = j;
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

  public static void setPlayerI(int new_i) {
    playerI = new_i;
  }

  public static void setPlayerJ(int new_j) {
    playerJ = new_j;
  }

  public static int getPlayerI() {
    return playerI;
  }

  public static int getPlayerJ() {
    return playerJ;
  }

}

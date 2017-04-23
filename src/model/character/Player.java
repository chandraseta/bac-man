package model.character;

import model.GameCharacter;

import java.awt.*;

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
  private static int state;

  /**
   * Berisi informasi orientasi arah gerak Player.
   * n = Up
   * e = Right
   * s = Down
   * w = Left
   */
  private static char orientation;

  /**
   *
   */
  private static int playerI = 20;
  /**
   *
   */
  private static int playerJ = 12;

  public Player() {
    super(20, 12, "\\assets\\bacbac_down.gif");
    state = 0;
    playerI = 20;
    playerJ = 12;
    orientation = 's';
  }

  public Player(int i, int j, String sprite) {
    // TODO: REPLACE STUB WITH PLAYER SPRITE
    super(i, j, sprite);
    state = 0;
    playerI = i;
    playerJ = j;
    orientation = 's';
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

  public static int getStatus() {
    return state;
  }

  public static char getOrientation() {
    return orientation;
  }

  public static int getPlayerI() {
    return playerI;
  }

  public static int getPlayerJ() {
    return playerJ;
  }

  public static Point getPosition() {
    return new Point(playerI, playerJ);
  }

  public static void setStatus(int newState) {
    state = newState;
  }

  public static void setOrientation(char newOrientation) {
    orientation = newOrientation;
  }

  public static void setPlayerI(int newI) {
    playerI = newI;
  }

  public static void setPlayerJ(int newJ) {
    playerJ = newJ;
  }
}

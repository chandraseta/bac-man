package model.character;

import model.Arena;
import model.GameCharacter;

import java.awt.*;

/**
 * Kelas berisi karakter Player yang dapat dikendalikan pemain.
 *
 * @author
 * @version
 * @since
 */
public class Player extends GameCharacter {

  /**
   * Atribut yang berisi informasi status Player.
   * 0 = Normal
   * 1 = Super
   * 2 = Dead
   */
  private static int state;

  /**
   * Atribut yang berisi informasi orientasi arah gerak Player.
   * n = Up
   * e = Right
   * s = Down
   * w = Left
   */
  private static char orientation;

  /**
   * Posisi y pada player yang telah diinisialisasi.
   */
  private static int playerI = 20;

  /**
   * Posisi x pada player yang telah diinisialisasi.
   */
  private static int playerJ = 12;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan objek Player pada permainan.
   * </p>
   */
  public Player() {
    super((int) Arena.getPlayerInitPos().getX(), (int) Arena.getPlayerInitPos().getY(),
        "\\assets\\bacbac_down.gif");
    state = 0;
    playerI = 20;
    playerJ = 12;
    orientation = 's';
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan objek Player pada permainan dengan parameter.
   * </p>
   *
   * @param i Posisi y Player.
   * @param j Posisi x Player.
   * @param sprite Path direktori sprite Player.
   */
  public Player(int i, int j, String sprite) {
    super(i, j, sprite);
    state = 0;
    playerI = i;
    playerJ = j;
    orientation = 's';
  }

  /**
   *
   * @return
   */
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

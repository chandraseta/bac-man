package model.character;

import java.awt.Point;
import model.Arena;
import model.GameCharacter;

/**
 * Kelas berisi karakter Player yang dapat dikendalikan pemain.
 *
 * @author Rionaldi Chandraseta - 13515077
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
  private static int playerI;

  /**
   * Posisi x pada player yang telah diinisialisasi.
   */
  private static int playerJ;

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
    playerI = Arena.getPlayerInitPos().x;
    playerJ = Arena.getPlayerInitPos().y;
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
   * Fungsi mengembalikan state Player.
   *
   * @return State Player.
   */
  public static int getStatus() {
    return state;
  }

  /**
   * Method mengubah state Player menjadi state tertentu.
   *
   * @param newState State baru Player.
   */
  public static void setStatus(int newState) {
    state = newState;
  }

  /**
   * Fungsi mengembalikan orientasi pergerakan Player.
   *
   * @return Orientas Player.
   */
  public static char getOrientation() {
    return orientation;
  }

  /**
   * Method mengubah orientasi Player.
   *
   * @param newOrientation Orientasi baru Player.
   */
  public static void setOrientation(char newOrientation) {
    orientation = newOrientation;
  }

  /**
   * Fungsi mengembalikan ordinat Player pada Arena.
   *
   * @return Ordinat Player.
   */
  public static int getPlayerI() {
    return playerI;
  }

  /**
   * Method mengubah ordinat Player pada Arena.
   *
   * @param newI Ordinat baru Player.
   */
  public static void setPlayerI(int newI) {
    playerI = newI;
  }

  /**
   * Fungsi mengembalikan absis Player pada Arena.
   *
   * @return Absis Player.
   */
  public static int getPlayerJ() {
    return playerJ;
  }

  /**
   * Method mengubah absis Player pada Arena.
   *
   * @param newJ Absis baru Player.
   */
  public static void setPlayerJ(int newJ) {
    playerJ = newJ;
  }

  /**
   * Fungsi mengembalikan posisi Player.
   *
   * @return Posisi Player dalam Point.
   */
  public static Point getPosition() {
    return new Point(playerI, playerJ);
  }

  /**
   * Fungsi mengembalikan nilai kebenaran bahwa Player berada pada state normal.
   *
   * @return Nilai kebenaran apakah Player berada pada state normal.
   */
  public boolean isNormal() {
    return state == 0;
  }

  /**
   * Fungsi mengembalikan nilai kebenaran bahwa Player berada pada state super.
   *
   * @return Nilai kebenaran apakah Player berada pada state super.
   */
  public boolean isSuper() {
    return state == 1;
  }

  /**
   * Fungsi mengembalikan nilai kebenaran bahwa Player berada pada state dead.
   *
   * @return Nilai kebenaran apakah Player berada pada state dead.
   */
  public boolean isDead() {
    return state == 2;
  }
}
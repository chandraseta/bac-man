package model.character;

import java.awt.Point;
import model.Arena;

/**
 * Kelas Blinky mendefinisikan Ghost Blinky pada permainan.
 *
 * @author Felix Limanta - 13515065
 */
public class Blinky extends Ghost {

  /**
   * Koordinat y pada Blinky.
   */
  private static int blinkyI;

  /**
   * Koordinat x pada Blinky.
   */
  private static int blinkyJ;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Blinky.
   * </p>
   */
  public Blinky() {
    super((int) Arena.getBlinkyInitPos().getX(), (int) Arena.getBlinkyInitPos().getY(),
        "\\assets\\blinky.gif");
    scatterDestination = new Point(1, Arena.getMapWidth() - 2);
    blinkyI = (int) Arena.getBlinkyInitPos().getX();
    blinkyJ = (int) Arena.getBlinkyInitPos().getY();
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Blinky.
   * </p>
   *
   * @param i Posisi y pada Blinky.
   * @param j Posis x pada Blinky.
   */
  public Blinky(int i, int j) {
    super(i, j, "\\assets\\blinky.gif");
    scatterDestination = new Point(1, Arena.getMapWidth() - 2);
    blinkyI = i;
    blinkyJ = j;
  }

  /**
   * Getter posisi y Blinky.
   *
   * @return posisi y Blinky.
   */
  public static int getBlinkyI() {
    return blinkyI;
  }

  /**
   * Setter posisi y Blinky.
   *
   * @param newI posisi baru y Blinky.
   */
  public static void setBlinkyI(int newI) {
    blinkyI = newI;
  }

  /**
   * Getter posisi x Blinky.
   *
   * @return posisi x Blinky.
   */
  public static int getBlinkyJ() {
    return blinkyJ;
  }

  /**
   * Setter posisi x Blinky.
   *
   * @param newJ posisi baru x Blinky.
   */
  public static void setBlinkyJ(int newJ) {
    blinkyJ = newJ;
  }

  /**
   * Menentukan titik target yang akan dicapai Blinky.
   */
  public void getNextDestination() {
    destination = Player.getPosition();
  }
}
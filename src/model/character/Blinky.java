package model.character;

import model.Arena;

import java.awt.*;

/**
 * Kelas Blinky mendefinisikan Ghost Blinky pada permainan.
 *
 * @author
 * @version
 * @since
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
    super((int) Arena.getBlinkyInitPos().getX(), (int) Arena.getBlinkyInitPos().getY(), "\\assets\\blinky.gif");
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
   * Menentukan titik target yang akan dicapai Blinky.
   */
  public void getNextDestination() {
    destination = Player.getPosition();
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
   * Getter posisi x Blinky.
   *
   * @return posisi x Blinky.
   */
  public static int getBlinkyJ() {
    return blinkyJ;
  }

  /**
   * Setter posisi y Blinky.
   */
  public static void setBlinkyI(int newI) {
    blinkyI = newI;
  }

  /**
   * Setter posisi x Blinky.
   */
  public static void setBlinkyJ(int newJ) {
    blinkyJ = newJ;
  }
}
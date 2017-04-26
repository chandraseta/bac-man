package model.character;

import java.awt.Point;
import model.Arena;

/**
 * Kelas Pinky mendefinisikan Ghost Pinky pada permainan.
 *
 * @author
 */
public class Pinky extends Ghost {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Pinky.
   * </p>
   */
  public Pinky() {
    super((int) Arena.getPinkyInitPos().getX(), (int) Arena.getPinkyInitPos().getY(),
        "\\assets\\pinky.gif");
    scatterDestination = new Point(1, 1);
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Pinky dengan parameter.
   * </p>
   *
   * @param i Posisi y pada Pinky.
   * @param j Posisi x pada Pinky.
   */
  public Pinky(int i, int j) {
    super(i, j, "\\assets\\pinky.gif");
    scatterDestination = new Point(1, 1);
  }

  /**
   * Menentukan titik target yang akan dicapai Pinky.
   */
  public void getNextDestination() {
    int destI = Player.getPlayerI();
    int destJ = Player.getPlayerJ();

    switch (Player.getOrientation()) {
      case 'n':
        destI = Math.max(destI - 4, 0);
        break;
      case 'e':
        destJ = Math.min(destJ + 4, Arena.getMapLength());
        break;
      case 's':
        destI = Math.min(destI + 4, Arena.getMapLength());
        break;
      case 'w':
        destJ = Math.max(destJ - 4, 0);
        break;
      default:
        break;
    }

    destination.setLocation(destI, destJ);
  }
}
package model.character;

import java.awt.Point;
import model.Arena;

/**
 * Kelas Inky mendefinisikan Ghost Inky pada permainan.
 *
 * @author Felix Limanta - 13515065
 */
public class Inky extends Ghost {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Inky.
   * </p>
   */
  public Inky() {
    super((int) Arena.getInkyInitPos().getX(), (int) Arena.getInkyInitPos().getY(),
        "\\assets\\inky.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, Arena.getMapWidth() - 2);
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Inky dengan parameter.
   * </p>
   *
   * @param i Posisi y pada Inky.
   * @param j Posisi x pada Inky.
   */
  public Inky(int i, int j) {
    super(i, j, "\\assets\\inky.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, Arena.getMapLength() - 2);
  }

  /**
   * Menentukan titik target yang akan dicapai Inky.
   */
  public void getNextDestination() {
    int destI = Player.getPlayerI();
    int destJ = Player.getPlayerJ();

    switch (Player.getOrientation()) {
      case 'n':
        destI -= 2;
        break;
      case 'e':
        destJ += 2;
        break;
      case 's':
        destI += 2;
        break;
      case 'w':
        destJ -= 2;
        break;
      default:
        break;
    }

    int deltaI = destI - Blinky.getBlinkyI();
    int deltaJ = destJ - Blinky.getBlinkyJ();

    destI = Math.min(Math.max(destI + deltaI, 0), Arena.getMapWidth() - 1);
    destJ = Math.min(Math.max(destJ + deltaJ, 0), Arena.getMapLength() - 1);

    destination.setLocation(destI, destJ);
  }
}

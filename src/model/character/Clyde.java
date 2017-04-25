package model.character;

import model.Arena;

import java.awt.*;

/**
 * Kelas Clyde mendefinisikan Ghost Clyde pada permainan.
 *
 * @author
 */
public class Clyde extends Ghost {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Clyde.
   * </p>
   */
  public Clyde() {
    super((int) Arena.getClydeInitPos().getX(), (int) Arena.getClydeInitPos().getY(),
        "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, 1);
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Ghost Clyde dengan parameter.
   * </p>
   *
   * @param i Posisi y pada Clyde.
   * @param j Posisi x pada Clyde.
   */
  public Clyde(int i, int j) {
    super(i, j, "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, 1);
  }

  /**
   * Menentukan titik target yang akan dicapai Inky.
   */
  public void getNextDestination() {
    int distance = PathFinder.manhattanDistance(this.position, Player.getPosition());
    if (distance >= 8) {
      destination = Player.getPosition();
    } else {
      destination = scatterDestination;
    }
  }
}

package model.character;

import java.awt.Point;
import model.Arena;

/**
 *
 */
public class Clyde extends Ghost {

  public Clyde() {
    super((int) Arena.getClydeInitPos().getX(), (int) Arena.getClydeInitPos().getY(), "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, 1);
  }

  public Clyde(int i, int j) {
    super(i, j, "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 2, 1);
  }

  public void getNextDestination() {
    int distance = PathFinder.manhattanDistance(this.position, Player.getPosition());
    if (distance >= 8) {
      destination = Player.getPosition();
    } else {
      destination = scatterDestination;
    }
  }
}

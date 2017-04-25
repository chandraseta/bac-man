package model.character;

import java.awt.Point;
import model.Arena;

/**
 *
 */
public class Clyde extends Ghost {

  public Clyde() {
    super((int) Arena.getClydeInitPos().getX(), (int) Arena.getClydeInitPos().getY(), "\\assets\\clyde.gif");
//    scatterDestination = new Point(Arena.getMapWidth() - 1, 0);
    scatterDestination = new Point(0,0);
  }

  public Clyde(int i, int j) {
    super(i, j, "\\assets\\clyde.gif");
//    scatterDestination = new Point(Arena.getMapWidth() - 1, 0);
    scatterDestination = new Point(0,0);
  }

  public void getNextDestination() {
    int distance = PathFinder.manhattanDistance(this.position, Player.getPosition());
    System.out.println(distance);
    if (distance >= 8) {
      destination = Player.getPosition();
    } else {
      destination = scatterDestination;
    }
    destination = scatterDestination;
  }
}

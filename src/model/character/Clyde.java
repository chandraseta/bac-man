package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Clyde extends Ghost {

  public Clyde() {
    super((int) Arena.getClydeInitPos().getX(), (int) Arena.getClydeInitPos().getY(), "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 1, 0);
  }

  public Clyde(int i, int j) {
    super(i, j, "\\assets\\clyde.gif");
    scatterDestination = new Point(Arena.getMapWidth() - 1, 0);
  }

  public void getNextDestination() {
    if (PathFinder.manhattanDistance(this.position, Player.getPosition()) >= 8) {
      destination = Player.getPosition();
    } else {
      destination = scatterDestination;
    }
  }
}

package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Clyde extends Ghost {

  public Clyde(int i, int j) {
    super(i, j, "\\assets\\ghost_d.png");
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

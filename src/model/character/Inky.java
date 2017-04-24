package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Inky extends Ghost{

  public Inky(int i, int j) {
    super(i, j, "\\assets\\ghost_c.png");
    scatterDestination = new Point(Arena.getMapWidth() - 1, Arena.getMapLength() - 1);
  }

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
    }

    int dI = destI - Blinky.getBlinkyI();
    int dJ = destJ - Blinky.getBlinkyJ();

    destI = Math.min(Math.max(destI + dI, 0), Arena.getMapWidth());
    destJ = Math.min(Math.max(destJ + dJ, 0), Arena.getMapLength());

    destination.setLocation(destI, destJ);
  }
}

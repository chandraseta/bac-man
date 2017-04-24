package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Pinky extends Ghost {

  public Pinky(int i, int j) {
    super(i, j, "\\assets\\ghost_b.png");
    scatterDestination = new Point(0,0);
  }

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
    }

    destination.setLocation(destI, destJ);
  }
}
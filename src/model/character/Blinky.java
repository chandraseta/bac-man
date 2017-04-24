package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Blinky extends Ghost {

  private static int blinkyI;
  private static int blinkyJ;

  public Blinky(int i, int j) {
    super(i, j, "\\assets\\ghost_a.png");
    scatterDestination = new Point(0, Arena.getMapWidth() - 1);
    blinkyI = i;
    blinkyJ = j;
  }

  public void getNextDestination() {
    destination = Player.getPosition();
  }

  public static int getBlinkyI() {
    return blinkyI;
  }

  public static int getBlinkyJ() {
    return blinkyJ;
  }

  public static void setBlinkyI(int newI) {
    blinkyI = newI;
  }

  public static void setBlinkyJ(int newJ) {
    blinkyJ = newJ;
  }
}
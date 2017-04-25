package model.character;

import model.Arena;

import java.awt.*;

/**
 *
 */
public class Blinky extends Ghost {

  private static int blinkyI;
  private static int blinkyJ;

  public Blinky() {
    super((int) Arena.getBlinkyInitPos().getX(), (int) Arena.getBlinkyInitPos().getY(), "\\assets\\blinky.gif");
    scatterDestination = new Point(1, Arena.getMapWidth() - 2);
    blinkyI = (int) Arena.getBlinkyInitPos().getX();
    blinkyJ = (int) Arena.getBlinkyInitPos().getY();
  }

  public Blinky(int i, int j) {
    super(i, j, "\\assets\\blinky.gif");
    scatterDestination = new Point(1, Arena.getMapWidth() - 2);
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
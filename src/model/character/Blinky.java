package model.character;

/**
 *
 */
public class Blinky extends Ghost {

  public static int blinkyI;
  public static int blinkyJ;

  public Blinky(int i, int j) {
    super(i, j, "\\assets\\ghost_a.png");
    blinkyI = i;
    blinkyJ = j;
  }

  public void getNextDestination() {
    destination.setLocation(Player.getPlayerI(), Player.getPlayerJ());
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
package model.character;

/**
 *
 */
public class Pinky extends Ghost {

  public Pinky(int i, int j) {
    super(i, j, "\\assets\\ghost_b.png");
  }

  public void getNextDestination() {
    // TODO: Check for matrix index boundary before assigning new destination.
  }
}
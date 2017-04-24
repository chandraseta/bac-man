package model.character;

/**
 *
 */
public class Pinky extends Ghost {

  public Pinky(int i, int j) {
    super(i, j, "\\assets\\ghost_b.png");
  }

  public void getNextDestination() {
    if (Player.getOrientation() == 's') {
      destination.setLocation(Player.getPlayerI()+4, Player.getPlayerJ());
    } else if (Player.getOrientation() == 'w') {
      destination.setLocation(Player.getPlayerI()-4, Player.getPlayerJ());
    } else if (Player.getOrientation() == 'a') {
      destination.setLocation(Player.getPlayerI(), Player.getPlayerJ()-4);
    } else {
      destination.setLocation(Player.getPlayerI(), Player.getPlayerJ()+4);
    }
  }
}
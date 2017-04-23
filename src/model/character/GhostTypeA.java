package model.character;

/**
 * Kelas berisi GhostTypeA yang menggunakan algoritma A* dalam mengejar player.
 */
public class GhostTypeA extends Ghost {
  public GhostTypeA(int i, int j) {
    super(i, j, "\\assets\\ghost_a.png");
  }

  public int moveTowardsPlayer() {
    return (new PathFinder(this.position, Player.getPosition())).getMovement();
  }
}

package model.character;

import model.GameCharacter;
import model.Arena;

import java.awt.Point;
import java.util.Random;

/**
 * Kelas abstract Ghost yang menjadi dasar berbagai tipe Ghost.
 */
public abstract class Ghost extends GameCharacter {
  /**
   * Berisi informasi status Ghost
   * 0 = Normal
   * 1 = Vulnerable
   * 2 = Dead
   */
  protected int state;

  protected Point destination;
  protected Point scatterDestination;

  protected PathFinder pathFinder;

  public Ghost(int i, int j, String sprite) {
    super(i, j, sprite);
    state = 0;
    destination = new Point(Player.getPlayerI(), Player.getPlayerJ());
    pathFinder = new PathFinder();
  }

  /**
   * Setter nilai state.
   * PREKONDISI: state [0..2]
   *
   * @param state Nilai state baru.
   */
  public void setState(int state) {
    this.state = state;
  }

  public boolean isNormal() {
    return state == 0;
  }

  public boolean isVulnerable() {
    return state == 1;
  }

  public boolean isDead() {
    return state == 2;
  }

  public abstract void getNextDestination();

  public int scatter() {
    pathFinder.calculateMovement(this.position, scatterDestination);
    return pathFinder.getMovement();
  }

  /**
   * Menggerakkan Ghost menuju Player saat kondisi normal.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status normal.
   */
  public int moveTowardsPlayer() {
    getNextDestination();
    System.out.println(destination);
    pathFinder.calculateMovement(this.position, destination);
    return pathFinder.getMovement();
  }

  /**
   * Menggerakkan Ghost menjauhi Player saat Player dalam mode Super.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status vulnerable.
   */
  public int moveAwayFromPlayer() {
    int [] weights = new int[4];
    Point ghostPos = this.position;
    Point playerPos = Player.getPosition();

    weights[0] = PathFinder.manhattanDistance(new Point(ghostPos.x - 1, ghostPos.y), playerPos);
    weights[1] = PathFinder.manhattanDistance(new Point(ghostPos.x, ghostPos.y + 1), playerPos);
    weights[2] = PathFinder.manhattanDistance(new Point(ghostPos.x + 1, ghostPos.y), playerPos);
    weights[3] = PathFinder.manhattanDistance(new Point(ghostPos.x, ghostPos.y - 1), playerPos);

    int movement = 0;
    int weight = weights[0];
    for (int i = 1; i < 4; ++i) {
      if (weights[i] > weight || weights[i] == weight && (new Random()).nextBoolean()) {
        weight = weights[i];
        movement = i + 1;
      }
    }

    return movement;
  }

  /**
   * Menggerakkan Ghost menuju Base setelah dimakan oleh Player.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status dead.
   */
  public int returnToBase() {
    int [] weights = new int[4];
    Point ghostPos = this.position;
    Point center = Arena.getRespawnPos();

    weights[0] = PathFinder.manhattanDistance(new Point(ghostPos.x - 1, ghostPos.y), center);
    weights[1] = PathFinder.manhattanDistance(new Point(ghostPos.x, ghostPos.y + 1), center);
    weights[2] = PathFinder.manhattanDistance(new Point(ghostPos.x + 1, ghostPos.y), center);
    weights[3] = PathFinder.manhattanDistance(new Point(ghostPos.x, ghostPos.y - 1), center);

    int movement = 0;
    int weight = weights[0];
    for (int i = 1; i < 4; ++i) {
      if (weights[i] > weight || weights[i] == weight && (new Random()).nextBoolean()) {
        weight = weights[i];
        movement = i + 1;
      }
    }

    return movement;
  }
}

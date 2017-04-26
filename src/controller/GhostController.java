package controller;

import model.Arena;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Ghost;
import model.character.Inky;
import model.character.Pinky;

/**
 * Kelas controller untuk Ghost.
 *
 * @author Felix Limanta - 13515065
 */
public class GhostController implements Runnable {

  /**
   * Objek Ghost yang dikendalikan pada permainan.
   */
  private Ghost [] ghosts;

  /**
   * Thread pada Ghost.
   */
  private Thread ghostThread;

  /**
   * Nama pada Thread.
   */
  private String threadName;
  
  private int [] movement = new int[4];

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Controller untuk objek Ghost dengan parameter type.
   * </p>
   *
   */
  public GhostController() {
    ghosts = new Ghost[] {
        new Blinky(Arena.getBlinkyInitPos().x, Arena.getBlinkyInitPos().y),
        new Pinky(Arena.getPinkyInitPos().x, Arena.getPinkyInitPos().y),
        new Inky(Arena.getInkyInitPos().x, Arena.getInkyInitPos().y),
        new Clyde(Arena.getClydeInitPos().x, Arena.getClydeInitPos().y)
    };
    this.threadName = "GhostController";
    
  }

  /**
   * Fungsi menentukan pergerakan Ghost selanjutnya.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost berdasarkan kondisi Ghost.
   */
  public void nextMovement() {
    for (int i = 0; i < ghosts.length; ++i) {
      if (ghosts[i].isNormal()) {
        movement[i] = ghosts[i].moveTowardsPlayer();
      } else if (ghosts[i].isVulnerable()) {
        movement[i] = ghosts[i].moveAwayFromPlayer();
      } else {
        movement[i] = ghosts[i].returnToBase();
      }
    }
  }

  /**
   * Method untuk menggerakkan Ghost berdasarkan kondisi Ghost.
   */
  public void run() {
    try {
      while (true) {
        nextMovement();
        for (int i = 0; i < ghosts.length; ++i) {
          if (movement[i] == 1) {
            ghosts[i].moveUp();
            if (ghosts[i] instanceof Blinky) {
              Blinky.setBlinkyI(ghosts[i].getI() - 1);
            }
          } else if (movement[i] == 2) {
            ghosts[i].moveRight();
            if (ghosts[i] instanceof Blinky) {
              Blinky.setBlinkyJ(ghosts[i].getJ() + 1);
            }
          } else if (movement[i] == 3) {
            ghosts[i].moveDown();
            if (ghosts[i] instanceof Blinky) {
              Blinky.setBlinkyI(ghosts[i].getI() + 1);
            }
          } else {
            ghosts[i].moveLeft();
            if (ghosts[i] instanceof Blinky) {
              Blinky.setBlinkyJ(ghosts[i].getJ() - 1);
            }
          }
        }
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method untuk memulai pergerakan Ghost.
   */
  public void start() {
    if (ghostThread == null) {
      ghostThread = new Thread(this, threadName);
      ghostThread.start();
    }
  }

  /**
   * Fungsi yang mengembalikan Objek Ghost pada permainan
   *
   * @return Objek Ghost pada permainan.
   */
  public Ghost [] getGhosts() {
    return ghosts;
  }
}

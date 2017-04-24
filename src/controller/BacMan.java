package controller;

import model.Arena;
import view.GameView;

/**
 * Kelas berisi semua aspek game BacMan.
 */
public class BacMan {
  private GameView gameView;
  private PlayerController bac;
  private ArenaController gameArena;
  private GhostController[] ghost;
  public static int score = 0;
  private int playerSuperDuration;
  private boolean gameEnd;

  public static void main(String[] args) {
    PlayerController bac = new PlayerController();
    GhostController[] ghostList = new GhostController[4];
    ghostList[0] = new GhostController((int) Arena.getBlinkyInitPos().getX(),
        (int) Arena.getBlinkyInitPos().getY(), 'a');
    ghostList[1] = new GhostController((int) Arena.getPinkyInitPos().getX(),
        (int) Arena.getPinkyInitPos().getY(), 'b');
    ghostList[2] = new GhostController((int) Arena.getInkyInitPos().getX(),
        (int) Arena.getInkyInitPos().getY(), 'c');
    ghostList[3] = new GhostController((int) Arena.getClydeInitPos().getX(),
        (int) Arena.getClydeInitPos().getY(), 'd');
    ArenaController gameArena = new ArenaController();
  }
}

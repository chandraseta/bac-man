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
  public static int score;
  private static boolean gameEnd;

  public static void main(String[] args) {
    ArenaController gameArena = new ArenaController();
    gameEnd = false;
    score = 0;
    PlayerController bac = new PlayerController();
    GhostController[] ghostList = new GhostController[4];
    ghostList[0] = new GhostController('a');
    ghostList[1] = new GhostController('b');
    ghostList[2] = new GhostController('c');
    ghostList[3] = new GhostController('d');
  }

  public static int getScore() {
    return score;
  }

  public static boolean isGameEnd() {
    return gameEnd;
  }

  public static void addScore(int value) {
    score += value;
  }
}

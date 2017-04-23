package controller;

import view.GameView;

/**
 * Kelas berisi semua aspek game BacMan.
 */
public class BacMan {
  private GameView gameView;
  private PlayerController bac;
  private ArenaController gameArena;
  private GhostController[] ghost;
  private int score;
  private int playerSuperDuration;
  private boolean gameEnd;

  public static void main(String[] args) {
    
  }
}

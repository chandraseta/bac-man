package controller;

import model.Arena;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;
import view.GameView;
import view.LoadingView;
import view.TitleView;

/**
 * Kelas berisi semua aspek game BacMan.
 */
public class BacMan {

  private GameView gameView;
  private PlayerController bac;
  private static Arena gameArena;
  private GhostController[] ghost;
  public static int score;
  private static boolean gameEnd;

  public static void main(String[] args) {
    gameEnd = false;
    score = 0;
    gameArena = new Arena();
    PlayerController bacman = new PlayerController();
    GhostController[] ghostList = new GhostController[4];
    ghostList[0] = new GhostController('a');
    ghostList[1] = new GhostController('b');
    ghostList[2] = new GhostController('c');
    ghostList[3] = new GhostController('d');
    TitleView titleView = new TitleView();
    while (TitleView.visibility) {
      System.out.println();
    }
    LoadingView loadingView = new LoadingView();
    loadingView.setVisible(true);
    GameView gameView = new GameView(bacman, (Blinky) ghostList[0].getGhost(),
        (Inky) ghostList[2].getGhost(),
        (Pinky) ghostList[1].getGhost(), (Clyde) ghostList[3].getGhost());
    loadingView.setVisible(false);
    gameView.setVisible(true);
    bacman.start();
    gameView.start();
    for (int i = 0; i < 4; i++) {
      ghostList[i].start();
    }
    while (!BacMan.isGameEnd()) {
      bacman.run();
      for (int i = 0; i < 4; i++) {
        ghostList[i].run();
      }
      gameView.run();
      gameEnd = (Arena.getCookieLeft() == 0);
    }
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

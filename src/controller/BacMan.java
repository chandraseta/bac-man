package controller;

import java.util.concurrent.TimeUnit;
import javax.swing.SwingWorker;
import model.Arena;
import model.VisibilityGraph;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Ghost;
import model.character.Inky;
import model.character.Pinky;
import model.character.Player;
import model.element.Cookie;
import view.CreditsView;
import view.GameOverView;
import view.GameView;
import view.LoadingView;
import view.TitleView;

/**
 * Kelas berisi semua aspek game BacMan.
 *
 * @author Oktavianus Handika - 13515035
 */
public class BacMan {

  /**
   * Score Player.
   */
  public static int score;
  /**
   * Controller untuk objek Player pada permainan.
   */
  private static PlayerController bac;

  /**
   * Arena permainan BacMan.
   */
  private static Arena gameArena;

  /**
   * Controller untuk objek Ghost pada permainan.
   */
//  private static GhostController[] ghostList;
  private static GhostController ghosts;

  /**
   * Penanda permainan sudah berakhir atau belum.
   */
  private static boolean gameEnd;
  /**
   * Tampilan permainan BacMan.
   */
  private GameView gameView;

  /**
   * Mengembalikan score Player.
   *
   * @return score yang didapat Player.
   */
  public static int getScore() {
    return score;
  }

  /**
   * Mengembalikan apakah permainan berakhir atau tidak.
   *
   * @return gameEnd permainan sudah berakhir atau belum.
   */
  public static boolean isGameEnd() {
    return gameEnd;
  }

  /**
   * Menambahkan score Player sejumlah value.
   *
   * @param value nilai yang ditambahkan ke score Player.
   */
  public static void addScore(int value) {
    score += value;
  }

  /**
   * Mengubah nilai gameEnd berdasarkan salah satu kondisi.
   * 1. Semua cookie telah dimakan.
   * 2. Player dan Ghost berada pada petak yang sama.
   *
   */
  public static void updateGameEnd() {
    boolean gameEnd = (Cookie.getCookieLeft() == 0);
    for (Ghost ghost: ghosts.getGhosts()) {
      gameEnd = gameEnd || (Player.getPlayerI() == ghost.getI() && Player.getPlayerJ() == ghost.getJ());
    }
  }

  /**
   * Program utama.
   *
   * @param args Command line argument.
   */
  public static void main(String[] args) {
    gameEnd = false;
    score = 0;
    gameArena = new Arena();
    new VisibilityGraph();
    bac = new PlayerController();
    ghosts = new GhostController();
    TitleView titleView = new TitleView();
    titleView.visibility = true;
    titleView.setVisible(true);
    while (TitleView.visibility) {
      System.out.println();
    }
    LoadingView loadingView = new LoadingView();
    loadingView.setVisible(true);
    try {
      TimeUnit.MILLISECONDS.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    GameView gameView = new GameView(bac, (Blinky) ghosts.getGhosts()[0],
        (Inky) ghosts.getGhosts()[2],
        (Pinky) ghosts.getGhosts()[1], (Clyde) ghosts.getGhosts()[3]);
    loadingView.setVisible(false);
    loadingView.dispose();
    gameView.setVisible(true);
    bac.start();
    ghosts.start();
    while (!gameEnd) {
      SwingWorker<Void, Void> gameWorker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
          gameView.updateGameView();
          Thread.sleep(1000);
          return null;
        }
      };
      gameWorker.execute();
      try {
        TimeUnit.MILLISECONDS.sleep(1000);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
      updateGameEnd();
    }
    GameOverView gameOverView = new GameOverView();
    titleView.dispose();
    gameView.setVisible(false);
    gameOverView.setVisible(true);
    gameView.dispose();
    try {
      TimeUnit.MILLISECONDS.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    CreditsView creditsView = new CreditsView();
    gameOverView.setVisible(false);
    gameOverView.dispose();
    creditsView.setVisible(true);
    creditsView.dispose();
  }
}

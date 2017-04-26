package controller;

import model.Arena;
import model.character.Player;

/**
 * Kelas controller untuk Player.
 *
 * @author Rionaldi Chandraseta - 13515077
 */
public class PlayerController implements Runnable {

  /**
   * Atribut objek Player.
   */
  private Player player;

  /**
   * Thread pada objek Player.
   */
  private Thread playerThread;

  /**
   * Nama pada Thread Player.
   */
  private String threadName;

  /**
   * Arah gerak pada Player.
   */
  private int direction;


  /**
   * <p>
   * Constructor
   *
   * Menciptakan PlayerController untuk mengendalikan Player saat permainan berlangsung.
   * </p>
   */
  public PlayerController() {
    player = new Player();
    this.threadName = "PlayerThread";
    direction = 0;
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan PlayerController dengan parameter.
   * </p>
   *
   * @param i Posisi y Player.
   * @param j Posisi x Player.
   * @param sprite Path direktori sprite Player.
   * @param threadName Nama Thread Player.
   */
  public PlayerController(int i, int j, String sprite, String threadName) {
    player = new Player(i, j, sprite);
    this.threadName = threadName;
  }

  /**
   * Mengatur arah pergerakan Player.
   *
   * @param direction Arah gerak Player.
   */
  public void setDirection(int direction) {
    this.direction = direction;
  }

  /**
   * Mengatur tampilan sprite player sesuai arah gerak Player.
   */
  public void move() {
    if (direction == 1) {
      if (Arena.getGrid(player.getI() - 1, player.getJ()).isAccessible()) {
        player.moveUp();
        Player.setPlayerI(player.getI() - 1);
      }
      Player.setOrientation('n');
      player.setNewImage("\\assets\\bacbac_up.gif");
    } else if (direction == 2) {
      if ((player.getI() == Arena.getTunnelRow() && (player.getJ() == Arena.getMapLength() - 1))) {
        player.teleport(player.getI(), 0);
        Player.setPlayerJ(0);
      }
      else if (Arena.getGrid(player.getI(), player.getJ() + 1).isAccessible()) {
        player.moveRight();
        Player.setPlayerJ(player.getJ() + 1);
      }
      Player.setOrientation('e');
      player.setNewImage("\\assets\\bacbac_right.gif");
    } else if (direction == 3) {
      if (Arena.getGrid(player.getI() + 1, player.getJ()).isAccessible()) {
        player.moveDown();
        Player.setPlayerI(player.getI() + 1);
      }
      Player.setOrientation('s');
      player.setNewImage("\\assets\\bacbac_down.gif");
    } else if (direction == 4) {
      if ((player.getI() == Arena.getTunnelRow()) && (player.getJ() == 0)) {
        player.teleport(player.getI(), Arena.getMapLength() - 1);
        Player.setPlayerJ(Arena.getMapLength() - 1);
      } else if (Arena.getGrid(player.getI(), player.getJ() - 1).isAccessible()) {
        player.moveLeft();
        Player.setPlayerJ(player.getJ() - 1);
      }
      Player.setOrientation('w');
      player.setNewImage("\\assets\\bacbac_left.gif");
    }
    if (Arena.getGrid(player.getI(), player.getJ()).getCookie().isAvailable()) {
      BacMan.addScore(Arena.getGrid(player.getI(), player.getJ()).getCookie().getValue());
      Arena.getGrid(player.getI(), player.getJ()).removeCookie();
    }
  }

  /**
   * Mengatur jalannya Thread Player selama permainan.
   */
  public void run() {
    try {
      while (true) {
        move();
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Memulai jalannya Thread pada Player.
   */
  public void start() {
    if (playerThread == null) {
      playerThread = new Thread(this, threadName);
      playerThread.start();
    }
  }

  /**
   * Fungsi mengembalikan objek Player.
   *
   * @return Objek Player pada permainan.
   */
  public Player getPlayer() {
    return player;
  }
}

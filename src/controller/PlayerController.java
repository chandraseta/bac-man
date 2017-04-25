package controller;

import model.Arena;
import model.character.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController implements Runnable, KeyListener {

  /**
   * Atribut objek Player.
   */
  private Player player;

  /**
   * Thread pada objek Player.
   */
  private Thread pThread;

  /**
   * Nama pada Thread Player.
   */
  private String threadName;

  /**
   * Arah gerak pada Player.
   */
  private int direction;

  /**
   *
   */
  public static boolean[] keyPressed = new boolean[256];

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

  @Override
  /**
   * 
   */
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    keyPressed[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyPressed[e.getKeyCode()] = false;
  }

  public int getDirection() {
    /*
    if ((keyPressed[KeyEvent.VK_W]) || (keyPressed[KeyEvent.VK_UP])) {
      return 1;
    } else if ((keyPressed[KeyEvent.VK_D]) || (keyPressed[KeyEvent.VK_RIGHT])) {
      return 2;
    } else if ((keyPressed[KeyEvent.VK_S]) || (keyPressed[KeyEvent.VK_DOWN])) {
      return 3;
    } else if ((keyPressed[KeyEvent.VK_A]) || (keyPressed[KeyEvent.VK_LEFT])) {
      return 4;
    } else {
    */
    return 2;
    //}
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public void move() {
    if (direction == 1) {
      if (Arena.getGrid(player.getI() - 1, player.getJ()).isAccessible()) {
        player.moveUp();
        Player.setPlayerI(player.getI() - 1);
      }
      Player.setOrientation('n');
      player.setNewImage("\\assets\\bacbac_up.gif");
    } else if (direction == 2) {
      if (Arena.getGrid(player.getI(), player.getJ() + 1).isAccessible()) {
        player.moveRight();
        Player.setPlayerJ(player.getJ() + 1);
        Arena.getGrid(player.getI(), player.getJ()).removeCookie();
      } else if ((player.getI() == Arena.getTunnelRow())
          && (player.getJ() == Arena.getMapLength() - 1)) {
        player.teleport(player.getI(), 0);
        Player.setPlayerJ(0);
        Arena.getGrid(player.getI(), player.getJ()).removeCookie();
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
      if (Arena.getGrid(player.getI(), player.getJ() - 1).isAccessible()) {
        player.moveLeft();
        Player.setPlayerJ(player.getJ() - 1);
      } else if ((player.getI() == Arena.getTunnelRow()) && (player.getJ() == 0)) {
        player.teleport(player.getI(), Arena.getMapLength() - 1);
        Player.setPlayerJ(Arena.getMapLength() - 1);
      }
      Player.setOrientation('w');
      player.setNewImage("\\assets\\bacbac_left.gif");
    }
    if (Arena.getGrid(player.getI(), player.getJ()).getCookie().isAvailable()) {
      BacMan.addScore(Arena.getGrid(player.getI(), player.getJ()).getCookie().getValue());
      Arena.getGrid(player.getI(), player.getJ()).removeCookie();
      Arena.eatCookie();
    }
    //////// DEBUG /////////
    System.out.println("Player position: " + player.getI() + " " + player.getJ());
    System.out.println("Player orientation: " + Player.getOrientation());
    ////////////////////////
  }

  public void run() {
    try {
      while (true) {
        move();
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    if (pThread == null) {
      pThread = new Thread(this, threadName);
      pThread.start();
    }
  }

  public Player getPlayer() {
    return player;
  }
}

package view;

import controller.PlayerController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;

/**
 * Kelas GameView yang berisi tampilan utama saat permainan berjalan.
 *
 * @author Holy Lovenia - 13515113
 */
public class GameView extends JFrame implements Runnable {

  private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  private static final String MOVE_UP = "move up";
  private static final String MOVE_RIGHT = "move right";
  private static final String MOVE_DOWN = "move down";
  private static final String MOVE_LEFT = "move left";
  private static JLabel keyInput = new JLabel();
  /**
   * Panel berisi Arena permainan.
   */
  private MapPanel mapPanel;
  /**
   * Panel berisi info permainan.
   */
  private InfoPanel infoPanel;
  /**
   * Thread untuk menjalankan interface program.
   */
  private Thread thread;
  /**
   * Nama Thread.
   */
  private String threadName;
  private JPanel gamePanel = new JPanel();
  private GridBagLayout gridBag = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

  /**
   * <p>
   * Constructor
   *
   * Menciptakan tampilan pada permainan.
   * </p>
   *
   * @param bacman PlayerController pada Player.
   * @param blinky Objek Blinky.
   * @param inky Objek Inky.
   * @param pinky Objek Pinky.
   * @param clyde Objek Clyde.
   */
  public GameView(PlayerController bacman, Blinky blinky, Inky inky, Pinky pinky, Clyde clyde) {
    mapPanel = new MapPanel(bacman, blinky, inky, pinky, clyde);
    infoPanel = new InfoPanel();
    updateGameView();
    threadName = "ViewThread";
    keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
    keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
    keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
    keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
    keyInput.getActionMap().put(MOVE_UP, new MoveAction(1, bacman));
    keyInput.getActionMap().put(MOVE_RIGHT, new MoveAction(2, bacman));
    keyInput.getActionMap().put(MOVE_DOWN, new MoveAction(3, bacman));
    keyInput.getActionMap().put(MOVE_LEFT, new MoveAction(4, bacman));
    add(keyInput);
  }

   /**
   * Melakukan update pada tampilan permainan.
   */
  public void updateGameView() {
    setTitle("Game Screen");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //mapPanel.revalidate();
    //infoPanel.revalidate();
    mapPanel.repaint();
    infoPanel.repaint();

    gamePanel.setLayout(gridBag);

    gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gridBag.setConstraints(mapPanel, gbc);
    gamePanel.add(mapPanel);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gridBag.setConstraints(infoPanel, gbc);
    gamePanel.add(infoPanel);

    add(gamePanel);
    pack();
  }

  /**
   * Mengatur jalannya Thread GameView selama permainan.
   */
  public void run() {
    try {
      while (true) {
        updateGameView();
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Memulai jalannya Thread pada GameView.
   */
  public void start() {
    if (thread == null) {
      thread = new Thread(this, threadName);
      thread.start();
    }
  }

  /**
   * Kelas MoveAction diturunkan dari AbstractAction.
   */
  public class MoveAction extends AbstractAction {

    int direction;
    PlayerController playerController;

    /**
     * <p>
     * Constructor
     *
     * Menciptakan MoveAction untuk menghubungkan PlayerController dengan arah gerak Player.
     * </p>
     *
     * @param direction Arah gerak Player.
     * @param playerController Objek Controller untuk Player.
     */
    MoveAction(int direction, PlayerController playerController) {
      this.direction = direction;
      this.playerController = playerController;
    }

    /**
     * Override actionPerformed Method.
     *
     * @param e Instance dari ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      playerController.setDirection(direction);
      playerController.move();
    }
  }

}

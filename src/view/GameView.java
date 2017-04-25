package view;

import controller.PlayerController;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView extends JFrame implements Runnable {

  private MapPanel mapPanel;
  private InfoPanel infoPanel;
  private Thread thread;
  private String threadName;
  private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  private static final String MOVE_UP = "move up";
  private static final String MOVE_RIGHT = "move right";
  private static final String MOVE_DOWN = "move down";
  private static final String MOVE_LEFT = "move left";
  private static JLabel keyInput = new JLabel();
  private JPanel gamePanel = new JPanel();
  private GridBagLayout gridBag = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

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

  public void updateGameView() {
    setTitle("Game Screen");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mapPanel.revalidate();
    //infoPanel.revalidate();
    mapPanel.repaint();
    //infoPanel.repaint();

    gamePanel.setLayout(gridBag);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gridBag.setConstraints(mapPanel, gbc);
    gamePanel.add(mapPanel);

    gbc.gridx = 1;
    gbc.gridy = 0;
    //gridBag.setConstraints(infoPanel, gbc);
    //gamePanel.add(infoPanel);

    add(gamePanel);
    pack();
  }

  public void run() {
    try {
      while (true) {
        updateGameView();
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    if (thread == null) {
      thread = new Thread(this, threadName);
      thread.start();
    }
  }

  public class MoveAction extends AbstractAction {

    int direction;
    PlayerController playerController;

    MoveAction(int direction, PlayerController playerController) {
      this.direction = direction;
      this.playerController = playerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      playerController.setDirection(direction);
      playerController.move();
    }
  }

}

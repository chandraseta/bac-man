package view;

import controller.BacMan;
import model.Arena;
import model.character.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView extends JFrame {
  private int scale;
  protected JLabel[][] mapLabel;
  private static final int DEFAULT_SCALE = 40;

  public GameView(Player bacman) {
    JFrame loadingFrame = new JFrame();
    JPanel loadingPanel = new JPanel();
    loadingPanel.setPreferredSize(new Dimension(1600, 950));
    loadingPanel.setBackground(new Color(180, 180, 180));
    GridBagLayout loadingLayout = new GridBagLayout();
    loadingPanel.setLayout(loadingLayout);
    GridBagConstraints loadingConstraints = new GridBagConstraints();
    loadingConstraints.fill = GridBagConstraints.BOTH;

    loadingConstraints.gridx = 0;
    loadingConstraints.gridy = 1;
    URL sleeping_bacbac_path = getClass().getResource("\\assets\\loading.gif");
    JLabel sleepingBacbac = new JLabel(new ImageIcon(sleeping_bacbac_path), JLabel.CENTER);
    sleepingBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
    loadingLayout.setConstraints(sleepingBacbac, loadingConstraints);
    loadingPanel.add(sleepingBacbac);

    loadingConstraints.gridx = 0;
    loadingConstraints.gridy = 0;
    JLabel loadingText = new JLabel("Loading...", JLabel.CENTER);
    loadingText.setFont(new Font("Arial", Font.CENTER_BASELINE, 100));
    loadingText.setForeground(Color.WHITE);
    loadingText.setBorder(new EmptyBorder(90, 10, 10, 10));
    loadingLayout.setConstraints(loadingText, loadingConstraints);
    loadingPanel.add(loadingText);

    loadingConstraints.gridx = 0;
    loadingConstraints.gridy = 2;
    URL loading_balls_path = getClass().getResource("\\assets\\loading-bar.gif");
    JLabel loadingBalls = new JLabel(new ImageIcon(loading_balls_path), JLabel.CENTER);
    //loadingBalls.setBorder(new EmptyBorder(240, 10, 10, 10));
    loadingLayout.setConstraints(loadingBalls, loadingConstraints);
    loadingPanel.add(loadingBalls);
    loadingFrame.add(loadingPanel);
    loadingFrame.pack();
    loadingFrame.setVisible(true);


    scale = DEFAULT_SCALE;
    setTitle("Game Screen");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel mapPanel = setMapPanel(bacman);
    JPanel infoPanel = setInfoPanel();
    JPanel gamePanel = new JPanel();

    GridBagLayout gridBag = new GridBagLayout();
    gamePanel.setLayout(gridBag);

    GridBagConstraints gbc = new GridBagConstraints();
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

    loadingFrame.setVisible(false);
    setVisible(true);
  }

  public JPanel setMapPanel(Player bacman) {
    JPanel mapPanel = new JPanel();
    mapPanel.setPreferredSize(new Dimension(1100, 950));
    mapPanel.setBackground(Color.black);
    mapPanel.setLayout(new GridLayout(Arena.getMapWidth(), Arena.getMapLength()));
    mapLabel = new JLabel[Arena.getMapWidth()][Arena.getMapLength()];
    for (int i = 0; i < Arena.getMapWidth(); i++) {
      for (int j = 0; j < Arena.getMapLength(); j++) {
        if((i == bacman.getI()) && (j == bacman.getJ())) {
          mapLabel[i][j] = createLabel(bacman.getImgPath());
          mapLabel[i][j].add(createLabel(Arena.getGrid(i, j).getImgPath()));
        } else {
          mapLabel[i][j] = createLabel(Arena.getGrid(i, j).getImgPath());
        }
      }
    }
    for (int i = 0; i < Arena.getMapWidth(); i++) {
      for (int j = 0; j < Arena.getMapLength(); j++) {
        mapPanel.add(mapLabel[i][j]);
      }
    }

    return mapPanel;
  }

  public JPanel setInfoPanel() {
    Font screenFont = null;
    Font scoreFont = null;
    try {
      screenFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\pac-font.ttf").openStream());
      scoreFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JPanel infoPanel = new JPanel();
    infoPanel.setBackground(new Color(0, 0, 0));
    infoPanel.setPreferredSize(new Dimension(500, 950));

    JLabel title = new JLabel("BACMAN", JLabel.CENTER);
    title.setBorder(new EmptyBorder(60, 10, 10, 10));
    title.setFont(screenFont.deriveFont(60f));
    title.setForeground(Color.WHITE);
    infoPanel.add(title);

    URL mascot_path = getClass().getResource("\\assets\\hooray.gif");
    JLabel mascot = new JLabel(new ImageIcon(mascot_path), JLabel.CENTER);
    mascot.setBorder(new EmptyBorder(100, 40, 10, 10));
    infoPanel.add(mascot);

    JLabel name = new JLabel("NAME: BACMAN", JLabel.LEFT);
    name.setBorder(new EmptyBorder(150, 10, 10, 10));
    name.setFont(scoreFont.deriveFont(120f));
    name.setForeground(Color.WHITE);
    infoPanel.add(name);

    JLabel score = new JLabel("SCORE: " + BacMan.score, JLabel.LEFT);
    score.setBorder(new EmptyBorder(10, 10, 10, 10));
    score.setFont(scoreFont.deriveFont(120f));
    score.setForeground(Color.WHITE);
    infoPanel.add(score);

    return infoPanel;
  }

  public boolean isGIF(String image_path) {
    return(image_path.contains(".gif"));
  }

  public JLabel createLabel(String image_path) {
    URL img_path = getClass().getResource(image_path);
    if(isGIF(image_path)) {
      return new JLabel(new ImageIcon(img_path));
    } else {
      ImageIcon imageIcon = new ImageIcon(img_path);
      Image image = imageIcon.getImage();
      Image newImage = image.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
      return new JLabel(new ImageIcon(newImage));
    }
  }

  public static void main(String[] args) {
    Player bacman = new Player();
    Arena arena = new Arena();
    GameView gameView = new GameView(bacman);
  }
}

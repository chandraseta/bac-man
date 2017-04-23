package view;

import model.Arena;
import model.character.Player;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView extends JFrame {
  private int scale;
  protected JLabel[][] mapLabel;
  private static final int DEFAULT_SCALE = 40;

  public GameView(Player bacman) {
    scale = DEFAULT_SCALE;
    JPanel gamePanel = new JPanel();
    gamePanel.setBackground(Color.black);
    gamePanel.setLayout(new GridLayout(Arena.getMapWidth(), Arena.getMapLength()));
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

    mapLabel[bacman.getI()][bacman.getJ()].add(createLabel(bacman.getImgPath()));

    for (int i = 0; i < Arena.getMapWidth(); i++) {
      for (int j = 0; j < Arena.getMapLength(); j++) {
        gamePanel.add(mapLabel[i][j]);
      }
    }
    add(gamePanel);
    setVisible(true);
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

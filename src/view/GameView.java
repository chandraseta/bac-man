package view;

import model.Arena;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView extends JFrame {
  private int scale;
  protected JPanel gamePanel;
  private static final int DEFAULT_SCALE = 40;

  public GameView(Arena arena) {
    scale = DEFAULT_SCALE;
    gamePanel = new JPanel();
    gamePanel.setBackground(Color.black);
    gamePanel.setLayout(new GridLayout(arena.getMapWidth(), arena.getMapLength()));
    for (int i = 0; i < arena.getMapWidth(); i++) {
      for (int j = 0; j < arena.getMapLength(); j++) {
        gamePanel.add(createLabel(arena.getGrid(i, j).getImgPath()));
      }
    }
    add(gamePanel);
    setVisible(true);
  }

  public boolean isGIF(String image_path) {
    return(image_path.indexOf(".gif") != -1);
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
    Arena arena = new Arena();
    GameView gameView = new GameView(arena);
  }
}

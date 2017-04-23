package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView {

  private int scale;
  private int length;
  private int width;
  private char[][] asciiMap;
  private JLabel[][] map;
  private static final String MAP_PATH = "src\\view\\assets\\map.txt";
  private static final int DEFAULT_SCALE = 30;

  public GameView() {
    scale = DEFAULT_SCALE;
    readMap();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.black);
    panel.setLayout(new GridLayout(width, length));
    map = new JLabel[width][length];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        setImageIcon(i, j);
        panel.add(map[i][j]);
      }
    }
    frame.add(panel);
    frame.setVisible(true);
  }

  public void readMap() {
    try {
      FileInputStream fstream;
      DataInputStream instream;
      BufferedReader buffread;
      fstream = new FileInputStream(String.format("%s", MAP_PATH));
      instream = new DataInputStream(fstream);
      buffread = new BufferedReader(new InputStreamReader(instream));
      String[] strSplit = buffread.readLine().split("\\s+");
      length = Integer.parseInt(strSplit[0]);
      width = Integer.parseInt(strSplit[1]);
      asciiMap = new char[width][length];
      String line = buffread.readLine();
      for (int row = 0; row < width; row++) {
        line = buffread.readLine();
        for (int col = 0; col < length; col++) {
          asciiMap[row][col] = line.charAt(col);
        }
      }
      buffread.close();
      instream.close();
      fstream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getImagePath(int row, int col) {
    String image_path = null;
    switch (asciiMap[row][col]) {
      case '-':
        image_path = "src\\view\\assets\\wall.png";
        break;
      case 'o':
        image_path = "src\\view\\assets\\cookie.png";
        break;
      case ' ':
        image_path = "src\\view\\assets\\grid.png";
        break;
      case 'A':
        image_path = "src\\view\\assets\\ghost_a.png";
        break;
      case 'B':
        image_path = "src\\view\\assets\\ghost_b.png";
        break;
      case 'C':
        image_path = "src\\view\\assets\\ghost_c.png";
        break;
      case 'D':
        image_path = "src\\view\\assets\\ghost_d.png";
        break;
      case 'P':
        image_path = "src\\view\\assets\\player.png";
        break;
      case 'S':
        image_path = "src\\view\\assets\\super_cookie.png";
        break;
      default:
        break;
    }
    return image_path;
  }

  public void setImageIcon(int row, int col) {
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(getImagePath(row, col)));
      ImageIcon icon = new ImageIcon(bufferedImage);
      Image img = icon.getImage();
      Image new_img = bufferedImage.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
      icon = new ImageIcon(new_img);
      map[row][col] = new JLabel(icon);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    GameView gameView = new GameView();
  }
}

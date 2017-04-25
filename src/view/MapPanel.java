package view;

import controller.PlayerController;
import model.Arena;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Kelas MapPanel mendefinisikan tampilan arena.
 */
public class MapPanel extends JComponent {

  private final int SCALE_WIDTH = 40;
  private final int SCALE_HEIGHT = 45;
  private final int MAP_WIDTH = Arena.getMapWidth();
  private final int MAP_LENGTH = Arena.getMapLength();
  private PlayerController bacman;
  private Blinky blinky;
  private Inky inky;
  private Pinky pinky;
  private Clyde clyde;
  private Point bacmanPreviousPosition = new Point();
  private Point blinkyPreviousPosition = new Point();
  private Point pinkyPreviousPosition = new Point();
  private Point inkyPreviousPosition = new Point();
  private Point clydePreviousPosition = new Point();
  private boolean isFirstTime = true;

  public MapPanel(PlayerController bacman, Blinky blinky, Inky inky, Pinky pinky, Clyde clyde) {
    this.bacman = bacman;
    this.blinky = blinky;
    this.inky = inky;
    this.pinky = pinky;
    this.clyde = clyde;
    setPreferredSize(new Dimension(MAP_LENGTH * SCALE_WIDTH, MAP_WIDTH * SCALE_HEIGHT));
  }

  public static boolean isGIF(String image_path) {
    return (image_path.contains(".gif"));
  }

  public static Image getScaledImage(Image srcImg, int w, int h) {
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
  }

  private Image getTileImage(String image_path) {
    URL img_path = getClass().getResource(image_path);
    ImageIcon imageIcon = new ImageIcon(img_path);
    Image image = imageIcon.getImage();
    if (isGIF(image_path)) {
      return image;
    } else {
      return getScaledImage(image, SCALE_WIDTH, SCALE_HEIGHT);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.black);
    g.fillRect(0, 0, getWidth(), getHeight());
    for (int i = 0; i < MAP_WIDTH; i++) {
      for (int j = 0; j < MAP_LENGTH; j++) {
        int x = j * SCALE_WIDTH;
        int y = i * SCALE_HEIGHT;
        Image tileImage;
        if ((i == bacman.getPlayer().getI()) && (j == bacman.getPlayer().getJ())) {
          tileImage = getTileImage(bacman.getPlayer().getImgPath());
        } else if ((i == blinky.getI()) && (j == blinky.getJ())) {
          tileImage = getTileImage((blinky.getImgPath()));
        } else if ((i == inky.getI()) && (j == inky.getJ())) {
          tileImage = getTileImage((inky.getImgPath()));
        } else if ((i == pinky.getI()) && (j == pinky.getJ())) {
          tileImage = getTileImage((pinky.getImgPath()));
        } else if ((i == clyde.getI()) && (j == clyde.getJ())) {
          tileImage = getTileImage((clyde.getImgPath()));
        } else {
          tileImage = getTileImage(Arena.getGrid(i, j).getImgPath());
        }
        g.drawImage(tileImage, x, y, this);
      }
    }
    g.dispose();
  }
}

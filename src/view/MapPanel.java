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

  /**
   * Lebar ukuran sprite yang ditampilkan pada 1 petak Arena.
   */
  private final int SCALE_WIDTH = 40;

  /**
   * Panjang ukuran sprite yang ditampilkan pada 1 petak Arena.
   */
  private final int SCALE_HEIGHT = 45;

  /**
   * Panjang Map dalam satuan panjang.
   */
  private final int MAP_WIDTH = Arena.getMapWidth();

  /**
   * Lebar Map dalam satuan panjang.
   */
  private final int MAP_LENGTH = Arena.getMapLength();

  /**
   * PlayerController dalam Arena.
   */
  private PlayerController bacman;

  /**
   * Objek Blinky.
   */
  private Blinky blinky;

  /**
   * Objek Inky.
   */
  private Inky inky;

  /**
   * Objek Pinky.
   */
  private Pinky pinky;

  /**
   * Objek Clyde.
   */
  private Clyde clyde;

  /**
   * Posisi Player sebelumnya.
   */
  private Point bacmanPreviousPosition = new Point();

  /**
   * Posisi Blinky sebelumnya.
   */
  private Point blinkyPreviousPosition = new Point();

  /**
   * Posisi Pinky sebelumnya.
   */
  private Point pinkyPreviousPosition = new Point();

  /**
   * Posisi Inky sebelumnya.
   */
  private Point inkyPreviousPosition = new Point();

  /**
   * Posisi Clyde sebelumnya.
   */
  private Point clydePreviousPosition = new Point();

  /**
   * Posisi Player sebelumnya.
   */
  private boolean isFirstTime = true;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Panel Map pada permainan.
   * </p>
   *
   * @param bacman PlayerController pada Player.
   * @param blinky Objek Blinky.
   * @param inky Objek Inky.
   * @param pinky Objek Pinky.
   * @param clyde Objek Clyde.
   */
  public MapPanel(PlayerController bacman, Blinky blinky, Inky inky, Pinky pinky, Clyde clyde) {
    this.bacman = bacman;
    this.blinky = blinky;
    this.inky = inky;
    this.pinky = pinky;
    this.clyde = clyde;
    setPreferredSize(new Dimension(MAP_LENGTH * SCALE_WIDTH, MAP_WIDTH * SCALE_HEIGHT));
  }

  /**
   * Fungsi mengembalikan apakah gambar merupakan gambar berekstensi gif.
   *
   * @param image_path Path direktori gambar.
   * @return Gambar berekstensi gif atau tidak.
   */
  public static boolean isGIF(String image_path) {
    return (image_path.contains(".gif"));
  }

  /**
   * Fungsi mengembalikan gambar yang telah di-resize.
   *
   * @param srcImg Gambar yang akan di-resize.
   * @param w Lebar gambar.
   * @param h Panjang gambar.
   * @return Gambar yang telah di-resize berdasarkan parameter w dan h.
   */
  public static Image getScaledImage(Image srcImg, int w, int h) {
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
  }

  /**
   * Fungsi mengembalikan gambar dari suatu path direktori.
   *
   * @param image_path Path direktori gambar.
   * @return Gambar dengan ukuran yang telah disesuaikan.
   */
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

  /**
   * Override method paintComponent.
   *
   * @param g menampilkan graphic pada InfoPanel.
   */
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

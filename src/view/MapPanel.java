package view;

import controller.PlayerController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import model.Arena;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;

/**
 * Kelas MapPanel mendefinisikan tampilan arena.
 *
 * @author Holy Lovenia - 13515113
 */
public class MapPanel extends JComponent {

  /**
   * Lebar ukuran sprite yang ditampilkan pada 1 petak Arena.
   */
  private final int scaleWidth = 40;

  /**
   * Panjang ukuran sprite yang ditampilkan pada 1 petak Arena.
   */
  private final int scaleHeight = 45;

  /**
   * Panjang Map dalam satuan panjang.
   */
  private final int mapWidth = Arena.getMapWidth();

  /**
   * Lebar Map dalam satuan panjang.
   */
  private final int mapLength = Arena.getMapLength();

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
    setPreferredSize(new Dimension(mapLength * scaleWidth, mapWidth * scaleHeight));
  }

  /**
   * Fungsi mengembalikan apakah gambar merupakan gambar berekstensi gif.
   *
   * @param imagePath Path direktori gambar.
   * @return Gambar berekstensi gif atau tidak.
   */
  public static boolean isGif(String imagePath) {
    return (imagePath.contains(".gif"));
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
   * @param imagePath Path direktori gambar.
   * @return Gambar dengan ukuran yang telah disesuaikan.
   */
  private Image getTileImage(String imagePath) {
    URL imgPath = getClass().getResource(imagePath);
    ImageIcon imageIcon = new ImageIcon(imgPath);
    Image image = imageIcon.getImage();
    if (isGif(imagePath)) {
      return image;
    } else {
      return getScaledImage(image, scaleWidth, scaleHeight);
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
    for (int i = 0; i < mapWidth; i++) {
      for (int j = 0; j < mapLength; j++) {
        int x = j * scaleWidth;
        int y = i * scaleHeight;
        Image tileImage;
        if ((i == bacman.getPlayer().getI()) && (j == bacman.getPlayer().getJ())) {
          tileImage = getTileImage(bacman.getPlayer().getImgPath());
          g.drawImage(tileImage, x, y, this);
        } else if ((i == blinky.getI()) && (j == blinky.getJ())) {
          tileImage = getTileImage((blinky.getImgPath()));
          g.drawImage(tileImage, x, y, this);
        } else if ((i == inky.getI()) && (j == inky.getJ())) {
          tileImage = getTileImage((inky.getImgPath()));
          g.drawImage(tileImage, x, y, this);
        } else if ((i == pinky.getI()) && (j == pinky.getJ())) {
          tileImage = getTileImage((pinky.getImgPath()));
          g.drawImage(tileImage, x, y, this);
        } else if ((i == clyde.getI()) && (j == clyde.getJ())) {
          tileImage = getTileImage((clyde.getImgPath()));
          g.drawImage(tileImage, x, y, this);
        } else if ((!Arena.getGrid(i, j).isAccessible()) || (Arena.getGrid(i, j).getCookie()
            .isAvailable())) {
          tileImage = getTileImage(Arena.getGrid(i, j).getImgPath());
          g.drawImage(tileImage, x, y, this);
        } else {

        }
      }
    }
    g.dispose();
  }
}

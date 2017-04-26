package view;

import controller.BacMan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import model.character.Player;

/**
 * Kelas InfoPanel mendefinisikan panel yang berisi info permainan.
 */
public class InfoPanel extends JComponent {

  /**
   * <p>
   * Constructor
   *
   * Menciptakan InfoPanel dengan size yang sudah ditentukan.
   * </p>
   */
  public InfoPanel() {
    setPreferredSize(new Dimension(500, 860));
  }

  /**
   * Fungsi mengembalikan font pada InfoPanel.
   *
   * @return Font pada InfoPanel.
   */
  private Font getScreenFont() {
    Font scoreFont = null;
    try {
      scoreFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return scoreFont;
  }

  /**
   * Fungsi mengembalikan sebuah gambar. Skala ukuran menjadi parameter gambar.
   *
   * @param imagePath Path direktori gambar.
   * @param widthScale Skala lebar gambar.
   * @param heightScale Skala panjang gambar.
   * @return Gambar yang telah diatur skalanya dari parameter input.
   */
  private Image getBacbacImage(String imagePath, int widthScale, int heightScale) {
    URL imgPath = getClass().getResource(imagePath);
    ImageIcon imageIcon = new ImageIcon(imgPath);
    Image image = imageIcon.getImage();
    if (MapPanel.isGif(imagePath)) {
      return image;
    } else {
      return MapPanel.getScaledImage(image, widthScale, heightScale);
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
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());

    int x = getWidth() / 10;
    int y = getHeight() / 9;
    g.drawImage(getBacbacImage("\\assets\\BacManLogo.png", 400, 100), x, y, this);

    x = getWidth() / 4;
    y = getHeight() / 3;
    if (Player.getStatus() == 1) {
      g.drawImage(getBacbacImage("\\assets\\super_bacbac.gif", 0, 0), x, y, this);
    } else {
      g.drawImage(getBacbacImage("\\assets\\hooray.gif", 0, 0), x, y, this);
    }

    x = getWidth() / 6;
    y = getHeight() * 4 / 5;
    g.setColor(Color.WHITE);
    g.setFont(getScreenFont().deriveFont(120f));
    String playerName;
    if (TitleView.playerName.length() > 6) {
      playerName = TitleView.playerName.substring(0, 6);
    } else {
      playerName = TitleView.playerName;
    }
    g.drawString("NAME: " + playerName.toUpperCase(), x, y);

    y = getHeight() * 4 / 5 + 70;
    g.drawString("SCORE: " + BacMan.score, x, y);

    g.dispose();
  }
}

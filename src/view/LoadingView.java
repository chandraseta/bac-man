package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Kelas LoadingView berisi tampilan saat sedang loading GameView.
 *
 * @author Holy Lovenia - 13515113
 */
public class LoadingView extends JFrame {

  /**
   * Panel yang dibuat saat melakukan loading.
   */
  protected JPanel loadingPanel;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan LoadingView pada window saat dengan melakukan window.
   * </p>
   */
  public LoadingView() {
    setTitle("Loading");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    loadingPanel = new JPanel();
    loadingPanel.setPreferredSize(new Dimension(1600, 950));
    loadingPanel.setBackground(new Color(0, 0, 0));
    GridBagLayout loadingLayout = new GridBagLayout();
    loadingPanel.setLayout(loadingLayout);
    GridBagConstraints loadingConstraints = new GridBagConstraints();
    loadingConstraints.fill = GridBagConstraints.BOTH;
    loadingPanel.add(createLoadingText(loadingLayout, loadingConstraints));
    loadingPanel.add(createLoadingBacBac(loadingLayout, loadingConstraints));
    loadingPanel.add(
        createLoadingGhost(loadingLayout, loadingConstraints, 0, "\\assets\\loading_blinky.gif"));
    loadingPanel.add(
        createLoadingGhost(loadingLayout, loadingConstraints, 1, "\\assets\\loading_pinky.gif"));
    loadingPanel.add(
        createLoadingGhost(loadingLayout, loadingConstraints, 2, "\\assets\\loading_inky.gif"));
    loadingPanel.add(
        createLoadingGhost(loadingLayout, loadingConstraints, 3, "\\assets\\loading_clyde.gif"));

    add(loadingPanel);
    pack();
  }

  /**
   * Fungsi mengembalikan sebuah text yang akan ditampilkan saat melakukan Loading.
   *
   * @param loadingLayout Layout untuk text Loading.
   * @param loadingConstraints Constraints mengelola layout text.
   * @return Text pada window Loading.
   */
  public JLabel createLoadingText(GridBagLayout loadingLayout,
      GridBagConstraints loadingConstraints) {
    loadingConstraints.gridx = 0;
    loadingConstraints.gridy = 0;
    loadingConstraints.gridwidth = 4;
    Font loadingFont = null;
    try {
      loadingFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel loadingText = new JLabel("Loading...", JLabel.CENTER);
    loadingText.setBorder(new EmptyBorder(20, 10, 10, 10));
    loadingText.setFont(loadingFont.deriveFont(400f));
    loadingText.setForeground(Color.WHITE);
    loadingLayout.setConstraints(loadingText, loadingConstraints);
    return loadingText;
  }

  /**
   * Menciptakan label loading dari gambar BacBac.
   *
   * @param loadingLayout Layout untuk gambar saat Loading.
   * @param loadingConstraints Constraints mengelola layout gambar.
   * @return Gambar BacBac ditampilkan dalam window Loading.
   */
  public JLabel createLoadingBacBac(GridBagLayout loadingLayout,
      GridBagConstraints loadingConstraints) {
    loadingConstraints.gridx = 0;
    loadingConstraints.gridy = 1;
    loadingConstraints.gridwidth = 4;
    URL sleepingBacbacPath = getClass().getResource("\\assets\\loading.gif");
    JLabel sleepingBacbac = new JLabel(new ImageIcon(sleepingBacbacPath), JLabel.CENTER);
    sleepingBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
    loadingLayout.setConstraints(sleepingBacbac, loadingConstraints);
    return sleepingBacbac;
  }

  /**
   * Menciptakan label loading dari gambar Ghost.
   *
   * @param loadingLayout Layout untuk gambar saat Loading.
   * @param loadingConstraints Constraints mengelola layout gambar.
   * @param gridx Posisi menyamping dalam layout.
   * @param imagePath Path untuk gambar yang ingin ditampilkan.
   * @return Gambar Ghost ditampilkan dalam window Loading.
   */
  public JLabel createLoadingGhost(GridBagLayout loadingLayout,
      GridBagConstraints loadingConstraints, int gridx, String imagePath) {
    loadingConstraints.gridx = gridx;
    loadingConstraints.gridy = 2;
    loadingConstraints.gridwidth = 1;
    URL loadingGhostPath = getClass().getResource(imagePath);
    JLabel loadingGhost = new JLabel(new ImageIcon(loadingGhostPath), JLabel.CENTER);
    loadingGhost.setBorder(new EmptyBorder(30, 10, 10, 10));
    loadingLayout.setConstraints(loadingGhost, loadingConstraints);
    return loadingGhost;
  }
}
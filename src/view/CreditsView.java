package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Kelas CreditsView mendefinisikan window Credits.
 */
public class CreditsView extends JFrame {

  /**
   * Panel credits permainan.
   */
  private JPanel creditsPanel;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan window view untuk credits.
   * </p>
   */
  public CreditsView() {
    setTitle("Game Over");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    creditsPanel = new JPanel();
    creditsPanel.setPreferredSize(new Dimension(1600, 950));
    creditsPanel.setBackground(Color.BLACK);
    GridBagLayout creditsLayout = new GridBagLayout();
    creditsPanel.setLayout(creditsLayout);
    GridBagConstraints creditsConstraints = new GridBagConstraints();
    creditsConstraints.fill = GridBagConstraints.BOTH;
    creditsPanel.add(createCreditsText(creditsLayout, creditsConstraints));
    creditsPanel.add(createCreditsBacBac(creditsLayout, creditsConstraints));
    creditsPanel.add(createCreditsHandika(creditsLayout, creditsConstraints));
    creditsPanel.add(createCreditsFelix(creditsLayout, creditsConstraints));
    creditsPanel.add(createCreditsRionaldi(creditsLayout, creditsConstraints));
    creditsPanel.add(createCreditsHoly(creditsLayout, creditsConstraints));

    add(creditsPanel);
    pack();
  }

  /**
   * Fungsi mengembalikan label credits permainan.
   *
   * @param creditsLayout Layout untuk credits.
   * @param creditsConstraints Constraints mengelola layout credits.
   * @return Label credits permainan.
   */
  public JLabel createCreditsBacBac(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 0;
    creditsConstraints.gridy = 1;
    creditsConstraints.gridwidth = 2;
    URL credits_bacbac_path = getClass().getResource("\\assets\\credit.gif");
    JLabel creditsBacbac = new JLabel(new ImageIcon(credits_bacbac_path), JLabel.CENTER);
    creditsBacbac.setBorder(new EmptyBorder(10, 10, 60, 10));
    creditsLayout.setConstraints(creditsBacbac, creditsConstraints);
    return creditsBacbac;
  }

  /**
   * Fungsi mengembalikan teks credits pada label.
   *
   * @param creditsLayout Layout text pada credits.
   * @param creditsConstraints Constraints mengelola layout text credits.
   * @return Layout text yang akan ditampilkan di credits.
   */
  public JLabel createCreditsText(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 0;
    creditsConstraints.gridy = 0;
    creditsConstraints.gridwidth = 2;
    Font creditsFont = null;
    try {
      creditsFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel creditsText = new JLabel("CREDITS", JLabel.CENTER);
    creditsText.setBorder(new EmptyBorder(20, 10, 60, 10));
    creditsText.setFont(creditsFont.deriveFont(300f));
    creditsText.setForeground(Color.WHITE);
    creditsLayout.setConstraints(creditsText, creditsConstraints);
    return creditsText;
  }

  /**
   * Fungsi mengembalikan identitas Handika pada credits.
   *
   * @param creditsLayout Layout text pada credits.
   * @param creditsConstraints Constraints mengelola layout text credits.
   * @return Layout text yang akan ditampilkan di credits.
   */
  public JLabel createCreditsHandika(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 0;
    creditsConstraints.gridy = 2;
    creditsConstraints.gridwidth = 1;
    Font creditsFont = null;
    try {
      creditsFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel creditsText = new JLabel("OKTAVIANUS HANDIKA - 13515035", JLabel.CENTER);
    creditsText.setBorder(new EmptyBorder(20, 20, 20, 20));
    creditsText.setFont(creditsFont.deriveFont(100f));
    creditsText.setForeground(Color.WHITE);
    creditsLayout.setConstraints(creditsText, creditsConstraints);
    return creditsText;
  }

  /**
   * Fungsi mengembalikan identitas Felix pada credits.
   *
   * @param creditsLayout Layout text pada credits.
   * @param creditsConstraints Constraints mengelola layout text credits.
   * @return Layout text yang akan ditampilkan di credits.
   */
  public JLabel createCreditsFelix(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 1;
    creditsConstraints.gridy = 2;
    creditsConstraints.gridwidth = 1;
    Font creditsFont = null;
    try {
      creditsFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel creditsText = new JLabel("FELIX LIMANTA - 13515065", JLabel.CENTER);
    creditsText.setBorder(new EmptyBorder(20, 20, 20, 20));
    creditsText.setFont(creditsFont.deriveFont(100f));
    creditsText.setForeground(Color.WHITE);
    creditsLayout.setConstraints(creditsText, creditsConstraints);
    return creditsText;
  }

  /**
   * Fungsi mengembalikan identitas Rio pada credits.
   *
   * @param creditsLayout Layout text pada credits.
   * @param creditsConstraints Constraints mengelola layout text credits.
   * @return Layout text yang akan ditampilkan di credits.
   */
  public JLabel createCreditsRionaldi(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 0;
    creditsConstraints.gridy = 3;
    creditsConstraints.gridwidth = 1;
    Font creditsFont = null;
    try {
      creditsFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel creditsText = new JLabel("RIONALDI CHANDRASETA - 13515077", JLabel.CENTER);
    creditsText.setBorder(new EmptyBorder(20, 20, 20, 20));
    creditsText.setFont(creditsFont.deriveFont(100f));
    creditsText.setForeground(Color.WHITE);
    creditsLayout.setConstraints(creditsText, creditsConstraints);
    return creditsText;
  }

  /**
   * Fungsi mengembalikan identitas Holy pada credits.
   *
   * @param creditsLayout Layout text pada credits.
   * @param creditsConstraints Constraints mengelola layout text credits.
   * @return Layout text yang akan ditampilkan di credits.
   */
  public JLabel createCreditsHoly(GridBagLayout creditsLayout,
      GridBagConstraints creditsConstraints) {
    creditsConstraints.gridx = 1;
    creditsConstraints.gridy = 3;
    creditsConstraints.gridwidth = 1;
    Font creditsFont = null;
    try {
      creditsFont = Font.createFont(Font.TRUETYPE_FONT,
          getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel creditsText = new JLabel("HOLY LOVENIA - 13515113", JLabel.CENTER);
    creditsText.setBorder(new EmptyBorder(20, 20, 20, 20));
    creditsText.setFont(creditsFont.deriveFont(100f));
    creditsText.setForeground(Color.WHITE);
    creditsLayout.setConstraints(creditsText, creditsConstraints);
    return creditsText;
  }

  /**
   * Program utama.
   *
   * @param args Command line argument.
   */
  public static void main(String[] args) {
    CreditsView creditsView = new CreditsView();
    creditsView.setVisible(true);
  }
}

package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Kelas TitleView mendefinisikan interface pada awal sebelum memulai permainan.
 */
public class TitleView extends JFrame implements ActionListener {

    /**
     *
     */
    protected JPanel titlePanel;

    /**
     * Suatu objek pada interface ditampilkan atau tidak.
     */
    public static boolean visibility;

    /**
     * <p>
     * Constructor
     *
     * Menciptakan sebuah window tampilan untuk menampilkan judul permainan.
     * </p>
     */
    public TitleView() {
        setTitle("BacMan");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(1600, 950));
        titlePanel.setBackground(Color.black);
        GridBagLayout titleLayout = new GridBagLayout();
        titlePanel.setLayout(titleLayout);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.fill = GridBagConstraints.BOTH;
        titlePanel.add(createLogo(titleLayout, titleConstraints));
        titlePanel.add(createText(titleLayout, titleConstraints));
        JTextField textField = createTextInput(titleLayout, titleConstraints);
        titlePanel.add(textField);

        add(titlePanel);
        pack();
        setVisible(true);
        visibility = true;
    }

    /**
     * Menciptakan logo permainan.
     *
     * @param titleLayout
     * @param titleConstraints
     * @return logo Logo permainan.
     */
    public JLabel createLogo(GridBagLayout titleLayout, GridBagConstraints titleConstraints) {
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        URL logo_path = getClass().getResource("\\assets\\BacManLogo.png");
        ImageIcon imageIcon = new ImageIcon(logo_path);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1200, 300, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(newImage));
        titleLayout.setConstraints(logo, titleConstraints);
        return logo;
    }

    /**
     * Menciptakan
     *
     * @param titleLayout
     * @param titleConstraints
     * @return
     */
    public JLabel createText(GridBagLayout titleLayout, GridBagConstraints titleConstraints) {
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 1;
        Font titleFont = null;
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel titleText = new JLabel("Are you ready?", JLabel.CENTER);
        titleText.setFont(titleFont.deriveFont(100f));
        titleText.setForeground(Color.WHITE);
        titleText.setBorder(new EmptyBorder(50, 10, 10, 10));
        titleLayout.setConstraints(titleText, titleConstraints);
        return titleText;
    }

    public JTextField createTextInput(GridBagLayout titleLayout, GridBagConstraints titleConstraints) {
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 2;
        Font titleFont = null;
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JTextField textField = new JTextField();
        textField.setLayout(null);
        textField.setSize(50, 10);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(titleFont.deriveFont(40f));
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        titleLayout.setConstraints(textField, titleConstraints);
        textField.addActionListener(ae -> {
            String name = textField.getText();
            if(textField.getText() != null) {
                visibility = false;
                setVisible(false);
            }
        });
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

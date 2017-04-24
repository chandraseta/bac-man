package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Holy on 24-Apr-17.
 */
public class TitleView extends JFrame implements ActionListener {
    protected JPanel titlePanel;

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
    }

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
        JTextField textField = new JTextField();
        textField.setColumns(10);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        titleLayout.setConstraints(textField, titleConstraints);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = textField.getText();
                System.out.println(name);
            }
        });


        return textField;
    }

    public static void main(String[] args) {
        TitleView titleView = new TitleView();
        titleView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

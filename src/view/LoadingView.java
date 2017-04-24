package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Holy on 24-Apr-17.
 */
public class LoadingView extends JFrame {
    protected JPanel loadingPanel;

    public LoadingView() {
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
        loadingPanel.add(createLoadingGhost(loadingLayout, loadingConstraints, 0, "\\assets\\loading_blinky.gif"));
        loadingPanel.add(createLoadingGhost(loadingLayout, loadingConstraints, 1, "\\assets\\loading_pinky.gif"));
        loadingPanel.add(createLoadingGhost(loadingLayout, loadingConstraints, 2, "\\assets\\loading_inky.gif"));
        loadingPanel.add(createLoadingGhost(loadingLayout, loadingConstraints, 3, "\\assets\\loading_clyde.gif"));

        add(loadingPanel);
        pack();
    }

    public JLabel createLoadingText(GridBagLayout loadingLayout, GridBagConstraints loadingConstraints) {
        loadingConstraints.gridx = 0;
        loadingConstraints.gridy = 0;
        loadingConstraints.gridwidth = 4;
        Font loadingFont = null;
        try {
            loadingFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
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

    public JLabel createLoadingBacBac(GridBagLayout loadingLayout, GridBagConstraints loadingConstraints) {
        loadingConstraints.gridx = 0;
        loadingConstraints.gridy = 1;
        loadingConstraints.gridwidth = 4;
        URL sleeping_bacbac_path = getClass().getResource("\\assets\\loading.gif");
        JLabel sleepingBacbac = new JLabel(new ImageIcon(sleeping_bacbac_path), JLabel.CENTER);
        sleepingBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
        loadingLayout.setConstraints(sleepingBacbac, loadingConstraints);
        return sleepingBacbac;
    }

    public JLabel createLoadingGhost(GridBagLayout loadingLayout, GridBagConstraints loadingConstraints, int gridx, String image_path) {
        loadingConstraints.gridx = gridx;
        loadingConstraints.gridy = 2;
        loadingConstraints.gridwidth = 1;
        URL loading_ghost_path = getClass().getResource(image_path);
        JLabel loadingGhost = new JLabel(new ImageIcon(loading_ghost_path), JLabel.CENTER);
        loadingGhost.setBorder(new EmptyBorder(30, 10, 10, 10));
        loadingLayout.setConstraints(loadingGhost, loadingConstraints);
        return loadingGhost;
    }
}
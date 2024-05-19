package edu.hitsz.application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenu {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JComboBox<String> bgmComboBox;
    private JLabel bgmLabel;
    private JPanel bgmPanel;

    public StartMenu(){
        bgmComboBox.addItem("开");
        bgmComboBox.addItem("关");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new EasyGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                game.setMusicFlag(bgmComboBox.getSelectedItem() == "开");
                game.setGameLevel(1);
                game.action();
            }
        });

        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new NormalGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                game.setMusicFlag(bgmComboBox.getSelectedItem() == "开");
                game.setGameLevel(2);
                game.action();
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new HardGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                game.setMusicFlag(bgmComboBox.getSelectedItem() == "开");
                game.setGameLevel(3);
                game.action();
            }
        });

    }

    public Component getMainPanel() {
        return mainPanel;
    }
}

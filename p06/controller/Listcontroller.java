/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.list;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Listcontroller {

    list screen;
    int width = 300;
    int height = 300;
    public DefaultListModel<String> model = new DefaultListModel<>();

    public Listcontroller(list screen) {
        this.screen = screen;
        screen.getBookList().setModel(model);
        model.add(0, "Bioshock Infinite");
        model.add(1, "Final Fantasy 7");
        model.add(2, "The Last Of Us");
        model.add(3, "Tiki Cart");
        screen.getBookList().setSelectedIndex(0);
        showImg();
        screen.setVisible(true);
    }

    private ImageIcon getIcon(String name, int width, int height) {
        ImageIcon i = new ImageIcon(getClass().getClassLoader().getResource(name));
        Image img = i.getImage();
        Image newImg = img.getScaledInstance(width, height, 0);
        i = new ImageIcon(newImg);
        return i;
    }

    public void showImg() {
        int i = screen.getBookList().getSelectedIndex();

        switch (i) {
            case 0:
                screen.getTxtpic().setIcon(getIcon("563213.jpg", width, height));
                screen.getTxtpic().setText("");
                break;
            case 1:
                screen.getTxtpic().setIcon(getIcon("final-fantasy-vii-hd-wallpapers-33116-3206975.jpg", width, height));
                screen.getTxtpic().setText("");
                break;
            case 2:
                screen.getTxtpic().setIcon(getIcon("image.jpg", width, height));
                screen.getTxtpic().setText("");
                break;
            case 3:
                screen.getTxtpic().setIcon(getIcon("tiki_sdcard.jpg", width, height));
                screen.getTxtpic().setText("");
                break;
            default:
                screen.getTxtpic().setText("NO SOURCE FOUND");
                break;
        }
    }

}

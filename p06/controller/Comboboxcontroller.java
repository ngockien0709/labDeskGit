/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.combobox;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Comboboxcontroller {

    combobox screen;
    public DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    int width = 300;
    int height = 300;
    
    public Comboboxcontroller(combobox screen) {
        this.screen = screen;
        addCombobox();
    }
    
    private void addCombobox() {
        screen.getCombobox().setModel(model);
        model.insertElementAt("Bioshock Infinite", 0);
        model.insertElementAt("Final Fantasy 7", 1);
        model.insertElementAt("The Last Of Us", 2);
        model.insertElementAt("Tiki Cart", 3);
        screen.getCombobox().setSelectedIndex(0);
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
        
        screen.getCombobox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = screen.getCombobox().getSelectedIndex();
                
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
        );
        
    }
}

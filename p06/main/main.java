/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.combobox;
import GUI.list;
import controller.Comboboxcontroller;
import controller.Listcontroller;

/**
 *
 * @author Admin
 */
public class main {
    public static void main(String[] args) {
        list lscreen = new list();
        Listcontroller list = new Listcontroller(lscreen);
        combobox cscreen = new combobox();
        Comboboxcontroller combobox = new Comboboxcontroller(cscreen);
    }
}

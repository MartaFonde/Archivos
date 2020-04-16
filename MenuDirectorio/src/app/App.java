package app;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args){
        MenuDirectorio menu = new MenuDirectorio();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(1000, 800);
        menu.setVisible(true);

    }
}
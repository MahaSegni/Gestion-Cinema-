/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.awt.FlowLayout;
import javax.swing.*;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;

public class Test extends JFrame{
 
    public Test(){
    super("window");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try{
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\piJava\\src\\images\\logo.png")))));
 
    }catch(IOException e)
    {
        e.printStackTrace();
     
    }
    this.setLayout(new FlowLayout());
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    }

public static void main(String[] args) {

       Test t = new Test();

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Color;
import java.awt.FlowLayout;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class c2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea ca= new JTextArea();
	private JTextField cf=new JTextField();                                  
	private JButton b=new JButton();
	private JLabel l=new JLabel();

	c2(){                                                // Layout and Properties defined in Constructer                                         

		   
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
		
		//f.setSize(400,400);
          
		this.setTitle("HappyPark");
		this.add(ca);
		this.add(cf);
		ca.setSize(300,310);
		ca.setLocation(1, 1);
		//ca.setBackground(Color.lightGray.brighter());
		cf.setSize(300,20);
		cf.setLocation(1,320);
		this.add(b);
		l.setText("SEND");
		b.add(l);
		b.setSize(400,20);
		b.setLocation(300,320);

		b.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==b) {                           // Message sents on Click button

					String text=cf.getText().toLowerCase();
					ca.setForeground(Color.lightGray);
					ca.append("You-->"+text+"\n");
					cf.setText("");

					if(text.contains("hi")) {                         // input Checking
						replyMeth("Hi there welcome to happy park");
					}
					else if(text.contains("how are you")) {
						replyMeth("I'm Good :).Thankyou for asking");
					}
					else if(text.contains("what is your name")) {
						replyMeth("I'm the Trending BINOD");
					}
                                        else if(text.contains("I have a problem")) {
						replyMeth("ok your reclamation will be treated!");
					}
					else if(text.contains("gender")) {
						replyMeth("I'm Female.Don't Try to Flirt with me"+"\n"+"you Fell in love :)");
					}
					else if(text.contains("love you")) {
						replyMeth("I'm Feeling Shy :) Love you to");
					}
					else if(text.contains("bye")) {
						replyMeth("Too Soon :( AnyWays"+"\n"+"STAY HOME STAY SAFE ");
					}
					else 
						replyMeth("I Can't Understand");

				}

			}

		});

	}
	public void replyMeth(String s) {                          // Reply Method
		ca.append("HappyPark-->"+s+"\n");         
	}


}



public class chatbot2 {                                     //Driver Class

	public static void main(String[] args) {             //main class

          new c2();                                  // instantiation 
	}

}
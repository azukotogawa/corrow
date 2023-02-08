import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageManager extends JFrame{
    
	JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JLabel wordfire = new JLabel();
    JLabel wordof = new JLabel();
    
    public static void main(String[] args)
    {
 	   new ImageManager();
    }
    
    public ImageManager()
    {
           setTitle("Fire of Espilon");
           setVisible(true);
           setSize(1440, 900);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           
           jl.setIcon(new ImageIcon("C:/Users/Cole/Desktop/Fire of Espilon Data/Images/MainScreenImage.png"));
           wordfire.setIcon(new ImageIcon("C:/Users/Cole/Desktop/Fire of Espilon Data/Images/Word Fire.png"));
           wordof.setIcon(new ImageIcon("C:/Users/Cole/Desktop/Fire of Espilon Data/Images/Word Of.png"));
           jp.setBackground(Color.black);
           jl.setOpaque(true);
           jp.add(wordfire);
           jp.add(wordof);
           jp.add(jl);
           add(jp);
           validate();

   		   
    }
    
}

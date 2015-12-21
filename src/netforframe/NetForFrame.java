/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netforframe;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



class MyFrame extends JFrame   
 {   

    public MyPanel panel;
     public MyFrame()   
     {   
         setTitle("Граббер");   
         panel = new MyPanel();   
         setResizable(true);    
         setContentPane(panel);   
         
         
         
         pack();   
     }   
    
 } 
    



 class MyPanel extends JPanel implements MouseWheelListener
 {    
     private Image img;
     Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
     public int division=50;
     private final JSlider slider;
     private final ChangeListener listener;
    private MouseWheelListener a;
    private String newline;
     
   
     public MyPanel()   
         {
             final Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                
                this.setLayout(new FlowLayout(FlowLayout.LEFT));
                this.addMouseWheelListener(this);
                
                listener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				
				JSlider source = (JSlider) event.getSource();
                                division = source.getValue();   
                                
                                				
			}
		};
                
                
                slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(10);
		addSlider(slider, "");
             javax.swing.Timer timer = new javax.swing.Timer( 100, new ActionListener()
                     
                     
  {
      
      @Override
      public void actionPerformed(ActionEvent e)
      {
          try {
              img = new Robot().createScreenCapture(screenRect);
              
              
          } catch (AWTException ex) {
              Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  } );
     timer.start();

    
         }
    
     
     
     
     public void mouseWheelMoved(MouseWheelEvent mwe) {
                     
                     if(mwe.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
                     {
                        division = division + mwe.getUnitsToScroll();
                        slider.setValue(slider.getValue() + mwe.getUnitsToScroll());
                        if(division == 0)
                        {
                           division = 1;
                           slider.setValue(1);
                        }
                     }
      
      
    }
     
     
     public void addSlider(JSlider s, String description)
	{
		s.addChangeListener(listener);
		JPanel panel1 = new JPanel();
		panel1.add(s);
		panel1.add(new JLabel(description));
		this.add(panel1);
	}   

     @Override
     public void paintComponent(Graphics g)
       {    
         
         super.paintComponent(g);
         
         Graphics2D gr = (Graphics2D)g;         
         gr.drawImage(img,0,0,null);
      
                
          
         int rezW = sSize.width/division;
         int rezH = sSize.height/division;
         int item = 0; 
         BasicStroke pen1 = new BasicStroke(2);
         gr.setStroke(pen1);
         for (int i = 0;i < sSize.width;i=i+rezW)
         {     
                 
             switch(item)
             {
                 case 0: gr.setColor(Color.red); item++; break;
                 case 1: gr.setColor(Color.green); item++; break;
                 case 2: gr.setColor(Color.blue); item++; break;
                 case 3: gr.setColor(Color.black); item = 0; break;
                         
             }
                 
             gr.drawLine(i, 0, i, sSize.height);
             gr.drawLine(0, i, sSize.width, i);                
         }
         
         super.repaint();
       }
 
 }   




public class NetForFrame {
   
  public NetForFrame() {
        
    }

    public static void main(String[] args) {
       
        MyFrame frame = new MyFrame(); 
      
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        frame.setSize(sSize.width, sSize.height);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
         frame.setVisible(true);
        
         
        
    }

   
    
}

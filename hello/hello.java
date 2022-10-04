package hello;

import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class hello {
   public static void main(String[] args)  {
      JFrame.setDefaultLookAndFeelDecorated(true);
      // Create the GUI on the event-dispatching thread
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            createWindow();                      
         }
      });
   }

   private static void createWindow() {          
      JFrame frame = new JFrame("Rounded Shaped Window");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setVisible(true);          
   }

   private static void createUI(final JFrame frame) {
      frame.setLayout(new GridBagLayout());
      frame.setSize(200, 200);            
      frame.setLocationRelativeTo(null);              
      frame.add(new JButton("Hello World"));  

      frame.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            frame.setShape(new  RoundRectangle2D.Double(0,0,frame.getWidth(),
               frame.getHeight(), 20, 20));
         }
      });
   }
}
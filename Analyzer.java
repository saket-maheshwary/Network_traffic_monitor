import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.*;
import jpcap.packet.*;
import java.awt.*;
import java.awt.event.AWTEventListener.*;
import javax.swing.*;
public class Analyzer extends JFrame
{
    ArrayList<Packet> ar=new ArrayList();
    float p,p2,p3,p4,p5;
    Packet pkt;
    float c,c1,c2,c3,c4,c5;
    panel pn;
    int k; 
   public Analyzer(ArrayList<Packet> ar)
   {
       setLayout(null);
       this.ar =ar;
       panel pn=new panel();
       pn.setBounds(100,100,500,600);
       add(pn);
       setVisible(true);
       setSize(500,500);
      
        for(k=0;k<ar.size()-1;k++)
        {
            pkt =ar.get(k) ;
            c++;
            if(pkt instanceof UDPPacket)
            {
                c1++;
            }
            
            else if(pkt instanceof ARPPacket)
            {
                c3++;
                
            }
            else if(pkt instanceof ICMPPacket)
            {
                c2++;
            }
            else if(pkt instanceof TCPPacket)
            {
                c4++;
            }
            else
                c5++;
         }  
            p=(c1*100)/c;
            p2=(c2*100)/c;
            p3=(c3*100)/c;
            p4=(c4*100)/c;
            p5=(c5*100)/c;
   }
   
   class panel extends JPanel
   {
      
       public void paint(Graphics g)
       {
           g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100,100,500,500);
            g.setColor(Color.BLACK);
            g.drawLine(100,400,500,400);
            g.drawString("UDP",120,420);
            g.drawString("ICMP",190,420);
            g.drawString("ARP",280,420);
            g.drawString("TCP",360,420);
            g.drawString("OTHERS",420,420);
            g.setColor(Color.red);
            g.fillRect(100,400-(int)p,60,(int)p);
            g.setColor(Color.BLUE);
            g.fillRect(180,400-(int)p2,60,(int)p2);
            g.setColor(Color.green);
            g.fillRect(260,400-(int)p3,60,(int)p3);
            g.setColor(Color.MAGENTA);
            g.fillRect(340,400-(int)p4,60,(int)p4);
            g.setColor(Color.YELLOW);
            g.fillRect(420,400-(int)p5,60,(int)p5);
            
       }
   }
   public static void main(String args[])
   {
       
   }
           
}

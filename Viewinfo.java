import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import jpcap.*;
import jpcap.packet.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
public class Viewinfo extends javax.swing.JFrame implements ListSelectionListener 
{  
    byte []b=new byte[4];
    String s,st;
    int x,rv; 
    JTable jt;
    JScrollPane sp;
    ArrayList<Packet> al=new ArrayList<Packet>();
    ArrayList<Packet> store=new ArrayList<Packet>();
      
   public Viewinfo(ArrayList<Packet> al) 
        {
        initComponents();
        setLayout(null);
        this.al=al;
        TableModel tm=new TableModel();
        jt=new JTable(tm);  
        jt.getSelectionModel().addListSelectionListener(this);
        sp=new JScrollPane(jt);    
        sp.setBounds(50,10,500,300);
        add(sp);
        setSize(600, 400);
        setVisible(true);
       
       }

    @Override
  public void valueChanged(ListSelectionEvent e) 
       {
        if (e.getSource() == jt.getSelectionModel())            
            {
              int r=jt.getSelectedRow();  
                Object o=jt.getValueAt(r, 5);       
                jTextArea1.setText(o.toString());  
                Packet pk=al.get(r);                                  
                if(pk instanceof UDPPacket)
                 {
                    UDPPacket udp = (UDPPacket) pk;
                    jTextArea2.setText("Source ip :"+udp.src_ip.toString()+"\r\n"+
                    "Destination ip :"+udp.dst_ip.toString()+"\r\n"+
                    "Source port is :"+String.valueOf(udp.src_port)+"\r\n"+
                    "Destination port is :"+String.valueOf(udp.dst_port)+"\r\n"+
                    "Offset is :"+String.valueOf(udp.offset)+"\r\n"
                    );
                }
               else if(pk instanceof TCPPacket)
               {
                 TCPPacket tcp = (TCPPacket) (pk);
                jTextArea2.setText("Source ip :"+tcp.src_ip.toString()+"\r\n"+
                "Destination ip :"+tcp.dst_ip.toString()+"\r\n"+
                "Source port is :"+String.valueOf(tcp.src_port)+"\r\n"+
                "Destination port is :"+String.valueOf(tcp.dst_port)+"\r\n"+
                "Offset is :"+String.valueOf(tcp.offset)+"\r\n"
                    );                           
              }
             else if(pk instanceof ARPPacket)
                 {
                     ARPPacket arp = (ARPPacket) (pk); 
               
                     b=arp.target_protoaddr;
                     s="";
                     x=0;
                    for(int i=0;i<4;i++)
                       {
                       
                       if(b[i]<0)
                       {
                           x=256-b[i];
                           s=s+String.valueOf(x);
                       }
                       else
                       {
                           x=b[i];
                           s=s+String.valueOf(x);
                       }
                   }                 
                    b=arp.sender_protoaddr;
                   String  ss="";
                    x=0;
                   for(int i=0;i<4;i++)
                     {
                       
                       if(b[i]<0)
                       {
                           x=256-b[i];
                           ss=ss+String.valueOf(x);
                       }
                       else
                       {
                           x=b[i];
                           ss=ss+String.valueOf(x);
                       }
                   }
      jTextArea2.setText("Sender Address :"+ss+"\r\n"+"Target Address"+s+"\r\n");
           }
           else if(pk instanceof ICMPPacket)
           {
               ICMPPacket icmp = (ICMPPacket) (pk);
          
           }  
           else
               jTextArea2.setText("No Information Available");
          }
 }
 public class TableModel extends AbstractTableModel
   {
     String []st={"PACKET NO.","TIME","PROTOCOL","SOURCE IP","DESTN IP","INFO"};
  
     public String getColumnName(int n)
      {
      
      return st[n];
      }
  
        @Override
        public int getRowCount() 
        {
             return al.size();
        }

        @Override
        public int getColumnCount()
        {
         return 6;   
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
           {
            Packet pk=al.get(rowIndex);
            if(columnIndex==0)
            {
              return rowIndex+1;
            }
           else if(columnIndex==1)
            {
                return pk.sec;
            }
           else if(columnIndex==2)
            {
               if (pk instanceof UDPPacket)
               {
                   UDPPacket udp = (UDPPacket) pk;
                   if(udp.src_port==0||udp.dst_port==0)
                   {
                       return "RESERVED";
                   }
                   else if(udp.src_port==7||udp.dst_port==7)
                   {
                       return "ECHO";
                   }
                   else if(udp.src_port==7||udp.dst_port==7)
                   {
                      return "MSG SEND";
                   }
                   else
                   return "UDP";
               }
               else if (pk instanceof ICMPPacket)
                                                
                 return "ICMP";
               
               else if (pk instanceof ARPPacket)
                  return "ARP";
               
               else if (pk instanceof TCPPacket)
                {
                   TCPPacket tcp = (TCPPacket) (pk);
                    if(tcp.src_port==80||tcp.dst_port==80)
                   {
                       return "HTTP";
                   }
                   else if(tcp.src_port==21||tcp.dst_port==21)
                   {
                       return "FTP";
                   }
                   else if(tcp.src_port==107||tcp.dst_port==107)
                   {
                       return "TELNET";
                   }
                   else if(tcp.src_port==443||tcp.dst_port==443)
                   {
                       return "HTTPS";
                   }
                    else if(tcp.src_port==135||tcp.dst_port==135)
                   {
                       return "DCE";
                   }
                     else if(tcp.src_port==139||tcp.dst_port==139)
                   {
                       return "NETBIOS";
                   }
                      else if(tcp.src_port==25||tcp.dst_port==25)
                   {
                       return "SMTP";
                   }
                   else    
                   return "IPv6";
               }
               else
                   return "OTHERS";              
            }
           else if(columnIndex==3)
            {
            if (pk instanceof UDPPacket)
            { 
                UDPPacket udp = (UDPPacket) pk;
                    return (udp.src_ip);
            }
               
               else if (pk instanceof ICMPPacket)
               { 
                   ICMPPacket icmp = (ICMPPacket) (pk);
                   return (icmp.src_ip);
               }
               else if (pk instanceof ARPPacket)
               { 
                    ARPPacket arp = (ARPPacket) (pk);
                   b=arp.sender_protoaddr;
                   s="";x=0;
                   for(int i=0;i<4;i++)
                   {
                       
                       if(b[i]<0)
                       {
                           x=256-b[i];
                           s=s+String.valueOf(x);
                       }
                       else
                       {
                           x=b[i];
                           s=s+String.valueOf(x);
                       }
                   }
                   return s;
               }
               else if (pk instanceof TCPPacket)
               {  
                   TCPPacket tcp = (TCPPacket) (pk);
                   return (tcp.src_ip); 
               }
               
               else 
                   return"-";
            
            }
           else if(columnIndex==4)
            {
                if (pk instanceof UDPPacket)
                {
                    UDPPacket udp = (UDPPacket) pk;  
                    return (udp.dst_ip);
                }
               else if (pk instanceof ICMPPacket)
               {   
                  ICMPPacket icmp = (ICMPPacket) (pk);
                   return (icmp.dst_ip);
               }
               
               else if (pk instanceof ARPPacket)              
             {
                 ARPPacket arp = (ARPPacket) (pk);
                   b=arp.target_protoaddr;
                   s="";
                   x=0;
                   for(int i=0;i<4;i++)
                   {
                       
                       if(b[i]<0)
                       {
                           x=256-b[i];
                           s=s+String.valueOf(x);
                       }
                       else
                       {
                           x=b[i];
                           s=s+String.valueOf(x);
                       }
                   }
                   return s;
              }
               else if (pk instanceof TCPPacket)
               {
                   TCPPacket tcp = (TCPPacket) (pk);
                   return (tcp.dst_ip);
               } 
                else
                   return "-";
            }
           else  
           {
                b=pk.data;
                s="";x=0;
               for(byte c:b)
               {
                   if(c<0)
                   {
                       s+=(char) (256-c);
                   }
                   else
                   {
                       s+=(char) c;
                   }
              }
             return s;                  
           }
          
        } 
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 430, 520, 90);

        jButton2.setText("Save packets");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(610, 480, 113, 28);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 320, 520, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        if(rv==JFileChooser.APPROVE_OPTION)
        {
        try {
            JFileChooser ch=new JFileChooser();
            ch.showSaveDialog(null);
            File f=ch.getSelectedFile();
            String fnme=f.getAbsolutePath();
            fos = new FileOutputStream(fnme);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(al); 
            JOptionPane.showMessageDialog(this,"Successfully Saved");
            fos.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PacketDemo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
      }
    }//GEN-LAST:event_jButton2ActionPerformed
      
    public static void main(String args[]) {
       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Viewinfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viewinfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viewinfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viewinfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       // Viewinfo h=new Viewinfo();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}

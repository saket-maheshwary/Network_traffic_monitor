
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.*;
import jpcap.packet.*;
import java.awt.*;
import java.awt.event.AWTEventListener.*;
import javax.swing.*;

public class PacketDemo extends javax.swing.JFrame {

    JpcapCaptor cap;
    NetworkInterface[] devices;
    ArrayList<UDPPacket> arr1;
    ArrayList<ICMPPacket> arr2;
    ArrayList<ARPPacket> arr3;
    ArrayList<TCPPacket> arr4;
    ArrayList<Packet> arr;
    ArrayList<Integer> a;
    ArrayList<Packet> store = new ArrayList();
    Packet pk;
    mypanel p1;
    boolean flag = true;
    float count, count1, count2, count3, count4, count5;
    float p, p2, p3, p4, p5;
    int n1, n2, i, diff, j, rv;

    class Athread implements Runnable {

        Thread t1;

        Athread() throws IOException {
            cap = JpcapCaptor.openDevice(devices[0], 65535, true, 20);
            arr1 = new ArrayList<UDPPacket>();
            arr2 = new ArrayList<ICMPPacket>();
            arr3 = new ArrayList<ARPPacket>();
            arr4 = new ArrayList<jpcap.packet.TCPPacket>();
            arr = new ArrayList<Packet>();
            t1 = new Thread(this);
            t1.start();
        }

        public void run() {
            count = count1 = count2 = count3 = count4 = count5 = 0.0f;
            p = p2 = p3 = p4 = p5 = 0.0f;
            try {
                while (flag) {
                    pk = cap.getPacket();
                    if (pk != null) {
                        count++;
                        textArea1.append(pk + "\r\n");
                        arr.add(pk);
                        if(arr.size()==1000)
                        {
                            arr.clear();
                            arr1.clear();
                            arr2.clear();
                            arr3.clear();
                            arr4.clear();
                            
                        }
                        jTextField1.setText(String.valueOf(count));

                        if (pk instanceof UDPPacket) {
                            UDPPacket udp = (UDPPacket) (pk);
                            arr1.add(udp);
                            count1++;
                        } else if (pk instanceof ICMPPacket) {
                            ICMPPacket icmp = (ICMPPacket) (pk);
                            arr2.add(icmp);
                            count2++;

                        } else if (pk instanceof ARPPacket) {
                            ARPPacket arp = (ARPPacket) (pk);
                            arr3.add(arp);
                            count3++;

                        } else if (pk instanceof TCPPacket) {
                            TCPPacket tcp = (TCPPacket) (pk);
                            arr4.add(tcp);
                            count4++;
                        } else {
                            count5++;
                        }

                        p = (count1 * 100) / count;
                        p2 = (count2 * 100) / count;
                        p3 = (count3 * 100) / count;
                        p4 = (count4 * 100) / count;
                        p5 = (count5 * 100) / count;
                        jTextField2.setText(String.valueOf(count1) + " (" + String.valueOf(p) + "%)");
                        jTextField3.setText(String.valueOf(count2) + " (" + String.valueOf(p2) + "%)");
                        jTextField4.setText(String.valueOf(count3) + " (" + String.valueOf(p3) + "%)");
                        jTextField5.setText(String.valueOf(count4) + " (" + String.valueOf(p4) + "%)");
                        jTextField6.setText(String.valueOf(count5) + " (" + String.valueOf(p5) + "%)");
                    }
                }

                count = count1 = count2 = count3 = count4 = count5 = 0.0f;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public PacketDemo() {
        initComponents();
        label1.setVisible(false);
        label2.setVisible(false);
        jTextField1.setVisible(false);
        jLabel1.setVisible(false);
        STOP.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton5.setVisible(false);

        devices = JpcapCaptor.getDeviceList();
        for (int i = 0; i < devices.length; i++) {
            for (NetworkInterfaceAddress a : devices[i].addresses) {
                choice1.add(a.address.getHostName() + " (" + devices[i].description + ")");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        textArea1 = new java.awt.TextArea();
        choice1 = new java.awt.Choice();
        STOP = new java.awt.Button();
        Start = new java.awt.Button();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        getContentPane().setLayout(null);
        getContentPane().add(textArea1);
        textArea1.setBounds(60, 140, 688, 80);
        getContentPane().add(choice1);
        choice1.setBounds(60, 20, 688, 20);

        STOP.setBackground(new java.awt.Color(255, 0, 0));
        STOP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        STOP.setLabel("Stop");
        STOP.setName("STOP");
        STOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STOPActionPerformed(evt);
            }
        });
        getContentPane().add(STOP);
        STOP.setBounds(454, 60, 100, 33);
        STOP.getAccessibleContext().setAccessibleName(null);

        Start.setBackground(new java.awt.Color(0, 153, 0));
        Start.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Start.setLabel("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        getContentPane().add(Start);
        Start.setBounds(186, 60, 100, 32);

        label1.setForeground(new java.awt.Color(255, 0, 0));
        label1.setText("Connection stopped");
        label1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                label1ComponentHidden(evt);
            }
        });
        getContentPane().add(label1);
        label1.setBounds(310, 90, 131, 20);

        label2.setForeground(new java.awt.Color(0, 153, 0));
        label2.setText("Connection started......");
        label2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                label2ComponentHidden(evt);
            }
        });
        getContentPane().add(label2);
        label2.setBounds(310, 60, 134, 20);

        jLabel1.setText("total packets =");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(758, 140, 110, 25);

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(872, 142, 128, 20);

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("udp packets =");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(758, 176, 91, 14);

        jLabel3.setText("icmp packets =");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(758, 214, 91, 14);

        jLabel4.setText("arp packets =");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(758, 255, 80, 14);

        jLabel5.setText("tcp packets =");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(758, 293, 66, 14);

        jTextField2.setText("0");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(872, 173, 128, 20);

        jTextField3.setText("0");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(872, 211, 128, 20);

        jTextField4.setText("0");
        getContentPane().add(jTextField4);
        jTextField4.setBounds(872, 252, 128, 20);

        jTextField5.setText("0");
        getContentPane().add(jTextField5);
        jTextField5.setBounds(872, 290, 128, 20);

        jTextField6.setText("0");
        getContentPane().add(jTextField6);
        jTextField6.setBounds(872, 332, 128, 20);

        jLabel6.setText("others packets =");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(758, 335, 82, 14);

        jButton1.setText("traffic grph");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(872, 20, 97, 23);

        jButton2.setText("View Detail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(872, 61, 97, 23);

        jButton3.setText("Save packets");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(865, 370, 126, 23);

        jButton5.setText("Open file packet");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(865, 404, 126, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        if (evt.getSource() == Start) {
            jButton1.setVisible(true);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
            jButton5.setVisible(true);
            STOP.setVisible(true);
            flag = true;
            jTextField1.setVisible(true);
            jLabel1.setVisible(true);
            label1.setVisible(false);
            label2.setVisible(true);
            p1 = new mypanel();
            p1.setBounds(100, 150, 500, 600);
            add(p1);
            p1.repaint();
            try {
                new Athread();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
    }//GEN-LAST:event_StartActionPerformed

    private void STOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STOPActionPerformed
        if (evt.getSource() == STOP) {
            flag = false;
            label1.setVisible(true);
            label2.setVisible(false);
            jTextField1.setVisible(true);
            jLabel1.setVisible(true);
            jButton1.setVisible(false);
            p1.repaint();
        }
    }//GEN-LAST:event_STOPActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        netpanel n = new netpanel();
        n.setResizable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        flag = false;
        JOptionPane.showMessageDialog(this, "This will STOP packet capturing");
        label2.setVisible(false);
        label1.setVisible(true);
        Viewinfo obj = new Viewinfo(arr);
        obj.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        flag = false;
        JOptionPane.showMessageDialog(this, "This will STOP packet capturing");
        label2.setVisible(false);
        label1.setVisible(true);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        if (rv == JFileChooser.APPROVE_OPTION) {
            try {
                JFileChooser ch = new JFileChooser();
                ch.showSaveDialog(null);
                File f = ch.getSelectedFile();
                String fnme = f.getAbsolutePath();
                fos = new FileOutputStream(fnme);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(arr);
                JOptionPane.showMessageDialog(this, "Successfully Saved");
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(PacketDemo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (rv == JFileChooser.APPROVE_OPTION) {
            try {
                JFileChooser ch = new JFileChooser();
                ch.showOpenDialog(null);
                File f = ch.getSelectedFile();
                String fnm = f.getAbsolutePath();
                fis = new FileInputStream(fnm);
                ois = new ObjectInputStream(fis);
                try {
                    store = (ArrayList<Packet>) (ois.readObject());
                    Analyzer aa = new Analyzer(store);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
                Logger.getLogger(PacketDemo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void label1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_label1ComponentHidden
   }//GEN-LAST:event_label1ComponentHidden

    private void label2ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_label2ComponentHidden
   }//GEN-LAST:event_label2ComponentHidden

    class mypanel extends JPanel {

        public void paint(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100, 100, 480, 350);
            g.setColor(Color.BLACK);
            g.drawLine(100, 400, 500, 400);
            g.drawString("UDP", 120, 420);
            g.drawString("ICMP", 190, 420);
            g.drawString("ARP", 280, 420);
            g.drawString("TCP", 360, 420);
            g.drawString("OTHERS", 420, 420);
            g.setColor(Color.red);
            g.fillRect(100, 400 - (int) p, 60, (int) p);
            g.setColor(Color.BLUE);
            g.fillRect(180, 400 - (int) p2, 60, (int) p2);
            g.setColor(Color.green);
            g.fillRect(260, 400 - (int) p3, 60, (int) p3);
            g.setColor(Color.MAGENTA);
            g.fillRect(340, 400 - (int) p4, 60, (int) p4);
            g.setColor(Color.YELLOW);
            g.fillRect(420, 400 - (int) p5, 60, (int) p5);

        }
    }

    class netpanel extends JFrame {

        public netpanel() {
            setLayout(null);
            mypanel2 p2 = new mypanel2();
            p2.setBounds(0, 0, 500, 500);
            add(p2);
            Bthread b1 = new Bthread();
            setVisible(true);
            setSize(500, 500);
        }

        class Bthread implements Runnable {

            Thread t2;

            Bthread() {
                n1 = n2 = 0;
                a = new ArrayList();
                t2 = new Thread(this);
                t2.start();
                for (int index = 0; index < 10; index++) {
                    a.add(0);
                }
            }

            @Override
            public void run() {

                for (; true;) {
                    n1 = arr.size();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    diff = n1 - n2;
                    a.remove(0);
                    a.add(diff);
                    n2 = n1;
                    repaint();
                }
            }
        }

        class mypanel2 extends JPanel {

            public void paint(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 500, 500);
                g.setColor(Color.green);
                g.drawLine(0, 450, 500, 450);
                g.drawLine(0, 0, 0, 500);
                g.drawLine(50, 0, 50, 500);
                g.drawLine(100, 0, 100, 500);
                g.drawLine(150, 0, 150, 500);
                g.drawLine(200, 0, 200, 500);
                g.drawLine(250, 0, 250, 500);
                g.drawLine(300, 0, 300, 500);
                g.drawLine(350, 0, 350, 500);
                g.drawLine(400, 0, 400, 500);
                g.drawLine(450, 0, 450, 500);
                g.drawLine(500, 0, 500, 500);
                g.drawLine(0, 50, 500, 50);
                g.drawLine(0, 100, 500, 100);
                g.drawLine(0, 150, 500, 150);
                g.drawLine(0, 200, 500, 200);
                g.drawLine(0, 250, 500, 250);
                g.drawLine(0, 300, 500, 300);
                g.drawLine(0, 350, 500, 350);
                g.drawLine(0, 400, 500, 400);


                for (j = 0; j < a.size() - 1; j++) {
                    g.setColor(Color.red);
                    g.drawLine(j * 50, 450 - a.get(j), (j + 1) * 50, 450 - a.get(j + 1));
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
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
            java.util.logging.Logger.getLogger(PacketDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PacketDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PacketDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PacketDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PacketDemo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button STOP;
    private java.awt.Button Start;
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}

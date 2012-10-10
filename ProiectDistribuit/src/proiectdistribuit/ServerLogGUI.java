/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author MindSlave
 */
public class ServerLogGUI extends JFrame{
    
    JScrollPane pane;
    JButton inchide = new JButton("STOP!");
    JTextArea text;
    JButton ascunde = new JButton("»");
    JButton afiseaza = new JButton("«");
    int spaceing=5;
    Toolkit toolkit =  Toolkit.getDefaultToolkit ();
    Anima a;
    int frameheight=200;
    int framewidth=250;
    MainGUI mg;
    Server server;
    InetAddress ia=null;
    Socket socket=null;
    Vector<String> v=new Vector<String>();


    public ServerLogGUI(MainGUI mg) {
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerLogGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mg=mg;
        v.add(ia.getHostName());
        v.add(ia.getHostAddress());
        v.add(String.valueOf(9999));
        mg.adaugaintabel(v);
        setTitle("LOG SERVER SEARCH");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
        setBounds((int)toolkit.getScreenSize().getWidth()-framewidth-spaceing,
                (int)toolkit.getScreenSize().getHeight()-(frameheight*2)-(spaceing*10),
                framewidth,
                frameheight);
        make();

        ascunde.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                animaascundere();
                ascunde.setVisible(false);
                afiseaza.setVisible(true);
            }
        });
        afiseaza.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                animaafisare();
                afiseaza.setVisible(false);
                ascunde.setVisible(true);
            }
        });
        inchide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                inchideserver();
            }
        });
    }

    void make(){

        afiseaza.setVisible(false);
        afiseaza.setBounds(0, 0, 35,frameheight );
        add(afiseaza);
        ascunde.setVisible(true);
        ascunde.setBounds(0, 0, 35,frameheight );
        add(ascunde);
        inchide.setVisible(true);
        inchide.setBounds(afiseaza.getWidth()+spaceing,frameheight-30-spaceing, framewidth-afiseaza.getWidth()-(spaceing*2), 30);
        add(inchide);
        text = new JTextArea();
        text.setVisible(true);
        text.setEditable(false);
        pane = new JScrollPane(text);
        pane.setVisible(true);
        pane.setBounds(afiseaza.getWidth()+spaceing, spaceing,
                framewidth-afiseaza.getWidth()-(spaceing*2), frameheight-inchide.getHeight()-(spaceing*2));
        add(pane);
        server=new Server(true,this);
        server.start();

    }

    void adaugaText(String srt){
       text.setText(srt+"\n"+text.getText());
    }
    void animaafisare(){

        a=new Anima(this, 1);
        a.start();
    }
    void animaascundere(){
        a=new Anima(this, 0);
        a.start();
    }
    void inchideserver(){
         this.dispose();
        server.connect=false;
        try {
            server.serverSocket.close();
        } catch (IOException ex) {
            //cui ii pasa
        }
        try {
            socket = new Socket(ia, 9999);
        } catch (IOException ex) {
            //who cares
        }
 
       
    }

}

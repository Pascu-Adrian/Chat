/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author MindSlave
 */
public class ClientGUI extends JFrame{
    JScrollPane panou;
    JScrollPane panou1;
    JTextArea content=new JTextArea();
    JTextField input=new JTextField();
    JButton send=new JButton("SEND");
    JButton privat=new JButton("PRIVAT");
    JTable useri=new JTable();
    Vector<String> head;
    Vector<Vector> vectoruseri = new Vector<Vector>();
    TableModel tm;
    int port;
    String address;
    Client cl=null;
    Socket socket;
    InetAddress ia;
    ClientPrivatGUI cpg=null;
    String nick;
    public Vector<ClientPrivatGUI> privati;

    public ClientGUI() {
    }


    public ClientGUI(Client cl,String nick) {
        privati=new Vector<ClientPrivatGUI>();
        this.cl=cl;
        this.nick=nick;
        setTitle("CONECTAT CA"+nick);
        setVisible(true);
        setBounds(200, 200, 500, 500);
        setLayout(null);
        
        setResizable(true);
        make();




     addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent ev) {
                deconecteaza();
            }
        });
        
        send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                trimitePUB(input.getText());
                input.setText("");

            }
        });
        input.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10){
                    trimitePUB(input.getText());
                input.setText("");
                }
            }
        });
        privat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideprivat(String.valueOf(useri.getModel().getValueAt(useri.getSelectedRow(),0)),"");
            }
        });
        useri.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
              privat.setEnabled(true);
            }
            public void mouseReleased(MouseEvent e) {
                privat.setEnabled(true);
            }

            public void mousePressed(MouseEvent e) {
                privat.setEnabled(true);
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });


        }

 
    

    public void make(){
        vectoruseri=new Vector();
        head=new Vector<String>();
        head.add("Nick");
        head.add("IP");
        tm=new DefaultTableModel(vectoruseri, head);

        content=new JTextArea();
        content.setVisible(true);
        content.setEditable(false);
        panou=new JScrollPane(content);
        panou.setVisible(true);
        panou.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panou.setBounds(10, 10, 200, 200);
        panou.setAutoscrolls(true);
        add(panou);
        useri=new JTable(tm);
        useri.setVisible(true);
        useri.setAutoCreateRowSorter(true);

        panou1=new JScrollPane(useri);
        panou1.setVisible(true);
        panou1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panou1.setBounds(220, 10, 200, 200);
        add(panou1);

        input.setVisible(true);
        input.setBounds(10, 220, 200, 100);
        add(input);

        send.setVisible(true);
        send.setBounds(220, 220, 100, 100);
        add(send);

        privat.setVisible(true);
        privat.setBounds(330,220, 100, 100);
        privat.setEnabled(false);
        add(privat);
        
    }
    public void privat(String txt){
        String[] str=txt.split("«");
        if(privati.size()==0){
            deschideprivat(str[1],str[2]);
        }
        else{
        for(int i=0;i<privati.size();i++){
           if(i<privati.size()-1){
         if(privati.elementAt(i).nick.equals(str[1])){
             privati.elementAt(i).adaugatext(str[1]+": "+str[2]);
             break;
         }
            }
         if(i==privati.size()-1){
               if(privati.elementAt(i).nick.equals(str[1])){
             privati.elementAt(i).adaugatext(str[1]+": "+str[2]);
             break;
           }
                else{
                deschideprivat(str[1],str[2]);
               

               }
           }
        }
        }
    }
    public void deschideprivat(String nickul,String txt){
        cpg=new ClientPrivatGUI(this, nickul);
        cpg.adaugatext(nickul+": "+txt);
        privati.add(cpg);
    }
    public void inchideprivat(ClientPrivatGUI cprg){
        for(int i=0;i<privati.size();i++){
            if(privati.elementAt(i).nick.equals(cprg.nick)){
                privati.elementAt(i).dispose();
                privati.removeElementAt(i);
            }
        }
    }
    public void inchidetoateprivat(){
        for(int i=0;i<privati.size();i++){
            inchideprivat(privati.elementAt(i));
        }
    }
    void adaugaText(String msg){
        content.setText(msg+"\n"+content.getText());
    }

    public void incarcaLista(Vector<Vector<String>> v){
        tm=new DefaultTableModel(v,head);
        useri.setModel(tm);
    }

    public void trimitePUB(String msg){
        cl.ct.send("PUB«"+nick+"«"+msg);
    }
    public void trimitePRV(String msg){
        cl.ct.send("PRV«"+nick+"«"+msg);
    }

    void deconecteaza(){
        inchidetoateprivat();
                cl.ct.send("LGO");
                cl.ct.ok=false;
                dispose();
    }

}

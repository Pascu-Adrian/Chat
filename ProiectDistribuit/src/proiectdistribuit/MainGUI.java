/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author MindSlave
 */
public class MainGUI extends JFrame implements Runnable{
    ServerLogGUI slg=null;
    CautareLogGUI clg=null;
    Client cl=null;
    int selectedindex=-1;
     JOptionPane op=new JOptionPane();
     String[] addressArray;
     Vector<Vector> VectorListaServere = new Vector<Vector>();
     Vector<String> Coloane = new Vector<String>();

    JButton connect = new JButton("CONNECT");
    JButton host = new JButton("HOST");
    JButton reset = new JButton("RESET");

    TableModel model;
    JTable tabel;
    JScrollPane panou;

    JLabel from = new JLabel("From:");
    JLabel to = new JLabel("To:");

    JSpinner from1 = new JSpinner(new SpinnerNumberModel(1, 1, 255, 1));
    JSpinner from2 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
    JSpinner from3 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
    JSpinner from4 = new JSpinner(new SpinnerNumberModel(1, 1, 255, 1));

    JSpinner to1 = new JSpinner(new SpinnerNumberModel(1, 1, 255, 1));
    JSpinner to2 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
    JSpinner to3 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
    JSpinner to4 = new JSpinner(new SpinnerNumberModel(1, 1, 255, 1));

    JLabel atentie = new JLabel("Atentie! durata de cautare este de:");
    JLabel durata = new JLabel("0.33");
    JLabel secunde = new JLabel("sec");

    JButton cauta = new JButton("CAUTA SERVERE");

    Anima a;

    Toolkit toolkit =  Toolkit.getDefaultToolkit ();

    public MainGUI() {
        setTitle("SERVER CLIENT BUNDLE");
        setVisible(true);
        setBounds((toolkit.getScreenSize().width/2)-500, (toolkit.getScreenSize().height/2)-500, 0, 0);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        CreeazaObiecteInterfata();

        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        host.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                host();
            }
        });
        reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        cauta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cautaON();
            }
        });
        from4.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(verificadiferenta()){
                    calculeazadurata();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Valoarea de inceput a ipului nu poate fi mai mare decat cea de sfarsit",
                            "Eroare valori ip", JOptionPane.ERROR_MESSAGE);
                    from4.setValue(to4.getValue());
                }
            }
        });
        from3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(verificadiferenta()){
                    calculeazadurata();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Valoarea de inceput a ipului nu poate fi mai mare decat cea de sfarsit",
                            "Eroare valori ip", JOptionPane.ERROR_MESSAGE);
                    from3.setValue(to3.getValue());
                    from4.setValue(to4.getValue());
                }
            }
        });
        to3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(verificadiferenta()){
                    calculeazadurata();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Valoarea de inceput a ipului nu poate fi mai mare decat cea de sfarsit",
                            "Eroare valori ip", JOptionPane.ERROR_MESSAGE);
                    to3.setValue(from3.getValue());
                }
            }
        });
        to4.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(verificadiferenta()){
                    calculeazadurata();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Valoarea de inceput a ipului nu poate fi mai mare decat cea de sfarsit",
                            "Eroare valori ip", JOptionPane.ERROR_MESSAGE);
                    to4.setValue(from4.getValue());
                }
            }
        });

        tabel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
              selectedindex=tabel.getSelectedRow();
            }
            public void mouseReleased(MouseEvent e) {
                selectedindex=tabel.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
                selectedindex=tabel.getSelectedRow();
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });


        
        a=new Anima(this);
        a.start();




    }

    public void run() {
 
    }

    void incarcaspinnere(){
          try {
           InetAddress ia = InetAddress.getLocalHost();
           addressArray=ia.getHostAddress().split("\\.");
        } catch (UnknownHostException ex) {

        }

        from1.setValue(Integer.parseInt(addressArray[0]));
        from2.setValue(Integer.parseInt(addressArray[1]));
        from3.setValue(Integer.parseInt(addressArray[2]));
        from4.setValue(Integer.parseInt(addressArray[3]));

        to1.setValue(Integer.parseInt(addressArray[0]));
        to2.setValue(Integer.parseInt(addressArray[1]));
        to3.setValue(Integer.parseInt(addressArray[2]));
        to4.setValue(Integer.parseInt(addressArray[3]));

        from1.setEnabled(false);
        from2.setEnabled(false);

        to1.setEnabled(false);
        to2.setEnabled(false);
    }
    
    void calculeazadurata(){
        durata.setText(Double.toString(((((Integer.parseInt(String.valueOf(to3.getValue()))-
                            Integer.parseInt(String.valueOf(from3.getValue())))*255)+
                            Integer.parseInt(String.valueOf(to4.getValue()))-
                            Integer.parseInt(String.valueOf(from4.getValue())))*1.3)/2));
    }

    boolean verificadiferenta(){
        if(Integer.parseInt(String.valueOf(from3.getValue()))>Integer.parseInt(String.valueOf(to3.getValue()))){
            return false;
        }
        if(Integer.parseInt(String.valueOf(from4.getValue()))>Integer.parseInt(String.valueOf(to4.getValue()))){
            if(Integer.parseInt(String.valueOf(from3.getValue()))<Integer.parseInt(String.valueOf(to3.getValue()))){
            return true;
        }
            else{
                return false;
            }
        }
        if(Integer.parseInt(String.valueOf(from4.getValue()))<=Integer.parseInt(String.valueOf(to4.getValue()))&&
                Integer.parseInt(String.valueOf(from3.getValue()))<=Integer.parseInt(String.valueOf(to3.getValue()))){
            return true;
        }

        else{
            return false;
         }
    }

    void CreeazaObiecteInterfata(){

        Coloane.add("NUME");
        Coloane.add("IP ADRESS");
        Coloane.add("SERVER PORT");


        model = new DefaultTableModel(VectorListaServere, Coloane);
        tabel = new JTable(model);

        tabel.setVisible(true);
        tabel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        tabel.setAutoCreateRowSorter(true);



        connect.setVisible(true);
        connect.setBounds(-200, 10, 80, 30);
        add(connect);


        host.setVisible(true);
        host.setBounds(-200, 10, 80, 30);
        add(host);


        reset.setVisible(true);
        reset.setBounds(-200, 10, 80, 30);
        add(reset);

        tabel.setVisible(true);
        tabel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        panou=new JScrollPane(tabel);
        panou.setVisible(true);
        panou.setBounds(-400, connect.getY()+connect.getHeight()+10, this.getWidth()*2, 200);
        add(panou);

        from.setVisible(true);
        from.setBounds(-200, panou.getY()+panou.getHeight()+10, 40, 30);
        add(from);

        from1.setVisible(true);
        from1.setBounds(-200, panou.getY()+panou.getHeight()+10, 55, 30);
        add(from1);

        from2.setVisible(true);
        from2.setBounds(-200, panou.getY()+panou.getHeight()+10, 55, 30);
        add(from2);

        from3.setVisible(true);
        from3.setBounds(-200, panou.getY()+panou.getHeight()+10, 55, 30);
        add(from3);

        from4.setVisible(true);
        from4.setBounds(-200, panou.getY()+panou.getHeight()+10, 55, 30);
        add(from4);

        to.setVisible(true);
        to.setBounds(-200, from.getY()+from.getHeight()+10, 40, 30);
        add(to);

        to1.setVisible(true);
        to1.setBounds(-200, from.getY()+from.getHeight()+10, 55, 30);
        add(to1);

        to2.setVisible(true);
        to2.setBounds(-200, from.getY()+from.getHeight()+10, 55, 30);
        add(to2);

        to3.setVisible(true);
        to3.setBounds(-200, from.getY()+from.getHeight()+10, 55, 30);
        add(to3);

        to4.setVisible(true);
        to4.setBounds(-200, from.getY()+from.getHeight()+10, 55, 30);
        add(to4);

        incarcaspinnere();

        cauta.setVisible(true);
        cauta.setBounds(-400, to.getY()+to.getHeight()+10,this.getWidth()*2, 30);
        add(cauta);

        atentie.setVisible(true);
        atentie.setBounds(-200, cauta.getY()+cauta.getHeight()+10, 200, 30);
        add(atentie);

        durata.setVisible(true);
        durata.setBounds(-200, cauta.getY()+cauta.getHeight()+10,45 , 30);
        add(durata);

        secunde.setVisible(true);
        secunde.setBounds(-200, cauta.getY()+cauta.getHeight()+10, 30, 30);
        add(secunde);
    }


    public void adaugaintabel(Vector<String> vos1){
        if(!VectorListaServere.contains(vos1)){
          VectorListaServere.addElement(vos1);
      }
        model=new DefaultTableModel(VectorListaServere, Coloane);
        tabel.setModel(model);
        if(selectedindex!=-1){
        tabel.setRowSelectionInterval(selectedindex, selectedindex);
        }

    }
    public void stergedintabel(Vector<String> vos){
        if(VectorListaServere.contains(vos)&&!VectorListaServere.isEmpty()){
          VectorListaServere.removeElement(vos);
          model=new DefaultTableModel(VectorListaServere, Coloane);
        tabel.setModel(model);
       if(selectedindex!=-1){
           if(selectedindex<tabel.getModel().getRowCount()){
        tabel.setRowSelectionInterval(selectedindex, selectedindex);
           }
            else{
               if(tabel.getModel().getRowCount()>0){
                   selectedindex=tabel.getModel().getRowCount()-1;
                   tabel.setRowSelectionInterval(selectedindex, selectedindex);
               }
                else{
                   selectedindex=-1;
                   tabel.clearSelection();
                }
           }
        }
      }
        


    }

    public void cautaON(){
        clg = new CautareLogGUI(this);
        
    }

    //spinner to int
    public int sti(JSpinner spinner){
        return Integer.parseInt(String.valueOf(spinner.getValue()));
    }

    
    public void host(){
        slg=new ServerLogGUI(this);
    }

    public void connect(){
        if(tabel.getSelectedRow()!=-1){
            cl=new Client(Integer.parseInt(String.valueOf(tabel.getModel().getValueAt(tabel.getSelectedRow(),2 ))),String.valueOf(tabel.getModel().getValueAt(tabel.getSelectedRow(),1 )),this);
            }
        else{
        JOptionPane.showMessageDialog(null, "Va rog selectati un server din lista pentru a va conecta", "Eroare conectare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void reset(){

        if(slg!=null){
        slg.inchideserver();
        }
        if(clg!=null){
        clg.inchide();
        }
        if(cl!=null){
            cl.ct.inchide();
        }
        resettabel();
        incarcaspinnere();
    }
    public void resettabel(){
        VectorListaServere.removeAllElements();
        model=new DefaultTableModel(VectorListaServere, Coloane);
        tabel.setModel(model);
    }

}

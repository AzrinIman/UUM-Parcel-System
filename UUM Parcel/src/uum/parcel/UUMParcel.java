/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uum.parcel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class UUMParcel {


    ArrayList<Student> studentlist = new ArrayList<Student>();
    String header[] = new String[]{"ParcelID", "Ownername", "sendername", "dateofarrival", "phone", "courier" };
    DefaultTableModel dtm = new DefaultTableModel(header, 1);

    UUMParcel() {

        JFrame frame = new JFrame("UUM Parcel System");
        frame.setSize(750, 400);

        JLabel jlabel = new JLabel("Owner Name");
        jlabel.setBounds(20, 10, 90, 20);
        frame.add(jlabel);

        JLabel jlabela = new JLabel("ParcelID No");
        jlabela.setBounds(20, 30, 90, 20);
        frame.add(jlabela);

        JLabel jlabelb = new JLabel("Sender Name");
        jlabelb.setBounds(20, 50, 90, 20);
        frame.add(jlabelb);
        
        JLabel jlabelc = new JLabel("Date of Arrival");
        jlabelc.setBounds(350, 10, 90, 20);
        frame.add(jlabelc);
        
        JLabel jlabeld = new JLabel("Phone Number");
        jlabeld.setBounds(350, 30, 90, 20);
        frame.add(jlabeld);
        
        JLabel jlabele = new JLabel("Courier");
        jlabele.setBounds(350, 50, 90, 20);
        frame.add(jlabele);

        JTextField jtfownername = new JTextField();
        jtfownername.setBounds(110, 10, 200, 20);
        frame.add(jtfownername);

        JTextField jtfparcelID = new JTextField();
        jtfparcelID.setBounds(110, 30, 200, 20);
        frame.add(jtfparcelID);

        JTextField jtfsendername = new JTextField();
        jtfsendername.setBounds(110, 50, 200, 20);
        frame.add(jtfsendername);
        
        JTextField jtfdateofarrival = new JTextField();
        jtfdateofarrival.setBounds(440, 10, 150, 20);
        frame.add(jtfdateofarrival);
        
        JTextField jtfphone = new JTextField();
        jtfphone.setBounds(440, 30, 150, 20);
        frame.add(jtfphone);
        
        JTextField jtfcourier = new JTextField();
        jtfcourier.setBounds(440, 50, 150, 20);
        frame.add(jtfcourier);

        JButton jbuttoninsert = new JButton("INSERT");
        jbuttoninsert.setBounds(90, 95, 90, 30);
        frame.add(jbuttoninsert);

        JButton jbuttondelete = new JButton("DELETE");
        jbuttondelete.setBounds(190, 95, 90, 30);
        frame.add(jbuttondelete);

        JButton jbuttonsearch = new JButton("SEARCH");
        jbuttonsearch.setBounds(290, 95, 90, 30);
        frame.add(jbuttonsearch);
        
        JButton jbuttonupdate = new JButton("UPDATE");
        jbuttonupdate.setBounds(390, 95, 90, 30);
        frame.add(jbuttonupdate);

        JButton jbuttonexit = new JButton("EXIT");
        jbuttonexit.setBounds(490, 95, 90, 30);
        frame.add(jbuttonexit);

        //table creation
        JTable jtable = new JTable();
        jtable.setBounds(100, 200, 600, 600);
        frame.add(jtable);
        jtable.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(jtable);
        scrollPane.setBounds(20, 150, 700, 180);
        frame.add(scrollPane);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(500);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(500);

        jbuttoninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ownername = jtfownername.getText().toUpperCase();
                String parcelID = jtfparcelID.getText();
                String sendername = jtfsendername.getText().toUpperCase();
                String dateofarrival = jtfdateofarrival.getText();
                String phone = jtfphone.getText();
                String courier = jtfcourier.getText();
                if (ownername.length() < 5) {
                    JOptionPane.showMessageDialog(frame, "Owner Name should contain more than 5 char!!!");
                    return;
                }
                if (!isInteger(parcelID)) {
                    JOptionPane.showMessageDialog(frame, "ParcelID should only contain integer!!!");
                    return;
                }

                Student student = new Student(ownername, parcelID, sendername, dateofarrival, phone, courier);
                studentlist.add(student);//create object list array
                writeData();
            }
        });

        jbuttondelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String parcelID = JOptionPane.showInputDialog("ParcelID number to delete?");
                if (parcelID != null) {
                    System.out.println("not null");
                    for (int i = 0; i < studentlist.size(); i++) {
                        if (studentlist.get(i).getParcelID().equalsIgnoreCase(parcelID)) {
                            studentlist.remove(i);
                        }
                    }
                    writeData();
                }
            }
        });

        jbuttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String parcelID = JOptionPane.showInputDialog("Enter ParcelID Number?");
                if (parcelID != null) {
                    for (int i = 0; i < studentlist.size(); i++) {
                        if (studentlist.get(i).getParcelID().equalsIgnoreCase(parcelID)) {
                            JOptionPane.showMessageDialog(frame, "Found!!!");
                            jtfparcelID.setText(studentlist.get(i).getParcelID());
                            jtfownername.setText(studentlist.get(i).getOwnername());
                            jtfownername.setText(studentlist.get(i).getSendername());
                            jtfdateofarrival.setText(studentlist.get(i).getDateofarrival());
                            jtfphone.setText(studentlist.get(i).getPhone());
                            jtfcourier.setText(studentlist.get(i).getCourier());
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Not Found!!!");
                }
            }
        });
          jbuttonupdate.addActionListener(new ActionListener() {
            private String parcelID;
            @Override
            public void actionPerformed(ActionEvent ae) {
                String parcelID = jtfparcelID.getText();
                String ownername = jtfownername.getText();
                String sendername = jtfsendername.getText();
                String dateofarrival = jtfdateofarrival.getText();
                String phone = jtfphone.getText();
                String courier = jtfcourier.getText();
                
               
                if (parcelID != null) {
                    for (int i = 0; i < studentlist.size(); i++) {
                        if (studentlist.get(i).getParcelID().equalsIgnoreCase(parcelID)) {
                            studentlist.get(i).setOwnername(ownername);
                            studentlist.get(i).setSendername(sendername);
                            studentlist.get(i).setDateofarrival(dateofarrival);
                            studentlist.get(i).setPhone(phone);
                            studentlist.get(i).setCourier(courier);
                            JOptionPane.showMessageDialog(frame, "Updated!!!");
                        }
                    }
                }
                writeData();
            }
        });

        jbuttonexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false); //you can't see me!
                frame.dispose();
            }
        });

        readData();
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.getSelectedRow();
                jtfparcelID.setText(dtm.getValueAt(row, 0).toString());
                jtfownername.setText(dtm.getValueAt(row, 1).toString());
                jtfsendername.setText(dtm.getValueAt(row, 2).toString());
                jtfdateofarrival.setText(dtm.getValueAt(row, 3).toString());
                jtfphone.setText(dtm.getValueAt(row, 4).toString());
                jtfcourier.setText(dtm.getValueAt(row, 5).toString());
            }
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UUMParcel uumparcel = new UUMParcel();
        

    }

    void readData() { //read data from "data.txt" and display on table
        try {
            File file = new File("data.txt"); //create file
            file.createNewFile();//if not exit
            FileReader f = new FileReader("data.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '-') {
                    System.out.println(sb);
                    String studentarray[] = sb.toString().split(",");
                    Student student = new Student(studentarray[0], studentarray[1], studentarray[2], studentarray[3], studentarray[4],studentarray[5]);
                    studentlist.add(student);
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            dtm.setRowCount(0); //update table
            for (int i = 0; i < studentlist.size(); i++) {//populate table using object list
                Object[] objs = {studentlist.get(i).getParcelID(), studentlist.get(i).getOwnername(), studentlist.get(i).getSendername(), studentlist.get(i).getDateofarrival(), studentlist.get(i).getPhone(), studentlist.get(i).getCourier()};
                dtm.addRow(objs);
            }
        } catch (IOException e) {
        }
    }

    private void writeData() { //write data to file "data.txt"
        try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < studentlist.size(); i++) {
                sb.append(studentlist.get(i).getOwnername() + "," + studentlist.get(i).getParcelID() + "," + studentlist.get(i).getSendername() + "," + studentlist.get(i).getDateofarrival() + "," + studentlist.get(i).getPhone() + "-" + studentlist.get(i).getCourier());
            }
            f.write(sb.toString());
            f.close();
        } catch (IOException e) {
            return;
        }
        dtm.setRowCount(0); //update table content
        for (int i = 0; i < studentlist.size(); i++) {//populate table using object list
            Object[] objs = {studentlist.get(i).getParcelID(), studentlist.get(i).getOwnername(), studentlist.get(i).getSendername(), studentlist.get(i).getDateofarrival(), studentlist.get(i).getPhone(),studentlist.get(i).getCourier()};
            dtm.addRow(objs);
        }
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}

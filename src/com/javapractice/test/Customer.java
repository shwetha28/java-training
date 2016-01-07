/*QUE 1:create a table customer with id,name,description,email_id,dob.
 * QUE 2: write a program to fetch the data from the database and dispaly in the tabular format
 */

package com.javapractice.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.*;
import java.sql.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class Customer extends JDialog {


private JTable table; //to display in the tabular format
@SuppressWarnings({ "unchecked", "rawtypes" })
public static Vector rows() {	//to fetch all the row info created vector method name row

    Vector data = new Vector();
    
           try {
        	   	//connecting to the oracle database
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

			Statement  stmt = con.createStatement();
            //executing the query to get all the cust details
			ResultSet rset = stmt.executeQuery("SELECT * FROM SHWETHA.customer ");
            ResultSetMetaData md = rset.getMetaData();
            int columns = md.getColumnCount();



            while (rset.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rset.getObject(i));
                }

                data.addElement(row);

            }

            rset.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return data;
}


   @SuppressWarnings({ "unchecked", "rawtypes" })
public static Vector columns()	//similar to rows,but here extracting column names
    {   
        Vector cols = new Vector ();
        String sql2=null;
        try { 
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

            sql2 = "select * from SHWETHA.customer";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            for (int i = 1; i <= columns; i++) {
            cols.addElement(md.getColumnName(i));
            }
           } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return cols;
    }

public static void main(String args[]) {
    try {
        Customer dialog = new Customer();
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public Customer() {
    super();
    getContentPane().setLayout(null);//set the content pane and give the boundary values
    setTitle("customer table");
    setBounds(100, 100, 500, 363);
    setResizable(false);

    final JLabel customerLabel = new JLabel();//create a simple label to diaplay cust title
    customerLabel.setText("Listing \"customer\" content . . . ");
    customerLabel.setBounds(10, 34, 274, 16);
    getContentPane().add(customerLabel);

    final JLabel label = new JLabel();
    label.setBounds(402, 18, 49, 48);
    getContentPane().add(label);

    final JButton okButton = new JButton();//simple button is created to exit from the window
    okButton.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent arg0) {
            dispose();
            setVisible(false);
        }


    });
    okButton.setText("Ok");
    okButton.setBounds(10, 291, 148, 32);
    getContentPane().add(okButton);
    
    final JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 85, 474, 187);
    getContentPane().add(scrollPane);

    table = new JTable(rows(), columns()); 
    scrollPane.setViewportView(table);

    final JButton printButton_1_1 = new JButton();//to print the table contents
    printButton_1_1.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent arg0) {
            try {
                table.print();//invoking print method on the table created
            } catch (PrinterException e) {

                e.printStackTrace();
            }
        }
    });
    
        
    }

}

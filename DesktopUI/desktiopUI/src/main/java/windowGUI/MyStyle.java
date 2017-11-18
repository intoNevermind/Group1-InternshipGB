package windowGUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyStyle {

    private static final String font = "Arial";

    public void setStyle(ArrayList<Component> listComponent){
        for (int i = 0; i < listComponent.size(); i++) {
            if(listComponent.get(i) instanceof JLabel){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 18));
                listComponent.get(i).setForeground(Color.DARK_GRAY);
            }
            if(listComponent.get(i) instanceof JTextField || listComponent.get(i) instanceof JPasswordField){
                listComponent.get(i).setFont(new Font(font, Font.BOLD, 14));
                listComponent.get(i).setForeground(Color.BLACK);
            }
            if(listComponent.get(i)instanceof JButton){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 12));
                listComponent.get(i).setForeground(Color.DARK_GRAY);
            }
            if(listComponent.get(i) instanceof JCheckBox){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 14));
                listComponent.get(i).setForeground(Color.DARK_GRAY);
            }
            if(listComponent.get(i) instanceof JTabbedPane){
                listComponent.get(i).setFont(new Font(font, Font.BOLD, 14));
                listComponent.get(i).setForeground(Color.DARK_GRAY);
            }
            if(listComponent.get(i) instanceof JDateChooser){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 18));
                listComponent.get(i).setForeground(Color.BLUE);
            }
            if(listComponent.get(i) instanceof JTable){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 14));
                listComponent.get(i).setForeground(Color.BLACK);
            }
            if(listComponent.get(i) instanceof JOptionPane){
                listComponent.get(i).setFont(new Font(font, Font.PLAIN, 16));
                listComponent.get(i).setForeground(Color.DARK_GRAY);
            }
        }
    }
}

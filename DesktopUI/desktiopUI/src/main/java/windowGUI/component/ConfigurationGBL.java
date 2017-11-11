package windowGUI.component;

import windowGUI.MyCalendar;

import javax.swing.*;
import java.awt.*;

public class ConfigurationGBL {
    private int numberStr = 0;

    public GridBagConstraints configGBC(Component component, boolean moveToNewLine){
        GridBagConstraints gbc =  new GridBagConstraints();
        if(component instanceof JLabel){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 1;
            gbc.anchor = GridBagConstraints.EAST;
            return gbc;
        }
        if(component instanceof JComboBox || component instanceof MyCalendar){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        if(component instanceof JButton){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = GridBagConstraints.REMAINDER ;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        return gbc;
    }

}

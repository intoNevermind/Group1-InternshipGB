package windowGUI.component;

import java.awt.*;

public class ConfigurationGBL {
    private int numberStr = 0;

    public GridBagConstraints configGBCTest(int anchor, int gridWidth, boolean moveToNewLine) {
        GridBagConstraints gbc = new GridBagConstraints();

        if (moveToNewLine) numberStr++;
        gbc.gridy = numberStr;
        gbc.gridwidth = gridWidth;
        gbc.anchor = anchor;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    public GridBagConstraints configGBCTest( int gridWidth, boolean moveToNewLine) {
        GridBagConstraints gbc = new GridBagConstraints();
        if (moveToNewLine) numberStr++;
        gbc.gridy = numberStr;
        gbc.gridwidth = gridWidth;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        return gbc;
    }
}

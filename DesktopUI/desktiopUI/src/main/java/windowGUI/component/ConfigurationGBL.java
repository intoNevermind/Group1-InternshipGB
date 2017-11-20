package windowGUI.component;

import java.awt.*;
/*
 * Класс-конфигурация, отвечающий за расположение елементов графического интерфейса
 * */
public class ConfigurationGBL {
    private int numberStr = 0;

    /*
     * перегруженный метод, используется для настроек расположения компонента
     * */
    public GridBagConstraints configGBC(int anchor, int gridWidth, boolean moveToNewLine) {
        GridBagConstraints gbc = new GridBagConstraints();

        if (moveToNewLine) numberStr++;
        gbc.gridy = numberStr;
        gbc.gridwidth = gridWidth;
        gbc.anchor = anchor;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    public GridBagConstraints configGBC(int gridWidth, boolean moveToNewLine) {
        GridBagConstraints gbc = new GridBagConstraints();

        if (moveToNewLine) numberStr++;
        gbc.gridy = numberStr;
        gbc.gridwidth = gridWidth;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        return gbc;
    }
}

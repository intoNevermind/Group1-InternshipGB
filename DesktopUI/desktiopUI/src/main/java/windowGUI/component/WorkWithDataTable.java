package windowGUI.component;

import javax.swing.*;
/*
 * Класс для работы с таблицей данных
 * */
public class WorkWithDataTable {

    /*
     * методд удаляющий таблицу данных
     * */
   public void removeDataTable(JScrollPane dataScrollPane, JPanel panel){
        for (int i = 0; i <  panel.getComponents().length; i++) {
            if( panel.getComponents()[i].equals(dataScrollPane)){
                panel.remove(dataScrollPane);
            }
        }
        panel.updateUI();
    }
}

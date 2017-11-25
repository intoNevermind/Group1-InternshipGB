package windowGUI.component;


import javax.swing.*;

public class WorkWithDataTable {

   public void removeDataTable(JScrollPane dataScrollPane, JPanel panel){
        for (int i = 0; i <  panel.getComponents().length; i++) {
            if( panel.getComponents()[i].equals(dataScrollPane)){
                panel.remove(dataScrollPane);
            }
        }
        panel.updateUI();
    }
}

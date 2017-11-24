package windowGUI.component;

import javax.swing.*;
import java.util.ArrayList;

public class WorkWithItemsJComboBox {
    public void refreshList(ArrayList<String> listAdd, ArrayList<String> listDelete,ArrayList<String> listBefore, ArrayList<String> listAfter, JComboBox<Object> box) {
        addName(listAdd, box);
        delName(listDelete, box);
        editName(listBefore, listAfter, box);
    }

    private void addName(ArrayList<String> listAdd, JComboBox<Object> box){
        if(listAdd.size() > 0){
            for (int i = 0; i < listAdd.size(); i++) {
               box.addItem(listAdd.get(i));
            }
            listAdd.clear();
        }
    }

    private void delName(ArrayList<String> listDelete, JComboBox<Object> box){
        if(listDelete.size() > 0){
            for (int i = 0; i < listDelete.size(); i++) {
                box.removeItem(listDelete.get(i));
            }
            listDelete.clear();
        }
    }

    private void editName(ArrayList<String> beforeList, ArrayList<String> afterList, JComboBox<Object> box){
        if(beforeList.size() > 0 && afterList.size() > 0 && beforeList.size() == afterList.size()){
            for (int i = 0; i < beforeList.size(); i++) {
                box.removeItem(beforeList.get(i));
                box.addItem(afterList.get(i));
            }
            beforeList.clear();
            afterList.clear();
        }
    }
}

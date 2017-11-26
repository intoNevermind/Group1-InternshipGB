package windowGUI.component;

import javax.swing.*;
import java.util.ArrayList;
/*
 * Класс, отвечающий за изменения элементов выпадающего списка
 * */
public class ChangeItemsJComboBox {

    /*
     * метод, обновляющий выпадающий список
     * */
    public void refreshList(ArrayList<String> listAdd, ArrayList<String> listDelete, ArrayList<String> listBefore, ArrayList<String> listAfter, JComboBox<Object> box) {
        addItem(listAdd, box);
        delItem(listDelete, box);
        editItem(listBefore, listAfter, box);
    }

    /*
     * метод, добавляющий элемент в выпадающий список
     * */
    private void addItem(ArrayList<String> listAdd, JComboBox<Object> box){
        if(listAdd.size() > 0){
            for (int i = 0; i < listAdd.size(); i++) box.addItem(listAdd.get(i));
            listAdd.clear();
        }
    }

    /*
     * метод, удаляющий элемент из выпадающего списока
     * */
    private void delItem(ArrayList<String> listDelete, JComboBox<Object> box){
        if(listDelete.size() > 0){
            for (int i = 0; i < listDelete.size(); i++) box.removeItem(listDelete.get(i));
            listDelete.clear();
        }
    }

    /*
     * метод, изменяющий элемент в выпадающем списоке
     * */
    private void editItem(ArrayList<String> beforeList, ArrayList<String> afterList, JComboBox<Object> box){
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

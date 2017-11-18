package windowGUI.component.workDirectory;

import windowGUI.MyStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/*
 * Класс отвечающий за панель вкладок справочников
 * */
public class ListOfTabsDirectory {
    private static final MyStyle MY_STYLE = new MyStyle();

    private static final String NAME_LIST_OF_TABS = "Справочники";

    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();
    private static final PersonsDirectory PERSONS_DIRECTORY = new PersonsDirectory();
    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();

    public ListOfTabsDirectory() {
        MY_STYLE.setStyle(getListComponents());

        addTabs();
    }

    /*
     * метод, отвечающий за передачу всех элементов панели вкладок для установки графического вида
     * */
    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);
        return listComponent;
    }

    /*
     * метод, заполняющий панель вкладок
     * */
    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);
        LIST_OF_TABS.addTab(SITES_DIRECTORY.getNameTab(), SITES_DIRECTORY.getPanelDirectory());
        LIST_OF_TABS.addTab(PERSONS_DIRECTORY.getNameTab(), PERSONS_DIRECTORY.getPanelDirectory());
        LIST_OF_TABS.addTab(KEY_WORDS_DIRECTORY.getNameTab(), KEY_WORDS_DIRECTORY.getPanelDirectory());
    }

    /*
     * </getters>
     * */
    public JTabbedPane getListOfTabs() {
        return LIST_OF_TABS;
    }
    public String getNameListOfTabs() {
        return NAME_LIST_OF_TABS;
    }
    /*
     * </getters>
     * */
}

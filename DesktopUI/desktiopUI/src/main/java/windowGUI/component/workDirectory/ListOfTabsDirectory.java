package windowGUI.component.workDirectory;

import windowGUI.MyStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListOfTabsDirectory {
    private static final String NAME_LIST_OF_TABS = "Справочники";

    private static final MyStyle MY_STYLE = new MyStyle();

    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    private static final SitesDirectory sitesDirectory = new SitesDirectory();
    private static final PersonsDirectory personDirectory = new PersonsDirectory();
    private static final KeyWordsDirectory keyWordsDirectory = new KeyWordsDirectory();

    public ListOfTabsDirectory() {
        MY_STYLE.setStyle(getListComponents());

        addTabs();
    }

    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);
        LIST_OF_TABS.addTab(sitesDirectory.getNameTab(), sitesDirectory.getPanelDirectory());
        LIST_OF_TABS.addTab(personDirectory.getNameTab(),personDirectory.getPanelDirectory());
        LIST_OF_TABS.addTab(keyWordsDirectory.getNameTab(), keyWordsDirectory.getPanelDirectory());
    }

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);
        return listComponent;
    }

    public JTabbedPane getListOfTabs() {
        return LIST_OF_TABS;
    }

    public String getNameListOfTabs() {
        return NAME_LIST_OF_TABS;
    }
}

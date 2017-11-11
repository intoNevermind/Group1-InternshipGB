package windowGUI.component.workDirectory;

import javax.swing.*;

public class ListOfTabsDirectory {
    private static final String NAME_LIST_OF_TABS = "Справочники";
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane();

    private static final SitesDirectory sitesDirectory = new SitesDirectory();
    private static final PersonDirectory personDirectory = new PersonDirectory();
    private static final KeyWordsDirectory keyWordsDirectory = new KeyWordsDirectory();

    public ListOfTabsDirectory() {
        addTabs();
    }

    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);
        LIST_OF_TABS.addTab(sitesDirectory.getTabName(), sitesDirectory.getPanelDirectory());
        LIST_OF_TABS.addTab(personDirectory.getTabName(),personDirectory.getPanelDirectory());
        LIST_OF_TABS.addTab(keyWordsDirectory.getTabName(), keyWordsDirectory.getPanelDirectory());
    }

    public static JTabbedPane getListOfTabs() {
        return LIST_OF_TABS;
    }

    public static String getNameListOfTabs() {
        return NAME_LIST_OF_TABS;
    }
}

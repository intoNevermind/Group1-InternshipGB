package windowGUI;

import javax.swing.*;


public class SelectionOfStatistics  {

    private static final int SIZE_WIDTH = 400;
    private static final int SIZE_HEIGHT = 500;
    private static final String WINDOW_TITLE = "Selection of statistics";
    private static final JFrame window = new JFrame();

    public SelectionOfStatistics()  {
            new ConfigurationsWindowGUI().setConfigWindow(window, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);
    }


}

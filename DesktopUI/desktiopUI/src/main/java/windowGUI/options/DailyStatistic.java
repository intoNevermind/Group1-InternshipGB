package windowGUI.options;

import windowGUI.Calendar;
import windowGUI.options.workSQL.*;
import javax.swing.*;
import java.awt.*;

public class DailyStatistic extends Statistics{
    private static final String TAB_NAME = "Ежедневная статистика";

    private static final GridBagLayout GBL = new GridBagLayout();

    private static final JLabel headlineSite = new JLabel(" Сайты: ");
    private static final JLabel headlinePersons = new JLabel(" Личности: ");
    private static final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private static final JLabel headlineFinishPeriod = new JLabel(" по: ");
    private static final JLabel totalNumberPages = new JLabel();

    private static final JButton btnConfirm = new JButton(" Подтвердить");

    private static final ProcessingPersonTable PPT = new ProcessingPersonTable();
    private static final ProcessingSitesTable PST = new ProcessingSitesTable();

    private static final JComboBox<Object> listSite = new JComboBox<>(PST.getColumnName());
    private static final JComboBox<Object> listPersons = new JComboBox<>(PPT.getColumnName());

    private static final Calendar startCalendar = new Calendar();
    private static final Calendar finishCalendar = new Calendar();

    public DailyStatistic() {
        super();
        setTabName(TAB_NAME);
        getOptionsPanel().setLayout(GBL);

        fillOptionsPanel();
        getPanelStat().add(getOptionsPanel(), BorderLayout.NORTH);

        data = new Object[][]{{"21.21.2012", 1}, {"21.21.2010", 2}, {"21.21.2011", 3}};
        columnNames = new String[]{"Дата", "Количество новых страниц"};
        dataTable = new JTable(data,columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);

        totalNumberPages.setText("Всего новых страниц за период: " + countTotalNumberPages());
        getPanelStat().add(totalNumberPages, BorderLayout.SOUTH);
    }

    @Override
    public void fillOptionsPanel() {
        GBL.setConstraints(headlineSite, configGBC(headlineSite,false));
        getOptionsPanel().add(headlineSite);
        GBL.setConstraints(listSite, configGBC(listSite,false));
        getOptionsPanel().add(listSite);

        GBL.setConstraints(headlinePersons, configGBC(headlineSite,true));
        getOptionsPanel().add(headlinePersons);
        GBL.setConstraints(listPersons, configGBC(listSite,false));
        getOptionsPanel().add(listPersons);

        GBL.setConstraints(headlineStartPeriod, configGBC(headlineSite, true));
        getOptionsPanel().add(headlineStartPeriod);
        GBL.setConstraints(startCalendar, configGBC(startCalendar,false));
        getOptionsPanel().add(startCalendar);

        GBL.setConstraints(headlineFinishPeriod, configGBC(headlineSite,true));
        getOptionsPanel().add(headlineFinishPeriod);
        GBL.setConstraints(finishCalendar, configGBC(finishCalendar,false));
        getOptionsPanel().add(finishCalendar);

        GBL.setConstraints(btnConfirm, configGBC(btnConfirm, true));
        getOptionsPanel().add(btnConfirm);
    }

    private long countTotalNumberPages(){
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            count += (int)data[i][1];
        }
        return count;
    }
}

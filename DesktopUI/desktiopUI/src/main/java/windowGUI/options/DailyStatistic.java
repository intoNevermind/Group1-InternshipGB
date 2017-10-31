package windowGUI.options;

import com.toedter.calendar.JDateChooser;
import windowGUI.Calendar;

import javax.swing.*;

import java.awt.*;

public class DailyStatistic extends Statistics{

    private static final GridBagLayout GBL = new GridBagLayout();

    private static final JLabel headlineSite = new JLabel(" Сайты: ");
    private static final JLabel headlinePersons = new JLabel(" Личности: ");
    private static final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private static final JLabel headlineFinishPeriod = new JLabel(" по: ");

    private final JComboBox listSite;
    private final JComboBox listPersons;

    private static final Calendar startCalendar = new Calendar();
    private static final Calendar finishCalendar = new Calendar();
    private static final JDateChooser dateChooser = new JDateChooser();

    private static final JButton btnConfirm = new JButton(" Подтвердить");

    private static JLabel totalNumberPages;

    private static String[] sitesList = new String[10];
    private static String[] personsList = new String[10];


    public DailyStatistic() {
        setTabName("Ежедневная статистика");
        optionsPanel.setLayout(GBL);

        fillList();
        listSite = new JComboBox(sitesList);
        listPersons = new JComboBox(personsList);

        activateCalendar();

        fillOptionsPanel();
        panelStat.add(optionsPanel, BorderLayout.NORTH);

        data = new Object[][]{{"21.21.2012", 1}, {"21.21.2010", 2}, {"21.21.2011", 3}};
        columnNames = new String[]{"Дата", "Количество новых страниц"};
        dataTable = new JTable(data,columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        panelStat.add(dataScrollPane, BorderLayout.CENTER);

        totalNumberPages = new JLabel("Всего новых страниц за период: " + countTotalNumberPages());
        panelStat.add(totalNumberPages, BorderLayout.SOUTH);
    }

    @Override
    public void fillList() {
        for (int i = 0; i < 10; i++) {
            sitesList[i] = "Сайт " + (i + 1);
        }
        for (int i = 0; i < 10; i++) {
            personsList[i] = "Личность " + (i + 1);
        }
    }

    @Override
    public void fillOptionsPanel() {
        GBL.setConstraints(headlineSite, configGBC(headlineSite,false));
        optionsPanel.add(headlineSite);
        GBL.setConstraints(listSite, configGBC(listSite,false));
        optionsPanel.add(listSite);

        GBL.setConstraints(headlinePersons, configGBC(headlineSite,true));
        optionsPanel.add(headlinePersons);
        GBL.setConstraints(listPersons, configGBC(listSite,false));
        optionsPanel.add(listPersons);

        GBL.setConstraints(headlineStartPeriod, configGBC(headlineSite, true));
        optionsPanel.add(headlineStartPeriod);
        GBL.setConstraints(startCalendar, configGBC(startCalendar,false));
        optionsPanel.add(startCalendar);

        GBL.setConstraints(headlineFinishPeriod, configGBC(headlineSite,true));
        optionsPanel.add(headlineFinishPeriod);
        GBL.setConstraints(finishCalendar, configGBC(finishCalendar,false));
        optionsPanel.add(finishCalendar);

        GBL.setConstraints(btnConfirm, configGBC(btnConfirm, true));
        optionsPanel.add(btnConfirm);
    }

    private void activateCalendar(){
        dateChooser.setBounds(20, 20, 200, 20);
        EventQueue.invokeLater(() ->{
            try {
                dateChooser.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private long countTotalNumberPages(){
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            count += (int)data[i][1];
        }
        return count;
    }
}

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
        GridBagConstraints headersStr1 =  new GridBagConstraints();
        headersStr1.gridx = GridBagConstraints.RELATIVE;
        headersStr1.gridy = 0;
        headersStr1.gridwidth  = 1;
        headersStr1.anchor = GridBagConstraints.EAST;
        headersStr1.weightx = 0.0;

        GridBagConstraints comboBoxStr1 =  new GridBagConstraints();
        comboBoxStr1.gridx = GridBagConstraints.RELATIVE;
        comboBoxStr1.gridy = 0;
        comboBoxStr1.gridwidth  = 2;
        comboBoxStr1.fill = GridBagConstraints.BOTH;
        comboBoxStr1.weightx = 1.0;

        GridBagConstraints headersStr2 =  new GridBagConstraints();
        headersStr2.gridx = GridBagConstraints.RELATIVE;
        headersStr2.gridy = 1;
        headersStr2.gridwidth  = 1;
        headersStr1.anchor = GridBagConstraints.EAST;
        headersStr2.weightx = 0.0;

        GridBagConstraints calendarStr2 =  new GridBagConstraints();
        calendarStr2.gridx = GridBagConstraints.RELATIVE;
        calendarStr2.gridy = 1;
        calendarStr2.gridwidth  = 2;
        calendarStr2.fill = GridBagConstraints.BOTH;
        calendarStr2.weightx = 1.0;

        GridBagConstraints btnStr3 =  new GridBagConstraints();
        btnStr3.gridx = GridBagConstraints.RELATIVE;
        btnStr3.gridy = 2;
        btnStr3.gridwidth  = GridBagConstraints.REMAINDER ;
        btnStr3.fill = GridBagConstraints.BOTH;
        btnStr3.weightx = 1.0;

        GBL.setConstraints(headlineSite, headersStr1);
        optionsPanel.add(headlineSite);
        GBL.setConstraints(listSite, comboBoxStr1);
        optionsPanel.add(listSite);
        GBL.setConstraints(headlinePersons, headersStr1);
        optionsPanel.add(headlinePersons);
        GBL.setConstraints(listPersons, comboBoxStr1);
        optionsPanel.add(listPersons);

        GBL.setConstraints(headlineStartPeriod, headersStr2);
        optionsPanel.add(headlineStartPeriod);
        GBL.setConstraints(startCalendar, calendarStr2);
        optionsPanel.add(startCalendar);
        GBL.setConstraints(headlineFinishPeriod, headersStr2);
        optionsPanel.add(headlineFinishPeriod);
        GBL.setConstraints(finishCalendar, calendarStr2);
        optionsPanel.add(finishCalendar);

        GBL.setConstraints(btnConfirm, btnStr3);
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

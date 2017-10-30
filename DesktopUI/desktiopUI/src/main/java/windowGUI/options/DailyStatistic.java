package windowGUI.options;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyStatistic extends Statistics{

    private static final JLabel headlineComboBoxSite = new JLabel(" Сайты: ");
    private static final JLabel headlineComboBoxPersons = new JLabel(" Личности: ");
    private static final JLabel headlineComboBoxPeriod = new JLabel(" Период c: ");
    private static final JLabel headlineComboBoxPeriod1 = new JLabel(" по: ");
    private static final JButton btnConfirm = new JButton(" Подтвердить");
    private final JComboBox listSite;
    private final JComboBox listPersons;
    private static JLabel totalNumberPages;

    private static final DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormatter dateFormatter = new DateFormatter(date);
    private static final JFormattedTextField startDate = new JFormattedTextField(dateFormatter);
    private static final JFormattedTextField finalDate = new JFormattedTextField(dateFormatter);

    private static String[] sitesList = new String[10];
    private static String[] personsList = new String[10];

    public DailyStatistic() {
        setTabName("Ежедневная статистика");

        fillList();
        listSite = new JComboBox(sitesList);
        listPersons = new JComboBox(personsList);

        configDate();

        data = new Object[][]{{"21.21.2012", 1}, {"21.21.2010", 2}, {"21.21.2011", 3}};
        columnNames = new String[]{"Дата", "Количество новых страниц"};
        dataTable = new JTable(data,columnNames);
        dataScrollPane = new JScrollPane(dataTable);

        totalNumberPages = new JLabel("Всего новых страниц за период: " + countTotalNumberPages());
        fillOptionsPanel();
        optionsPanel.setLayout(new GridBagLayout());

        panelStat.add(dataScrollPane, BorderLayout.CENTER);
        panelStat.add(optionsPanel, BorderLayout.NORTH);
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
        optionsPanel.add(headlineComboBoxSite);
        optionsPanel.add(listSite);
        optionsPanel.add(headlineComboBoxPersons);
        optionsPanel.add(listPersons);



        optionsPanel.add(headlineComboBoxPeriod);
        optionsPanel.add(startDate);
        optionsPanel.add(headlineComboBoxPeriod1);
        optionsPanel.add(finalDate);
        optionsPanel.add(btnConfirm);
    }

    private void configDate(){
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(false);
        finalDate.setColumns(7);
        finalDate.setValue(new Date());
        startDate.setColumns(7);
        startDate.setValue(new Date());
    }

    private long countTotalNumberPages(){
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            count += (int)data[i][1];
        }
        return count;
    }
}

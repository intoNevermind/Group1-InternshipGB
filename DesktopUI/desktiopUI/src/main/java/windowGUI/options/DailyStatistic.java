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


    DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    DateFormatter dateFormatter = new DateFormatter(date);

    JFormattedTextField startDate = new JFormattedTextField(dateFormatter);
    JFormattedTextField finalDate = new JFormattedTextField(dateFormatter);

    private static String[] sitesList = new String[10];
    private static String[] personsList = new String[10];

    public DailyStatistic() {
        setTabName("Ежедневная статистика");

        fillList();
        listSite = new JComboBox(sitesList);
        listPersons = new JComboBox(personsList);

        fillOptionsPanel();
        optionsPanel.setLayout(new GridBagLayout());

        panelStat.add(optionsPanel, BorderLayout.NORTH);
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

        configDate();

        optionsPanel.add(headlineComboBoxPeriod);
        optionsPanel.add(startDate);
        optionsPanel.add(headlineComboBoxPeriod1);
        optionsPanel.add(finalDate);
        optionsPanel.add(btnConfirm);
    }

    private void configDate(){
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        finalDate.setColumns(7);
        finalDate.setValue(new Date());
        startDate.setColumns(7);
        startDate.setValue(new Date());
    }
}

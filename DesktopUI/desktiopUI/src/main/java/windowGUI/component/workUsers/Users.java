package windowGUI.component.workUsers;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.processingData.ProcessingUsersTable;
import windowGUI.editingDirectoryWindow.add.AddKeyWordWindow;
import windowGUI.editingDirectoryWindow.delete.DelKeyWordWindow;
import windowGUI.editingDirectoryWindow.edit.EditKeyWordWindow;
import windowGUI.registrationOrEntryWindow.editingUsersWindow.AddUserWindow;
import windowGUI.registrationOrEntryWindow.editingUsersWindow.DelUserWindow;
import windowGUI.registrationOrEntryWindow.editingUsersWindow.EditUserWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Users {
    private static final MyStyle MY_STYLE = new MyStyle();

    private static final String TAB_NAME = "Пользователи";

    private static final int PANEL_USERS_SIZE_WIDTH = ApplicationWindow.getSizeWidth();
    private static final int PANEL_USERS_SIZE_HEIGHT = ApplicationWindow.getSizeHeight();

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private static final JPanel panelUser = new JPanel(new BorderLayout());
    private static final JPanel btnPanel = new JPanel(new FlowLayout());

    private static final ProcessingUsersTable P_USERS_T = new ProcessingUsersTable();


    private static final JButton btnAdd = new JButton("Добавить");
    private static final JButton btnDelete = new JButton("Удалить");
    private static final JButton btnEdit = new JButton("Редактировать");
    private static final String[] columnNames = new String[]{"Логин"};

    private static final JTable dataTable = new JTable(P_USERS_T.getArrayFillTable(columnNames.length), columnNames);;
    private static final JScrollPane dataScrollPane = new JScrollPane(dataTable);

    private static String userLogin ;

    public Users() {
        MY_STYLE.setStyle(getListComponents());

        panelUser.setPreferredSize(new Dimension(PANEL_USERS_SIZE_WIDTH, PANEL_USERS_SIZE_HEIGHT));

        fillBtnPanel();

        addActionListenerForBtnAdd();
        addActionListenerForBtnDel();
        addActionListenerForBtnEdit();
        addActionListenerForDataTable();

        panelUser.add(dataScrollPane, BorderLayout.CENTER);
        panelUser.add(btnPanel,BorderLayout.SOUTH);
    }

    private void visibleWindowAdd(ActionEvent actionEvent){
        if(userLogin == null){
            JOptionPane.showMessageDialog(null,
                    "Для добавления пользователя необходимо выбрать пользователя из списка ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new AddUserWindow();
        }
    }
    private void visibleWindowDel(ActionEvent actionEvent){
        if(userLogin == null ){
            JOptionPane.showMessageDialog(null,
                    "Для удаления пользователя необходимо выбрать пользователя из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new DelUserWindow();
        }
    }
    private void visibleWindowEdit(ActionEvent actionEvent){
        if(userLogin == null){
            JOptionPane.showMessageDialog(null,
                    "Для редактирования пользователя необходимо выбрать пользователя из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new EditUserWindow();
        }
    }

    private void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        userLogin = (String) value;
    }

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(btnAdd);
        listComponent.add(btnDelete);
        listComponent.add(btnEdit);

        listComponent.add(dataTable);
        return listComponent;
    }

    private void fillBtnPanel(){
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
    }

    private void addActionListenerForBtnAdd(){
        btnAdd.addActionListener(this::visibleWindowAdd);
    }

    private void addActionListenerForBtnDel(){
        btnDelete.addActionListener(this::visibleWindowDel);
    }

    private void addActionListenerForBtnEdit(){
        btnEdit.addActionListener(this::visibleWindowEdit);
    }
    private void addActionListenerForDataTable(){
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
    }

    public String getTabName() {
        return TAB_NAME;
    }

    public JPanel getPanelUser() {
        return panelUser;
    }
}

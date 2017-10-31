package windowGUI.options.workSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLAnfrage {

    private String TABLE_NAME;
    private static final String NAME_COLUMN = "Name";

    public SQLAnfrage(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public Object[] fillList(){
        try {
            Statement psSelectPersons = ConnectionRepository.getConnectionToDB().createStatement();
            ResultSet rs = psSelectPersons.executeQuery("SELECT " + NAME_COLUMN + " FROM persons_statistics." + TABLE_NAME);

            ArrayList<String> listPersons = new ArrayList<>();
            int x = rs.getMetaData().getColumnCount();
            int j = 0;

            while(rs.next()){
                for(int i = 1; i <= x; i++){
                    listPersons.add(rs.getString(i));
                    j++;
                }
            }

            Object[] arr = new Object[listPersons.size()];
            for (int i = 0; i < listPersons.size(); i++) {
                arr[i] = listPersons.get(i);
            }

            return arr;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

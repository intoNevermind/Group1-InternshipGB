package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.restApi.PojoUsers;
import windowGUI.component.workDB.restApi.RestApiForUsersTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UsersTable extends ConnectServer{
    private RestApiForUsersTable restApiForUsersTable = getRetrofit().create(RestApiForUsersTable.class);
    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listLogin = new ArrayList<>();
    private static final ArrayList<Boolean> listAdmin = new ArrayList<>();
    private static final ArrayList<Boolean> listActive = new ArrayList<>();
    private static final LinkedHashMap<String,Boolean> listLoginAndAdmin = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Boolean> listLoginAndActive = new LinkedHashMap<>();
    private static final LinkedHashMap<Integer,String> listIDAndLogin = new LinkedHashMap<>();

    private static UsersTable instance;

    public static UsersTable getInstance() {
        if(instance == null){
            instance = new UsersTable();
        }
        return instance;
    }

    private UsersTable() {
        infoAllUsers();
    }

    private void infoAllUsers(){
        try {
            Response<ArrayList<PojoUsers>> response = restApiForUsersTable.getListAllUsers().execute();
            ArrayList<PojoUsers> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                listID.add(list.get(i).getId());
                listLogin.add(list.get(i).getLogin());
                listAdmin.add(list.get(i).getAdmin());
                listActive.add(list.get(i).getActive());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String userLogin , boolean userAdmin, String userPassword, boolean userActive){
        try {
            Response<ResponseBody> response = restApiForUsersTable.addUser(userLogin, userAdmin,userPassword, userActive).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delUser(int userID){
        try {
            Response<ResponseBody> response = restApiForUsersTable.delUser(userID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyUser(int userID, String userLogin , boolean userAdmin, String userPassword, boolean userActive){
        try {
            Response<ResponseBody> response = restApiForUsersTable.modifyUser(userID, userLogin, userAdmin, userPassword, userActive).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<Integer, String> getListIDAndLogin() {
        for (int i = 0; i < getListID().size(); i++) {
            listIDAndLogin.put(getListID().get(i), getListLogin().get(i));
        }
        return listIDAndLogin;
    }

    public LinkedHashMap<String, Boolean> getListLoginAndAdmin() {
        for (int i = 0; i < getListLogin().size(); i++) {
            listLoginAndAdmin.put(getListLogin().get(i), getListAdmin().get(i));
        }
        return listLoginAndAdmin;
    }

    public LinkedHashMap<String, Boolean> getListLoginAndActive() {
        for (int i = 0; i < getListLogin().size(); i++) {
            listLoginAndActive.put(getListLogin().get(i), getListActive().get(i));
        }
        return listLoginAndActive;
    }

    private ArrayList<Integer> getListID() {
        return listID;
    }

    private ArrayList<String> getListLogin(){
        return listLogin;
    }


    private ArrayList<Boolean> getListAdmin(){
        return listAdmin;
    }

    private ArrayList<Boolean> getListActive(){
        return listActive;
    }
}

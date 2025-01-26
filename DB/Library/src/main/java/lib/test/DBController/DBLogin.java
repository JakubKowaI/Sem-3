package lib.test.DBController;

public class DBLogin {
    private int ID;
    private DBConnection dbConnection;

//    public boolean login(String login, String password) {
//        this.ID=new DBConnection(-1).test();
//        DBConnection dbConnection = new DBConnection(-1);
//
//        dbConnection.close();
//        return false;
//    }

    public DBLogin(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

//    public int ID(String login, String password) {
//        return dbConnection.getID();
//    }
}

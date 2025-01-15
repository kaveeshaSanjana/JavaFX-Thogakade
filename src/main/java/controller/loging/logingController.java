package controller.loging;

import db_Connection.DB_Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logingController {
    public static boolean loging (User user) throws SQLException {
        String key = "1234";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);


        PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("SELECT * FROM users WHERE usesrname = ?");
        stmt.setObject(1,user.getName());
        ResultSet rst = stmt.executeQuery();
        if(rst.next()){
            return rst.getString("usesrname").equalsIgnoreCase(user.getName()) && basicTextEncryptor.decrypt(rst.getString("password")).equalsIgnoreCase(user.getPassword());
        }
        return false;
    }


}

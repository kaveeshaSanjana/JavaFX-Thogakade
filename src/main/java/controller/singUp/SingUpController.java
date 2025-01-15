package controller.singUp;

import db_Connection.DB_Connection;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingUpController {
    public static boolean addUser(User user) throws SQLException {
        String key = "1234";
        BasicTextEncryptor basicTextEncrypter = new BasicTextEncryptor();
        basicTextEncrypter.setPassword(key);


        PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("INSERT INTO users(usesrname,password,email) VALUES(?,?,?)");
        stmt.setObject(1,user.getName());
        stmt.setObject(2,basicTextEncrypter.encrypt(user.getPassword()));
        stmt.setObject(3,user.getEmail());
        return stmt.executeUpdate()>0;
    }

    public static boolean checkPasswordComferemd(User user) {
        return user.getPassword().equalsIgnoreCase(user.getComfermPassword());
    }
}

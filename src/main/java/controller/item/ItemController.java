package controller.item;

import db_Connection.DB_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController implements ItemService{
    private static ItemController itemController;

    private ItemController(){}

    public static ItemController getItemController(){
        return itemController==null?itemController=new ItemController():itemController;
    }

    @Override
    public ObservableList<String> getAll() {
        ObservableList<String> itemArrayList = FXCollections.observableArrayList();
        try {
            ResultSet rst = DB_Connection.getDbConnection().getConnection().createStatement().executeQuery("SELECT * FROM Item");
            while (rst.next()){
                itemArrayList.add(rst.getString(1));
            }
            return itemArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(Item Iitem) {
        return false;
    }

    @Override
    public boolean deleteItem(String itemId) {
        return false;
    }

    @Override
    public boolean saveItem(Item Iitem) {
        return false;
    }

    @Override
    public Item searchItem(String itemId) throws SQLException {
        PreparedStatement pstm = DB_Connection.getDbConnection().getConnection().prepareStatement("SELECT * FROM item WHERE code = ?");
        pstm.setObject(1,itemId);
        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return new Item(rst.getString(1),
                            rst.getString(2),
                                    rst.getDouble(3),
                                    rst.getInt(4));
        }
        return null;
    }
}

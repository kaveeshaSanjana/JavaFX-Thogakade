package controller.item;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    ObservableList<String> getAll();
    boolean updateItem(Item Iitem);

    boolean deleteItem(String itemId);

    boolean saveItem(Item Iitem);

    Item searchItem(String itemId) throws SQLException;
}

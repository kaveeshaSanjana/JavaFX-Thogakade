package controller.order;

public class OrderController {
private static OrderController orderController;

private OrderController(){}

public static OrderController getOrderController(){
    return orderController==null?orderController = new OrderController():orderController;
}



}
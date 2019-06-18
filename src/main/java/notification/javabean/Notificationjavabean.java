package notification.javabean;

public class Notificationjavabean {
    /**
     * action : 接受or拒絕or完成
     * CustomerID : account 訂單裡面存的
     */

    private String action;
    private String CustomerID;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
}

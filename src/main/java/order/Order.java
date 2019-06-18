package order.javabean;

import java.util.List;

public class Order {

    /**
     * CustomerID : 帳號
     * Status : 已結帳
     * Type : 內用
     * Number : 123
     * date : 2019-04-08T12:47:27.527Z
     * Note : null
     * TotalPrice : 110
     * MyMenu : [{"Name":"火腿蛋餅","Price":"30","Amount":"2"},{"Name":"咔啦雞腿堡","Price":"55","Amount":"2"}]
     */

    private String CustomerID;
    private String Status;
    private String Type;
    private String Number;
    private String date;
    private Object Note;
    private String TotalPrice;
    private List<MyMenuBean> MyMenu;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getNote() {
        return Note;
    }

    public void setNote(Object Note) {
        this.Note = Note;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public List<MyMenuBean> getMyMenu() {
        return MyMenu;
    }

    public void setMyMenu(List<MyMenuBean> MyMenu) {
        this.MyMenu = MyMenu;
    }

    public static class MyMenuBean {
        /**
         * Name : 火腿蛋餅
         * Price : 30
         * Amount : 2
         */

        private String Name;
        private String Price;
        private String Amount;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }
    }
}

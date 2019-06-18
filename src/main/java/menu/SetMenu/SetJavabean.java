package menu.SetMenu;

public class SetJavabean {
    /**
     * Name : 沙拉三明治套餐
     * Price : 60
     * Description : 用白煮蛋和馬鈴薯搭配著自製醬汁混合而成的沙拉醬，再配上剛烤好的三明治，以及熱的美式咖啡，迎接美好的一天
     * url : https://ai-rest.cse.ntou.edu.tw/牛肉漢堡薯條套餐.jpg
     */

    private String Name;
    private String Price;
    private String Description;
    private String url;

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

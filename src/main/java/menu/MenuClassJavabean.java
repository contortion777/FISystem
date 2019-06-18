package menu;

public class MenuClassJavabean {
    /**
     * ClassName : 漢堡
     * url : https://ai-rest.cse.ntou.edu.tw/漢堡.jpg
     */

    private String ClassName;
    private String url;
    
    public MenuClassJavabean(String ClassName, String url) {
    	this.ClassName = ClassName;
    	this.url = url;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }
}

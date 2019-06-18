package order.javabean;

public class ResponseStatus {

    /**
     * Status : 200
     * date : 2019-04-01
     */

    private int Status;
    private String date;
    private String error;
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String geterror() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

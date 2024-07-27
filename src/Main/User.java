package Main;

public class User {
    private String userId;
    private String userName;
    private String email;

    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public void displayUserInfo(){
        System.out.println("ID          : " + getUserId());
        System.out.println("Username    : " + getUserName());
        System.out.println("Email       : " + getEmail());
    }

    public void placeOrder(Order order){}

    public void updateEmail(String newEmail){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

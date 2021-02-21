package Model;

import java.time.LocalDateTime;

public class Users {
    private int userId;
    private String userName;
    private String password;
    private LocalDateTime userCreateDate;
    private String userCreatedBy;
    private LocalDateTime userUpdatedDate;
    private String userUpdatedBy;

    /**
     * This method is the constructor for the user object.
     * @param userName The user's username.
     * @param password The user's password.
     */
    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(LocalDateTime userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public String getUserCreatedBy() {
        return userCreatedBy;
    }

    public void setUserCreatedBy(String userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
    }

    public LocalDateTime getUserUpdatedDate() {
        return userUpdatedDate;
    }

    public void setUserUpdatedDate(LocalDateTime userUpdatedDate) {
        this.userUpdatedDate = userUpdatedDate;
    }

    public String getUserUpdatedBy() {
        return userUpdatedBy;
    }

    public void setUserUpdatedBy(String userUpdatedBy) {
        this.userUpdatedBy = userUpdatedBy;
    }

    @Override
    public String toString() {
        return String.valueOf(userId);
    }
}

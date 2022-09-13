package ma.fukuro192.yabai.request;

public class AuthRequest {
    
    private String username;

    private String password;

    public AuthRequest() {
        super();
    }

    public AuthRequest(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

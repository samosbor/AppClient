package net;

/**
 * Created by samosbor on 5/18/18.
 */

public class LoginResult {
    /**
     * The auth token to respond with
     */
    String authToken;
    /**
     * The userName passed in with the request
     */
    String userName;
    /**
     * The person ID of the user's generated person object
     */
    String personId;
    /**
     * The message to be output in case of error
     */
    String message;

    /**
     * The constructor for the register response object
     *
     * @param authToken
     * @param userName
     * @param personId
     */
    public LoginResult(String authToken, String userName, String personId) {
        this.authToken = authToken;
        this.userName = userName;
        this.personId = personId;
    }

    public LoginResult(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personId;
    }

    public void setPersonID(String personId) {
        this.personId = personId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

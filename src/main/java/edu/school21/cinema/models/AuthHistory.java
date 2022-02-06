package edu.school21.cinema.models;

public class AuthHistory {
    private Long user_id;
    private String type;

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String time;
    private String address;

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }
}

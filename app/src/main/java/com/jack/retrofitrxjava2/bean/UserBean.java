package com.jack.retrofitrxjava2.bean;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class UserBean {
    private String token;
    private User  user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    class User{

        String username;
        int id;
        int status;
        int type;
        String usernum;
        String mobile;



        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsernum() {
            return usernum;
        }

        public void setUsernum(String usernum) {
            this.usernum = usernum;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}

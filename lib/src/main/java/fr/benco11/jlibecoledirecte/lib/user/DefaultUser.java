package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.user.User;

public class DefaultUser implements User {
    private final long idLogin;
    private String email;
    private String password;
    private String username;
    private String phone;
    private String firstName;
    private String lastName;
    private String sex;
    private String schoolName;

    public DefaultUser(String email, String password, String username, String phone,
            String firstName,
            String lastName, String sex, String schoolName, long idLogin) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.schoolName = schoolName;
        this.idLogin = idLogin;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String sex() {
        return sex;
    }

    @Override
    public String schoolName() {
        return schoolName;
    }

    @Override
    public User changePassword(String password) {
        this.password = password;
        return this;
    }
}

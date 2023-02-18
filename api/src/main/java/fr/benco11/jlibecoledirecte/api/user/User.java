package fr.benco11.jlibecoledirecte.api.user;

public interface User {
    String email();

    String username();

    String password();

    String phone();

    String firstName();

    String lastName();

    String sex();

    String schoolName();

    User changePassword(String password);
}

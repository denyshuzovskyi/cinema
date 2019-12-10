package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Long id;
    private String surname;
    private String name;
    private String birthday;
    private String email;
    private String password;
    private UserRole role;

    public User() {}

    public User(String surname, String name, String birthday, String email, String password, UserRole role) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String surname, String name, String birthday, String email, String password, UserRole role) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public enum UserRole {
        user, admin
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, birthday, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
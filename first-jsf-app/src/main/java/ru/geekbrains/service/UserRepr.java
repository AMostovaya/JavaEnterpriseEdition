package ru.geekbrains.service;

import ru.geekbrains.persist.User;

public class UserRepr {

    private Long id;
    private String name;
    private String password;
    private Long roleId;
    private String roleName;

    public UserRepr() {
    }

    public UserRepr(Long id, String name, String password, Long roleId, String roleName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        if (user.getRole() != null) {
            this.roleId = user.getRole().getId();
            this.roleName = user.getRole().getName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}

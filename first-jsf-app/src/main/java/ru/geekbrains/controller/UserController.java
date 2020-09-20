package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Role;
import ru.geekbrains.persist.RoleRepository;
import ru.geekbrains.service.UserRepr;
import ru.geekbrains.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    UserService userService;

    private UserRepr user;

    @EJB
    private RoleRepository roleRepository;

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public List<UserRepr> getAllUsers()  {
        return userService.findAll();
    }

    public String editUser(UserRepr user) {
        this.user = user;
        return "user.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserRepr user)  {
        userService.delete(user.getId());
    }

    public String createUser() {
        this.user = new UserRepr();
        return "user.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        logger.info("SAVE");
        if (user.getId() != null) {
            userService.update(user);
        } else {
            userService.insert(user);
        }
        return "/admin/users.xhtml?faces-redirect=true";
    }

    public List<Role> getAllRoles() {
        logger.info("GETTING Role" + roleRepository.findAll());
        return roleRepository.findAll();
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "/index.xhtml?faces-redirect=true";
    }
}

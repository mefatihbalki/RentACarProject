package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;

    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        guiInitilaze(400, 400);

        // Login butonuna basıldığında username ve password girilmiş mi kontrol eder, uyarı verir.
        btn_login.addActionListener(e -> {
            JTextField[] checkedList = {this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkedList)) {
                Helper.showMessage("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText().trim(), this.fld_password.getText().trim());
                if (loginUser == null) {
                    Helper.showMessage("notFound");
                } else {
                    AdminView adminView = new AdminView(loginUser);
                    dispose();
                }
            }
        });
    }
}

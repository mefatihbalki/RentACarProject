package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        String theme = "Nimbus";
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (theme.equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static void showMessage(String str) {
        String message;
        String title;
        switch (str) {
            case "fill":
                message = "Please Fill In The The All Boxes";
                title = "Error!";
                break;
            case "done":
                message = "Processing is Succesful";
                title = "Succesfull!";
                break;
            case "error":
                message = "Wrong Operation!!";
                title = "Error!";
                break;
            case "notFound":
                message = "User Not Found!!";
                title = "Not Found!";
                break;
            default:
                message = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Are You Sure For This Event?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Are You Sure?",JOptionPane.YES_NO_OPTION) == 0;
    }

    // isFieldEmpty() : Textfield'ların dolu veya boş olması durumuna göre boolean değer döndürür.
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // isFieldListEmpty() : gerekli tüm fieldları gezer ve ayrı ayrı boolean değer döndürür böylece login ekranındaki uyarı dinamik olur.
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    // Sayfa üzerinde ortalanma işlemini kod tekrarlaması yapmadan gerçekleştirmeyi sağlar.
    public static int getLocationPoint(String axis, Dimension size) {
        switch (axis) {
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return 0;
        }
    }
}

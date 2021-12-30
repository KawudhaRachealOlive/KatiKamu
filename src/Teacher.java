import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Teacher extends JFrame {
    private final String tid;
    private JPanel teacherpanel;
    private JLabel title;
    private JButton RECORDSTUDENTSMARKSButton;
    private JButton REGISTERSTUDENTButton;
    public JButton loginbutton;
    private JButton logoutbutton;
    private JButton VIEWREGISTERSTUDENTSButton;
    private JButton SEARCHFORASTUDENTButton;
    private JButton ENTERTIMETABLEButton;

    public Teacher(String TID) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(teacherpanel);
        this.pack();
        this.tid = TID;
        //onClick of login button
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPageTeachers obj =new LoginPageTeachers("Teachers LogIn Form");
                obj.setVisible(true);
                dispose();
            }
        });
        //onClick of register button
        REGISTERSTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterStudent obj =new RegisterStudent(tid);
                obj.setVisible(true);
                dispose();
            }
        });
        //onClick of record students marks button
        RECORDSTUDENTSMARKSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterMarks obj =new EnterMarks("REGISTERED STUDENTS",tid);
                obj.setVisible(true);
                dispose();
            }
        });
        //onClick of logout button
        logoutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome obj =new welcome("YOU LOGGED OUT");
                obj.setVisible(true);
                dispose();
            }
        });
        //onClick of view registered students button
        VIEWREGISTERSTUDENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRegisteredStudents rstd = null;
                try {
                    rstd = new ViewRegisteredStudents(tid);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                rstd.setVisible(true);
                dispose();
            }
        });
        SEARCHFORASTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchForRegistrationNo obj = null;
                try {
                    obj = new SearchForRegistrationNo(tid);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                obj.setVisible(true);
                dispose();
            }
        });
        ENTERTIMETABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EnterTimetable obj =new EnterTimetable(tid);
                obj.setVisible(true);
                dispose();
            }
        });
    }
}

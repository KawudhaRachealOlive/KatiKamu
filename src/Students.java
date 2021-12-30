import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Students extends JFrame{
    private String Stdclass;
    private String SID;
    private JButton VIEWMARKSButton;
    private JPanel panel1;
    private JButton VIEWTIMETABLEButton;
    private JButton logoutButton;
//constructor taking in a Students ID and class as parameters
    public Students(String sid,String Stdclass) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.SID = sid;
        this.Stdclass = Stdclass;

//onClick of the view marks button

        VIEWMARKSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMarks obj = null;
                try {
                    obj = new ViewMarks(SID,Stdclass);
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
        //onClick of logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPageStudents obj =new LoginPageStudents("LOGGED OUT");
                obj.setVisible(true);
                dispose();
            }
        });
        //onClick of view timetable button
        VIEWTIMETABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //redirected to the display timetable class with the students id and class
                DisplayTimetable obj = null;
                try {
                    obj = new DisplayTimetable(SID,Stdclass);
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
    }
}


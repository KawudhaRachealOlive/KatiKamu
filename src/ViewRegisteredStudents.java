import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewRegisteredStudents extends JFrame{
    private final String tid;
    private JPanel regstdspanel;
    private JTable registeredStudstable;
    private JButton BACKButton;
    private JScrollPane scrollpane;


    public ViewRegisteredStudents(String tid) throws SQLException, InstantiationException, IllegalAccessException {
        super();
        this.tid=tid;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(regstdspanel);
        this.pack();


        List<RegStud> std = new ArrayList<>();

        DB conn = new DB();
        Statement st = conn.getConnection().createStatement();
        String sql = "SELECT * FROM Students";

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            String SID = rs.getString("SID");
            String FirstName = rs.getString("FirstName");
            String LastName = rs.getString("LastName");
            String Sex = rs.getString("Sex");
            String OptionalSubject = rs.getString("OptionalSubject");
            String aclass = rs.getString("Class");
            String registeredBy = rs.getString("RegisteredBy");
            std.add(new RegStud(SID,FirstName,LastName,Sex,OptionalSubject,aclass,registeredBy));
        }
        rs.close();
        ViewRegisteredStudents.StudentsTableModel studentsTableModel = new ViewRegisteredStudents.StudentsTableModel(std);
        registeredStudstable.setModel(studentsTableModel);
        registeredStudstable.setAutoCreateRowSorter(true);
        scrollpane.setViewportView(registeredStudstable);
        scrollpane.setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher obj =new Teacher(tid);
                obj.setVisible(true);
                dispose();
                obj.loginbutton.setEnabled(false);
            }
        });
    }


    public static void main(String [] args){
        JFrame frame = new JFrame("JTable table");
        frame.setVisible(true);
    }

    private static class StudentsTableModel extends AbstractTableModel {
        private String[] columnNames = {"SID","FirstName","LastName","Sex","OptionalSubject","Class","RegisterBy"};
        private final String [] COLUMNS = {"SID","FirstName","LastName","ParentsName","aclass","RegisteredBy"};
        private List<RegStud> rs;

        private StudentsTableModel(List<RegStud> student){
            this.rs = student;
        }

        @Override
        public int getRowCount() {
            return rs.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> rs.get(rowIndex).getSID();
                case 1 -> rs.get(rowIndex).getFirstName();
                case 2 -> rs.get(rowIndex).getLastName();
                case 3 -> rs.get(rowIndex).getSex();
                case 4 -> rs.get(rowIndex).getSubject();
                case 5 -> rs.get(rowIndex).getaClass();
                case 6 -> rs.get(rowIndex).getRegisteredBy();
                default ->"-";
            };
        }
        @Override
        public String getColumnName(int column){

            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex) != null) {
                return  getValueAt(0,columnIndex).getClass();
            }else{
                return  Object.class;
            }
        }

    }
}

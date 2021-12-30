import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewMarks extends JFrame {
    private String Stdclass;
    private JPanel viewMarkspanel;
    private JLabel title;
    private JTable viewMarkstable;
    private JButton BACKButton;
    private JScrollPane scrollpane;
    public String SID1;

    public ViewMarks(String sid,String Stdclass) throws SQLException, InstantiationException, IllegalAccessException {
        this.SID1=sid;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(viewMarkspanel);
        this.pack();
        this.Stdclass=Stdclass;

        System.out.println(SID1);

        List<Marks> marks = new ArrayList<>();
        DB conn = new DB();
        Statement st = conn.getConnection().createStatement();
//        String sql = "SELECT * FROM Marks WHERE SID ='" + SID1 + "'";
        String sql = "SELECT * FROM Marks WHERE SID ='" + SID1 + "'";
//        String sql = "SELECT * FROM `Marks`";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            String SID = rs.getString("SID");
            String Subject = rs.getString("Subject");
            int Marks = rs.getInt("Marks");
            marks.add(new Marks(SID,Subject,Marks));
        }
        rs.close();
        StudentsTableModel studentsTableModel = new StudentsTableModel(marks);
        viewMarkstable.setModel(studentsTableModel);
        viewMarkstable.setAutoCreateRowSorter(true);
        scrollpane.setViewportView(viewMarkstable);
        scrollpane.setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Students obj =new Students(SID1,Stdclass);
                obj.setVisible(true);
                dispose();
            }
        });
    }


    public static void main(String [] args){
        JFrame frame = new JFrame("JTable table");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private static class StudentsTableModel extends AbstractTableModel{
    private final String [] COLUMNS = {"SID","Subject","Marks"};
    private List<Marks> md;

    private StudentsTableModel(List<Marks> marks){
        this.md = marks;
    }

        @Override
        public int getRowCount() {
            return md.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> md.get(rowIndex).getName();
                case 1 -> md.get(rowIndex).getSex();
                case 2 -> md.get(rowIndex).getMarks();
                default ->"-";
            };
        }
        @Override
        public String getColumnName(int column){
            return COLUMNS[column];
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

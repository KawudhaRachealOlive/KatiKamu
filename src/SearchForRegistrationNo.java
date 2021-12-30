import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SearchForRegistrationNo extends JFrame{
    private JPanel Searchpanel;
    private JTextField searchtextField;
    private JButton SEARCHSTUDENTButton;
    private JList list1;
    private JButton BACKButton;
    protected JList<SearchRegistrationNo> listStudents = new JList<>();
    protected java.util.List<SearchRegistrationNo> students = new ArrayList<>();


    public SearchForRegistrationNo(String tid) throws SQLException, InstantiationException, IllegalAccessException {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Searchpanel);
        this.pack();
//connecting to the databse
        DB conn = new DB();
        Statement st = conn.getConnection().createStatement();
        //query to select from the students table
        String sql = "SELECT SID FROM Students";
        ResultSet rs = st.executeQuery(sql);
        //creating a list to store the values from the database
        List<Item> allItems = new ArrayList<Item>();
        //iterate through the values
        while (rs.next()) {
            String sid = rs.getString("SID");
            System.out.println(sid);
            allItems.add(new Item(sid));
        }
        rs.close();
        System.out.println(allItems);
        final DefaultListModel<Item> listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        //Initialize the listModel to initially contain all items:
        allItems.forEach(item -> listModel.addElement(item));
//intializing the finalAllItems variable of type Item
        List<Item> finalAllItems = allItems;

        //onButtonClick of the search button
        SEARCHSTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //capture the values in the text fiels
                final String searchText = searchtextField.getText();
                //clear the list model
                listModel.clear();
                if (searchText.isEmpty()) //If the search text field is empty, lets say we want to display all values:
                    finalAllItems.forEach(item -> listModel.addElement(item));
                else
                    //if textfield is not empty
                //we iterate through the values in the finalAllItems list
                    finalAllItems.forEach(item -> {
                        if (item.getName().equalsIgnoreCase(searchText)) {
                            //display the value to the list listmodel
                            listModel.addElement(item);
                        }
                    });
            }
        });
        //validating of the search textField
        searchtextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (searchtextField.getText().equals("")){
                    SEARCHSTUDENTButton.setEnabled(false);
                }
                else {
                    SEARCHSTUDENTButton.setEnabled(true);
                }

            }
        });

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

    public static class Item {
        private String name;

        public Item(final String name) {
            this.name = Objects.requireNonNull(name);
        }

        public String getName() {
            return name;
        }

        //Overriding toString method, to avoid implementing a custom ListCellRenderer...
        @Override
        public String toString() {
            return getName();
        }
    }
}


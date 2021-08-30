import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CustomerDetails;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsontoJava {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Connection con = null;
        ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useSSL = false", "root", "password");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where Location ='Asia'");

        while (rs.next()) {
            CustomerDetails c = new CustomerDetails();
            c.setCourseName(rs.getString(1));
            c.setPurchasedDate(rs.getString(2));
            c.setAmount(rs.getInt(3));
            c.setLocation(rs.getString(4));
            a.add(c);
            }

        for (int i = 0; i < a.size(); i++) {
            ObjectMapper obj = new ObjectMapper();
            obj.writeValue(new File("/Users/lavinaagrawal/TechProjects/JsonJava/src/main/java/customerInfo" + i + ".json"), a.get(i));

        }
        con.close();
    }
}

package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SimpleJson {
    private static FileWriter file;
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Connection con = null;

        ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useSSL = false", "root", "password");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where Location ='Asia'");
        JsonArray js = new JsonArray();

        while (rs.next()) {
            //  3 diff json files,3 diff java objects
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
            Gson g = new Gson();
            String jsonString = g.toJson(a.get(i));
            js.add(jsonString);
        }

            //Json Simple dependency
            JSONObject jo = new JSONObject();
            jo.put("data", js);
            System.out.println("Lavina");
            System.out.println(jo.toJSONString());
            String unescapedString = StringEscapeUtils.unescapeJava(jo.toJSONString());
            System.out.println(unescapedString);
            String string1 = unescapedString.replace("\"{", "{");
            String finalString = string1.replace("}\"", "}");
            System.out.println(finalString);

          try(FileWriter file = new FileWriter("/Users/lavinaagrawal/TechProjects/JsonJava/src/main/java/SingleJson.json")){
            file.write(finalString);}
            con.close();
        }

    }





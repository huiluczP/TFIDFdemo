package data;

import java.sql.*;
import java.util.ArrayList;

/*
 * 数据库连接
 * 获取所有文档信息
 */
public class DocumentDao {
    private static final String USERNAME="root";
    private static final String PASSWORD="123456aaa";
    private static final String URL="jdbc:mysql://localhost:3306/test";
    private static final String CLASSNAME="com.mysql.jdbc.Driver";

    private Connection con=null;
    public Connection getCon(){
        return con;
    }

    public void initCon() {
        con = null;
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWholeDoc(){
        ArrayList<String> docs=new ArrayList<String>();
        String sql="select document from wholedoc";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                docs.add(rs.getString("document"));
            }
            return docs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}

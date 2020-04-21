package Util.InsertData.operate;

import java.sql.DriverManager;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public abstract class JDBC {
	protected Connection conn = null;
	public JDBC(String databases, String username, String password){
		StringBuffer url = new StringBuffer();	
		url.append("jdbc:mysql://localhost:3306/"+databases+"?");
		url.append("user="+username+"&password="+password);		
		url.append("&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC");

        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

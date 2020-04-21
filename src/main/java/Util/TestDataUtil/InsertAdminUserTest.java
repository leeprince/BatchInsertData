package Util.TestDataUtil;

import org.springframework.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertAdminUserTest {

    static Connection conn = null;
 
    public static void initConn() throws ClassNotFoundException, SQLException {
 
        String sql;
        String url = "jdbc:mysql://localhost:3306/community?"
                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
 
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 返回real_name
    public static String real(){
    	char [] reals = {'们','力','电','量','得','工','定','化','起','的','和','有','生','人','来','学','要','化','国','会','经','多','度','时','实','进','定','法','线','经','长','党'};
    	int count = (int)(1 + Math.random() * 5);
    	String real_name = "";
    	for(int i = 0; i < count ; i++){
    		real_name += reals[(int)(1 + Math.random() * (reals.length - 1))]; // 
    	}
    	return real_name;
    }
    public static String user(){
    	char [] users = {'1', '2', '3', '4', 'a', 'b', 'c', 'd', 'f', '发', '额', '哦', '看', '的', 'の', '啊', 'b','s', 'e', 'd', 'o', 'p', '-', 'h'};
    	int count = (int)(1 + Math.random() * 6);
    	String user = "";
    	for(int i = 0; i < count ; i++){
    		user += users[(int)(1 + Math.random() * (users.length - 1))]; // 
    	}
    	return user;
    }
    public static int roleId(){
    	int [] roles = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    	return roles[(int)(1 + Math.random() * (roles.length - 1))];
    }
    public static String randomStr(int size) {
        //定义一个空字符串
        String result = "";
 
        for (int i = 0; i < 6; ++i) {
            //生成一个97~122之间的int类型整数
            int intVal = (int) (Math.random() * 26 + 97);
            //强制转换（char）intVal 将对应的数值转换为对应的字符
            //将字符进行拼接
            result = result + (char) intVal;
        }
        //输出字符串
        return result;
    }
 
 
    public static void insert() {
        // 开时时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO admin_user (user_name, `password`, role_id, real_name, id) VALUES ";
 
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = conn.prepareStatement("");
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 5; i++) {
                // 第次提交步长
                for (int j = 1; j <= 1000000; j++) {
                    // 构建sql后缀  SYSDATE(),
                    suffix.append("('" + user() + "','f8f1fc66a2c25825aede1f285187b7e8', '" + roleId() + "','" + randomStr(6) + "', 00" +(int) (Math.random() * 5)+"),");
                }
                // 构建完整sql
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行sql
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
//                System.out.print(sql);
            }
           
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("cast : " + (end - begin));
    }
 
 
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        initConn();
        insert();
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
 
    }

}

package Util.propertiesUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesUtil {
     public static void main(String[] args) throws Exception {
    	 //方式一
    	 InputStream in = new BufferedInputStream(new FileInputStream("文件路径名"));
    	 Properties p = new Properties();
    	 p.load(in);
    	 System.out.println(p.getProperty("version"));
    	 //方式二
    	 InputStream ins = PropertiesUtil.class.getResourceAsStream("文件路径名");
    	 Properties ps = new Properties();
    	 ps.load(ins);
    	 System.out.println(ps.getProperty("version"));
    	 //方式三
    	 InputStream inss = PropertiesUtil.class.getClassLoader().getResourceAsStream("文件名");
    	 Properties pss = new Properties();
    	 pss.load(inss);
    	 System.out.println(pss.getProperty("version"));
    	 //方式四
    	 InputStream insss = ClassLoader.getSystemResourceAsStream("文件名");
    	 Properties psss = new Properties();
    	 psss.load(insss);
    	 System.out.println(pss.getProperty("version"));
    	 
    	 //方式五
    	 ResourceBundle rb = ResourceBundle.getBundle("文件名前缀", Locale.getDefault());
    	 System.out.println(rb.getObject("version"));
    	 //方式六
    	 InputStream is = new BufferedInputStream(new FileInputStream("文件名前缀"));
    	 ResourceBundle rbs = new PropertyResourceBundle(is);
    	 System.out.println(rbs.getObject("version"));
	}
}

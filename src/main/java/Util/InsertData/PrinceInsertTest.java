package Util.InsertData;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import Util.InsertData.InsertOnlyUtil;
public class PrinceInsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ParseException {
	 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

//    	String [] column = {"menoy", "city", "gender"};
//    	String [] colType = {"money",  "city", "num(2)"};


//		// 设置数据表的字段
		String [] column = {"id", "`name`", "city", "gender", "birthdate", "mobile", "photo", "monthsalary", "yearbonus"};
		// 数据的测试数据类型
		String [] colType = {"char(18)", "chinese", "city", "num(2)", "day", "phone:0", "xxxx:0", "money", "money:0"};
		InsertOnlyUtil insertUtil = new InsertOnlyUtil("my_optimize", "root", "leeprince");
		insertUtil.setColType(colType);
		insertUtil.setColumn(column);
		insertUtil.setCount(10); // 一次添加多少的数据
		insertUtil.setBatches(1); // 添加几次
		insertUtil.setTableName("p_customers"); // 数据表
		insertUtil.insert();
		System.out.println(insertUtil.getSql());

		// 测试关联数据新增


//		String [][] column = {
//			{"equipmentMD5", "type", "age"},
//			{"equipmentMD5", "equipmentMac", "datetime", "age", "gender", "timestamp"}
//		};
//		String [][] colType = {
//			{"char(18)", "num(7)", "num(120)"},
//			{"char(18)", "generate:0", "date","num(120)", "num(2)", "timestamp"}
//		};
//		String [] table_name  = {"details", "equipment"};
//		String [] hasFile = {"equipmentMD5", "equipmentMD5"};
//
//		InsertHasUtil insertHasUtil = new InsertHasUtil("join", "root", "root");
//
//		insertHasUtil.setColType(colType);
//		insertHasUtil.setColumn(column);
//		insertHasUtil.setCount(1000000); // 一次添加多少的数据
//		insertHasUtil.setBatches(1); // 添加几次
//		insertHasUtil.setTableName(table_name); // 数据表
//		insertHasUtil.setHasFile(hasFile);
//		insertHasUtil.insert();
//		StringBuffer[] sql = insertHasUtil.getSql();
//   	   	for(int j = 0; 2 > j; j++){
//            System.out.println(sql[j].toString());
//        }

	    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}
}
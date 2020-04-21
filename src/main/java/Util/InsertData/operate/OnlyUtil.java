package Util.InsertData.operate;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import Util.InsertData.operate.FileUtil;

public class OnlyUtil extends FileUtil{
	// 列名
	protected String [] column;
	// 字段类型
	protected String [] colType;
	
	protected String tableName ;
	
	public OnlyUtil(String databases, String username, String password) {
		super(databases, username, password);
		// TODO Auto-generated constructor stub
	}
    

	public String [] getColType() {
		String arr [] = new String [colType.length];
		for(int i = 0; colType.length > i; i++){
			arr[i] = this.getTypeValue(colType[i]);
		}
		return arr;
	}
	
	public void setColumn(String[] column) {
		this.column = column;
	}

	public void setColType(String[] colType) {
		this.colType = colType;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}

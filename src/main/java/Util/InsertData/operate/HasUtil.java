package Util.InsertData.operate;

import java.sql.Connection;
import org.apache.commons.lang3.StringUtils;

public class HasUtil extends FileUtil{
	
	// 列名
	protected String [][] column;
	// 字段类型
	protected String [][] colType;
	
	protected String [] tableName ;
	
	protected String [] hasFile;
	
	protected int hasCount;
	
	public HasUtil(String databases, String username, String password) {
		super(databases, username, password);
	}
	// 字段处理
	public String[] getColType() {
		
		int [] hasSite = this.getHasSite();
		String arr [] = new String [colType.length];
		String has = "";//记录关联参数值
		for(int i = 0; this.hasCount > i; i++){
			String temp [] = new String [colType[i].length];
			for(int j = 0; colType[i].length > j; j++){
				if(hasSite[i] == j){ // 关联值
					if (has.equals("")){
						temp[j] = this.getTypeValue(colType[i][j]);
						has = temp[j];
					} else {
						temp[j] = has;
					}
				} else {
					temp[j] = this.getTypeValue(colType[i][j]);
				}
			}
			arr[i] = StringUtils.join(temp , ",");
		}
		return arr;
	}

	public void setColumn(String[][] column) {
		this.column = column;
	}

	public void setColType(String[][] colType) {
		this.colType = colType;
	}

	public String getTableName(int i) {
		return tableName[i];
	}

	public void setTableName(String[] tableName) {
		this.tableName = tableName;
	}

	public void setHasFile(String[] hasFile) {
		this.hasFile = hasFile;
	}
	public int[] getHasSite() {
		
		int [] hasSite = new int [column.length];
		for(int i = 0;  this.hasCount > i; i++){
			for(int j = 0; column[i].length > j; j++){
				if(this.hasFile[i].equals(column[i][j])){
					hasSite[i] = j;
				}
			}
		}
		return hasSite;
	}
}

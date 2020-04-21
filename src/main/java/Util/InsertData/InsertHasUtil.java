package Util.InsertData;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import Util.InsertData.operate.HasUtil;


public class InsertHasUtil extends HasUtil{

	private int count;
	
	private int batches;
	
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBatches() {
		return batches;
	}

	public void setBatches(int batches) {
		this.batches = batches;
	}
	public InsertHasUtil(String databases, String username, String password) {
		super(databases, username, password);
		this.setCount(1);
		this.setBatches(1);
	}
	/**
    * 返回前缀
    * @return
    */
   private StringBuffer[] getPrefixSql(){
	   StringBuffer[] stringBuffers = new StringBuffer[column.length];
	   for(int i = 0; this.hasCount > i; i++){
		   stringBuffers[i] = new StringBuffer();
		   String table = this.getTableName(i);
		   String [] cl = column[i];
		   stringBuffers[i].append("INSERT INTO "+this.getTableName(i)+" (" + StringUtils.join(cl, ",")+ " ) VALUES ");
	   }
	   return stringBuffers;
   }

   private StringBuffer[] getSuffixSql() {
	   StringBuffer[] stringBuffers = new StringBuffer[colType.length];
	   String [] cols = this.getColType();
	   for(int i = 0;  this.hasCount > i; i++){		
		   stringBuffers[i] = new StringBuffer();
		   stringBuffers[i].append("(");
		   stringBuffers[i].append(cols[i]);
		   stringBuffers[i].append(")");
	   }
	   return stringBuffers;
   }
   
   public StringBuffer[] getSql(){
	   this.hasCount = this.column.length;
	   StringBuffer[] sql = new StringBuffer[this.hasCount];
	   sql = this.getPrefixSql();
       // 第次提交步长
       for (int j = 1; j <= this.count; j++) {
           // 构建sql后缀  SYSDATE(),
    	   StringBuffer [] suffix = this.getSuffixSql();
    	   for(int i = 0; this.hasCount > i; i++){
    		   sql[i].append(suffix[i]+",");
    	   }
       }
       StringBuffer[] ret = new StringBuffer[this.hasCount];
       for(int i = 0; this.hasCount > i; i++){
    	   ret[i] = new StringBuffer();
    	   ret[i].append(sql[i].substring(0, sql[i].length() - 1));
       }
       return ret; 
   }
   
   public void insert() {
	  
       try {
           // 保存sql后缀
           StringBuffer suffix = new StringBuffer();
           // 设置事务为非自动提交
           conn.setAutoCommit(false);
           // 比起st，pst会更好些
           PreparedStatement pst = conn.prepareStatement("");
           // 外层循环，总提交事务次数
           for (int i = 1; i <= this.getBatches(); i++) {
               // 构建完整sql
        	   StringBuffer[] sql = getSql();
        	   for(int j = 0; this.hasCount > j; j++){
        		   // 添加执行sql
                   pst.addBatch(sql[j].toString());
                   // 执行操作
                   pst.executeBatch();
               }
               // 提交事务
               conn.commit();
               // 清空上一次添加的数据
               suffix = new StringBuffer();
           }
           // 头等连接
           pst.close();
           conn.close();
           System.out.println(this.getCount()*this.getBatches() + "数据 添加成功");
       } catch (SQLException e) {
    	   System.out.println(this.getCount()*this.getBatches() + "数据 添加失败");
           e.printStackTrace();
       }
   }
}

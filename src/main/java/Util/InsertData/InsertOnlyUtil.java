package Util.InsertData;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javafx.scene.Parent;

import org.apache.commons.lang3.StringUtils;

import Util.InsertData.operate.OnlyUtil;


public class InsertOnlyUtil extends OnlyUtil{
	
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
	public InsertOnlyUtil(String databases, String username, String password){
		super(databases,username,password);
		this.setCount(1);
		this.setBatches(1);
	}
	/**
    * 返回前缀
    * @return
    */
   private StringBuffer getPrefixSql(){
   	   // sql前缀
	   StringBuffer suffix = new StringBuffer();
	   suffix.append("INSERT INTO "+this.getTableName()+" (" + StringUtils.join(column, ",")+ " ) VALUES ");
	   return suffix;
   }

   private StringBuffer getSuffixSql(){
	   StringBuffer ret = new StringBuffer();
	   ret.append("(");
	   ret.append(StringUtils.join(this.getColType() , ","));
	   ret.append(")");
	   return ret;
   }
   
   public String getSql(){
	   StringBuffer sql = new StringBuffer();
	   sql = this.getPrefixSql();
       // 第次提交步长
       for (int j = 1; j <= this.count; j++) {
           // 构建sql后缀  SYSDATE(),
    	   sql.append(this.getSuffixSql()+",");
       }
      return sql.substring(0, sql.length() - 1);
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
               String sql = getSql();
//               System.out.println(sql);
               // 添加执行sql
               pst.addBatch(sql);
               // 执行操作
               pst.executeBatch();
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

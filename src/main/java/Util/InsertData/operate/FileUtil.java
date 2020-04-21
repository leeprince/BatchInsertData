package Util.InsertData.operate;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public abstract class FileUtil extends JDBC{
    protected StringBuffer getValue(String type, int size) {
    	StringBuffer ret = new StringBuffer();
    	ret.append("'");
    	if(type.equals("randStr") || type.equals("english")){
    		ret.append(this.getEnglish(size));// 随机字母
    	} else if (type.equals("chinese") || type.equals("name")) {
    		ret.append(this.randString(size)); // 随机中文
    	} else if (type.equals("orderSn")){
    		ret.append(this.orderSn());  // 随机订单编号
    	} else if (type.equals("passowrd") || type.equals("char")){
    		ret.append(this.getChar(size));    // 随机中文
    	} else if (type.equals("num") || type.equals("int")){
    		ret.append(this.getInt(size));// 随机数字
    	} else if (type.equals("relevanceId")){
    		ret.append(this.relevanceId());
    	} else if (type.equals("city")){ // 
    		ret.append(this.city());
    	} else if (type.equals("date")){ // 获取具体某一天的时间节点		
    		ret.append(this.getDate());
    	} else if (type.equals("money")){ // money
    		ret.append(this.money());
    	} else if (type.equals("day")){ // 获取具体的某一天
    		ret.append(this.day());
    	} else if (type.equals("phone") || type.equals("tel")){ // getDate
    		ret.append(this.getTel());
    	} else if (type.equals("timestamp")){
    		ret.append(DateUtil.date2TimeStamp(this.getDate(), "yyyy-MM-dd HH:mm:ss"));
    	} else if (type.equals("generate")){
    		ret.append(this.generateString());
    	} else {
    		ret.append(type);
    	}	
    	ret.append("'");
    	return ret;
    }
	    
	
	public FileUtil(String databases, String username, String password) {
		super(databases, username, password);
		// TODO Auto-generated constructor stub
	}

	protected String getTypeValue(String colType2){
    	String type = "";
    	int size = 5;
    	if(colType2.toString().contains(":") ){
    		// 允许为空的情况，给予默认值
    		String [] arr = colType2.toString().split(":");
    		if(this.getInt(2) == 0){
    			return getDefual(arr[1]);
    		}
        	colType2 = arr[0];
    	}

    	// 获取类型名
    	if( colType2.toString().contains("(")){
    		String [] arr = colType2.toString().split("[()]");
    		type = arr[0]; 
    		size = Integer.parseInt(arr[1]);
    	} else {
    		type = colType2.toString();
    	}  	
    	
    	return this.getValue(type, size) + "";
    }

    protected String getDefual(String type){
    	if (type.equals("NULL")){
    		return "NULL";
    	} else if (type.equals("2")){
    		return this.getInt(2)+"";
    	} 
    	return type;
    }
    
    protected double money(){
    	double min = 0.01;//最小值
  	    double max = 10000;//总和
  	    int scl =  2;//小数最大位数
  	    int pow = (int) Math.pow(10, scl);//指定小数位
  	    return Math.floor((Math.random() * (max - min) + min) * pow) / pow;
    }
    
    public int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    /**
     * 随机生成手机号码
     * @return
     */
    protected String getTel() {
    	String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index=this.getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(this.getNum(1,888)+10000).substring(1);
        String third=String.valueOf(this.getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    /**
     * 随机生成城市名
     * @return
     */
    protected String city(){
    	String [] reals = {"北京","长沙","上海","广州","深圳","河南","内蒙古","天津","江西","南京","武汉","城都"};
    	return reals[(int)(1 + Math.random() * (reals.length - 1))];
    }
    /**
     * 返回int类型的数据 0 ~ 00
     * @param size
     * @return
     */
    protected int getInt(int size){
    	return  (int) ( 0 + Math.random() * size);
    }   
    /**
     * 随机字母~字符串
     * @param size 字符串长度
     * @return
     */
	protected StringBuffer getEnglish(int size) {
        //定义一个空字符串
    	StringBuffer result =  new StringBuffer();
        for (int i = 0; i < size; ++i) {
            //生成一个97~122之间的int类型整数
            int intVal = (int) (Math.random() * 26 + 97);
            //强制转换（char）intVal 将对应的数值转换为对应的字符
            //将字符进行拼接
            result.append((char) intVal);
        }
        // 输出字符串
        return result;
    }
	
	protected String generateString(){
		StringBuffer result =  new StringBuffer();
		return this.getEnglish(1)+""+this.getInt(5);
	}
    /**
     * 随机字母~字符串
     * @param size 字符串长度
     * @return
     */
	protected StringBuffer getChinese(int size) {
        //定义一个空字符串
    	StringBuffer result =  new StringBuffer();
        for (int i = 0; i < size; ++i) {
            //将字符进行拼接
        	result.append(this.randChar());
        }
        // 输出字符串
        return result;
	
    }
	/**
	 * 随机生成常见中文
	 * @return
	 */
	protected String randChar(){
	  	String str = "";
	    int hightPos; //
	    int lowPos;
	    Random random = new Random();
	    hightPos = (176 + Math.abs(random.nextInt(39)));
	    lowPos = (161 + Math.abs(random.nextInt(93)));
	    byte[] b = new byte[2];
	    b[0] = (Integer.valueOf(hightPos)).byteValue();
	    b[1] = (Integer.valueOf(lowPos)).byteValue();
	    try {
	      str = new String(b, "GBK");
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	    return str;
	}
    /**
     * 随机生成字符串 数字+字母
     * @param size
     * @return
     */
	protected StringBuffer getChar(int size){
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		String strAll = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < size; i++) {
			 sb.append(strAll.charAt((int) (Math.random()*62)));
		}
		return sb;
	}

	protected String day(){
        Random rd=new Random();
        int year=rd.nextInt(2)+2017;  //生成[2010,2017]的整数；年
        int month=rd.nextInt(12)+1;   //生成[1,12]的整数；月
        int Day=rd.nextInt(28)+1;       //生成[1,30)的整数；日
        return year+"-"+month+"-"+Day;
    }
	
	/**
	 * 随机生成yyyy-mm-dd时间
	 * 
	 * @return
	 */
	protected String getDate(){
        Random rd=new Random();
        int year=rd.nextInt(2)+2017;  //生成[2010,2017]的整数；年
        int month=rd.nextInt(12)+1;   //生成[1,12]的整数；月
        int Day=rd.nextInt(28)+1;       //生成[1,28)的整数；日
        int hour=rd.nextInt(23);       //生成[0,23)的整数；小时
        int minute=rd.nextInt(60);   //生成分钟
        int second=rd.nextInt(60);   //秒
        return year+"-"+month+"-"+Day+"  "+hour+":"+minute+":"+second;
    }
   
	/**
     * 随机关联主键id
     * @return
     */
	protected int relevanceId(){
    	// 关联id范围
    	int [] relevance = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    	return relevance[(int)(1 + Math.random() * (relevance.length - 1))];
    }
    /**
     * 随机订单编号
     * @return
     */
	protected String orderSn(){
    	return "C180322"+System.currentTimeMillis()+getChar(2);
    }
	/**
	 * 随机字符串 
	 * @param maxSize 字符串最大长度
	 * @return string 随机的字符串
	 */
	protected StringBuffer randString(int maxSize){
    	// 随机字符串内容
    	char [] stringArr = {'们','力','电','量','得','工','稍','王','来','妞','拰','银','龙','水','鹏','哦','定','化','起','的','和','有','生','人','来','学','要','化','国','会','经','多','度','时','实','进','定','法','线','经','长','党'};
    	int count = (int)(1 + Math.random() * maxSize);
    	StringBuffer suffix = new StringBuffer();
    	for(int i = 0; i < count ; i++){
    		suffix.append(stringArr[(int)(1 + Math.random() * (stringArr.length - 1))]); // 
    	}
    	return suffix;
    }
}

package Constants;

import org.springframework.util.ClassUtils;

/*
 * 依赖jar
 * <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>4.3.9.RELEASE</version>
</dependency>

 */
/**
 * final  类放关键字段
 * @author Administrator
 *
 */
public class Constants {
	
	/**
	 * 文件分隔符
	 */
	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");
	/**
	 * 行分隔符
	 */
	public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");
	/**
	 * 路径分隔符
	 */
	public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");
	/**
	 * 密码
	 */
	public static final String QRCODE_PATH = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+SF_FILE_SEPARATOR+"qrcode"; 
	/**
	 * 微信账单
	 */
	public static final String WEIXIN_BILL = "tradetime, ghid, mchid, submch, deviceid, wxorder, bzorder, openid, tradetype, tradestatus, bank, currency, totalmoney, redpacketmoney, wxrefund, bzrefund, refundmoney, redpacketrefund, refundtype, refundstatus, productname, bzdatapacket, fee, rate";
	/**
	 * 路径信息的xml
	 */
	public static final String PATH_BASE_INFO_XML = SF_FILE_SEPARATOR+"WEB-INF"+SF_FILE_SEPARATOR+"xmlConfig"+SF_FILE_SEPARATOR;
	/**
	 * 用户的信息
	 */
	public static final String CURRENT_USER = "UserInfo";
	/**
	 * 成功
	 */
	public static final String SUCCESS = "success";
	/**
	 * 失败
	 */
	public static final String FAIL = "fail";
	
}

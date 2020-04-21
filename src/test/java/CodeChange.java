

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeChange {
	/**
	 * 把中文字符串转换为十六进制Unicode编码字符串<br>
	 *“配置成功，重启后生效”<br>
	 * --->><br>
	 * \u914d\u7f6e\u6210\u529f\uff0c\u91cd\u542f\u540e\u751f\u6548
	 */
	public static String stringToUnicode(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255)
				str += "\\u" + Integer.toHexString(ch);
			else
				str += "\\" + Integer.toHexString(ch);
		}
		return str;
	}

	/**
	 * 把十六进制Unicode编码字符串转换为中文字符串<br>
	 * \u914d\u7f6e\u6210\u529f\uff0c\u91cd\u542f\u540e\u751f\u6548<br>
	 * ---->><br>
	 * "配置成功，重启后生效"
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;

		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	public static void main(String[] args) {
		
		List<String>list=new ArrayList<String>();
		list.add("\\u9879\\u76eecontextPath \\u6b22\\u8fce\\u5173\\u6ce8 https://blog.52itstyle.com");
		list.add("\\u670d\\u52a1\\u7aef\\u53e3");
		list.add("#session\\u6700\\u5927\\u8d85\\u65f6\\u65f6\\u95f4(\\u5206\\u949f)\\uff0c\\u9ed8\\u8ba4\\u4e3a30\r\n" + 
				"");
		list.add("#\\u8be5\\u670d\\u52a1\\u7ed1\\u5b9aIP\\u5730\\u5740\\uff0c\\u542f\\u52a8\\u670d\\u52a1\\u5668\\u65f6\\u5982\\u672c\\u673a\\u4e0d\\u662f\\u8be5IP\\u5730\\u5740\\u5219\\u629b\\u51fa\\u5f02\\u5e38\\u542f\\u52a8\\u5931\\u8d25\\uff0c\\u53ea\\u6709\\u7279\\u6b8a\\u9700\\u6c42\\u7684\\u60c5\\u51b5\\u4e0b\\u624d\\u914d\\u7f6e\r\n" + 
				"");
		list.add("#tomcat\\u6700\\u5927\\u7ebf\\u7a0b\\u6570\\uff0c\\u9ed8\\u8ba4\\u4e3a200\r\n" + 
				"");
		list.add("#tomcat\\u7684URI\\u7f16\\u7801\r\n" + 
				"");
		list.add("#spring boot\\u4ece\\u63a7\\u5236\\u53f0\\u6253\\u5370\\u51fa\\u6765\\u7684\\u65e5\\u5fd7\\u7ea7\\u522b\\u53ea\\u6709ERROR, WARN \\u8fd8\\u6709INFO\\uff0c\\u5982\\u679c\\u4f60\\u60f3\\u8981\\u6253\\u5370debug\\u7ea7\\u522b\\u7684\\u65e5\\u5fd7\r\n" + 
				"");
		list.add("#x\\u6388\\u6743\\u7801g\\uff0c\\u5728QQ\\u90ae\\u7bb1\\u5ba2\\u6237\\u7aef\\u751f\\u6210 \\u4fee\\u6539\\u6210\\u81ea\\u5df1\\u7684\\uff0c\\u5c0f\\u5fc3\\u4f1a\\u62a5\\u9519  \\u8bbe\\u7f6e-\\u8d26\\u6237-\\u5f00\\u542f\\u670d\\u52a1-\\u83b7\\u53d6\\u6388\\u6743\\u7801\r\n" + 
				"");
		list.add("# \\u8fde\\u63a5\\u6c60\\u6700\\u5927\\u8fde\\u63a5\\u6570\\uff08\\u4f7f\\u7528\\u8d1f\\u503c\\u8868\\u793a\\u6ca1\\u6709\\u9650\\u5236\\uff09\r\n" + 
				"");
		list.add("# \\u8fde\\u63a5\\u6c60\\u6700\\u5927\\u963b\\u585e\\u7b49\\u5f85\\u65f6\\u95f4\\uff08\\u4f7f\\u7528\\u8d1f\\u503c\\u8868\\u793a\\u6ca1\\u6709\\u9650\\u5236\\uff09\r\n" + 
				"");
		list.add("# \\u8fde\\u63a5\\u6c60\\u4e2d\\u7684\\u6700\\u5927\\u7a7a\\u95f2\\u8fde\\u63a5\r\n" + 
				"");
		list.add("# \\u8fde\\u63a5\\u8d85\\u65f6\\u65f6\\u95f4\\uff08\\u6beb\\u79d2\\uff09\r\n" + 
				"");
		list.add("#ZooKeeper\\u6ce8\\u518c\\u4e2d\\u5fc3  \\u81ea\\u884c\\u66ff\\u6362\r\n" + 
				"");
		for (String string : list) {
			System.out.println(CodeChange.unicodeToString(string));
		}
		// 转换Unicode码为汉字
		//String s3 = ;

		//System.out.println("s3: " + s3);
	}
}
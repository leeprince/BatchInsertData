package Util.ClassUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.AddressUtils.AddressUtils;  
  
public class ClassUtil {

	private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
	/** 
     * @param source 被复制的实体类对象 
     * @param to 复制完后的实体类对象   
     * @throws Exception 
     */  
    public static void Copy(Object source, Object to) throws Exception { 
    	logger.info("==========================开始复制=========================");
        // 获取属性    
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),java.lang.Object.class);    
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();    
    
        BeanInfo destBean = Introspector.getBeanInfo(to.getClass(),java.lang.Object.class);    
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();    
    
        try {    
            for (int i = 0; i < sourceProperty.length; i++) {    
    
                for (int j = 0; j < destProperty.length; j++) {    
    
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {    
                        // 调用source的getter方法和dest的setter方法    
                        destProperty[j].getWriteMethod().invoke(to,sourceProperty[i].getReadMethod().invoke(source));    
                        break;    
                    }    
                }    
            }    
            logger.info("==========================复制成功=========================");
        } catch (Exception e) {    
        	logger.error("属性复制失败:" + e.getMessage());
        }    
    }

	/**
	 * 随机6位数的id
	 * @return
	 */
	public static String getId() {
        logger.info("==========================生成Id=========================");
		Random rd=new Random();
		char first = (char)(65+rd.nextInt(26));
		char second = (char)(65+rd.nextInt(26));
		int thirdly=rd.nextInt(100);
		int four=rd.nextInt(100);
		String id= ""+first+second+thirdly+four;
        logger.info("id:"+id);
		return id;
	}
}

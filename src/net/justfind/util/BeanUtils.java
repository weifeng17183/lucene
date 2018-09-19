package net.justfind.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.search.Query;

/**
 * <br>
 * <b>功能：</b>bean工具类<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月21日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class BeanUtils {
    /**
     * <br>
     * <b>功能：</b>javaBean对象转为document对象<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月21日 <br>
     * @param obj
     * @return Document
     */
    public static Document beanToDocument(Object obj) {
        Document document = new Document();
        try {
            Class clazz = obj.getClass();
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String init = fieldName.substring(0, 1).toUpperCase();
                String methodName = "get" + init + fieldName.substring(1);
                Method reflectMethod = getMethod(clazz, methodName);
                if (reflectMethod != null) {
                    Object returnValue = reflectMethod.invoke(obj, null);
                    if (returnValue == null) {
                        continue;
                    }
                    document.add(new Field(fieldName, returnValue.toString(), Store.YES, Index.ANALYZED_NO_NORMS));
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    private static Method getMethod(Class clazz, String methodName) {
        Method reflectMethod = null;
        try {
            reflectMethod = clazz.getMethod(methodName, null);
        } catch (Exception e) {
            return null;
        }
        return reflectMethod;
    }

    /**
     * <br>
     * <b>功能：</b>将document对象转为bean对象<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月21日 <br>
     * @param document
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object documentToBean(Document document, Class clazz, Query query) throws Exception {
        Object obj = clazz.newInstance();
        try {
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String fieldValue = document.get(fieldName);
                if (StringUtils.isBlank(fieldValue)||"null".equals(fieldValue)) {
                    continue;
                }else if(field.getGenericType().toString().equals("class java.math.BigDecimal")){
                    BigDecimal bigLoanAmount = new BigDecimal(fieldValue);
//                    NumberFormat currency = NumberFormat.getCurrencyInstance(); 
//                    document.getField(fieldName).setValue(currency.format(bigLoanAmount));
//                    fieldValue = document.get(fieldName);
                    org.apache.commons.beanutils.BeanUtils.setProperty(obj, fieldName, bigLoanAmount);
                    continue;
                }else if("name".equals(field.getName())){
                    document.getField(fieldName).setValue(HighLightUtil.doHighLight(query, fieldName, fieldValue));
                    fieldValue = document.get(fieldName);
                }
                org.apache.commons.beanutils.BeanUtils.setProperty(obj, fieldName, fieldValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        org.apache.commons.beanutils.BeanUtils.setProperty(obj, "id", document.get("id"));
        return obj;
    }
}

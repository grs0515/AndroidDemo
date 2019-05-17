package com.grs.demo.annotation.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author:gaoruishan
 * @date:202019-05-09/09:40
 * @email:grs0515@163.com
 */
public class Test {
    /**
     * 1，首相考虑代码和数据库的映射 2,再考虑query() 方法的实现
     * @param args
     */
    public static void main(String[] args) {
        UserBean f1 = new UserBean();
        f1.setId(10);// 查询ID为10的用户

        UserBean f2 = new UserBean();
        f2.setUsername("Lucy,LucyMerry");// 模糊查询用户名为Lucy的用户
        f2.setAge(18);

        UserBean f3 = new UserBean();// 查询邮箱为其中的任意一个的
        f3.setEmail("liu@sina.com");
        // f3.setEmail("liu@sina.com,zh@163.com,77777@qq.com");

        System.out.println(query(f1));
        System.out.println(query(f2));
        System.out.println(query(f3));

        DepartmentBean d = new DepartmentBean();
        d.setAmount(10);
        d.setName("技术部");
        System.out.println(query(d));
//        输出:
//        select * from user where id=10
//        select * from user where username in('Lucy','LucyMerry') and age=18
//        select * from user where email='liu@sina.com'
//        select * from department where name='技术部' and amount=10
    }

    /**
     * 查询
     * 说明:通过Class获取对成员变量的处理
     * 1,是否存在 = class.isAnnotationPresent(注解类)
     * 2,注解类 = class.getAnnotation(注解类); 注入值= 注解类.value()
     * 3,所有的字段Field[]  = class.getDeclaredFields(); 字段名 = field.getName()
     * 4,调用方法获取字段值:Method = class.getMethod(方法名) ; 字段值 = m.invoke(class)
     * @param obj
     * @return
     */
    private static String query(Object obj) {
        StringBuilder sb = new StringBuilder();
        // 1，获取到class
        Class c = obj.getClass();
        // 2,获取到table的名字
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        // 添加查询语句,其中where 1=1 为了拼接方便
        sb.append("select * from ").append(tableName).append(" where");
        // 3,遍历所有的字段
        Field[] fArray = c.getDeclaredFields();
       boolean isFrist = false;
        for (int i = 0; i < fArray.length; i++) {
            Field field = fArray[i];
            // 4, 处理每个字段对应的sql: 拿到字段名－拿到字段值－拼接sql
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;// 不存在继续 for遍历
            }
            Column column = field.getAnnotation(Column.class);
            // 拿到注解上的名字
            String value = column.value();
            // 拿到字段名
            String filedName = field.getName();
            // 拼接getXX
            String getMethodNameString = "get"
                    + filedName.substring(0, 1).toUpperCase()
                    + filedName.substring(1);
            Object fileValue = null;
            try {
                Method getMethod = c.getMethod(getMethodNameString);
                fileValue = (Object) getMethod.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 5, 拼装sql
            if (fileValue == null || (fileValue instanceof Integer && (Integer) fileValue == 0)) {
                // 如果fileValue字段没有值，下一个
                continue;
            }
            if (isFrist) {
                sb.append(" and");
            }else {
                isFrist = true;
            }
            sb.append(" "+filedName);
            if (fileValue instanceof String) {
                // 情况: String中有,分割
                if (((String) fileValue).contains(",")) {
                    String[] values = ((String) fileValue).split(",");
                    sb.append(" in(");// 继续拼接
                    for (String v : values) {
                        sb.append("'" + v + "'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("=").append("'" + fileValue + "'");
                }
            } else {
                sb.append("=").append(fileValue);
            }
        }
        return sb.toString();
    }
}

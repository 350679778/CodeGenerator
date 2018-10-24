/*
 * TOP SECRET Copyright 2006-2015 Transsion.com All right reserved. This software is the confidential and proprietary
 * information of Transsion.com ("Confidential Information"). You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you entered into with Transsion.com.
 */
package com.codegen.util;

/**
 * ClassName:MethodUtil 根据Mybatis生成的mapper类中定义的方法，<br>
 * 获取其方法的相关属性（如方法类型，方法名称、方法参数类型、方法参数名称以及方法本身的行为类型）的工具类 <br/>
 * Date: 2018年10月21日 下午2:28:36 <br/>
 * 
 * @author fenglibin1982@163.com
 * @Blog http://blog.csdn.net/fenglibing
 * @version
 * @see
 */
public class MethodUtil {

    /**
     * 获取方法的操作类型，根据mapper的方法截取成insert, select, delete和update
     * 
     * @param str 传入的参数是mapper中定义的方法，如"int deleteByPrimaryKey(Integer id);"
     * @return 返回操作的类型，如示例返回"delete"
     */
    public static String getMethodActionType(String str) {
        String methodName = getMethodName(str);
        StringBuilder actionType = new StringBuilder();
        for (int i = 0; i < methodName.length(); i++) {
            char c = methodName.charAt(i);
            if (c >= 65 && c <= 90) {
                break;
            } else {
                actionType.append(String.valueOf(methodName.charAt(i)));
            }
        }
        return actionType.toString();
    }

    /**
     * 获取方法的反回类型
     * 
     * @param str 传入的参数是mapper中定义的方法，如"int deleteByPrimaryKey(Integer id);"
     * @return 返回方法的类型，如示例返回int
     */
    public static String getMethodReturnType(String str) {
        StringBuilder methodType = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 32) {
                methodType.append(String.valueOf(str.charAt(i)));
            } else {
                break;
            }
        }
        return methodType.toString();
    }

    /**
     * 获取方法的名称
     * 
     * @param str 传入的参数是mapper中定义的方法，如"int deleteByPrimaryKey(Integer id);"
     * @return 返回方法的名称，如根据示例返回"getMethodName"
     */
    public static String getMethodName(String str) {
        StringBuilder methodName = new StringBuilder();
        boolean methodNameBegin = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 32) {
                methodNameBegin = true;
                continue;
            }
            if (methodNameBegin == false) {
                continue;
            }
            if (c == 40) { // 遇到了方法中的"("串，说明方法名称已经提取完成
                break;
            }
            methodName.append(String.valueOf(c));
        }
        return methodName.toString();
    }

    /**
     * 获取方法的参数类型
     * 
     * @param str 传入的参数是mapper中定义的方法，如"int deleteByPrimaryKey(Integer id);"
     * @return 返回方法的类型，如根据实例会返回Integer
     */
    public static String getMethodParamType(String str) {
        StringBuilder methodParamType = new StringBuilder();
        boolean paramBegin = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 40) {
                paramBegin = true;
                continue;
            }
            if (paramBegin == false) {
                continue;
            }
            if (c == 32 || c == 41) { // 遇到了方法中的空格" "串或者遇到右括号")"，说明参数类型已经提取完成
                break;
            } else {
                methodParamType.append(String.valueOf(c));
            }
        }
        return methodParamType.toString();
    }

    /**
     * 获取方法的参数名称
     * 
     * @param str 传入的参数是mapper中定义的方法，如"int deleteByPrimaryKey(Integer id);"
     * @return 返回参数的名称，如根据示例会返回id
     */
    public static String getMethodParamName(String str) {
        StringBuilder methodParamName = new StringBuilder();
        boolean paramBegin = false;
        boolean paramBeginName = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 40) {
                paramBegin = true;
                continue;
            }
            if (paramBegin == false) {
                continue;
            }
            if (c == 32 && !paramBeginName) { // 遇到了方法中的空格" "串，说明参数类型已经提取完成
                paramBeginName = true;
                continue;
            } else if (!paramBeginName) {
                continue;
            }
            if (c == 41) {
                break;
            }
            methodParamName.append(String.valueOf(c));
        }
        return methodParamName.toString();
    }

    /**
     * 根据返回的方法类型，如果是基本数据类型，则转换为相应的对象类型，否则直接返回
     * 
     * @param methodType
     * @return
     */
    public static String typeConvert(String methodType) {

        if ("short".equals(methodType)) {
            return "Short";
        } else if ("int".equals(methodType)) {
            return "Integer";
        } else if ("long".equals(methodType)) {
            return "Long";
        } else if ("float".equals(methodType)) {
            return "Float";
        } else if ("double".equals(methodType)) {
            return "Double";
        } else if ("boolean".equals(methodType)) {
            return "Boolean";
        } else if ("char".equals(methodType)) {
            return "Char";
        } else if ("byte".equals(methodType)) {
            return "Byte";
        }
        return methodType;
    }

    public static String getMethodParamType(String methodName, String methodParamType, String modelName) {
        if ("selectByPage".equals(methodName)) {
            return "Page<"+modelName+">";
        }
        return methodParamType;
    }
}

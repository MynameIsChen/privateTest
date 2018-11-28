package com.chen.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 检查
 */
public class CheckUtil {

    public CheckUtil() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNonEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNonEmpty(List list) {
        return !isEmpty(list);
    }

    public static boolean isEmpty(Set set) {
        return set == null || set.size() == 0;
    }

    public static boolean isNonEmpty(Set set) {
        return !isEmpty(set);
    }

    public static boolean isEmpty(Map map) {
        return map == null;
    }

    public static boolean isNonEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }

    public static boolean isNonEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(Object[] object) {
        return object == null || object.length == 0;
    }

    public static boolean isNonEmpty(Object[] object) {
        return !isEmpty(object);
    }

    /**
     *  在区间内
     */
    public static boolean isRange(Object[] object, int index) {
        return isNonEmpty(object) && index >= 0 && object.length > index;
    }

    public static boolean isRange(List list, int index) {
        return isNonEmpty(list) && index >= 0 && list.size() > index;
    }

    public static boolean isRange(String str, int index) {
        return isNonEmpty(str) && index >= 0 && str.length() > index;
    }
}

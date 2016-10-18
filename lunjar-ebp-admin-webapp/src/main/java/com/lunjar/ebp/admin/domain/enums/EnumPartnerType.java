package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 接入者类型用户类型  1企业 2个人 默认为0
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/6 11:06
 * @version %I%,%G%
 * @see
 */
public enum EnumPartnerType implements DataDictionaryEnum {
    /** 未定 */
    DEFAULT(0, "未审核"),
    /** 企业 */
    ENTERPRISE(2, "企业"),
    /** 个人 */
    PERSONAL(1, "个人"),
    ;

    private int value;
    private String text;
    private DataDictionary dataDictionary;

    private static Map<Integer, EnumPartnerType> pool = new HashMap<Integer, EnumPartnerType>();

    static {
        for (EnumPartnerType each : EnumPartnerType.values()) {
            EnumPartnerType defined = pool.get(each.getValue());
            if (null != defined) {
                throw new IllegalArgumentException(defined.toString() + " defined as same code with "
                        + each.toString());
            }
            pool.put(each.getValue(), each);
        }
    }

    EnumPartnerType(int value, String text) {
        this.value = value;
        this.text = text;
        dataDictionary = new DataDictionary();
        dataDictionary.setValue(value + "");
    }

    @Override
    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public static EnumPartnerType valueOf(int value) {
        return pool.get(value);
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return text;
    }
}

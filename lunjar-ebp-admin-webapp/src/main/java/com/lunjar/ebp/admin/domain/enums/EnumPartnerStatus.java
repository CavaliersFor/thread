package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 接入者实名审核状态
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/6 11:06
 * @version %I%,%G%
 * @see
 */
public enum EnumPartnerStatus implements DataDictionaryEnum {
    /**
     * 未审核
     */
    AUDIT_NORMAL(1, "未审核", "#ff6600"),
    /**
     * 审核通过
     */
    AUDIT_SUCCESS(2, "审核通过", "green"),
    /**
     * 审核不通过
     */
    ADUIT_FAIL(3, "审核不通过", "red"),
    /**
     * 审核中
     */
    ADUIT_PARSING(4, "审核中", "#0abdbb"),
    ;

    private int value;
    private String text;
    private String color;

    private DataDictionary dataDictionary;

    private static Map<Integer, EnumPartnerStatus> pool = new HashMap<Integer, EnumPartnerStatus>();

    static {
        for (EnumPartnerStatus each : EnumPartnerStatus.values()) {
            EnumPartnerStatus defined = pool.get(each.getValue());
            if (null != defined) {
                throw new IllegalArgumentException(defined.toString() + " defined as same code with "
                        + each.toString());
            }
            pool.put(each.getValue(), each);
        }
    }

    EnumPartnerStatus(int value, String text, String color) {
        this.value = value;
        this.text = text;
        this.color = color;
        dataDictionary = new DataDictionary();
        dataDictionary.setValue(value + "");
        dataDictionary.setText(text);
        dataDictionary.setColor(color);
    }

    @Override
    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public static EnumPartnerStatus valueOf(int value) {
        return pool.get(value);
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return text;
    }
}

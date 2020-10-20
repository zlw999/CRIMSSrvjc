package com.crims.apps.common.struct;

public class CommonColumn{

    /**
     *  字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段长度
     */
    private int length;

    /**
     * 小数点数
     */
    private int decimalLength;

    /**
     * 是否为可以为null
     */
    private boolean nullValue = true;

    /**
     * 是否是主键，默认false
     */
    private boolean key = false;

    /**
     * 是否自动递增，默认false 只有主键才能使用
     */
    private boolean autoIncrement = false;

    /**
     * 注释
     */
    private String dsp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDecimalLength() {
        return decimalLength;
    }

    public void setDecimalLength(int decimalLength) {
        this.decimalLength = decimalLength;
    }

    public boolean isNullValue() {
        return nullValue;
    }

    public void setNullValue(boolean nullValue) {
        this.nullValue = nullValue;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }
}


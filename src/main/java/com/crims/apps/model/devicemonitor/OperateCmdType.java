package com.crims.apps.model.devicemonitor;

public enum  OperateCmdType {

    INSERT(0x00010801, "添加"),
    UPDATE(0x00010802, "修改"),
    Delete(0x00010803, "删除"),
    MOVE(0x00010804, "迁移"),
    REMOVE(0x00010805, "报废");

    // 普通方法
    public static String getTypeName(String typeVal) {
        for (OperateCmdType type : OperateCmdType.values()) {
            if (type.getTypeVal().equals(typeVal)) {
                return type.typeName;
            }
        }
        return null;
    }

    public static OperateCmdType getEnumType(String typeVal)
    {
        for (OperateCmdType type : OperateCmdType.values()) {
            if (type.getTypeVal().equals(typeVal)) {
                return type;
            }
        }
        return null;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.typeVal + "_" + this.typeName;
    }

    private Integer typeVal;
    private String typeName;

    private OperateCmdType(Integer typeVal, String typeName)
    {
        this.typeVal = typeVal;
        this.typeName = typeName;
    }

    public Integer getTypeVal() {
        return typeVal;
    }

    public String getTypeName() {
        return typeName;
    }
}

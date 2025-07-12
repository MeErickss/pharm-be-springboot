package com.example.pharm.model.enumeration;

public enum OffsetEnum {
    OFFSET_1(0),
    OFFSET_2(100),
    OFFSET_3(200),
    OFFSET_4(300),
    OFFSET_5(400);

    private final int value;

    OffsetEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

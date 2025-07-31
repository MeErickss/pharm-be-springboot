package com.example.pharm.dto;

public class ReadRequestDto {
    public String type;    // "holding", "input", "coil", "discrete"
    public int slaveId = 1;
    public int address;
    public int length;
}

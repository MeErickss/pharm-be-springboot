package com.example.pharm.service;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.exception.ModbusInitException;
import org.springframework.stereotype.Service;

@Service
public class ModbusService {
    private ModbusMaster master;
    private boolean connected = false;

    public synchronized void connect(String host, int port, int slaveId) throws ModbusInitException {
        if (connected) return;
        IpParameters params = new IpParameters();
        params.setHost(host);
        params.setPort(port);

        master = new ModbusFactory().createTcpMaster(params, true);
        master.setTimeout(2000);
        master.init();
        master.setRetries(1);
        connected = true;
    }


    public int[] read(String type, int slaveId, int start, int len) throws Exception {
        if (!connected) throw new IllegalStateException("Não conectado ao Modbus");
        int[] data = new int[len];

        switch (type) {
            case "holding":
                for (int i = 0; i < len; i++) {
                    // registrador holding como unsigned 2-byte
                    BaseLocator<Number> locator =
                            BaseLocator.holdingRegister(slaveId, start + i, DataType.TWO_BYTE_INT_UNSIGNED);
                    Number val = master.getValue(locator);
                    data[i] = (val != null) ? val.intValue() : 0;
                }
                break;

            case "input":
                for (int i = 0; i < len; i++) {
                    BaseLocator<Number> locator =
                            BaseLocator.inputRegister(slaveId, start + i, DataType.TWO_BYTE_INT_UNSIGNED);
                    Number val = master.getValue(locator);
                    data[i] = (val != null) ? val.intValue() : 0;
                }
                break;

            case "coil":
                for (int i = 0; i < len; i++) {
                    BaseLocator<Boolean> locator =
                            BaseLocator.coilStatus(slaveId, start + i);
                    Boolean val = master.getValue(locator);
                    data[i] = (val != null && val) ? 1 : 0;
                }
                break;

            case "discrete":
                for (int i = 0; i < len; i++) {
                    BaseLocator<Boolean> locator =
                            BaseLocator.inputStatus(slaveId, start + i);
                    Boolean val = master.getValue(locator);
                    data[i] = (val != null && val) ? 1 : 0;
                }
                break;

            default:
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }

        return data;
    }

    public void write(String type, int slaveId, int address, int value) throws Exception {
        if (!connected) throw new IllegalStateException("Não conectado ao Modbus");

        switch (type) {
            case "holding":
                BaseLocator<Number> holdingLocator =
                        BaseLocator.holdingRegister(slaveId, address, DataType.TWO_BYTE_INT_UNSIGNED);
                master.setValue(holdingLocator, value);
                break;

            case "coil":
                BaseLocator<Boolean> coilLocator =
                        BaseLocator.coilStatus(slaveId, address);
                master.setValue(coilLocator, value != 0);
                break;

            default:
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }

    public synchronized void close() {
        if (master != null) {
            master.destroy();
            connected = false;
        }
    }
}

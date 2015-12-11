package view.modbusserver;

import com.serotonin.modbus4j.base.ModbusUtils;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.exception.IllegalDataTypeException;
import com.serotonin.modbus4j.exception.ModbusIdException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;

public class ModbusLocator {
    private static final int[] DATA_TYPES = { //
            DataType.TWO_BYTE_INT_UNSIGNED, //
            DataType.TWO_BYTE_INT_SIGNED, //
            DataType.FOUR_BYTE_INT_UNSIGNED, //
            DataType.FOUR_BYTE_INT_SIGNED, //
            DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED, //
            DataType.FOUR_BYTE_INT_SIGNED_SWAPPED, //
            DataType.FOUR_BYTE_FLOAT, //
            DataType.FOUR_BYTE_FLOAT_SWAPPED, //
            DataType.EIGHT_BYTE_INT_UNSIGNED, //
            DataType.EIGHT_BYTE_INT_SIGNED, //
            DataType.EIGHT_BYTE_INT_UNSIGNED_SWAPPED, //
            DataType.EIGHT_BYTE_INT_SIGNED_SWAPPED, //
            DataType.EIGHT_BYTE_FLOAT, //
            DataType.EIGHT_BYTE_FLOAT_SWAPPED, //
            DataType.TWO_BYTE_BCD, //
            DataType.FOUR_BYTE_BCD, //
            DataType.FOUR_BYTE_BCD_SWAPPED, //
    };

    private int dataType;
    private int range;
    private byte[] data;

    public ModbusLocator(int range, byte[] data) {
        this.range = range;
        this.data = data;
    }

    private static void appendBCD(StringBuilder sb, byte b) {
        sb.append(bcdNibbleToInt(b, true));
        sb.append(bcdNibbleToInt(b, false));
    }

    private static int bcdNibbleToInt(byte b, boolean high) {
        int n;
        if (high)
            n = (b >> 4) & 0xf;
        else
            n = b & 0xf;
        if (n > 9)
            n = 0;
        return n;
    }

    private int getRegisterCount() {
        switch (dataType) {
            case DataType.TWO_BYTE_INT_UNSIGNED:
            case DataType.TWO_BYTE_INT_SIGNED:
            case DataType.TWO_BYTE_BCD:
                return 1;
            case DataType.FOUR_BYTE_INT_UNSIGNED:
            case DataType.FOUR_BYTE_INT_SIGNED:
            case DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED:
            case DataType.FOUR_BYTE_INT_SIGNED_SWAPPED:
            case DataType.FOUR_BYTE_FLOAT:
            case DataType.FOUR_BYTE_FLOAT_SWAPPED:
            case DataType.FOUR_BYTE_BCD:
            case DataType.FOUR_BYTE_BCD_SWAPPED:
                return 2;
            case DataType.EIGHT_BYTE_INT_UNSIGNED:
            case DataType.EIGHT_BYTE_INT_SIGNED:
            case DataType.EIGHT_BYTE_INT_UNSIGNED_SWAPPED:
            case DataType.EIGHT_BYTE_INT_SIGNED_SWAPPED:
            case DataType.EIGHT_BYTE_FLOAT:
            case DataType.EIGHT_BYTE_FLOAT_SWAPPED:
                return 4;
        }
        throw new RuntimeException("Unsupported data type: " + dataType);
    }

    public Number getValue(int offset, int dataType) {
        this.dataType = dataType;
        offset *= 2;

        if (range == RegisterRange.COIL_STATUS || range == RegisterRange.INPUT_STATUS)
            throw new IllegalDataTypeException("Only binary values can be read from Coil and Input ranges");

        if (!ArrayUtils.contains(DATA_TYPES, dataType))
            throw new IllegalDataTypeException("Invalid data type");

        try {
            ModbusUtils.validateOffset(offset);
            ModbusUtils.validateEndOffset(offset + getRegisterCount() - 1);
        } catch (ModbusTransportException e) {
            throw new ModbusIdException(e);
        }

        // 2 bytes
        if (dataType == DataType.TWO_BYTE_INT_UNSIGNED)
            return new Integer(((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == DataType.TWO_BYTE_INT_SIGNED)
            return new Short((short) (((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff)));

        if (dataType == DataType.TWO_BYTE_BCD) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            return Short.parseShort(sb.toString());
        }

        // 4 bytes
        if (dataType == DataType.FOUR_BYTE_INT_UNSIGNED)
            return new Long(((long) ((data[offset] & 0xff)) << 24) | ((long) ((data[offset + 1] & 0xff)) << 16)
                    | ((long) ((data[offset + 2] & 0xff)) << 8) | ((data[offset + 3] & 0xff)));

        if (dataType == DataType.FOUR_BYTE_INT_SIGNED)
            return new Integer(((data[offset] & 0xff) << 24) | ((data[offset + 1] & 0xff) << 16)
                    | ((data[offset + 2] & 0xff) << 8) | (data[offset + 3] & 0xff));

        if (dataType == DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED)
            return new Long(((long) ((data[offset + 2] & 0xff)) << 24) | ((long) ((data[offset + 3] & 0xff)) << 16)
                    | ((long) ((data[offset] & 0xff)) << 8) | ((data[offset + 1] & 0xff)));

        if (dataType == DataType.FOUR_BYTE_INT_SIGNED_SWAPPED)
            return new Integer(((data[offset + 2] & 0xff) << 24) | ((data[offset + 3] & 0xff) << 16)
                    | ((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == DataType.FOUR_BYTE_FLOAT)
            return Float.intBitsToFloat(((data[offset] & 0xff) << 24) | ((data[offset + 1] & 0xff) << 16)
                    | ((data[offset + 2] & 0xff) << 8) | (data[offset + 3] & 0xff));

        if (dataType == DataType.FOUR_BYTE_FLOAT_SWAPPED)
            return Float.intBitsToFloat(((data[offset + 2] & 0xff) << 24) | ((data[offset + 3] & 0xff) << 16)
                    | ((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == DataType.FOUR_BYTE_BCD) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            appendBCD(sb, data[offset + 2]);
            appendBCD(sb, data[offset + 3]);
            return Integer.parseInt(sb.toString());
        }

        if (dataType == DataType.FOUR_BYTE_BCD_SWAPPED) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset + 2]);
            appendBCD(sb, data[offset + 3]);
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            return Integer.parseInt(sb.toString());
        }

        // 8 bytes
        if (dataType == DataType.EIGHT_BYTE_INT_UNSIGNED) {
            byte[] b9 = new byte[9];
            System.arraycopy(data, offset, b9, 1, 8);
            return new BigInteger(b9);
        }

        if (dataType == DataType.EIGHT_BYTE_INT_SIGNED)
            return new Long(((long) ((data[offset] & 0xff)) << 56) | ((long) ((data[offset + 1] & 0xff)) << 48)
                    | ((long) ((data[offset + 2] & 0xff)) << 40) | ((long) ((data[offset + 3] & 0xff)) << 32)
                    | ((long) ((data[offset + 4] & 0xff)) << 24) | ((long) ((data[offset + 5] & 0xff)) << 16)
                    | ((long) ((data[offset + 6] & 0xff)) << 8) | ((data[offset + 7] & 0xff)));

        if (dataType == DataType.EIGHT_BYTE_INT_UNSIGNED_SWAPPED) {
            byte[] b9 = new byte[9];
            b9[1] = data[offset + 6];
            b9[2] = data[offset + 7];
            b9[3] = data[offset + 4];
            b9[4] = data[offset + 5];
            b9[5] = data[offset + 2];
            b9[6] = data[offset + 3];
            b9[7] = data[offset];
            b9[8] = data[offset + 1];
            return new BigInteger(b9);
        }

        if (dataType == DataType.EIGHT_BYTE_INT_SIGNED_SWAPPED)
            return new Long(((long) ((data[offset + 6] & 0xff)) << 56) | ((long) ((data[offset + 7] & 0xff)) << 48)
                    | ((long) ((data[offset + 4] & 0xff)) << 40) | ((long) ((data[offset + 5] & 0xff)) << 32)
                    | ((long) ((data[offset + 2] & 0xff)) << 24) | ((long) ((data[offset + 3] & 0xff)) << 16)
                    | ((long) ((data[offset] & 0xff)) << 8) | ((data[offset + 1] & 0xff)));

        if (dataType == DataType.EIGHT_BYTE_FLOAT)
            return Double.longBitsToDouble(((long) ((data[offset] & 0xff)) << 56)
                    | ((long) ((data[offset + 1] & 0xff)) << 48) | ((long) ((data[offset + 2] & 0xff)) << 40)
                    | ((long) ((data[offset + 3] & 0xff)) << 32) | ((long) ((data[offset + 4] & 0xff)) << 24)
                    | ((long) ((data[offset + 5] & 0xff)) << 16) | ((long) ((data[offset + 6] & 0xff)) << 8)
                    | ((data[offset + 7] & 0xff)));

        if (dataType == DataType.EIGHT_BYTE_FLOAT_SWAPPED)
            return Double.longBitsToDouble(((long) ((data[offset + 6] & 0xff)) << 56)
                    | ((long) ((data[offset + 7] & 0xff)) << 48) | ((long) ((data[offset + 4] & 0xff)) << 40)
                    | ((long) ((data[offset + 5] & 0xff)) << 32) | ((long) ((data[offset + 2] & 0xff)) << 24)
                    | ((long) ((data[offset + 3] & 0xff)) << 16) | ((long) ((data[offset] & 0xff)) << 8)
                    | ((data[offset + 1] & 0xff)));

        throw new RuntimeException("Unsupported data type: " + dataType);
    }
}

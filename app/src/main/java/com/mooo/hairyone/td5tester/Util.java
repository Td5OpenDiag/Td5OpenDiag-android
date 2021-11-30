package com.mooo.hairyone.td5tester;

import com.mooo.hairyone.td5tester.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public  class Util {

    public static String byte_array_to_hex(byte[] data, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(String.format("%02X ", data[i] & 0xFF));
        }
        return sb.toString();
    }

    public static String integer_to_binary(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    public static short bytes2short(byte hi_byte, byte lo_byte) {
        // https://stackoverflow.com/questions/736815/2-bytes-to-short-java
        // https://henkelmann.eu/2011/02/a-curse-on-java-bitwise-operators/
        return (short) (hi_byte << 8 | lo_byte & 0xFF);
    }

    public static void log_msg(String msg) {
        EventBus.getDefault().post(new MessageEvent(msg));
    }


    public static int get_bit(byte value, int position)
    {
        return (value >> position) & 1;
    }

}

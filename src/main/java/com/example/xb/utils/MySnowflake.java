package com.example.xb.utils;

public class MySnowflake {
    /** 开始时间戳 */
    private final long startTimestamp = 1600758933000L;
    /** 机房ID */
    private long roomId;
    /** 机器ID */
    private long workId;
    /** 序列号 */
    private long serialNum;
    /** 机房ID 位长度 */
    private final long roomIdBits = 5L;
    /** 机器ID 位长度 */
    private final long workIdBits = 5L;
    /** 序列号 位长度 */
    private final long serialNumBits = 12L;
    /** 时间戳左移位数 */
    private final long timestampShiftBits = roomIdBits + workIdBits + serialNumBits;
    /** 机房ID左移位数 */
    private final long roomIdShiftBits = workIdBits + serialNumBits;
    /** 机器ID左移位数 */
    private final long workIdShiftBits = serialNumBits;
    /** 机房ID最大值 (-1L ^ (-1L << roomIdBits)) */
    private final long roomIdMax = 31L;
    /** 机器ID最大值 (-1L ^ (-1L << workIdMax)) */
    private final long workIdMax = 31L;
    /** 序列号最大值 (-1L ^ (-1L << serialNumBits)) */
    private final long serialNumMax = 4095L;
    /** 最后一次生成uuid的时间 */
    private long lastTimestamp = -1L;

    public MySnowflake(long roomId, long workId) {
        if (roomId < -1L || roomId > roomIdMax) {
            throw new RuntimeException("机房ID无效!");
        }
        if (workId < -1L || workId > workIdMax) {
            throw new RuntimeException("机器ID无效!");
        }

        this.roomId = roomId;
        this.workId = workId;
    }

    public synchronized long getUUID() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("当前时间戳比上次生成UUID的时间戳小!");
        }
        // 如果同一时刻同时收到另外一个生成请求，序列号就+1
        if (currentTimestamp == lastTimestamp) {
            // 如果累加值大于序列号最大值的时候就变为0,等到下一毫秒再生成
            serialNum = (serialNum + 1) & serialNumMax;
            if (0 == serialNum) {
                while (currentTimestamp <= lastTimestamp) {
                    currentTimestamp = System.currentTimeMillis();
                }
            }
        } else {
            serialNum = 0L;
        }

        lastTimestamp = currentTimestamp;
        long uuid = ((currentTimestamp - startTimestamp) << timestampShiftBits)
                | (roomId << roomIdShiftBits)
                | (workId << workIdShiftBits)
                | serialNum;
        return uuid;
    }
//
//    public static void main(String[] args) {
//        // 机房ID为30，机器ID为30
//        MySnowflake snowflake = new MySnowflake(30, 30);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(snowflake.getUUID());
//        }
//    }
}

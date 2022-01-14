package ink.akira.utils.client.snowflake;

import com.google.common.base.Preconditions;

import java.util.Date;

/**
 * Created by Allen on 2018/6/1.
 */
public class SnowFlakeIdGenerator implements ISnowFlakeIdGenerator {
    //   id format  =>
    //   timestamp |idType     | workerId | sequence
    //   41        |4          |  10      | 8
    public static final int TIME_STAMP_BITS = 41;
    public static final int ID_TYPE_BITS = 4;
    public static final int WORKER_ID_BITS = 10;
    public static final int SEQUENCE_BITS = 8;
    public static final long TIME_EPOCH = 1262275200000L; //Fri Jan 01 00:00:00 CST 2010

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private IdType idType;
    private int workerId;

    public SnowFlakeIdGenerator(IdType idType, int workerId) {
        Preconditions.checkNotNull(idType, "IdType should not be null");
        Preconditions.checkArgument(idType.getId() >= 0 && idType.getId() < 1 << ID_TYPE_BITS, String.format("IdType.id invalid, it should be in [0,%s)", 1 << ID_TYPE_BITS));
        Preconditions.checkArgument(workerId >= 0 && workerId < 1 << WORKER_ID_BITS, String.format("workerId invalid, it should be in [0,%s)", 1 << WORKER_ID_BITS));
        this.idType = idType;
        this.workerId = workerId;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Override
    public synchronized long generateLongId() {
        long timestamp = System.currentTimeMillis();
        // TODO 时钟回调处理是否可优化
        if (timestamp < lastTimestamp) {
            throw new InvalidSystemClockException("Clock moved backwards.  Refusing to generate id for " + (
                    lastTimestamp - timestamp) + " milliseconds.");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) / (1 << SEQUENCE_BITS);
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - TIME_EPOCH) << (ID_TYPE_BITS + WORKER_ID_BITS + SEQUENCE_BITS)) |
                (idType.getId() << (WORKER_ID_BITS + SEQUENCE_BITS)) |
                (workerId << SEQUENCE_BITS) |
                sequence;
    }

    @Override
    public Date getDate(long id) {
        long timDiff = id >> ID_TYPE_BITS + WORKER_ID_BITS + SEQUENCE_BITS;
        return new Date(TIME_EPOCH + timDiff);
    }

    @Override
    public IdType getIdType(long id) {
        int typeId = (int) ((id >> WORKER_ID_BITS + SEQUENCE_BITS) & ((1 << ID_TYPE_BITS) - 1));
        return IdType.getById(typeId);
    }

    @Override
    public int getWorkerId(long id) {
        return (int) ((id >> SEQUENCE_BITS) & ((1 << WORKER_ID_BITS) - 1));
    }

    @Override
    public int getSequence(long id) {
        return (int) (id & (1 << SEQUENCE_BITS) - 1);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}

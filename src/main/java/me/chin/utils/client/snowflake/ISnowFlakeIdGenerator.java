package me.chin.utils.client.snowflake;

import java.util.Date;

/**
 * Created by Allen on 2018/6/1.
 */
public interface ISnowFlakeIdGenerator {
    long generateLongId();
    Date getDate(long id);
    IdType getIdType(long id);
    int getWorkerId(long id);
    int getSequence(long id);
}

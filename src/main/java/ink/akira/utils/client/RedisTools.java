package ink.akira.utils.client;

import redis.clients.jedis.Jedis;

/**
 * Created by Allen on 2018/6/21.
 */
public class RedisTools {
    public static final long MILLISECONDS_IN_SECONDS = 1000L;

    public boolean getLock(Jedis jedis, String lockKey, String identifier) {
        return !(jedis == null || lockKey == null) && jedis.setnx(lockKey, identifier) == 1L;
    }

    public boolean getLock(Jedis jedis, String lockKey, String identifier, int lockTimeoutSeconds) {
        if (jedis == null || lockKey == null) return false;
        Long result = jedis.setnx(lockKey, identifier);
        if (result == 1L) {
            jedis.expire(lockKey, lockTimeoutSeconds);
            return true;
        }
        return false;
    }

    public boolean tryLock(Jedis jedis, String lockKey, String identifier, int tryTimeSeconds) {
        if (jedis == null || lockKey == null) return false;
        long end = System.currentTimeMillis() + tryTimeSeconds * MILLISECONDS_IN_SECONDS;
        while (System.currentTimeMillis() < end) {
            if (jedis.setnx(lockKey, identifier) == 1L) {
                return true;
            }
        }
        return false;
    }

    public boolean tryLock(Jedis jedis, String lockKey, String identifier, int tryTimeSeconds, int lockTimeoutSeconds) {
        if (jedis == null || lockKey == null) return false;
        long end = System.currentTimeMillis() + tryTimeSeconds * MILLISECONDS_IN_SECONDS;
        while (System.currentTimeMillis() < end) {
            Long result = jedis.setnx(lockKey, identifier);
            if (result == 1L) {
                jedis.expire(lockKey, lockTimeoutSeconds);
                return true;
            }
        }
        return false;
    }

    public boolean delLock(Jedis jedis, String lockKey) {
        return !(jedis == null || lockKey == null) && jedis.del(lockKey) > 0;
    }

}

package test.utils;

import me.chin.utils.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by ChenHouZhang on 2017/8/10.
 */
public class JedisUtilTest {
    @Test
    public void testJedis(){
        Jedis jedis = JedisUtil.getJedis();
        String firstKey = jedis.get("firstKey");
        System.out.println("firstKey = " + firstKey);
        Jedis jedis1 = JedisUtil.getJedis();
        Jedis jedis2 = JedisUtil.getJedis();
        Jedis jedis3 = JedisUtil.getJedis();
        Jedis jedis4 = JedisUtil.getJedis();
        Jedis jedis5 = null;
        int i = JedisUtil.closeReturnJedis(jedis, jedis1, jedis2, jedis3, jedis4,jedis,jedis);
        System.out.println("i = " + i);
        /*System.out.println("++++++++++++");
        Jedis jedisFromPool = JedisUtil.getJedisFromPool();
        String firstKey = jedisFromPool.get("firstKey");
        System.out.println("firstKey = " + firstKey);
        Jedis jedisFromPool1 = JedisUtil.getJedisFromPool();
        Jedis jedisFromPool2= JedisUtil.getJedisFromPool();
        Jedis jedisFromPool3 = JedisUtil.getJedisFromPool();
        Jedis jedisFromPool4 = JedisUtil.getJedisFromPool();
        int i1 = JedisUtil.closeReturnJedis(jedisFromPool, jedisFromPool1, jedisFromPool2, jedisFromPool3, jedisFromPool4);
        System.out.println("i1 = " + i1);*/
    }
}

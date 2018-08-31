package me.chin.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by ChenHouZhang on 2017/8/10.
 */
public class JedisUtil {
    private static String host;
    private static int port;
    private static String pass;
    private static int dataNum;
    private static JedisPool pool;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("redis");//配置文件相对工程根目录的相对路径（不含扩展名）
        host = rb.getString("redis.host");
        port = Integer.parseInt(rb.getString("redis.port"));
        pass = rb.getString("redis.auth");
        dataNum = Integer.parseInt(rb.getString("redis.data.num"));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(rb.getString("redis.pool.maxIdle")));
        config.setMinIdle(Integer.parseInt(rb.getString("redis.pool.minIdle")));
        config.setMaxWaitMillis(Long.parseLong(rb.getString("redis.pool.maxWait")));
        config.setMaxTotal(Integer.parseInt(rb.getString("redis.pool.maxActive")));
        pool = new JedisPool(config,host,port, Integer.parseInt(rb.getString("redis.pool.connectionTimeout")),pass);
    }

    /**
     * 获取单一jedis
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = new Jedis(host, port);
        if (null != pass && !"".equals(pass))
            jedis.auth(pass);
        jedis.select(dataNum);
        return jedis;
    }

    /**
     * 从连接池中获取jedis
     * @return
     */
    public static Jedis getJedisFromPool(){
        Jedis jedis = null;
        if (pool != null){
            jedis = pool.getResource();
            jedis.select(dataNum);
        }
        return jedis;
    }

    /**
     * 使用连接池或不使用连接池都使用这个方法
     * @param jedises
     * @return
     */
    public static int closeReturnJedis(Jedis... jedises){
        int n = 0;
        for (Jedis jedis : jedises) {
            if (null != jedis){
                jedis.close();
                n++;
            }
        }
        return n;
    }
}

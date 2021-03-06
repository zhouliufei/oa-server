package com.fanfan.Util;
import com.fanfan.RedisConfigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class RedisUtil {

    private static final Integer EXPIRE = 3600 * 24 * 7;

    @Autowired
    private RedisConfigration redisConfigration;

    private JedisPool jedisPool;

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @PostConstruct
    public void init() {
        jedisPool = redisConfigration.redisPoolFactory();
    }


    //存数据
    public void set(String key,Integer expire,String value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            if(expire == null) {
                jedis.setex(StringUtil.TOKEN_PREFIX + key,EXPIRE,value);
            } else {
                jedis.setex(StringUtil.TOKEN_PREFIX + key,expire,value);
            }
        }catch (Exception e) {
            logger.info("新增redis数据出错");
            e.printStackTrace();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
    //取数据
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try{
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.info("获取redis数据出错");
            e.printStackTrace();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    //删除数据
    public void delete(String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            logger.info("删除redis数据出错");
            e.printStackTrace();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public void expireTime(String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.expire(key,EXPIRE);
        }catch (Exception e) {
            logger.info("更新redis数据出错");
            e.printStackTrace();
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    //jedis 加入数据到set中
    public long addToSet(String key,String value) {
        Jedis jedis = null;
        Long num = null;
        try{
            jedis = jedisPool.getResource();
            num = jedis.sadd(key,value);
        }catch (Exception e) {
            logger.info("插入数据到set出错");
            e.printStackTrace();
        }finally {
            if(jedis != null) {
                jedis.close();
            }
            return num.longValue();
        }
    }

    //jedis查看数据是否在set中
    public boolean sismember(String key,String value) {
        Jedis jedis = null;
        Boolean exist = null;
        try{
            jedis = jedisPool.getResource();
            exist = jedis.sismember(key,value);
        }catch (Exception e) {
            logger.info("查看数据是否在set中出错");
            e.printStackTrace();
        }finally {
            if(jedis != null) {
                jedis.close();
            }
            return exist;
        }
    }

    //jedis 从set中移除数据
    public long srem(String key,String value) {
        Jedis jedis = null;
        Long num = null;
        try{
            jedis = jedisPool.getResource();
            num = jedis.srem(key,value);
        }catch (Exception e) {
            logger.info("删除set数据出错");
            e.printStackTrace();
        }finally {
            if(jedis != null) {
                jedis.close();
            }
            return num.longValue();
        }
    }

    //jedis 取出set中所有数据
    public Set<String> queryAllFromSet(String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.smembers(key);
        }catch (Exception e) {
            logger.info("获取redis数据出错");
            e.printStackTrace();
        }finally {
            if(jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

}

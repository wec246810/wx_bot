package com.ysk.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ysk.entity.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by Y.S.K on 2017/8/6 in wx_bot.
 */
//优化点:缓存优化
//    get from cache
//        if null
//            get db
//        else
//    put cache

public class RedisDao {
    //    private final Logger logger=new Logger();
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);

    public User getUser(String username) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:" + username;
                //采用自定义序列化
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    User user = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, user, schema);
                    //user被反序列化
                    return user;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putUser(User user) {
        //序列化的过程
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:" + user.getUserName();
                byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));//缓存器
                //超时缓存
                int timeout = 6*6;//1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                System.out.println("-----------------------------result------"+result);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

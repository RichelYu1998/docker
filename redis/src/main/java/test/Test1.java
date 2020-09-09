package test;

import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import java.util.LinkedList;
import java.util.List;

public class Test1 {
    @Test
    public void test1() {
        JedisPoolConfig cfg = new JedisPoolConfig();
        List<JedisShardInfo> shards = new LinkedList<JedisShardInfo>();
        shards.add(new JedisShardInfo("192.168.64.150", 7000));
        shards.add(new JedisShardInfo("192.168.64.150", 7001));
        shards.add(new JedisShardInfo("192.168.64.150", 7002));
        ShardedJedisPool pool = new ShardedJedisPool(cfg, shards);
        ShardedJedis j = pool.getResource();
        for (int i = 0; i < 100; i++) {
            j.set("key"+i, "value"+i);
        }
        pool.close();
    }
}

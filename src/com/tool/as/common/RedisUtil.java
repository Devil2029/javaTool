package com.tool.as.common;

import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 缓存工具
 * 
 * @author lp
 */
public class RedisUtil {

	private static final Logger log = Logger.getLogger(RedisUtil.class);
	//
	// private static String HOST = "118.178.230.113";
	// private static int PORT = 6379;
	// private static int MAX_ACTIVE = 1024;
	// private static int MAX_IDLE = 200;
	// private static int MAX_WAIT = 10000;
	// private static String PASSWORD = "123456";

	private static JedisPool jedisPool = null;

	/*
	 * 初始化redis连接池
	 */
	private static Jedis initPool(String HOST, int PORT, int MAX_ACTIVE,
			int MAX_IDLE, int MAX_WAIT, String PASSWORD) {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);// 最大连接数
			config.setMaxIdle(MAX_IDLE);// 最大空闲连接数
			config.setMaxWaitMillis(MAX_WAIT);// 获取可用连接的最大等待时间
			jedisPool = new JedisPool(config, HOST, PORT);
			Jedis jedis = jedisPool.getResource();
			jedis.auth(PASSWORD);// 密码
			return jedis;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 放入数据
	 * 
	 * @param key
	 *            键
	 * @param val
	 *            值
	 */
	public synchronized static void put(String key, String val, String HOST,
			int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.set(key, val);
			jedis.close();
		} catch (Exception e) {
			log.error("redis放入数据到中错误", e);
		}
	}

	/**
	 * 放入数据
	 * 
	 * @param key
	 *            键
	 * @param val
	 *            值
	 * @param expire
	 *            过期时间 （ 单位为 秒）
	 */
	public synchronized static void putTime(String key, String val, int expire,
			String HOST, int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.expire(key, expire);
			jedis.close();
		} catch (Exception e) {
			log.error("redis放入数据到中错误", e);
		}
	}

	/**
	 * 取数据集合
	 * 
	 * @param key
	 *            键
	 * @return
	 */

	public synchronized static Set<String> getList(String key, String HOST,
			int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Set<String> account = null;
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			account = jedis.keys(key);
			jedis.close();
		} catch (Exception e) {
			log.error("redis取数据错误", e);
		}
		return account;
	}

	/**
	 * 取数据
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public synchronized static String get(String key, String HOST, int PORT,
			int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT, String PASSWORD) {
		String account = "";
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			account = jedis.get(key);
			jedis.close();
		} catch (Exception e) {
			log.error("redis取数据错误", e);
		}
		return account;
	}

	/**
	 * 判断 key 是否存在
	 * 
	 * @param key
	 *            键
	 * @return true:存在，false:不存在
	 */
	public synchronized static boolean isExist(String key, String HOST,
			int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		boolean bool = false;
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			bool = jedis.exists(key);
			jedis.close();
		} catch (Exception e) {
			log.error("redis判断 key是否存在错误", e);
		}
		return bool;
	}

	/**
	 * 移除数据
	 * 
	 * @param key
	 */
	public synchronized static void remove(String key, String HOST, int PORT,
			int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT, String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			log.error("redis移除数据错误", e);
		}

	}

	/**
	 * 追加数据
	 * 
	 * @param key
	 * @param val
	 */
	public synchronized static void append(String key, String val, String HOST,
			int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.append(key, val);
			jedis.close();
		} catch (Exception e) {
			log.error("redis追加数据错误", e);
		}

	}

	/**
	 * 修改键名
	 * 
	 * @param oldKey
	 * @param newKey
	 */
	public synchronized static void rename(String oldKey, String newKey,
			String HOST, int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.rename(oldKey, newKey);
			jedis.close();
		} catch (Exception e) {
			log.error("redis修改键名错误", e);
		}

	}

	/**
	 * 过期时间
	 * 
	 * @param Key
	 *            键
	 * @param sec
	 *            时间，单位为 秒
	 */
	public synchronized static void expire(String Key, int sec, String HOST,
			int PORT, int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT,
			String PASSWORD) {
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			jedis.expire(Key, sec);
		} catch (Exception e) {
			log.error("redis过期时间错误", e);
		}
	}

	/**
	 * 取数据
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public synchronized static byte[] getObj(byte[] key, String HOST, int PORT,
			int MAX_ACTIVE, int MAX_IDLE, int MAX_WAIT, String PASSWORD) {
		byte[] account = null;
		Jedis jedis = null;
		try {
			if (jedisPool == null) {
				jedis = initPool(HOST, PORT, MAX_ACTIVE, MAX_IDLE, MAX_WAIT,
						PASSWORD);
			} else {
				jedis = jedisPool.getResource();
			}
			account = jedis.get(key);
			jedis.close();
		} catch (Exception e) {
			log.error("redis取数据错误", e);
		}
		return account;
	}
}

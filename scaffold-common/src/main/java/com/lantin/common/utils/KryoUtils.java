package com.lantin.common.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.util.Pool;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Kryo 二进制序列化工具
 * 使用池化技术线程安全
 *
 * @author Gan Luanqing
 * @date 2021/12/12 1:21 周日
 */
@Slf4j
public class KryoUtils {

	private static final Pool<Kryo> kryoPool = new Pool<>(true, false, 1024) {
		@Override
		protected Kryo create() {
			return createKryo();
		}
	};
	private final Pool<Input> inputPool = new Pool<>(true, false, 512) {
		@Override
		protected Input create() {
			return new Input(8192);
		}
	};
	private final Pool<Output> outputPool = new Pool<>(true, false, 512) {
		@Override
		protected Output create() {
			return new Output(8192, -1);
		}
	};


	private static Kryo createKryo() {
		Kryo kryo = new Kryo();

		kryo.setRegistrationRequired(false);
		kryo.setReferences(false);
		kryo.addDefaultSerializer(Throwable.class, new JavaSerializer());
		return kryo;
	}

	public static <T> byte[] serialize(T obj) {
		if (obj != null) {
			Kryo kryo = kryoPool.obtain();
			try (ByteArrayOutputStream os = new ByteArrayOutputStream();
			     Output output = new Output(os)) {
				kryo.writeObject(output, obj);
				output.flush();
				return os.toByteArray();
			} catch (IOException e) {
				log.warn("Kryo序列化失败", e);
			} finally {
				kryoPool.free(kryo);
			}
		}
		return null;
	}

	/**
	 * 把byte[]反序列化成指定的java对象
	 *
	 * @param bytes   字节数组
	 * @param objType 指定的java对象
	 * @return 指定的java对象
	 */
	public static <T> T deserialize(byte[] bytes, Class<T> objType) {

		if (null != bytes && bytes.length > 0 && null != objType) {
			Kryo kryo = kryoPool.obtain();
			try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			     Input input = new Input(is)) {
				return kryo.readObject(input, objType);
			} catch (Exception e) {
				log.warn("Kryo反序列化失败", e);
			} finally {
				kryoPool.free(kryo);
			}
		}
		return null;
	}

	/**
	 * 把byte[]反序列化成指定的java对象List
	 *
	 * @param bytes 字节数组
	 * @param eType 元素的类型
	 * @return 指定的java对象List
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> deserializeList(byte[] bytes, Class<T> eType) {

		if (null != bytes && bytes.length > 0 && null != eType) {
			Kryo kryo = kryoPool.obtain();
			try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			     Input input = new Input(is)) {
				return kryo.readObject(input, ArrayList.class);
			} catch (Exception e) {
				log.warn("Kryo反序列化失败", e);
			} finally {
				kryoPool.free(kryo);
			}
		}
		return new ArrayList<>();
	}

}

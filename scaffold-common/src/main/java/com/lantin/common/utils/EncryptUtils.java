package com.lantin.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class EncryptUtils {

	private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static MessageDigest messagedigest = null;

	private static final String MD5 = "MD5";
	private static final String SHA_1 = "SHA-1";
	private static final String SHA_256 = "SHA-256";

	public static String getMD5(String str){
		try {
			return getByAlgorithmAndByte(MD5,str.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String getSHA1(String str){
		try {
			return getByAlgorithmAndByte(SHA_1,str.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}


	/**
	 * 对文件进行MD5
	 *
	 * @param file 文件
	 * @return 加密code
	 */
	public static String getMD5(File file) throws IOException {
		try {
			return getByAlgorithmAndFile(MD5, file);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String getSHA1(File file) throws IOException {
		try {
			return getByAlgorithmAndFile(SHA_1, file);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String getSHA256(File file) throws IOException {
		try {
			return getByAlgorithmAndFile(SHA_256, file);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	public static String getCRC32(File file) {
		CRC32 crc32 = new CRC32();
		// MessageDigest.get
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				crc32.update(buffer, 0, length);
			}
			return crc32.getValue() + "";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getByAlgorithmAndByte(String algorithm, byte[] bytes) throws NoSuchAlgorithmException {
		messagedigest = MessageDigest.getInstance(algorithm);
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String getByAlgorithmAndFile(String algorithm, File file) throws NoSuchAlgorithmException, IOException {
		messagedigest = MessageDigest.getInstance(algorithm);
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}





	private static String bufferToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (byte x : bytes) {
			appendHexPair(x, sb);
		}
		return sb.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		//高4位
		char c0 = hexDigits[(bt&0xf0) >> 4];
		//低4位
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static void main(String[] args) throws IOException {
		File file = new File("/Users/lantin/Desktop/test.log");
		String md5 = getMD5(file);
		String sha1 = getSHA1(file);
		String sha256 = getSHA256(file);
		String crc32 = getCRC32(file);
		System.out.println("code:"+md5);
		System.out.println("code:"+sha1);
		System.out.println("code:"+sha256);
		System.out.println("code:"+crc32);


		// String str = "abdfda";
		//
		// String s = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
		// String md51 = getMD5(str);
		// System.out.println(md51);
		// System.out.println(s.equals(md51));

	}
}
package com.tool.as.common;

/**
 * @desc base64 转码和解码工具（在http协议下，传输视频文件或者音频文件）
 * @author 罗盼
 * 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.sun.org.apache.xml.internal.security.utils.Base64;


public class FileUtil {

	public static void main(String[] args) throws Exception{
		String filepath = "C:/a.jpg";
		System.out.println("file length:"+new File(filepath).length());
		String str = encode2(filepath);
		System.out.println(str);
		System.out.println(str.length());
		decode2("C:/caiwenhong111.jpg",str);
	}
	/*************************************第一种方法发现有问题，请勿用，请验证下*********************************************/
	/** 
	 * 有换行问题（我已验证）
	 * 转码  -把文件 转为密码
	 * @param filepath  文件路径
	 * @return  
	 */
	public static String encode(String filepath) throws Exception{
		FileInputStream fis =new FileInputStream(filepath);
		byte[] bs = new byte[(int)new File(filepath).length()];
		fis.read(bs);
		return Base64.encode(bs);
	}
	

	
	/**   解码
	 * @param filepath 文件路径
	 * @param str 
	 */
	public static void decode(String filepath,String str) throws Exception{
		byte[] bs = Base64.decode(str);
		FileOutputStream fos =new FileOutputStream(filepath);
		fos.write(bs);
		fos.close();

	}
	/*************************************第二种方法更好（解决了换行的问题）*********************************************/
	
	/** 可以解决 换行问题（我已验证）
	 *  转码  -把文件 转为密码
	 * @param filepath  文件路径
	 * @return  
	 */
	public static String encode2(String filepath) throws Exception{
		FileInputStream fis =new FileInputStream(filepath);
		byte[] bs = new byte[(int)new File(filepath).length()];
		fis.read(bs);
		return org.apache.commons.codec.binary.Base64.encodeBase64String(bs);
	}
	
	
	/**   解码
	 * @param filepath 文件路径
	 * @param str 
	 */
	public static void decode2(String filepath,String str) throws Exception{
		byte[] bs = org.apache.commons.codec.binary.Base64.decodeBase64(str);
		FileOutputStream fos =new FileOutputStream(filepath);
		fos.write(bs);
		fos.close();

	}

}



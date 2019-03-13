package com.tool.as.common;




import java.util.Random;
import java.util.UUID;

public class RandomUtil {
	public static void main(String[] args) { 
//		  java.util.Random r=new java.util.Random(100); 
		for(int i =0;i<12;i++){
			System.out.println(getUUID());
		}
//		System.out.println(System.currentTimeMillis());
//		System.out.println((System.currentTimeMillis()+"").length());
	}
	/**
	 * 1/max的几率
	 * @param max
	 * @return
	 */
	public static boolean isBingo(int max){
		
	     int min=1;
	     Random random = new Random();
	     int s = random.nextInt(max)%(max-min+1) + min;
	     if(s == max){
	    	 return true;
	     }else
	    	 return false;
	}
	/**
	 * range/max 的几率
	 * @param max
	 * @param range
	 * @return
	 */
	public static boolean isBingoRange(int max,int range){
		
	     int min=1;
	     Random random = new Random();
	     int s = random.nextInt(max)%(max-min+1) + min;
	     if(s <= range){
	    	 return true;
	     }else
	    	 return false;
	}
	/**
	 * 获得min（包括）到max（不包括）的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNumber(int min,int max){
	     Random random = new Random();
	     return random.nextInt(max)%(max-min+1) + min;
	}
	/**
	 * 获取随机数
	 * @return
	 */
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成验证码
	 * @param length
	 * @return
	 */
	public static int generateNum(int length){
		return (int)((Math.random()*9+1)*Math.pow(10,length-1));
	}
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }  
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) { //length表示生成字符串的长度  
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
}

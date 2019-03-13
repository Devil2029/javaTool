package com.tool.as.common;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
/**
 * @description:上传图片工具类
 * @author lp
 */
public class UpLoadPicsUtil {
	
	private static final Logger log = Logger.getLogger(UpLoadPicsUtil.class);
	/***
	 * 保存图片文件（压缩）
	 * @param file
	 * @return
	 */
	public  static List<String> savePicFile(MultipartFile[] files, HttpServletRequest request, String realPath) {
		List<String> list=new ArrayList<String>();
		try {
			// 判断文件是否为空
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i] != null) {
						MultipartFile file = files[i];
						//上传图片 最大不能超过3M
//						if (file.getSize()>3145728) {
//							return null;
//						}
						// 保存文件
						if (!file.isEmpty()) {
							// 文件原名称
							String fileName = file.getOriginalFilename();
						     // 图片的后缀  ( 如 .jpg)
							String profix = fileName.substring(fileName.lastIndexOf("."));
							
							String fileNameNew=DateUtil.date2String(8, new Date())+RandomUtil.getRandomNum(6);
				             //文件名称 (如  lzx.jpg)
							String localName = fileNameNew+profix;
				           
							String localNameNew = fileNameNew+"_small"+profix;
							//保存到数据库中的路径地址
							File targetFile = new File(realPath,localName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							targetFile.setWritable(true,false);
							// 保存
							file.transferTo(targetFile);
							//压缩图片 
							//ImageCompressUtil.zipImageFile(realPath+File.separator+localName, 1280, 1280, 1f, "_small");
							String fromPic=realPath+File.separator+localName;
							String toPic=realPath+File.separator+localNameNew;
							Thumbnails.of(fromPic).scale(0.6f).toFile(toPic);//按比例缩小
							String savePath="/shd/imgs"+File.separator+localNameNew;
							list.add(savePath);
						}else {
							list.add("");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("上传图片失败",e);
		}
		return list;
	}
	
	
	/***
	 * 保存图片文件 Base64解码保存图片
	 * @param file
	 * @return
	 */
	public  static List<String> savePicFileBase64(String[] fileStrs, HttpServletRequest request, String realPath) {
		List<String> list=new ArrayList<String>();
		try {
			// 判断文件是否为空
			if (fileStrs != null && fileStrs.length > 0) {
				for (int i = 0; i < fileStrs.length; i++) {
					 if (fileStrs[i] != null && fileStrs[i] != ""  &&  fileStrs[i].length()>0) {
							String fileStr = fileStrs[i];
							String fileNameNew=DateUtil.date2String(8, new Date())+RandomUtil.getRandomNum(6);
							//当文件夹不存在时候，创建文件夹
							File file=new File(realPath);
							if (!file.exists()) {
								file.mkdirs();
							}
							FileUtil.decode2(realPath+"/"+fileNameNew+".jpg", fileStr);
							String localNameNew_small = fileNameNew+"_small"+".jpg";
							//ImageCompressUtil.zipImageFile(realPath+File.separator+fileNameNew+".jpg", 1280, 1280, 1f, "_small");
							String fromPic=realPath+File.separator+fileNameNew+".jpg";
							String toPic=realPath+File.separator+localNameNew_small;
							Thumbnails.of(fromPic).scale(0.6f).toFile(toPic);//按比例缩小
							String url_small = "/shd/imgs/"+localNameNew_small;
							list.add(url_small);
						}else {
							list.add("");
						}
					}
				}
		} catch (Exception e) {
			log.error("上传图片失败",e);
		}
		return list;
	}
	
	
	/***
	 * 保存图片文件（不压缩）
	 * @param file
	 * @return
	 */
	public  static List<String> savePicFileNoCompression(MultipartFile[] files, HttpServletRequest request, String realPath) {
		List<String> list=new ArrayList<String>();
		try {
			// 判断文件是否为空
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i] != null) {
						MultipartFile file = files[i];
						//上传图片 最大不能超过3M
//						if (file.getSize()>3145728) {
//							return null;
//						}
						// 保存文件
						if (!file.isEmpty()) {
							// 文件原名称
							String fileName = file.getOriginalFilename();
						     // 图片的后缀  ( 如 .jpg)
							String profix = fileName.substring(fileName.lastIndexOf("."));
							
							String fileNameNew=DateUtil.date2String(8, new Date())+RandomUtil.getRandomNum(6);
				             //文件名称 (如  lzx.jpg)
							String localName = fileNameNew+profix;
							//保存到数据库中的路径地址
							File targetFile = new File(realPath,localName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							targetFile.setWritable(true,false);
							// 保存
							file.transferTo(targetFile);
							String savePath="/shd/imgs"+File.separator+localName;
							list.add(savePath);
						}else {
							list.add("");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("上传图片失败",e);
		}
		return list;
	}
	
	
	/***
	 * 保存图片文件（压缩）  并指定到规定的文件中
	 * @param file
	 * @return
	 * C:/apache-tomcat-7.0.54/webapps/pic/
	 */
	public  static List<String> savePicFolder(MultipartFile[] files, HttpServletRequest request, String FolderName,String fileUlr) {
		List<String> list=new ArrayList<String>();
		String realPath =fileUlr+"shd/"+FolderName;
		try {
			// 判断文件是否为空
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i] != null) {
						MultipartFile file = files[i];
						//上传图片 最大不能超过3M
//						if (file.getSize()>3145728) {
//							return null;
//						}
						// 保存文件
						if (!file.isEmpty()) {
							// 文件原名称
							String fileName = file.getOriginalFilename();
						     // 图片的后缀  ( 如 .jpg)
							String profix = fileName.substring(fileName.lastIndexOf("."));
							
							String fileNameNew=DateUtil.date2String(8, new Date())+RandomUtil.getRandomNum(6);
				             //文件名称 (如  lzx.jpg)
							String localName = fileNameNew+profix;
				           
							String localNameNew = fileNameNew+"_small"+profix;
							//保存到数据库中的路径地址
							File targetFile = new File(realPath,localName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							targetFile.setWritable(true,false);
							// 保存
							file.transferTo(targetFile);
							//压缩图片 
							//ImageCompressUtil.zipImageFile(realPath+File.separator+localName, 1280, 1280, 1f, "_small");
							String fromPic=realPath+File.separator+localName;
							String toPic=realPath+File.separator+localNameNew;
							Thumbnails.of(fromPic).scale(0.6f).toFile(toPic);//按比例缩小
							String savePath="/shd"+File.separator+FolderName+File.separator+localNameNew;
							list.add(savePath);
						}else {
							list.add("");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("上传图片失败",e);
		}
		return list;
	}
	
	
	/***
	 * 保存图片文件（不压缩）   并指定到规定的文件中
	 * @param file
	 * @return
	 */
	public  static List<String> savePicFolderNoCompression(MultipartFile[] files, HttpServletRequest request, String FolderName,String fileUlr) {
		List<String> list=new ArrayList<String>();
		String realPath =fileUlr+"shd/"+FolderName;
		try {
			// 判断文件是否为空
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i] != null) {
						MultipartFile file = files[i];
						//上传图片 最大不能超过3M
//						if (file.getSize()>3145728) {
//							return null;
//						}
						// 保存文件
						if (!file.isEmpty()) {
							// 文件原名称
							String fileName = file.getOriginalFilename();
						     // 图片的后缀  ( 如 .jpg)
							String profix = fileName.substring(fileName.lastIndexOf("."));
							
							String fileNameNew=DateUtil.date2String(8, new Date())+RandomUtil.getRandomNum(6);
				             //文件名称 (如  lzx.jpg)
							String localName = fileNameNew+profix;
							//保存到数据库中的路径地址
							File targetFile = new File(realPath,localName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							targetFile.setWritable(true,false);
							// 保存
							file.transferTo(targetFile);
							String savePath="/shd"+File.separator+FolderName+File.separator+localName;
							list.add(savePath);
						}else {
							list.add("");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("上传图片失败",e);
		}
		return list;
	}
	
	
}

package com.tool.as.common;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lp
 * 字符 处理工具类
 */
public class StrUtil {
	
	static Logger logger = LoggerFactory.getLogger(StrUtil.class) ;
	/**
	 * 判断某个对象是否为空 集合类、数组做特殊处理
	 * 
	 * @param obj
	 * @return 如为空，返回true,否则false
	 * @author lp 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;

		// 如果不为null，需要处理几种特殊对象类型
		if (obj instanceof String) {
			return obj.equals("");
		} else if (obj instanceof Collection) {
			// 对象为集合
			Collection coll = (Collection) obj;
			return coll.size() == 0;
		} else if (obj instanceof Map) {
			// 对象为Map
			Map map = (Map) obj;
			return map.size() == 0;
		} else if (obj.getClass().isArray()) {
			// 对象为数组
			return Array.getLength(obj) == 0;
		} else {
			// 其他类型，只要不为null，即不为empty
			return false;
		}
	}
	
	//去掉list中重复的值
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }
	
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }
    public static void toSript(HttpServletResponse httpServletResponse, String str) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append(str);
        sb.append("</script>");
        httpServletResponse.setContentType("text/html");
        writer(httpServletResponse, sb.toString());
    }
    
    public static void writer(HttpServletResponse response, String str) {
        try {
            // 设置页面不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密2次
     */
    /*public static String setPwd2times(String pwd){
    	return MD5Utils.string2MD5(MD5Utils.string2MD5(pwd));
    }*/
    /**
     * 保留2位小数
     */
    public static double keep2decimal(double f) {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    /**
     * 保留8位小数
     */
    public static double keep8decimal(double f) {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    
    /**
     *  读取html中所有的标签内容
	 * @param filePath 文件路径 
	 * @return 获得html的全部内容 
	 */ 
	@SuppressWarnings("resource")
	public static String readHtml(String filePath) { 
		BufferedReader br=null; 
		StringBuffer sb = new StringBuffer(); 
		try { 
		br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); //GB2312
		String temp=null;
		while((temp=br.readLine())!=null){ 
		sb.append(temp); 
		}
		} catch (FileNotFoundException e) { 
		e.printStackTrace(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return sb.toString(); 
	} 
	
	
    /**
     * 格式化 xml 文件，使其便于阅读
     */
    public static String formatXml(String str) throws Exception {
    	  Document document = null;
    	  document = DocumentHelper.parseText(str);
    	  // 格式化输出格式
    	  OutputFormat format = OutputFormat.createPrettyPrint();
    	  format.setEncoding("UTF-8");
    	  StringWriter writer = new StringWriter();
    	  // 格式化输出流
    	  XMLWriter xmlWriter = new XMLWriter(writer, format);
    	  // 将document写入到输出流
    	  xmlWriter.write(document);
    	  xmlWriter.close();

    	  return writer.toString();
     }
    
    /**
     * 把 文件流 转为 字符串
     */
    public static String stream2string(InputStream is) throws Exception {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }
    
    public static String stream2string2(InputStream is,String charset) throws Exception {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,charset));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }
    
	/**把小数转为 百分比
	 * @param p  数字
	 * @return   百分比
	 */
	public static String double2percent(double p) {
		if (p < 0) {
			return "-";
		}
		DecimalFormat df = new DecimalFormat("0.00%");
		return df.format(p);
	}
    
    /**
     * 获得文件 路径
     */
	public String getCurrentPath() {
		// 取得根目录路径
		String rootPath = getClass().getResource("/").getFile().toString();
		// 当前目录路径
		// String currentPath1=getClass().getResource(".").getFile().toString();
		// String currentPath2=getClass().getResource("").getFile().toString();
		// 当前目录的上级目录路径
		// String parentPath=getClass().getResource("../").getFile().toString();
		// String
		// parentPath=getClass().getResource("../../").getFile().toString();

		rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
		rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
		rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
		return rootPath;

	}
	
	/**获取浏览器版本信息
	 * @param request
	 * @return 浏览器的名字
	 */
	public static String getBrowserName(HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT").toLowerCase();
		if (agent.indexOf("msie 7") > 0) {
			return "ie7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "ie8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "ie9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "ie10";
		} else if (agent.indexOf("msie") > 0) {
			return "ie";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "ie11";
		} else {
			return "Others";
		}
	}
	
	/**
	 * 把中文编码转为 UTF-8格式
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
     * 分割List   lp
     *
     * @param list 待分割的list
     * @param pageSize    每段list的大小
     * @return List<<List<T>>
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size(); // list的大小
        int page = (listSize + (pageSize - 1)) / pageSize;// 页数
        List<List<T>> listArray = new ArrayList<List<T>>();// 创建list数组,用来保存分割后的list
        for (int i = 0; i < page; i++) { // 按照数组大小遍历
            List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
            for (int j = 0; j < listSize; j++) {// 遍历待分割的list
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;// 当前记录的页码(第几页)
                if (pageIndex == (i + 1)) {// 当前记录的页码等于要放入的页码时
                    subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
                }
                if ((j + 1) == ((j + 1) * pageSize)) {// 当放满一页时退出当前循环
                    break;
                }
            }
            listArray.add(subList);// 将分割后的list放入对应的数组的位中
        }
        return listArray;
    }
	/**
	 * 获取新闻中的图片信息(获取文本中所有的图片文件)
	 * @param htmlStr
	 * @return
	 */
    public   static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);	
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
//                pics.add(Constants.PIC_URL_SHOW_UEDITOR+m.group(1));
            	if (!m.group(1).contains("xls")&&!m.group(1).contains("doc") && !m.group(1).contains("ppt")) {
            		 pics.add(m.group(1));
				}
            }
        }
        return pics;
    }
    
    public static void main(String[] args) {
    	
//    	int progressbarValue=  (int)(StrUtil.keep2decimal(110/(110+0.0))*100);
//    	
//    	
//    	System.out.println(progressbarValue);
    	String aa="2016-09-09";
    	System.out.println(aa.substring(0, 7));
    	
	}
}

package com.tool.as.common;




import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;


public class XmlAnalysis {

	/**
	 * 对象解析成xml格式
	 * 
	 * @param obj
	 * @return
	 */
	public static String toXml(Object obj) {
		// 解决xstream将对象转换成xml 时出�?2个_
		XStream xStream = new XStream(new DomDriver("UTF-8",
				new XmlFriendlyNameCoder("-_", "_")));
		xStream.autodetectAnnotations(true);
		return xStream.toXML(obj);
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	public static <T> T formXml(String xml, Class<T> clazz) {
		XStream xStream = new XStream(new DomDriver());
		xStream.processAnnotations(clazz);
		return clazz.cast(xStream.fromXML(xml));
	}
}

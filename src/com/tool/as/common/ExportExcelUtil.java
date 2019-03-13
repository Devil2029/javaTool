package com.tool.as.common;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.tool.as.TestPojo;

public class ExportExcelUtil<T> {

	public String[] exportExcelHeader(Class<T> clz) {
		Field fields[] = clz.getDeclaredFields();
		String[] name = new String[fields.length];
		try {
			Field.setAccessible(fields, true);
			for (int i = 1; i < fields.length; i++) {
				name[i] = fields[i].getName();
				System.out.println(name[i] + "-> ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public static void exportExcel(Collection<Object> dataset, OutputStream out) {
		exportExcel("导出EXCEL文档", null, null, dataset, out, "yyyy-MM-dd");
	}

	public static void exportExcel(String[] headers,
			Collection<Object> dataset, OutputStream out) {
		exportExcel("导出EXCEL文档", null, headers, dataset, out, "yyyy-MM-dd");
	}

	public static void exportExcel(String title, String[] headers,
			Collection<Object> dataset, OutputStream out) {
		exportExcel(title, null, headers, dataset, out, "yyyy-MM-dd");
	}

	public static void exportExcel(String title, String[] headerTitle,
			String[] headers, Collection<Object> dataset, OutputStream out) {
		exportExcel(title, headerTitle, headers, dataset, out, "yyyy-MM-dd");
	}

	public static void exportExcel(String[] headerTitle, String[] headers,
			Collection<Object> dataset, OutputStream out, String pattern) {
		exportExcel("导出EXCEL文档", headerTitle, headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headerTitle
	 *            表格属性列名数组（标题）若为null，默认显示headers
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static void exportExcel(String title, String[] headerTitle,
			String[] headers, Collection<Object> dataset, OutputStream out,
			String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		int sheetCount = 1000;
		if (dataset.size() > sheetCount) {
			Iterator<Object> it = dataset.iterator();
			for (int i = 0; i <= 4; i++) {
				int index = 0;
				List<Object> list = new ArrayList<Object>();
				while (it.hasNext()) {
					index++;
					if (index < sheetCount) {
						list.add(it.next());
					} else {
						break;
					}
				}
				try {
					generateSheet(list, style, style2, workbook, pattern,
							headerTitle, headers, title + "_" + (i + 1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				generateSheet(dataset, style, style2, workbook, pattern,
						headerTitle, headers, title);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void generateSheet(Collection<Object> dataset,
			HSSFCellStyle style, HSSFCellStyle style2, HSSFWorkbook workbook,
			String pattern, String[] headerTitle, String[] headers, String title)
			throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("author");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		String[] showTitle = headers;
		if (!StrUtil.isEmpty(headerTitle))
			showTitle = headerTitle;
		int indexTitle = 0;// excel列下标
		for (short i = 0; i < showTitle.length; i++) {
			if (StrUtil.isEmpty(showTitle[i]))
				continue;
			HSSFCell cell = row.createCell(indexTitle);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(showTitle[i]);
			cell.setCellValue(text);
			indexTitle++;
		}
		if (StrUtil.isEmpty(dataset))
			return;
		// 遍历集合数据，产生数据行
		Iterator<Object> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object t = (Object) it.next();
			Class tCls = t.getClass();
			Method[] methods = t.getClass().getMethods();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = new Field[headers.length];

			for (short j = 0; j < headers.length; j++) {
				if (StrUtil.isEmpty(headers[j]))
					continue;
				Field field1 = tCls.getDeclaredField(headers[j]);
				fields[j] = field1;
			}
			int indexText = 0;// excel列下标
			for (short i = 0; i < fields.length; i++) {
				if (StrUtil.isEmpty(fields[i]))
					continue;

				Field field = fields[i];
				HSSFCell cell = row.createCell(indexText);

				cell.setCellStyle(style2);

				String fieldName = field.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, tCls);
				Method getMethod = pd.getReadMethod();
				Object value = getMethod.invoke(t, new Object[] {});
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				if (value instanceof Boolean) {
					boolean bValue = (Boolean) value;
					textValue = "是";
					if (!bValue)
						textValue = "否";
				} else if (value instanceof Date) {
					Date date = (Date) value;
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					textValue = sdf.format(date);
				} else if (value instanceof byte[]) {
					// 有图片时，设置行高为60px;
					row.setHeightInPoints(60);
					// 设置图片所在列宽度为80px,注意这里单位的一个换算
					sheet.setColumnWidth(indexText, (short) (35.7 * 80));
					// sheet.autoSizeColumn(i);
					byte[] bsValue = (byte[]) value;
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,
							255, (short) 6, index, (short) 6, index);
					anchor.setAnchorType(2);
					patriarch.createPicture(anchor, workbook.addPicture(
							bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
				} else {
					// 其它数据类型都当作字符串简单处理
					if (value == null)
						value = "";

					textValue = value.toString();
				}
				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (textValue != null) {
					Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;");
					Matcher matcher = p.matcher(textValue);
					if (matcher.matches()) {
						// 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue));
					} else {
						HSSFRichTextString richString = new HSSFRichTextString(
								textValue);
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLACK.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}
				}
				indexText++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ExportExcelUtil ex = new ExportExcelUtil();
		// String[] headers = ex.exportExcelHeader(MenuVo.class);
		List<Object> list = new ArrayList<Object>();// (List<MenuVo>)
		TestPojo testPojo2 = new TestPojo("1002", "李四", "湖南");
		TestPojo testPojo = new TestPojo();
		testPojo.setId("1001");
		testPojo.setName("张三");
		testPojo.setPrivilege("湖北");
		list.add(testPojo2);
		list.add(testPojo);
		list.add(new TestPojo("1003", "王五", "北京"));
		String[] headerTitle = { "编号", "名称", "权限" };
		String[] headers = { "id", "name", "privilege" };
		OutputStream out = new FileOutputStream("F:/c.xls");
		ex.exportExcel("excel测试导出", headerTitle, headers, list, out);
		out.close();
		// String url = "F://sss/c.xls";
		// System.out.println(url.lastIndexOf("/"));
		// System.out.println(url.substring(url.lastIndexOf("/") + 1));

		// downloadLocal();
		// JOptionPane.showMessageDialog(null, "导出成功!");
		// System.out.println("excel导出成功！");
	}

	/**
	 * 导出 下载excel
	 * 
	 * @param title
	 * @param headerTitle
	 * @param headers
	 * @param dataset
	 * @param fileUlr
	 * @param response
	 * @throws FileNotFoundException
	 */
	public static void downloadLocal(String title, String[] headerTitle,
			String[] headers, Collection<Object> dataset, String fileUlr,
			HttpServletResponse response) {
		try {
			OutputStream out = new FileOutputStream(fileUlr);
			exportExcel(title, headerTitle, headers, dataset, out);
			out.close();
			String newFileName = fileUlr
					.substring(fileUlr.lastIndexOf("/") + 1);
			// 读到流中
			InputStream inStream = new FileInputStream(fileUlr);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ newFileName.toString() + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

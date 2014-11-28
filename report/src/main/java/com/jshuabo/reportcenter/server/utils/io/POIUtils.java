package com.jshuabo.reportcenter.server.utils.io;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.exception.BaseRuntimeException;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.reportcenter.server.utils.Date.DateUtils;

/**
 * @ClassName: Execl2Object
 * @Description:
 * @author: peng.wu
 * @date: 2014年9月12日 下午4:15:23
 */
public class POIUtils {

    private static final Logger logger = LoggerFactory.getLogger(POIUtils.class);

    /**
     * @fieldName: format
     * @fieldType: SimpleDateFormat
     * @Description: excel number date 2014/9/5 9:00:00
     */
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * @Title: createWorkBook 创建 workbook
     * @param filePath
     * @return: Workbook
     */
    public static Workbook createWorkBook(String filePath) {
        Workbook workBook = null;
        try {
            workBook = WorkbookFactory.create(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            throw new BaseRuntimeException("execl2List FileNotFoundException with :"
                    + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new BaseRuntimeException("execl2List IOException with :"
                    + e.getLocalizedMessage());
        } catch (InvalidFormatException e) {
            throw new BaseRuntimeException("execl2List InvalidFormatException with :"
                    + e.getLocalizedMessage());
        }
        return workBook;
    }

    /**
     * @Title: execl2List execl all to list
     * @param filePath
     * @param startRow
     * @return: List<List<String>>
     */
    public static List<List<String>> execl2List(String filePath, int startRow) {
        List<List<String>> lists = new ArrayList<List<String>>();
        Workbook workBook = createWorkBook(filePath);
        for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workBook.getSheetAt(numSheet);
            if (sheet == null) continue;
            for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;
                List<String> rowList = getCellsValue(row);
                if (StringUtils.isEmpty(rowList)) continue;
                lists.add(rowList);
            }
        }
        return lists;
    }
    
    public static List<List<Object>> execlToList(String filePath, int startRow) {
        List<List<Object>> lists = new ArrayList<List<Object>>();
        Workbook workBook = createWorkBook(filePath);
        for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workBook.getSheetAt(numSheet);
            if (sheet == null) continue;
            for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;
                List<Object> rowList = getCellValue(row);
                if (StringUtils.isEmpty(rowList)) continue;
                lists.add(rowList);
            }
        }
        return lists;
    }


    /**
     * @Title: execl2ListMap execl 转 list Map
     * @param propertiesMap 对应的对象
     * @param str 表头
     * @param filePath 文件路径
     * @param startRow 重第几行开始
     * @return: List<Map<String,Object>>
     */
    public static List<Map<String, Object>> execl2ListMap(Map<Integer, String> propertiesMap, String[] str, String filePath, int startRow) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Workbook workBook = createWorkBook(filePath);

        for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {  // 循环工作表Sheet
            Sheet sheet = workBook.getSheetAt(numSheet);
            if (sheet == null) continue;

            for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;
                if (rowNum == startRow) {
                    List<String> list = checkExcelHand(getCellsValue(row, propertiesMap.size() - 1), str);
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (!StringUtils.isEmpty(list)) {
                        for (String temp : list) {
                            if (StringUtils.isEmpty(temp)) continue;
                            map.put("error", temp);
                        }
                        listMap.add(map);
                    }
                    if (!listMap.isEmpty()) return listMap;
                } else {
                    List<String> list = getCellsValue(row);
                    if (StringUtils.isEmpty(list)) continue;
                    Map<String, Object> map = cells2Map(propertiesMap, list);
                    if (map.isEmpty()) continue;
                    listMap.add(map);
                }
            }
        }
        return listMap;
    }

    /**
     * @Title: checkExcelHand 核实 excel 数据格式是否匹配
     * @param rowList 解析出来的表头
     * @param str 预 excel表头
     * @return: List<String>
     * @date: 2014年10月12日 下午9:15:14
     */
    private static List<String> checkExcelHand(List<String> rowList, String[] str) {
        List<String> lis = Arrays.asList(str);
        if (lis.containsAll(lis) && (rowList.size() == lis.size())) {
            rowList = null;
        } else {
            for (int i = 0; i < str.length; i++) {
                if (StringUtils.isEmpty(str[i])) continue;
                if (rowList.contains(str[i].trim())) {
                    rowList.remove(i);
                    if (str[i] == null) {
                        rowList.remove(i);
                    }
                }
            }
            for (int i = 0; i < rowList.size(); i++) {
                if (StringUtils.isEmpty(rowList.get(i))) rowList.remove(rowList.get(i));
            }
        }
        return rowList;
    }

    /**
     * @Title: getCellsValue 将每一行转换成 list
     * @param row
     * @return: List<String>
     * @date: 2014年9月13日 下午5:14:19
     */
    public static List<String> getCellsValue(Row row, Integer len) {
        List<String> list = new ArrayList<String>();
        
        for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) { // 循环列Cell
            Cell xssfCell = row.getCell(cellNum);
            if (list.size() == len) break;
            if (StringUtils.isEmpty(xssfCell)) {
                list.add(null);
            } else {
                list.add(getValue(xssfCell));
            };
        }
        return list;
    }

    /**
     * @Title: getCellsValue 解析 列 得到当前值
     * @param row
     * @return: List<String>
     * @date: 2014年10月27日 上午9:57:43
     */
    public static List<String> getCellsValue(Row row) {
        List<String> list = new ArrayList<String>();
        // 循环列Cell
        for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
            Cell xssfCell = row.getCell(cellNum);
            if (StringUtils.isEmpty(xssfCell)) {
                list.add(null);
            } else {
                if(StringUtils.isEmpty(getValue(xssfCell))) continue;
                list.add(getValue(xssfCell));
            };
        }
        if (list.size() == 0) return null;
        return list;
    }
    
    public static List<Object> getCellValue(Row row) {
        List<Object> list = new ArrayList<Object>();
        // 循环列Cell
        for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
            Cell xssfCell = row.getCell(cellNum);
            if (StringUtils.isEmpty(xssfCell)) {
                list.add(null);
            } else {
                list.add(getValue(xssfCell));
            };
        }
        if (list.size() == 0) return null;
        return list;
    }

    /**
     * @Title: getValue 解析 每个单元 个 的值
     * @param xssfCell
     * @return: String Cell Value
     * @date: 2014年9月12日 下午4:25:18
     */
    @SuppressWarnings("static-access")
    private static String getValue(Cell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
//            DecimalFormat df = new DecimalFormat("0");
            Double d = xssfCell.getNumericCellValue();
//            String temp = df.format(d);
            if(!StringUtils.isEmpty(d)){
                return String.valueOf(d);
            }
            return "";
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
            xssfCell.setCellType(Cell.CELL_TYPE_STRING);
            return xssfCell.getStringCellValue();
        } else {
            xssfCell.setCellType(Cell.CELL_TYPE_STRING);
            return xssfCell.getStringCellValue();
        }
    }

    /**
     * @Title: Cells2Object 将每 一行 row 转换成 object
     * @param propertiesMap
     * @param list execl head
     * @return: Object
     * @date: 2014年9月13日 下午5:08:27
     */
    public static Object cells2Object(Map<Integer, String> propertiesMap, List<String> list) {
        
        Map<String, PropertyDescriptor> propMap = new HashMap<String, PropertyDescriptor>();
        Object obj = null;
        String className = propertiesMap.get(-1);
        Map<?, ?> properDesc = null;
        
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.newInstance();

            BeanInfo bi = Introspector.getBeanInfo(clazz); // 获取对象的描述信息

            PropertyDescriptor[] pds = bi.getPropertyDescriptors(); // 属性对象 遍历 PropertyDescriptor
            for (PropertyDescriptor p : pds) {
                propMap.put(p.getName(), p);
            }
            
            properDesc = BeanUtils.describe(obj);
            Set<Integer> keys = propertiesMap.keySet();
            Iterator<Integer> iter = keys.iterator();
            for (int temp = 0; temp < list.size(); temp++) {
                Integer index = iter.next();
                if (index.intValue() == -1) {
                    continue;
                }
                String property = propertiesMap.get(index);
                if (properDesc.containsKey(property)) {
                    // 获取对象类型 java.lang.String
                    Class<?> propertyType = propMap.get(property).getPropertyType();
                    // 每个单元格的值
                    String value = list.get(index);
                    // String value = cells[index];
                    if (String.class.equals(propertyType)) {
                        PropertyUtils.setProperty(obj, property, value);
                    } else if (Date.class.equals(propertyType)) {
                        Date d;
                        try {
                            // d = format.parse(value);
                            d =
                                    DateUtils.numberDate2Format(Double.valueOf(value),DateUtils.ymd_hms);
                        } catch (Exception e) {
                            d = new Date();
                        }
                        PropertyUtils.setProperty(obj, property, d);
                    } else if (java.sql.Date.class.equals(propertyType)) {
                        Date d;
                        try {
                            // d = format.parse(value);
                            d =
                                    DateUtils.numberDate2Format(Double.valueOf(value),DateUtils.ymd_hms);
                        } catch (Exception e) {
                            d = new Date();
                        }
                        PropertyUtils.setProperty(obj, property, d);
                    } else if (int.class.equals(propertyType) || Integer.class.equals(propertyType)) {
                        int i = 0;
                        try {
                            i = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            i = 0;
                        }
                        PropertyUtils.setProperty(obj, property, i);
                    } else if (double.class.equals(propertyType)
                            || Double.class.equals(propertyType)) {
                        double d = 0;
                        try {
                            d = Double.parseDouble(value);
                        } catch (NumberFormatException e) {
                            d = 0.0;
                        }
                        PropertyUtils.setProperty(obj, property, d);
                    } else if (long.class.equals(propertyType) || Long.class.equals(propertyType)) {
                        long l = 0;
                        try {
                            l = Long.parseLong(value);
                        } catch (NumberFormatException e) {
                            l = 0l;
                        }
                        PropertyUtils.setProperty(obj, property, l);
                    } else if (float.class.equals(propertyType) || Float.class.equals(propertyType)) {
                        float f = 0;
                        try {
                            f = Float.parseFloat(value);
                        } catch (NumberFormatException e) {
                            f = 0l;
                        }
                        PropertyUtils.setProperty(obj, property, f);
                    } else if (boolean.class.equals(propertyType)
                            || Boolean.class.equals(propertyType)) {// boolean
                        Boolean b = BooleanUtils.toBooleanObject(value);
                        PropertyUtils.setProperty(obj, property, b);
                    } else {
                        PropertyUtils.setProperty(obj, property, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("exception with to Cells2Object :[{}]", e.getLocalizedMessage());
        }
        return obj;
    }

    /**
     * @Title: Cells2Map 每一行转成map
     * @param propertiesMap
     * @param list 表头
     * @return: Map<String,Object>
     * @date: 2014年9月14日 上午12:34:22
     */
    public static Map<String, Object> cells2Map(Map<Integer, String> propertiesMap, List<String> list) {
        Map<String, PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();
        Object object = null;
        String className = propertiesMap.get(-1);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<?, ?> pdMap = null;
        try {
            Class<?> clazz = Class.forName(className);
            object = clazz.newInstance();

            BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取对象的描述信息

            PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors(); // 属性对象 遍历
                                                                                         // PropertyDescriptor
            for (PropertyDescriptor p : propertyDescriptor) {
                propertyMap.put(p.getName(), p);
            }
            pdMap = BeanUtils.describe(object);
            Iterator<Integer> iter = propertiesMap.keySet().iterator();
            for (int temp = 0; temp < list.size(); temp++) {
                Integer index = iter.next();
                if (index.intValue() == -1) break;
                String property = propertiesMap.get(index);
                if (pdMap.containsKey(property)) {

                    Class<?> propertyType = propertyMap.get(property).getPropertyType(); // 获取对象类型
                                                                                         // java.lang.String

                    String value = list.get(index); // 每个单元格的值
                    if (StringUtils.isEmpty(value)) {
                        map.put(property, null);
                    } else if (String.class.equals(propertyType)) {
                        map.put(property, value);
                    } else if (Date.class.equals(propertyType)) {
                        Date d = null;
                        try {
                            Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); // 文字
                            Matcher m = p.matcher(value);
                            if (m.find()) {
                                map.put(property, value);
                            } else if (value.contains(":")) {
                                map.put(property, value);
                            } else {
                                d = DateUtils.numberDate2Format(Double.valueOf(value),DateUtils.hms);
                                map.put(property, d);
                            }
                        } catch (Exception e) {
                            if (!StringUtils.isEmpty(value)) {
                                d = DateFormatUtils.parse(value, DateUtils.ymd);
                                map.put(property, d);
                            }
                        }
                    } else if (java.sql.Date.class.equals(propertyType)) {
                        java.sql.Date d = null;
                        try {
                            if (value.contains("/")) {
                                d = DateUtils.convertSQLDate(value, DateUtils.yMd_hms);
                            } else {
                                java.util.Date udate = DateUtils.numberDate2Format(Double.parseDouble(value),DateUtils.ymd);
                                d = new java.sql.Date(udate.getTime());
                            }
                            if(StringUtils.isEmpty(d)){
                                String s = DateUtils.numberDate2FormatString(Double.parseDouble(value), DateUtils.hms);
                                map.put(property, s);
                            }else{
                                map.put(property, d);
                            }
                        } catch (Exception e) {
                            map.put(property, d);
                        }
                    } else if (java.sql.Time.class.equals(propertyType)) {
                        java.sql.Time d = null;
                        try {
                            String s = DateUtils.numberDate2FormatString(Double.parseDouble(value), DateUtils.hms);
                            map.put(property, s);
                        } catch (Exception e) {
                            map.put(property, d);
                        }
                    } else if (java.sql.Timestamp.class.equals(propertyType)) {
                        String s = DateUtils.numberDate2FormatString(Double.parseDouble(value), DateUtils.ymd_hms);
//                        java.sql.Timestamp t = new java.sql.Timestamp(DateUtils.numberDate2Format(Double.parseDouble(value), DateUtils.ymd_hms).getTime());
                        map.put(property, s);
                    } else if (int.class.equals(propertyType) || Integer.class.equals(propertyType)) {
                        int i = 0;
                        try {
                            i = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            i = 0;
                        }
                        map.put(property, i);
                    } else if (double.class.equals(propertyType)
                            || Double.class.equals(propertyType)) {
                        double d = 0;
                        try {
                            d = Double.parseDouble(value);
                        } catch (NumberFormatException e) {
                            d = 0.0;
                        }
                        map.put(property, d);
                    } else if (long.class.equals(propertyType) || Long.class.equals(propertyType)) {
                        long l = 0;
                        try {
                            l = Long.parseLong(value);
                        } catch (NumberFormatException e) {
                            l = 0l;
                        }
                        map.put(property, l);
                    } else if (float.class.equals(propertyType) || Float.class.equals(propertyType)) {
                        float f = 0;
                        try {
                            f = Float.parseFloat(value);
                        } catch (NumberFormatException e) {
                            f = 0l;
                        }
                        map.put(property, f);
                    } else if (boolean.class.equals(propertyType)
                            || Boolean.class.equals(propertyType)) {// boolean
                        Boolean b = BooleanUtils.toBooleanObject(value);
                        map.put(property, b);
                    } else {
                        map.put(property, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("exception with to cells2Map :[{}]", e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * @Title: setCellStyle 设置字体样式
     * @param wb
     * @return: CellStyle[] 字体样式
     * @date: 2014年9月22日 上午10:30:58
     */
    public static CellStyle[] cellStyle(Workbook wb) {
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        // DataFormat df = wb.createDataFormat();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.RED.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));
        return new CellStyle[] {cs, cs2};
    }

    /**
     * @Title: setHeard 设置标题
     * @param cellStyle 样式
     * @param sheet
     * @param row 行
     * @param cell 列
     * @param title 标题
     * @date: 2014年9月22日 上午10:31:37
     */
    public static void createHeard(Sheet sheet, Row row, Cell cell, CellStyle[] cellStyle,
            String... title) {
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < cellStyle.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 120));
        }

        if (!ObjectUtils.isEmpty(title)) {
            for (int i = 0; i < title.length; i++) {
                // 创建列（每行里的单元格）
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(cellStyle[0]);
            }
        }
    }

    /**
     * @Title: createSXSSFWorkbook
     * @param sheetName
     * @param title
     * @date: 2014年9月23日 下午4:23:03
     */
    public static SXSSFWorkbook createSXSSFWorkbook(String sheetName, String[] title) {

        SXSSFWorkbook workbook = new SXSSFWorkbook(200000);
        createSheet(workbook, sheetName, title);
        return workbook;
    }

    /**
     * @Title: createSheet
     * @param workbook
     * @param sheetName
     * @return: void
     * @date: 2014年9月23日 下午8:09:06
     */
    public static void createSheet(SXSSFWorkbook workbook, String sheetName, String[] title) {

        Sheet sheet = workbook.createSheet((sheetName != null) ? sheetName : "sheet");

        Row row = sheet.createRow(0); // create row to number one

        Cell cell = null;
        CellStyle[] cs = cellStyle(workbook);

        createHeard(sheet, row, cell, cs, title);
    }

    public static void createSheet(SXSSFWorkbook workbook, String sheetName) {

        Sheet sheet = workbook.createSheet((sheetName != null) ? sheetName : "sheet");

        Row row = sheet.createRow(0); // create row to number one

        Cell cell = null;
        CellStyle[] cs = cellStyle(workbook);

        createHeard(sheet, row, cell, cs);
    }

    /**
     * @Title: exprot
     * @param wb
     * @param response
     * @param name
     * @date: 2014年9月23日 下午8:09:15
     */
    public static void exprot(Workbook wb, HttpServletResponse response, String name) {

        OutputStream os = null;
        try {

            os = response.getOutputStream();

            String fileName = URLEncoder.encode(name + ".xlsx", "UTF-8");
            response.setContentType("Application/msexcel");
            response.setContentType("bin");
            response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
            // response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");

            wb.write(os);

        } catch (IOException e) {
            logger.debug("exportExcel IOException: [{}]", e.getLocalizedMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                logger.debug("OutputStream close exception with to :[{}]", e.getLocalizedMessage());
            }
        }
    }

    /**
     * @Title: isExcelFile is not it excel file
     * @param fileName
     * @return: boolean , yes return true, else false
     * @date: 2014年10月18日 下午12:40:36
     */
    public static boolean isExcelFile(String fileName) {
        String suxx = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
        if ("xls".equals(suxx) || "xlsx".equals(suxx)) return true;
        return false;
    }

    /**
     * @Title: getCellToMap row to map
     * @param row
     * @param propertiesMap
     * @return: Map<String,Object>
     */
    public static Map<String, Object> getCellToMap(Row row, Map<Integer, String> propertiesMap) {
        // TODO Auto-generated method stub
        List<String> rowList = POIUtils.getCellsValue(row);
        if (StringUtils.isEmpty(rowList) || rowList.size() <= 1) return null;
        return POIUtils.cells2Map(propertiesMap, rowList);
    }
    
    /**
     * @Title: getCellToObject row to object
     * @param row
     * @param propertiesMap
     * @return: Map<String,Object>
     */
    public static Object getCellToObject(Row row, Map<Integer, String> propertiesMap) {
        // TODO Auto-generated method stub
        List<String> rowList = POIUtils.getCellsValue(row);
        if (StringUtils.isEmpty(rowList)) return null;
        return POIUtils.cells2Object(propertiesMap, rowList);
    }

}

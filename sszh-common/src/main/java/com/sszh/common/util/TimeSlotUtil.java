package com.sszh.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title:	时间工具类-判断多个时间段是否出现重叠
 * @version	v1.0.0
 * @author	研发中心-张辉
 * @date	2019年05月06日  下午17:28:46  周一
 *
 */
public class TimeSlotUtil {
	
	/**
	 *  判断【时段区间】是否重叠
	 * 
	 * @param lsitMap		区间集合		
	 * 									date 		与这个区间绑定的ID信息或描述信息
	 * 									startArea 	区间开始值
	 * 									endArea 	区间结束值
	 * [
	 * 		{date=AS08998AS0DF8A0S9DF8, startArea=10800000, endArea=21600000},
	 * 		{date=AS08998AS0DF8A0S9DF9, startArea=21600000, endArea=28800000},
	 * 		{date=AS08998AS0DF8A0S9DG0, startArea=25200000, endArea=32400000}
	 * ]
	 * 
	 * @param doFristStop	中断操作		
	 * 									true 		只要有重叠，立即返回
	 * 									false 		直到所有值遍历完才返回
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> checkTimeArea(List<Map<String, String>> lsitMap, boolean doFristStop) throws Exception {
		SimpleDateFormat SDF_HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
		Collections.sort(lsitMap, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				Long num_1 = Long.valueOf(o1.get("startArea"));
				Long num_2 = Long.valueOf(o2.get("startArea"));
				int result = num_1.compareTo(num_2);
				return result;
			}
        });
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		Map<String, String> retMap = null;
		for (int i = 0; i < lsitMap.size(); i++) {
			BigDecimal startInt = new BigDecimal(lsitMap.get(i).get("startArea"));
			BigDecimal endInt = new BigDecimal(lsitMap.get(i).get("endArea"));
			for (int j = i + 1; j < lsitMap.size(); j++) {
				BigDecimal toStartInt = new BigDecimal(lsitMap.get(j).get("startArea"));
				BigDecimal toEndInt = new BigDecimal(lsitMap.get(j).get("endArea"));
				/**
				 * 如果指定的数与参数相等返回0。
				 * 如果指定的数小于参数返回 -1。
				 * 如果指定的数大于参数返回 1。
				 *
				 * 案例：
				 * Integer x = 5;
				 * System.out.println(x.compareTo(3));
				 * System.out.println(x.compareTo(5));
				 * System.out.println(x.compareTo(8));
				 *
				 * 输出结果：
				 * 1
				 * 0
				 * -1
				 *
				 */
				if (toStartInt.compareTo(endInt) == -1 || (startInt.compareTo(toStartInt) == 0 && endInt.compareTo(toEndInt) == 0)) {
					retMap = new HashMap<String, String>();
					retMap.put("old", lsitMap.get(i).get("date"));
					retMap.put("oldArea", SDF_HH_MM_SS.format(new Date(Long.valueOf(lsitMap.get(i).get("startArea")))) + "-" + SDF_HH_MM_SS.format(new Date(Long.valueOf(lsitMap.get(i).get("endArea")))));
					retMap.put("new", lsitMap.get(j).get("date"));
					retMap.put("newArea", SDF_HH_MM_SS.format(new Date(Long.valueOf(lsitMap.get(j).get("startArea")))) + "-" + SDF_HH_MM_SS.format(new Date(Long.valueOf(lsitMap.get(j).get("endArea")))));
					retList.add(retMap);
					if (doFristStop) {
						return retList;
					}
				}
			}
		}
		return retList;
	}
	
	
	
	/**
	 * 判断【数字区间】是否重叠
	 * 
	 * @param lsitMap		区间集合
	 * 									date 		与这个区间绑定的ID信息或描述信息
	 * 									startArea 	区间开始值
	 * 									endArea 	区间结束值
	 * [
	 * 		{date=AS08998AS0DF8A0S9DF8, startArea=10800000, endArea=21600000},
	 * 		{date=AS08998AS0DF8A0S9DF9, startArea=21600000, endArea=28800000},
	 * 		{date=AS08998AS0DF8A0S9DG0, startArea=25200000, endArea=32400000}
	 * ]
	 * 
	 * @param doFristStop	中断操作		
	 * 									true 		只要有重叠，立即返回
	 * 									false 		直到所有值遍历完才返回
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> checkMunberArea(List<Map<String, String>> lsitMap, boolean doFristStop) throws Exception {
		Collections.sort(lsitMap, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				Long num_1 = Long.valueOf(o1.get("startArea"));
				Long num_2 = Long.valueOf(o2.get("startArea"));
				int result = num_1.compareTo(num_2);
				return result;
			}
        });
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		Map<String, String> retMap = null;
		for (int i = 0; i < lsitMap.size(); i++) {
			BigDecimal endInt = new BigDecimal(lsitMap.get(i).get("endArea"));
			for (int j = i + 1; j < lsitMap.size(); j++) {
				BigDecimal toBeginInt = new BigDecimal(lsitMap.get(j).get("startArea"));
				if (toBeginInt.compareTo(endInt) == -1) {
					retMap = new HashMap<String, String>();
					retMap.put("old", lsitMap.get(i).get("date"));
					retMap.put("oldArea", Long.valueOf(lsitMap.get(i).get("startArea")) + ", " + Long.valueOf(lsitMap.get(i).get("endArea")));
					retMap.put("new", lsitMap.get(j).get("date"));
					retMap.put("newArea", Long.valueOf(lsitMap.get(j).get("startArea")) + ", " + Long.valueOf(lsitMap.get(j).get("endArea")));
					retList.add(retMap);
					if (doFristStop) {
						return retList;
					}
				}
			}
		}
		return retList;
	}
	
	
	public static void main(String[] args) throws Exception {
		/**
		 * 测试数值区间重叠
		 */
		//		List<Map<String, String>> lsitMap = new ArrayList<Map<String, String>>();
		//		Map<String, String> map = new HashMap<String, String>();
		//		map.put(     "date", "价格方案测试111111");															//这里存放的是与这组数据绑定的ID信息，便于记录哪一组区间发生重叠
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "11:00:00").getTime()));					//区间开始值
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "14:00:00").getTime()));					//区间结束值
		//		lsitMap.add(map);
		//		map = new HashMap<String, String>();
		//		map.put(     "date", "价格方案测试222222");
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "14:00:00").getTime()));
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "16:00:00").getTime()));
		//		lsitMap.add(map);
		//		map = new HashMap<String, String>();
		//		map.put(     "date", "价格方案测试333333");
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "15:00:00").getTime()));
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "17:00:00").getTime()));
		//		lsitMap.add(map);
		//		List<Map<String, String>> retList = checkMunberArea(lsitMap);
		//		for (Map<String, String> mmp : retList) {
		//			System.out.println("【"+mmp.get("old")+"】区间值：[" + mmp.get("oldArea") + "]与【"+mmp.get("new")+"】区间值["+ mmp.get("newArea") +"]发生重叠");
		//		}
		/**
		 * 测试时段区间重叠
		 */
		//		List<Map<String, String>> lsitMap = new ArrayList<Map<String, String>>();
		//		Map<String, String> map = new HashMap<String, String>();
		//		map.put(     "date", "测试场馆名称-价格方案测试111111");												//这里存放的是与这组数据绑定的ID信息，便于记录哪一组区间发生重叠
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "11:00:00").getTime()));					//区间开始值
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "15:00:00").getTime()));					//区间结束值
		//		lsitMap.add(map);
		//		map = new HashMap<String, String>();
		//		map.put(     "date", "测试场馆名称-价格方案测试222222");
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "14:00:00").getTime()));
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "16:00:00").getTime()));
		//		lsitMap.add(map);
		//		map = new HashMap<String, String>();
		//		map.put(     "date", "测试场馆名称-价格方案测试333333");
		//		map.put("startArea", String.valueOf(SDF.parse(NOW_DATE + "15:00:00").getTime()));
		//		map.put(  "endArea", String.valueOf(SDF.parse(NOW_DATE + "17:00:00").getTime()));
		//		lsitMap.add(map);
		//		List<Map<String, String>> retList = checkTimeArea(lsitMap, true);
		//		for (Map<String, String> mmp : retList) {
		//			System.out.println("价格方案冲突：【"+mmp.get("old")+"】与【"+mmp.get("new")+"】时段发生重叠，具体重叠时段：[" + mmp.get("oldArea") + "]["+ mmp.get("newArea") +"]");
		//		}
		
	}
	
	
	
	
	
	
}
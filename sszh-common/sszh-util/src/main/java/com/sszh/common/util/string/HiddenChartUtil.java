package com.sszh.common.util.string;

import com.sszh.common.util.CommonUtils;

/**
 * 隐藏字符-工具类
 *
 * @author seesun2012@163.com
 *
 */
public class HiddenChartUtil extends CommonUtils {


	/**
	 * 加密银行卡号，返回值如：6228 48** **** ***7 531
	 *
	 * @param str			原始字符串
	 * @param startIndex	开始位置（显示）
	 * @param endIndex		结束位置（显示）
	 * @param n				每n个字符隔开
	 * @param encryptionStr	指定加密字符，如"*"	--米字符号
	 * @param repStr		添加分隔的符号，如：“ ” -- 空格
	 * @return
	 */
	public static String replaceCardId(String str, Integer startIndex, Integer endIndex, Integer n, String encryptionStr, String repStr){
		return spaceReplace(hiddenStr(str, startIndex, endIndex, encryptionStr), n, repStr);
	}


	/**
	 * 每间隔n个字符插入特定字符，默认如：6228 4888 8888 8888 888 ...
	 *
	 * @param str		原始字符
	 * @param n			每n个字符隔开
	 * @param repStr	添加分隔的符号，如：“ ” -- 空格
	 * @return
	 */
	public static String spaceReplace(String str, Integer n, String repStr){
		if (n <= 0){
			n = 4;
		}
		if (null == repStr){
			repStr = "";
		}
		String newStr = "";
		char[] bankArray = str.toCharArray();
		for(int i=0;i<bankArray.length;i++){
		    if(i%n==0 && i>0){
		    	newStr += repStr;
		    }
		    newStr += bankArray[i];
		}
		return newStr;
	}


	/**
	 * 隐藏指定间隔字符，默认如：612237************4521
	 *
	 * @param str			原始字符串
	 * @param startIndex	开始位置
	 * @param endIndex		结束位置
	 * @param encryptionStr	指定加密字符
	 * @return
	 */
	public static String hiddenStr(String str, Integer startIndex, Integer endIndex, String encryptionStr){
		if (str.length()<=1) {
			return str;
		}
		if (null == encryptionStr){
			encryptionStr = "*";
		}
		String xxStr = "";
		String lenStr = str.trim().substring(startIndex, str.length()-endIndex);
		for (int i = 0, len = lenStr.length(); i < len; i++) {
			xxStr += encryptionStr;
		}
		xxStr = str.replaceFirst(lenStr, xxStr);
		return xxStr;
	}




}

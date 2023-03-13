package com.common.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

/**
 * @author maoyuchun
 * @version 创建时间：2016年4月11日 下午6:47:42
 */
public class AppUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

//    public static final String[] emptyStr = new String[]{};

//    private static final String fileSpeartor = "/";
//
//    private static final String fileDot = "_";
//
//    private static final String smallFile = "small/";

    //public static final ExecutorService executor = CustomThreadPoolExecutor.getInstance();

    static Pattern p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号

    private static Pattern idNumPattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");    //验证身份证号

    private static Pattern bankAccountPattern = Pattern.compile("^[0-9]*$");    //验证银行账户 由于各银行账号位数不同，暂只限制为纯数字组成的字符串

//    private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
//    static Pattern date = Pattern.compile("^((?:19|20)\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$"); /** 验证日期格式 yyyyMMdd */

    static {
        MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    public static Boolean CheckPhone(String phone) {
        return phone != null && p.matcher(phone).matches();
    }

    public static Boolean CheckIDNum (String idNum) {
        return idNum != null && idNumPattern.matcher(idNum).matches();
    }

    public static Boolean CheckBankAccount (String bankAccount) {
        return bankAccount != null && bankAccountPattern.matcher(bankAccount).matches();
    }
}

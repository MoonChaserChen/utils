package me.chin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class MD5Util {
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    /**
     * 生成签名结果(新版本使用)
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray,String keyName,String secretKey) {
        if (keyName != null && keyName.trim().length() != 0){
            throw new RuntimeException("keyName can't be empty");
        }
        String mysign = "";
        try {
            String prestr = TransferUtils.map2OrderedString(sArray);
            prestr = prestr + "&" + keyName + "=" + secretKey;
            mysign = getMD5String(prestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 生成签名结果（老版本使用）
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray,String secretKey) {
        String mysign = "";
        try {
            String prestr =TransferUtils.map2OrderedString(sArray);
            prestr = prestr + secretKey;
            mysign = getMD5String(prestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 生成32位大写MD5值
     */
    public static String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                        + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
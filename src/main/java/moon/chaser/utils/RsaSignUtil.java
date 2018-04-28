package moon.chaser.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * RSA的签名及验签
 * @author allen
 *
 */
public class RsaSignUtil {

    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKvqJTY/Lpl+D/y54uH89WSNg6hUgru+Ng3/4JiJQMkly/W/ANwPvVXYET6KiSaU097hyR9veShrA/QhcKUrqSICF1Q4JT2K6ec7jv6DVeQVyXL/17guDjfpEqAjSjQtd106dJ3PZ00xJ3MmRReuQ8FnpTZy+9b+1qcERAajTSmFAgMBAAECgYAeMKdKSHwxfl7mXeqHDzo051kolXbD65poiPfzMZ9L87RGur026Biy25fjqMZ/HQG8EWCVgakAPRLbq4exqeIwianK9gffDgN9qHnk+PPZeU61/HBv+8g6tADu1+QQYWTa/S14Id6vgME1m1fbdfovpyfPH58cwePqHUzRWjaswQJBAOfrqGx5solUEQrlSiMuHGiEgXrsm/tPHruvOuqvoDWWBxBPrwEyliYnMG9EtOet7W4YzVFBhzCupFvc2JDosbECQQC9w47LRf/rnm4RtcAlU00LjiPgBKiSY30twJOf+KO6jLx2KYfEJ+quuCOmk1ScPddTcj8D6pJV3vZ2sL8v/3YVAkAiMt3jIQ7ysTLSKH2GMoZ94ww01C8sH76obm6BrOQzAqyH+zgTNSJu/dhmj/sdLsiUM5QTJ1aIFzilUyucMrQBAkBw4oZtceS9+28q5d251oX8m7/Hob3N55UtxGONRFHRxarO+AGltMEs0qmSjA5HK5qOL2ZBJCeNIaeoD5iMKwHJAkEAr0CNIxESB/GtBDOw4Fjkv+qIG0nHeY59PUugn6qipYgcNye5HjDUSzGvNraCY0ayKOK+wZ88Ci+3LbiUBSOwow==";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private static Map<String, String> defaultConf = new HashMap<>();

    static {
        defaultConf.put("sign_type","RSA2");
        defaultConf.put("charset","UTF-8");
    }


    /**
     * RSA/RSA2 生成签名:sign_type与charset在paramMap传过来，否则使用defaultConf中默认的
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> rsaSign(Map<String, String> paramMap) throws Exception {
        return rsaSign(paramMap,null);
    }

    /**
     *
     * @param paramMap sign_type与charset在paramMap传过来，否则使用defaultConf中默认的，sign_type仅支持：RSA、RSA2
     * @param privateKey 生成签名的私钥，不传则使用自带的
     * @return
     * @throws Exception
     */
    public static Map<String, String> rsaSign(Map<String, String> paramMap,String privateKey) throws Exception {
        PrivateKey priKey = null;
        java.security.Signature signature = null;
        if (StringUtil.isBlank(privateKey)){
            privateKey = PRIVATE_KEY;
        }
        String signType = paramMap.get("sign_type");
        String charset = paramMap.get("charset");
        if(StringUtil.isEmpty(signType)){
            signType = defaultConf.get("sign_type");
        }
        if(StringUtil.isEmpty(charset)){
            charset = defaultConf.get("charset");
        }
        String content = MapUtil.map2OrderedString(paramMap);
        if (SIGN_TYPE_RSA.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new RuntimeException("不是支持的签名类型 : : signType=" + signType);
        }
        signature.initSign(priKey);

        if (StringUtil.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }

        byte[] signed = signature.sign();

        String sign = new String(Base64.encodeBase64(signed));
        paramMap.put("sign",sign);
        return paramMap;
    }


    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtil.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }


    private static String readText(InputStream ins) throws IOException {
        Reader reader = new InputStreamReader(ins);
        StringWriter writer = new StringWriter();

        io(reader, writer, -1);
        return writer.toString();
    }

    private static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }

        char[] buffer = new char[bufferSize];
        int amount;

        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }
}


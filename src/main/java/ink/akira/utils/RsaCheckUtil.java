package ink.akira.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * RSA的签名及验签
 * @author allen
 *
 */
public class RsaCheckUtil {

    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCr6iU2Py6Zfg/8ueLh/PVkjYOoVIK7vjYN/+CYiUDJJcv1vwDcD71V2BE+iokmlNPe4ckfb3koawP0IXClK6kiAhdUOCU9iunnO47+g1XkFcly/9e4Lg436RKgI0o0LXddOnSdz2dNMSdzJkUXrkPBZ6U2cvvW/tanBEQGo00phQIDAQAB";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private static Map<String, String> defaultConf = new HashMap<>();

    static {
        defaultConf.put("sign_type","RSA2");
        defaultConf.put("charset","UTF-8");
    }


    /**
     * 验签方法
     *
     * @return
     */
    public static boolean rsaCheck(Map<String, String> map) throws Exception {
        return rsaCheck(map,null);
    }

    /**
     *
     * @param map 包含生成签名的map
     * @param publicKey 检查签名的公钥
     * @return
     * @throws Exception
     */
    public static boolean rsaCheck(Map<String, String> map,String publicKey) throws Exception {
        java.security.Signature signature = null;
        String signType = map.get("sign_type");
        String charset = map.get("charset");
        if(StringUtils.isEmpty(signType)){
            signType = defaultConf.get("sign_type");
        }
        if(StringUtils.isEmpty(charset)){
            charset = defaultConf.get("charset");
        }
        String sign = map.get("sign");
        map.remove("sign");
        if (StringUtils.isBlank(publicKey)){
            publicKey = PUBLIC_KEY;
        }
        String content =TransferUtils.map2OrderedString(map);
        if(null == sign ){
            return false;
        }
        PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
        if (SIGN_TYPE_RSA.equals(signType)) {
            signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new Exception("不是支持的签名类型 : signType=" + signType);
        }
        signature.initVerify(pubKey);

        if (StringUtils.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        io(new InputStreamReader(ins), writer, -1);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
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


package ink.akira.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by ChenHouZhang on 2017/7/26.
 */
public class Test {

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        /*String str = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016080600179435&biz_content=%7B%22goods_type%22%3A%221%22%2C%22out_trade_no%22%3A%22CET000000000144%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102170183982%22%2C%22subject%22%3A%22123123%3F%3F%3F%3F%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%2210.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fsupersuper.wicp.net%3A38724%2Fpayment_api%2Fpayment%2FalipayNotify&sign=XtvE3oBCcSUJFCdGivoHbUpEfracT2Ah94FfPB%2F3n8dHYeQBRQx8BHJzC01XgrcDAvTjx19qR120xo786NHWnAFHzDzT9oTpIs68Vt2ZETgyVLuNbuMwHMAr%2FhJbDPCpn2prOe0%2BFykZQarcfvRLBq01rE8RXD0Yo7sqLSpQ2WiThiwC9y7Uzj8cHzqK04BO1HsNNOFVOIcWsLcqC%2BEZE9qGN9K5uO%2BfHyGi%2FnPjwQ4viKQ7mTFHaWh9x%2BmOnsYW4Ur5al0Ed%2FePECm2QciSzSzC%2BjmmpeRSQtVF7RPNFTPddgNpJ%2BjEGf1SH6peBNDq0CYFi4yVZKeW4A95N3Ci4w%3D%3D&sign_type=RSA2&timestamp=2017-07-26+14%3A59%3A39&version=1.0";
        String[] split = str.split("&");
        for (String s : split) {
            System.out.println(s);
        }
        String decode = URLDecoder.decode("%7B%22goods_type%22%3A%221%22%2C%22out_trade_no%22%3A%22CET000000000144%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102170183982%22%2C%22subject%22%3A%22123123%3F%3F%3F%3F%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%2210.0%22%7D");
        System.out.println("decode = " + decode);
        String decode1 = URLDecoder.decode("subject%22%3A%22CET%3F%3F%3F%3F%3F%3F-%3F%3F%3F%3F%3F%3F%22%2C%22","ISO8859-1");
        System.out.println("decode1 = " + decode1);*/
//        String encode = URLEncoder.encode("你好","UTF-8");
//        String decode2 = URLDecoder.decode(encode,"UTF-8");
        String msg = "subject%22%3A%22123123%E6%88%91%E6%98%AF%E4%B8%AD%E6%96%87%22%2C%22";
        String s = URLDecoder.decode(msg, "UTF-8");
        System.out.println("s = " + s);
    }

    @org.junit.Test
    public void test2() throws UnsupportedEncodingException {
        try {
            System.out.println("abc");
            return;
        } finally {
            System.out.println("cde");
        }
    }

    @org.junit.Test
    public void test3() {
        String str = "{\"alipay_trade_query_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"che***@163.com\",\"buyer_pay_amount\":\"0.00\",\"buyer_user_id\":\"2088702082035083\",\"invoice_amount\":\"0.00\",\"out_trade_no\":\"101947900001564\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.00\",\"send_pay_date\":\"2017-11-28 15:57:33\",\"total_amount\":\"0.01\",\"trade_no\":\"2017112821001004080286985121\",\"trade_status\":\"TRADE_SUCCESS\"},\"sign\":\"y6cf/57EcUmAxQ/ouwsJpXixMQbDhWpxX6a1g2mmid68e3hvPhQHYMywPqWZq/9knFrUhQWvT4SbxxXmGjqKiLIgsBbqbX+fVd4EkaxFeUZEZ0S+YLpIFScusSn/k7eFlIU+PuBGDP8FNbHM7wMxibwQMS57rd/rH1FwSrfl/6p+a4o5r2X2HWK/92ZTmT/rTMquAE5Xnh+1J2PGeLZPm6vp+y+6Z5ZxeT0wYg5qK1Ad/0RV35a8DQXSjLZhEU+GAbbICt59k5jdWi7aud/h3MgqaX2sqb9b9dTGoQAkgskeah96cpOksqcREmA/k11dChh4qGmyo/XlOMlrirtNFQ==\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject orderNo = jsonObject.getJSONObject("orderNo");
        JSONObject orderNoB = jsonObject.getJSONObject("orderNoB");
        System.out.println("orderNo = " + orderNo);
        System.out.println("orderNoB = " + orderNoB);
    }

    @org.junit.Test
    public void MultiSet() {
        String strWorld = "allen|rose|allen|allen|jack|rose";
        List<String> wordList = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(strWorld);
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);
        Set wordSet = new TreeSet(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o2.count - o1.count;
            }
        });
        for (String key : wordsMultiset.elementSet()) {
            wordSet.add(new Word(key, wordsMultiset.count(key)));
        }
    }

    class Word {
        private String word;
        private int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }


    @org.junit.Test
    public void MapSplitter() {
        String strWorld = "age:18|name:allen|school:cd";
        Map<String, String> split = Splitter.on("|").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(strWorld);
    }

    @org.junit.Test
    public void fastJson(){
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(MedalEnum.class);
        System.out.println(JSONObject.toJSONString(MedalEnum.BRONZE_MEDAL,config));
        System.out.println(JSON.toJSONString(MedalEnum.BRONZE_MEDAL,config));
    }

    enum MedalEnum {
        BRONZE_MEDAL(1,"铜牌"),
        SILVER_MEDAL(2,"银牌"),
        GOLD_MEDAL(3,"金牌"),
        ;
        private int flag;
        private String name;

        MedalEnum(int flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        public int getFlag() {
            return flag;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MedalEnum{" +
                    "flag=" + flag +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}

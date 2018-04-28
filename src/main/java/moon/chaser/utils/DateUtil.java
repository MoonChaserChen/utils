package moon.chaser.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by ChenHouZhang on 2017/11/22.
 */
public class DateUtil {
    public String format(Date date,String pattern){
        return DateFormatUtils.format(date,pattern);
    }
}

package moon.chaser.utils;


import java.io.File;

/**
 * Created by ChenHouZhang on 2017/11/3.
 */
public class FileUtil {
    /**
     * 创建随机文件名
     * @return
     */
    public static String createNewRandomFileName(){
        return RandomUtil.getLongRandomUniqueId();
    }

    /**
     * 创建随机文件名
     * @param suffix
     * @return
     */
    public static String createNewRandomFileName(String suffix){
        return createNewRandomFileName() + "." + suffix;
    }

    /**
     * 获取后缀
     * @param fileNameWithSuffix
     * @return
     */
    public static String getSuffix(String fileNameWithSuffix){
        if (fileNameWithSuffix == null){
            return null;
        }
        int i = fileNameWithSuffix.lastIndexOf(".");
        if (i == -1 || i == fileNameWithSuffix.length() - 1){
            return null;
        }
        return fileNameWithSuffix.substring(i + 1);
    }

    /**
     * 获取不带后缀文件名
     * @param fileNameWithSuffix
     * @return
     */
    public static String getFileNameWithOutSuffix(String fileNameWithSuffix){
        if (fileNameWithSuffix == null){
            return null;
        }
        int i = fileNameWithSuffix.lastIndexOf(".");
        if (i == -1 || i == fileNameWithSuffix.length() - 1){
            return fileNameWithSuffix;
        }
        return fileNameWithSuffix.substring(0,i);
    }

    /**
     * 将参数拼接为路径值
     * @param directories
     * @return
     */
    public static String joinDirectory(String... directories) {
        int length;
        if (directories == null || (length = directories.length) == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String directory = directories[i];
            if (directory == null){
                continue;
            }
            //非第一个头部不加/，有则去
            if (i != 0 && directory.startsWith("/")){
                directory = directory.substring(1);
            }
            //尾部加，无则加
            sb.append(directory.endsWith("/") ? directory : directory + "/");
        }
        return sb.toString();
    }

    /**
     * 将参数拼接为路径并创建此路径
     * @param directories
     * @return
     */
    public static String joinDirectoryAndCreate(String... directories) {
        String directoryStr = joinDirectory(directories);
        File directory = new File(directoryStr);
        if (!directory.isDirectory()){
            directory.mkdirs();
        }
        return directoryStr;
    }
}

package com.chen.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by chenxianglin on 2017/12/27.
 * Class note:
 */

public class DimensTools {
    private static final String TEMPLATE_DIMENS_FILE_NAME = "__do_not_modify_dimens.xml";
//    private static final String TEMPLATE_STRING_FILE_NAME = "__do_not_modify_strings.xml";
    private static final int LOOP = 5;
    private static final String COMMENT = "<!--  auto generated by DimensTools, do not modify or add anything, otherwise, your change will be lost!!! -->\r\n";
    private static final DecimalFormat df = new DecimalFormat("#.##");
    /**
     * 源文件
     *
     */
    static String templateFilePath = "./res/values-nodpi-1920x1080/" + TEMPLATE_DIMENS_FILE_NAME;

    // add new item by order.
    static String filePath800x480 = 	"./res/values-nodpi-800x480/";
    static String filePath800x432 = 	"./res/values-nodpi-800x432/";
    static String filePath800x444 = 	"./res/values-nodpi-800x444/";
    static String filePath854x480 = 	"./res/values-nodpi-854x480/";
    static String filePath791x480 = 	"./res/values-nodpi-791x480/";
    static String filePath960x540 = 	"./res/values-nodpi-960x540/";
    static String filePath960x640 = 	"./res/values-nodpi-960x640/";
    static String filePath1024x720 = 	"./res/values-nodpi-1024x720/";
    static String filePath1024x600 = 	"./res/values-nodpi-1024x600/";
    static String filePath1024x575 = 	"./res/values-nodpi-1024x575/";
    static String filePath1152x648 = 	"./res/values-nodpi-1152x648/";
    static String filePath1184x720 = 	"./res/values-nodpi-1184x720/";
    static String filePath1196x720 = 	"./res/values-nodpi-1196x720/";
    static String filePath1196x768 = 	"./res/values-nodpi-1196x768/";
    // Lenovo K860i
    static String filePath1200x720 =	"./res/values-nodpi-1200x720/";
    static String filePath1203x676 = 	"./res/values-nodpi-1203x676/";
    static String filePath1216x684 = 	"./res/values-nodpi-1216x684/";
    // 10MOONS MBX board (10moons) 1220x690_160
    static String filePath1220x690 = 	"./res/values-nodpi-1220x690/";
    static String filePath1228x691 =	"./res/values-nodpi-1228x691/";
    // wobo
    static String filePath1241x698 = 	"./res/values-nodpi-1241x698/";
    // wobo 1267x712_160
    static String filePath1267x712 = 	"./res/values-nodpi-1267x712/";
    static String filePath1278x718 = 	"./res/values-nodpi-1278x718/";
    // HMP8100_ATV_93 1280x656_213 飞利浦
    static String filePath1280x656 = 	"./res/values-nodpi-1280x656/";
    static String filePath1280x720 = 	"./res/values-nodpi-1280x720/";
    static String filePath1280x736 = 	"./res/values-nodpi-1280x736/";
    static String filePath1280x719 = 	"./res/values-nodpi-1280x719/";
    static String filePath1280x800 = 	"./res/values-nodpi-1280x800/";
    static String filePath1280x672 = 	"./res/values-nodpi-1280x672/";
    static String filePath1366x768 = 	"./res/values-nodpi-1366x768/";
    static String filePath1794x1080 = 	"./res/values-nodpi-1794x1080/";
    static String filePath1776x1080 = 	"./res/values-nodpi-1776x1080/";
    static String filePath1820x1030 = 	"./res/values-nodpi-1820x1030/";
    // Revue 1832x1028_320
    static String filePath1832x1028 = 	"./res/values-nodpi-1832x1028/";
    // MBX DVBT reference board (c03ref)
    static String filePath1840x1060 = 	"./res/values-nodpi-1840x1060/";
    // Revue 1916x1076_320
    static String filePath1916x1076 = 	"./res/values-nodpi-1916x1076/";
    static String filePath1916x1080 = 	"./res/values-nodpi-1916x1080/";
    // tp-link 1920x1008_240
    static String filePath1920x1008 = 	"./res/values-nodpi-1920x1008/";
    static String filePath1920x1128 = 	"./res/values-nodpi-1920x1128/";
    static String filePath1920x1152 = 	"./res/values-nodpi-1920x1152/";
    static String filePath2048x1460 = 	"./res/values-nodpi-2048x1460/";
    //2560x1600
    static String filePath2560x1600 = 	"./res/values-nodpi-2560x1600/";
    static String filePath2560x1532 = 	"./res/values-nodpi-2560x1532/";
    static String filePath2560x1536 = 	"./res/values-nodpi-2560x1536/";
    // 4k
    static String filePath3840x2160 = 	"./res/values-nodpi-3840x2160/";

    public static void main(String[] args) {
        createTemplateFile(templateFilePath);
//        RewriteFile(filePath1280x720, 1080 / 720f);
//        RewriteFile(filePath1280x719, 1080 / 719f);
//        RewriteFile(filePath1280x736, 1080 / 736f);
//        RewriteFile(filePath1280x800, 1080 / 800f);
//        RewriteFile(filePath1280x672, 1080 / 672f);
//        RewriteFile(filePath1280x656, 1080 / 656f);
//
//        RewriteFile(filePath854x480, 1080 / 480f);
//        RewriteFile(filePath800x480, 1080 / 480f);
//        RewriteFile(filePath800x432, 1080 / 432f);
//        RewriteFile(filePath800x444, 1080 / 444f);
//        RewriteFile(filePath791x480, 1080 / 480f);
//        RewriteFile(filePath960x540, 1080 / 540f);
//        RewriteFile(filePath960x640, 1080 / 640f);
//
//        RewriteFile(filePath1024x720, 1080 / 720f);
//        RewriteFile(filePath1024x600, 1080 / 600f);
//        RewriteFile(filePath1024x575, 1080 / 575f);
//        RewriteFile(filePath1184x720, 1080 / 720f);
//        RewriteFile(filePath1196x768, 1080 / 768f);
//        RewriteFile(filePath1366x768, 1080 / 768f);
//        RewriteFile(filePath1216x684, 1080 / 684f);
//        RewriteFile(filePath1278x718, 1080 / 718f);
//        RewriteFile(filePath1267x712, 1080 / 712f);
//        RewriteFile(filePath1220x690, 1080 / 690f);
//
//        RewriteFile(filePath2048x1460, 1080 / 1460f);
//        RewriteFile(filePath1920x1152, 1080 / 1152f);
//        RewriteFile(filePath1920x1128, 1080 / 1128f);
//        RewriteFile(filePath1916x1080, 1080 / 1080f);
//        RewriteFile(filePath1916x1076, 1080 / 1076f);
//
//        RewriteFile(filePath1820x1030, 1080 / 1030f);
//        RewriteFile(filePath1840x1060, 1080 / 1060f);
//
//        RewriteFile(filePath1832x1028, 1080 / 1028f);
//
//        RewriteFile(filePath1200x720, 1080 / 720f);
//        RewriteFile(filePath1196x720, 1080 / 720f);
//
//        RewriteFile(filePath1241x698, 1080 / 698f);
//        RewriteFile(filePath1228x691, 1080 / 691f);
//        RewriteFile(filePath1203x676, 1080 / 676f);
//        RewriteFile(filePath1152x648, 1080 / 648f);

        RewriteFile(filePath1920x1008, 1080 / 1008f);
        RewriteFile(filePath3840x2160, 1080 / 2160f);
        RewriteFile(filePath2560x1600, 1080 / 1600f);
        RewriteFile(filePath2560x1532, 1080 / 1532f);
        RewriteFile(filePath2560x1536, 1080 / 1536f);

        RewriteFile(filePath1794x1080, 1080 / 1080f);
        RewriteFile(filePath1776x1080, 1080 / 1080f);

    }

    public static void createTemplateFile(String filePath) {
        int startPx = -100;
        int endPx = 2000;

        try {
            File file = new File(filePath);
            mkResFile(file.getPath());
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<resources>\r\n");

            int loop = 0;
            for (int px = startPx ; px <= endPx; px ++) {
                if (loop % LOOP == 0) {
                    bw.write(COMMENT);
                }
                if (px < 0 ) {
                    bw.write("<dimen name=\"px_minus_" + (-px) + "\">" + px + "px</dimen>\r\n");
                } else {
                    bw.write("<dimen name=\"px" + (px) + "\">" + px + "px</dimen>\r\n");
                }

                loop++;
            }

            bw.write("</resources>\r\n");
            bw.flush();
            bw.close();

//            fw = new FileWriter(file.getParentFile().getPath() + "/" + TEMPLATE_STRING_FILE_NAME);
//            bw = new BufferedWriter(fw);
//
//            bw.write(COMMENT);
//            bw.write("<resources>\n\t<string name=\"values_dir\">"
//                    + (file.getParentFile().getName())
//                    + "</string>\n</resources>");
//            bw.flush();
//            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 读取文件 生成缩放后字符串 */
    public static String convertStreamToString(String filepath, float f) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filepath));
            String line = null;
            String endmark = "px</dimen>";
            String startmark = ">";
            int lineCount = 0;
            while ((line = bf.readLine()) != null) {
                if (line.startsWith("<!--")) {
                    continue;
                }

                if (line.contains(endmark)) {
                    int end = line.lastIndexOf(endmark);
                    int start = line.indexOf(startmark);
                    String stpx = line.substring(start + 1, end);
                    int px = Integer.parseInt(stpx);
                    double newpx = (double)(px / f);
                    newpx = convert(newpx);
                    String newline = line.replace(px + "px", newpx + "px");
                    sb.append(newline + "\r\n");

                    if (lineCount % LOOP == 0) {
                        sb.append(COMMENT);
                    }
                    lineCount++;
                } else {
                    sb.append(line + "\r\n");

                    if (lineCount % LOOP == 0) {
                        sb.append(COMMENT);
                    }
                    lineCount++;
                }
            }
            // System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    static double convert(double value){
        long l1 = Math.round(value*100);   //四舍五入
        double ret = l1/100.0;    //注意：使用100.0而不是100
        return ret;
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath
     *            要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static void mkResFile(String sPath) {
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {
            file.mkdirs();

        }
    }

    /** 存为新文件 */
    public static void RewriteFile(String filepath, float changes) {
        System.out.println(filepath + "  " + changes);
        String st = convertStreamToString(templateFilePath, changes);
        try {
            mkResFile(filepath);
            FileWriter fw = new FileWriter(filepath + TEMPLATE_DIMENS_FILE_NAME);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(st);
            bw.flush();
            bw.close();

//            fw = new FileWriter(filepath + TEMPLATE_STRING_FILE_NAME);
//            bw = new BufferedWriter(fw);

//            bw.write(COMMENT);
//            bw.write("<resources>\n\t<string name=\"values_dir\">"
//                    + (new File(filepath).getName())
//                    + "</string>\n</resources>");
//            bw.flush();
//            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}

package com.chen.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * 扩展文件管理工具类
 */
@SuppressWarnings("unused")
public class ExternalFileUtils {
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory() + File.separator;
    private static final String APP_PATH = ROOT_PATH + "chen" + File.separator;
    private static final String IMG_PATH = APP_PATH + "img" + File.separator;
    private static final String COIN_PATH = APP_PATH + "coin" + File.separator;
    private static final String VIDEO_PATH = APP_PATH + "video" + File.separator;
    private static final String CACHE_PATH = APP_PATH + ".cache" + File.separator;
    private static final String CRASH_PATH = APP_PATH + "crash" + File.separator;
    private static final String TAG = "ExternalFileUtils";

    public static void createAppDir() {
        try {
            if (isSdcardAvailable()) {
                File dir = new File(APP_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSaveDir(APP_PATH);
        getCacheImgPath();
        getCrashPath();
        getVideoPath();
        getCoinPath();
    }

    /**
     * 获取app外部存储路径
     */
    public static File getAppExternalFile() {
        if (isSdcardAvailable()) {
            return new File(APP_PATH);
        }
        return null;
    }

    public static File getAppExternalSubfolderFile(String path) {
        try {
            File appFile = getAppExternalFile();
            if (appFile != null) {
                File file = new File(appFile, path);
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        return null;
                    } else {
                        return file;
                    }
                }
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getFilesSubfolderFile(Context context, String path) {
        try {
            File file = new File(context.getFilesDir().getAbsoluteFile(), path);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                } else {
                    return file;
                }
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片缓存路径
     */
    public static File getCacheImgPath() {
        return getSaveDir(CACHE_PATH);
    }

    /**
     * 获取图片存储路径
     */
    public static File getImgPath() {
        return getSaveDir(IMG_PATH);
    }

    /**
     * 获取打赏礼物图片存储路径
     */
    public static File getCoinPath() {
        return getSaveDir(COIN_PATH);
    }

    /**
     * 获取图片存储路径
     */
    public static File getVideoPath() {
        return getSaveDir(VIDEO_PATH);
    }

    /**
     * 获取crash存储路径
     */
    public static File getCrashPath() {
        return getSaveDir(CRASH_PATH);
    }

    public static void delImgPath() {
        delFile(getCacheImgPath());
    }

    private static void delFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    delFile(f);
                }
            } else {
                file.delete();
            }
        }
    }

    public static File getSaveDir(String fileName) {
        try {
            if (isSdcardAvailable()) {
                File file = new File(fileName);
                if (!file.exists()) {
                    if (file.mkdir()) {
                        return file;
                    } else {
                        return null;
                    }
                } else {
                    if (file.isFile()) {
                        file.delete();
                        if (file.mkdir()) {
                            return file;
                        } else {
                            return null;
                        }
                    }
                }
                return file;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //创建文件夹及文件
    public static boolean createFile(String fileName) {
        try {
            File file = new File(fileName);
            return file.exists() || file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File getFile(String fileName) {
        File file = null;
        if (CheckUtil.isNonEmpty(fileName)) {
            file = new File(fileName);
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 文件是否存在
     */
    public static boolean isFileExists(String path) {
        try {
            File f = new File(path);
            return f.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否有sd卡
     */
    public static boolean isSdcardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isValidFile(File file) {
        return file != null && file.exists() && file.isFile();
    }
}

package com.grs.demo.dimens;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;


public class MakeUtils {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n";
    private static final String XML_RESOURCE_START = "<resources>\r\n";
    private static final String XML_RESOURCE_END = "</resources>\r\n";
    private static final String XML_DIMEN_TEMPLETE = "<dimen name=\"dp_%1$d\">%2$.2fdp</dimen>\r\n";
    private static final String XML_DIMEN_TEMPLETE_SP = "<dimen name=\"sp_%1$d\">%2$.2fdp</dimen>\r\n";


    private static final String XML_BASE_DPI = "<dimen name=\"base_dpi\">%ddp</dimen>\r\n";
    // 最大生成的数值
    private static final int MAX_SIZE = 720;
    // 字体最大生成的数值
    private static int MAX_SIZE_SP = 50;
    // 减少资源,分界值
    private static int divideNum = 420;

    /**
     * 生成的文件名
     */
    private static final String XML_NAME = "dimens.xml";
    private static final String TAG = MakeUtils.class.getSimpleName();

    /**
     * 生成的目标文件夹
     * 只需传宽进来就行
     * @param type     枚举类型
     * @param buildDir 生成的目标文件夹
     */
    public static void makeAll(int designWidth, DimenTypes type, String buildDir) {
        try {
            //生成规则
            final String folderName;
            if (type.getSwWidthDp() > 0) {
                //适配Android 3.2+
                folderName = "values-sw" + type.getSwWidthDp() + "dp";
            } else {
                return;
            }

            //生成目标目录
            File file = new File(buildDir + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.out.println(file.getAbsolutePath());
            //生成values文件
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + File.separator + XML_NAME);
            fos.write(makeAllDimens(type, designWidth).getBytes());
            fos.flush();
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成所有的尺寸数据
     * @param type
     * @return
     */
    private static String makeAllDimens(DimenTypes type, int designWidth) {
        float dpValue;
        String temp;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(XML_HEADER);
            sb.append(XML_RESOURCE_START);
            //备份生成的相关信息
            temp = String.format(XML_BASE_DPI, type.getSwWidthDp());
            sb.append(temp);
            for (int i = 0; i <= MAX_SIZE; i++) {
                //大于420 减少生成资源 分界
                if (i % 2 == 0 && i > divideNum) {
                    dpValue = px2dip((float) i, type.getSwWidthDp(), designWidth);
                    temp = String.format(XML_DIMEN_TEMPLETE, i, dpValue);
                    sb.append(temp);
                }
                if (i <= divideNum) {
                    dpValue = px2dip((float) i, type.getSwWidthDp(), designWidth);
                    temp = String.format(XML_DIMEN_TEMPLETE, i, dpValue);
                    sb.append(temp);
                }
            }
            for (int i = 0; i <= MAX_SIZE_SP; i++) {

                dpValue = px2dip((float) i, type.getSwWidthDp(), designWidth);
                temp = String.format(XML_DIMEN_TEMPLETE_SP, i, dpValue);
                sb.append(temp);
            }


            sb.append(XML_RESOURCE_END);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static float px2dip(float pxValue, int sw, int designWidth) {
        float dpValue = (pxValue / (float) designWidth) * sw;
        BigDecimal bigDecimal = new BigDecimal(dpValue);
        float finDp = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return finDp;
    }

}

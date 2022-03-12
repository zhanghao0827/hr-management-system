package per.hr.resource.manage.sys.utils;

import java.util.Arrays;

public class StringUtils {

    //disposition = form-data; name="file"; filename=""
    public static boolean isDownload(String disposition) {
        String[] split = disposition.split(";");// [form-data, name="file", filename=""]
        System.out.println(Arrays.toString(split));
        if (split.length == 3) {
            if (split[2].equals(" filename=\"\"")) {//注意空格
                return true;
            }
        }
        return false;
    }


}

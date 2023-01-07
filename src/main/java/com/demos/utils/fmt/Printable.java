package com.demos.utils.fmt;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

public class Printable {

    private static final int DEFAULT_COUNT = 40;

    public static String joinBorderLine(String context, String chr) {
        String borderLine = Strings.repeat(chr, DEFAULT_COUNT);
        return StringUtils.join(borderLine, StringUtils.SPACE, context, StringUtils.SPACE, borderLine);
    }
}

package com.demos.utils.fmt;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.compare.ComparableUtils;

import java.util.List;

public class Printable {

    private static final int DEFAULT_COUNT = 40;

    private List<String> lines = Lists.newArrayList();

    private int width;

    private String border;

    private int larWidth = 1;

    private int spaceNum = 1;

    private Position position = Position.CENTER;

    private Printable() {
    }

    public static String joinBorderLine(String context, String chr) {
        String borderLine = Strings.repeat(chr, DEFAULT_COUNT);
        return StringUtils.join(borderLine, StringUtils.SPACE, context, StringUtils.SPACE, borderLine);
    }

    public static Printable start() {
        return new Printable();
    }

    public Printable position(Position position) {
        this.position = position;
        return this;
    }

    public Printable width(int width) {
        this.width = width;
        return this;
    }

    public Printable border(String str) {
        this.border = str;
        return this;
    }

    public Printable larWidth(int larWidth) {
        this.larWidth = larWidth;
        return this;
    }

    public Printable spaceNum(int spaceNum) {
        this.spaceNum = spaceNum;
        return this;
    }

    public Printable line(String content) {
        this.lines.add(content);
        return this;
    }

    public String build() {
        lines.stream().mapToInt(String::length).max().ifPresent(width -> {
            if ((width + (this.larWidth + this.spaceNum) * 2) > this.width) {
                this.width = width;
            }
        });
        StringBuilder result = new StringBuilder(this.width * lines.size());
        result.append(Strings.repeat(this.border, this.width)).append(StringUtils.LF);
        String larBorder = Strings.repeat(this.border, this.larWidth);
        String spaces = Strings.repeat(StringUtils.SPACE, this.spaceNum);
        switch (this.position) {
            case LEFT -> lines.forEach(
                    str -> result
                            .append(larBorder)
                            .append(spaces)
                            .append(str)
                            .append(Strings.repeat(StringUtils.SPACE,
                                    this.width - (this.larWidth + this.spaceNum) * 2 - str.length()))
                            .append(spaces)
                            .append(larBorder)
                            .append(StringUtils.LF)
            );
            case CENTER -> lines.forEach(
                    str -> result
                            .append(larBorder)
                            .append(spaces)
                            .append(StringUtils.center(str, this.width - (this.larWidth + this.spaceNum) * 2))
                            .append(spaces)
                            .append(larBorder)
                            .append(StringUtils.LF)
            );
            case RIGHT -> lines.forEach(
                    str -> result
                            .append(larBorder)
                            .append(spaces)
                            .append(Strings.repeat(StringUtils.SPACE,
                                    this.width - (this.larWidth + this.spaceNum) * 2 - str.length()))
                            .append(str)
                            .append(spaces)
                            .append(larBorder)
                            .append(StringUtils.LF)
            );
        }
        result.append(Strings.repeat(this.border, this.width));
        return result.toString();
    }

    public enum Position {
        LEFT, RIGHT, CENTER,
        ;
    }
}

package com.copower.pmcc.assess.common.enums.word;

/**
 * @Auther: zch
 * @Date: 2019/1/25 17:37
 * @Description:
 */
public enum GenerateReportEnum {
    JUDGEOBJECTIMG(200,100,200,100,"常量图片宽度")
    ;
    private double width;
    private double height;
    private double top;
    private double left;
    private String dir;

    GenerateReportEnum(double width, double height, double top, double left, String dir) {
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
        this.dir = dir;
    }

    public double getLeft() {
        return left;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getTop() {
        return top;
    }
}

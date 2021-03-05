package com.uestc.virus.common;

import java.text.DecimalFormat;

public class CoordinateUtil {

    static DecimalFormat df=new DecimalFormat("0.000000");

    public static double lngChange(int i){
        return 103.933116+Double.parseDouble(df.format((double)(i - 400)/70000));
    }

    public static double latChange(int i){
        return 30.750244+Double.parseDouble(df.format((double)(i - 400)/70000));
    }
}

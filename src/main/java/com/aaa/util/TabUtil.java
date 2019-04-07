package com.aaa.util;

import com.aaa.entity.Circle;
import com.aaa.entity.Clable;
import com.aaa.service.CircleService;

import java.util.List;

public class TabUtil {
    public static  List<Circle> tabul(List<Clable> clables, Integer si, CircleService cs){
        Integer[] clable = new Integer[si];
        for (int i = 0; i < si; i++) {
            clable[i] = clables.get(i).getClableid();
        }
        Integer[] clabless = new Integer[]{-1};
        clable = clable.length == 0 ? clabless : clable;
        return cs.diffTimeBy(clable);
    }
}

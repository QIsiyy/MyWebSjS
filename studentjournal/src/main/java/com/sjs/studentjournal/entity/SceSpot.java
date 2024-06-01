package com.sjs.studentjournal.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SceSpot {
    private int id;
    private String name;
    private byte[] image;
    private int thumbs;
    private int popularity;

    //
    public int distance;   //最短距离
    public boolean flag;
    public static ArrayList<Integer> transfer;  //中转点数组
    private int V;    //顶点个数
    private int E;    //边的个数
    private int[][] adj;  //邻接矩阵
    private int[] dis;    //所有点的最短距离



}

package com.sjs.studentjournal.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Facility {
    private int id;
    List<Facility> adjcentvertices=new ArrayList<Facility>();
    private int type;


}

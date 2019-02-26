package com.yueny.example.elasticsearch.es.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder
public class LogModel {
    private long pk;
    /**
     * 系统名称
     */
    private String systemName;
    private String host;

    //日志描述
    private String desc;
    @Singular
    private List<Integer> catIds;

//    public LogModel(){
//        Random random = new Random();
//        this.id = Math.abs(random.nextLong());
//        int subId = Math.abs(random.nextInt());
//        this.subId = subId;
//        List<Integer> list = new ArrayList<Integer>(5);
//        for(int i=0;i<5;i++){
//            list.add(Math.abs(random.nextInt()));
//        }
//        this.catIds = list;
//        this.systemName = subId%1 == 0?"oa":"cms";
//        this.host = subId%1 == 0?"10.0.0.1":"10.2.0.1";
//        this.desc = "中文" + UUID.randomUUID().toString();
//    }

}

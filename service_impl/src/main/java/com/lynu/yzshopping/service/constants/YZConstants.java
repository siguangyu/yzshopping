package com.lynu.yzshopping.service.constants;

public class YZConstants {

    //默认为第一页，页大小为50
    public static final int PAGE = 1;
    public static final int PAGE_SIZE = 50;

    //爬取的商品可以进行收藏，映射表中的状态码为1
    //积分商城里的数据可以兑换，映射表中的状态码为2
    public static final int SAVE_STATUS = 1;
    public static final int EXCHANGE_STATUS = 2;


    //用户-商品映射表中的交易状态，处理中为0，交易完成为1
    public static final int TRANING_STATUS=0;
    public static final int TRAN_OK_STATUS=1;


    //积分表操作标志 1为+，2为-
    public static final String PLUS_SIGN = "1";
    public static final String MINUS_SIGN = "2";

    //签到所加积分数量
    public static final int SIGNED_SCORE_NUMBER=2;

    //积分商城是否下架，下架为1，不下架为0
    public static final int XIAJIA_NO=0;
    public static final int XISAJIA_YES=1;



}

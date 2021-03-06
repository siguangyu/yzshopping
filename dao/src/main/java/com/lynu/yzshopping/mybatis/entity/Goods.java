package com.lynu.yzshopping.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.id
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.picture_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private String pictureUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.good_name
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private String goodName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.price
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.good_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private String goodUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_time
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.id
     *
     * @return the value of goods.id
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.id
     *
     * @param id the value for goods.id
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.picture_url
     *
     * @return the value of goods.picture_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.picture_url
     *
     * @param pictureUrl the value for goods.picture_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.good_name
     *
     * @return the value of goods.good_name
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.good_name
     *
     * @param goodName the value for goods.good_name
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.price
     *
     * @return the value of goods.price
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.price
     *
     * @param price the value for goods.price
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.good_url
     *
     * @return the value of goods.good_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public String getGoodUrl() {
        return goodUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.good_url
     *
     * @param goodUrl the value for goods.good_url
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.create_time
     *
     * @return the value of goods.create_time
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.create_time
     *
     * @param createTime the value for goods.create_time
     *
     * @mbggenerated Tue Apr 09 15:21:44 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
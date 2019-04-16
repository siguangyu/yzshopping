package com.lynu.yzshopping.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class UserGoodMapping implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.user_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.goods_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.address_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private Integer addressId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.transaction_status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private String transactionStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_good_mapping.create_time
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.id
     *
     * @return the value of user_good_mapping.id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.id
     *
     * @param id the value for user_good_mapping.id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.user_id
     *
     * @return the value of user_good_mapping.user_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.user_id
     *
     * @param userId the value for user_good_mapping.user_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.goods_id
     *
     * @return the value of user_good_mapping.goods_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.goods_id
     *
     * @param goodsId the value for user_good_mapping.goods_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.address_id
     *
     * @return the value of user_good_mapping.address_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.address_id
     *
     * @param addressId the value for user_good_mapping.address_id
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.status
     *
     * @return the value of user_good_mapping.status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.status
     *
     * @param status the value for user_good_mapping.status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.transaction_status
     *
     * @return the value of user_good_mapping.transaction_status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.transaction_status
     *
     * @param transactionStatus the value for user_good_mapping.transaction_status
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_good_mapping.create_time
     *
     * @return the value of user_good_mapping.create_time
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_good_mapping.create_time
     *
     * @param createTime the value for user_good_mapping.create_time
     *
     * @mbggenerated Tue Apr 16 16:12:42 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
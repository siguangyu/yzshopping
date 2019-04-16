package com.lynu.yzshopping.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.user_id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.score_total
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private String scoreTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.operation_number
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private String operationNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.operation_sign
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private String operationSign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.operation_describe
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private String operationDescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.create_time
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.id
     *
     * @return the value of score.id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.id
     *
     * @param id the value for score.id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.user_id
     *
     * @return the value of score.user_id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.user_id
     *
     * @param userId the value for score.user_id
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.score_total
     *
     * @return the value of score.score_total
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public String getScoreTotal() {
        return scoreTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.score_total
     *
     * @param scoreTotal the value for score.score_total
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setScoreTotal(String scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.operation_number
     *
     * @return the value of score.operation_number
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public String getOperationNumber() {
        return operationNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.operation_number
     *
     * @param operationNumber the value for score.operation_number
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.operation_sign
     *
     * @return the value of score.operation_sign
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public String getOperationSign() {
        return operationSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.operation_sign
     *
     * @param operationSign the value for score.operation_sign
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.operation_describe
     *
     * @return the value of score.operation_describe
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public String getOperationDescribe() {
        return operationDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.operation_describe
     *
     * @param operationDescribe the value for score.operation_describe
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setOperationDescribe(String operationDescribe) {
        this.operationDescribe = operationDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.create_time
     *
     * @return the value of score.create_time
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.create_time
     *
     * @param createTime the value for score.create_time
     *
     * @mbggenerated Tue Apr 02 13:24:16 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
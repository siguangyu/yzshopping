package com.lynu.yzshopping.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: data-interface
 * @description: 访问日志
 * @author: houyu
 * @create: 2018-12-06 20:05
 */
@Entity
@Table(name = "VisitLog")
@EntityListeners(AuditingEntityListener.class)
public class VisitLog {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "id")
    private String id;

    private String ip;

    private String url;

    private String param;

    private String userBrowser;

    private Integer processingState;           // 处理状态: 0(处理失败)   1(处理成功)   2(不处理:频繁请求)

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;


    /**
     * =================================================================================
     */

    public VisitLog() {
    }

    public VisitLog(String ip, String url, String param, String userBrowser, Integer processingState) {
        this.ip = ip;
        this.url = url;
        this.param = param;
        this.userBrowser = userBrowser;
        this.processingState = processingState;
    }

    /**
     * =================================================================================
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getUserBrowser() {
        return userBrowser;
    }

    public void setUserBrowser(String userBrowser) {
        this.userBrowser = userBrowser;
    }

    public Integer getProcessingState() {
        return processingState;
    }

    public void setProcessingState(Integer processingState) {
        this.processingState = processingState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * =================================================================================
     */

    @Override
    public String toString() {
        return "VisitLog{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", param='" + param + '\'' +
                ", userBrowser='" + userBrowser + '\'' +
                ", processingState=" + processingState +
                ", createDate=" + createDate +
                '}';
    }
}

package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 通知
 */
@Entity
@Table(name = "mto_message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 所属用户Id
     */
    @Column(name = "user_id")
    private long userId;

    /**
     * 消息来源用户Id
     */
    @Column(name = "from_id")
    private long fromId;

    /**
     * 事件类型 {@link com.blog.base.lang.Consts#MESSAGE_EVENT_COMMENT}
     */
    private int event; // 事件

    /**
     * 关联文章ID
     */
    @Column(name = "post_id")
    private long postId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /**
     * 阅读状态 {@link com.blog.base.lang.Consts#UNREAD}
     */
    private int status;
}

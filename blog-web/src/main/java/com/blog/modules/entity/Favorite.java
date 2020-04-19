package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 喜欢/收藏
 */
@Entity
@Table(name = "mto_favorite", indexes = {
        @Index(name = "IK_USER_ID", columnList = "user_id")
})
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private long userId;

    /**
     * 内容ID
     */
    @Column(name = "post_id")
    private long postId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}

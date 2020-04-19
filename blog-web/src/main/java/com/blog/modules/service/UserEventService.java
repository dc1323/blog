package com.blog.modules.service;

import com.blog.base.lang.Consts;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Set;

public interface UserEventService {
    /**
     * 自增发布文章数
     * @param userId
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityPost(Long userId, boolean plus);

    /**
     * 自增评论数
     * @param userId
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityComment(Long userId, boolean plus);

    /**
     * 批量自动评论数
     * @param userIds
     * @param plus
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityComment(Set<Long> userIds, boolean plus);
}

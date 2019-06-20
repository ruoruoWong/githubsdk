package com.tpig.githubsdk.request;

import lombok.Data;

/**
 * Created by wangruoruo on 2019/6/20.
 */
@Data
public class SubmitMergeRequest {

    /**
     *
     */
    private String head;

    /**
     *
     */
    private String base;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String comment;

}

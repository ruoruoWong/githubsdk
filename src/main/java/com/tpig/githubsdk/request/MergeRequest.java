package com.tpig.githubsdk.request;

import lombok.Data;

/**
 * Created by wangruoruo on 2019/6/20.
 */
@Data
public class MergeRequest {

    private int pullRequestId;

    private String comment;

}

package com.tpig.githubsdk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangruoruo on 2019/6/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitMergeAndMergeRequest {

    private SubmitMergeRequest submitMergeRequest;

    private MergeRequest mergeRequest;
}

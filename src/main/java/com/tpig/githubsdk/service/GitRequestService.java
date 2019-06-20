package com.tpig.githubsdk.service;

import com.tpig.githubsdk.client.GitClient;
import com.tpig.githubsdk.exception.GitRequestException;
import com.tpig.githubsdk.request.MergeRequest;
import com.tpig.githubsdk.request.SubmitMergeRequest;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public interface GitRequestService {

    /**
     * @param gitClient
     * @param submitMergeRequest
     * @return
     * @throws GitRequestException
     */
    int submitPullRequest(GitClient gitClient, SubmitMergeRequest submitMergeRequest) throws GitRequestException;

    /**
     * @param gitClient
     * @param mergeRequest
     * @throws GitRequestException
     */
    void doMerge(GitClient gitClient, MergeRequest mergeRequest) throws GitRequestException;

}

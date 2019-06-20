package com.tpig.githubsdk.enums;

import com.google.gson.Gson;
import com.tpig.githubsdk.client.GitClient;
import com.tpig.githubsdk.exception.GitRequestException;
import com.tpig.githubsdk.request.MergeRequest;
import com.tpig.githubsdk.request.SubmitMergeAndMergeRequest;
import com.tpig.githubsdk.request.SubmitMergeRequest;
import com.tpig.githubsdk.service.GitRequestService;
import com.tpig.githubsdk.service.GitRequestServiceImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public enum GitRequestEnum {

    SUBMIT_PULL_REQUEST("pull_request") {
        public Object action(GitClient gitClient, String parameterJson, ActionCallback actionCallback) {
            Gson gson = new Gson();
            SubmitMergeRequest submitMergeRequest = gson.fromJson(parameterJson, SubmitMergeRequest.class);
            try {
                GitRequestService gitRequestService = new GitRequestServiceImpl();
                int pullRequestId = gitRequestService.submitPullRequest(gitClient, submitMergeRequest);
                actionCallback.success(pullRequestId);
                return pullRequestId;
            } catch (GitRequestException e) {
                actionCallback.failed(e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }, DO_MERGE("approve_merge") {
        public Object action(GitClient gitClient, String parameterJson, ActionCallback actionCallback) {
            Gson gson = new Gson();
            MergeRequest mergeRequest = gson.fromJson(parameterJson, MergeRequest.class);
            try {
                GitRequestService gitRequestService = new GitRequestServiceImpl();
                gitRequestService.doMerge(gitClient, mergeRequest);
            } catch (GitRequestException e) {
                e.printStackTrace();
            }
            return null;

        }
    }, SUBMIT_REQUEST_AND_MERGE("pull_request -> pull_request") {
        @Override
        public Object action(GitClient gitClient, String parameterJson, ActionCallback actionCallback) {
            Gson gson = new Gson();
            SubmitMergeAndMergeRequest submitMergeAndMergeRequest = gson.fromJson(parameterJson, SubmitMergeAndMergeRequest.class);
            GitRequestService gitRequestService = new GitRequestServiceImpl();
            try {
                int pullRequestId = gitRequestService.submitPullRequest(gitClient, submitMergeAndMergeRequest.getSubmitMergeRequest());
                submitMergeAndMergeRequest.getMergeRequest().setPullRequestId(pullRequestId);
                gitRequestService.doMerge(gitClient, submitMergeAndMergeRequest.getMergeRequest());
            } catch (GitRequestException e) {
                e.printStackTrace();
            }
            return null;
        }
    }, UNKNOW("unknow") {
        public Object action(GitClient gitClient, String parameterJson, ActionCallback actionCallback) {
            return null;
        }
    };

    private String code;

    GitRequestEnum(String code) {
        this.code = code;
    }

    public static GitRequestEnum getRequestEnumByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return UNKNOW;
        }
        for (GitRequestEnum gitRequestEnum : GitRequestEnum.values()) {
            if (StringUtils.equals(gitRequestEnum.code, code)) {
                return gitRequestEnum;
            }
        }
        return UNKNOW;
    }

    public abstract Object action(GitClient gitClient, String parameterJson, ActionCallback actionCallback);

    public interface ActionCallback {

        void success(Object response);

        void failed(String message);

    }

}

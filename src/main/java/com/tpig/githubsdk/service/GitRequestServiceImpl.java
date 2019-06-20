package com.tpig.githubsdk.service;

import com.tpig.githubsdk.client.GitClient;
import com.tpig.githubsdk.exception.GitRequestException;
import com.tpig.githubsdk.request.MergeRequest;
import com.tpig.githubsdk.request.SubmitMergeRequest;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;

import java.io.IOException;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public class GitRequestServiceImpl implements GitRequestService {

    /**
     * @param gitClient
     * @param submitMergeRequest
     * @return
     * @throws GitRequestException
     */
    public int submitPullRequest(GitClient gitClient, SubmitMergeRequest submitMergeRequest) throws GitRequestException {
        RepositoryId repo = new RepositoryId(gitClient.getRepoOwner(), gitClient.getRepoName());
        GitHubClient client = StringUtils.isEmpty(gitClient.getEnterpriseUrl()) ? new GitHubClient() : new GitHubClient(gitClient.getEnterpriseUrl());
        client.setCredentials(gitClient.getUsername(), gitClient.getPassword());
        PullRequestService pullRequestService = new PullRequestService(client);
        PullRequest request = new PullRequest();
        request.setTitle(submitMergeRequest.getTitle());
        request.setBody(submitMergeRequest.getComment());
        request.setHead(new PullRequestMarker().setLabel(submitMergeRequest.getHead()));
        request.setBase(new PullRequestMarker().setLabel(submitMergeRequest.getBase()));
        try {
            return pullRequestService.createPullRequest(repo, request).getNumber();
        } catch (IOException e) {
            throw new GitRequestException(e);
        }
    }

    /**
     * @param gitClient
     * @param mergeRequest
     * @throws GitRequestException
     */
    public void doMerge(GitClient gitClient, MergeRequest mergeRequest) throws GitRequestException {
        RepositoryId repo = new RepositoryId(gitClient.getRepoOwner(), gitClient.getRepoName());
        PullRequestService pullRequestService = getService(gitClient);
        try {
            pullRequestService.merge(repo, mergeRequest.getPullRequestId(), mergeRequest.getComment());
        } catch (IOException e) {
            throw new GitRequestException(e);
        }
    }

    /**
     * @param gitClient
     * @return
     */
    private PullRequestService getService(GitClient gitClient) {
        GitHubClient client = StringUtils.isEmpty(gitClient.getEnterpriseUrl()) ? new GitHubClient() : new GitHubClient(gitClient.getEnterpriseUrl());
        client.setCredentials(gitClient.getUsername(), gitClient.getPassword());
        return new PullRequestService(client);
    }
}

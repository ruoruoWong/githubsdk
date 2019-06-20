package com.tpig.githubsdk;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public class AuthenticateTest {

    @Test
    public void login_test() {
        GitHubClient client = new GitHubClient();
        client.setCredentials("446983327@qq.com", "wangyikan900301");
    }

    @Test
    public void create_pull_request_test() {

        RepositoryId repo = new RepositoryId("ruoruoWong", "git_test");

        GitHubClient client = new GitHubClient();
        client.setCredentials("446983327@qq.com", "wangyikan900301");
        PullRequestService pullRequestService = new PullRequestService(client);

        PullRequest request = new PullRequest();
        request.setBody("a fix");
        request.setTitle("this is a fix");
        request.setHead(new PullRequestMarker().setLabel("Rabbit"));
        request.setBase(new PullRequestMarker().setLabel("master"));
        try {
            PullRequest request1 = pullRequestService.createPullRequest(repo, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void merge_test() {
        RepositoryId repo = new RepositoryId("ruoruoWong", "git_test");
        GitHubClient client = new GitHubClient();
        client.setCredentials("446983327@qq.com", "wangyikan900301");
        PullRequestService pullRequestService = new PullRequestService(client);
        try {
            pullRequestService.merge(repo, 1, "merge");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void submit_pull_request_test() {
        String[] args = {"-u", "446983327@qq.com", "-p", "wangyikan900301", "-ro", "ruoruoWong", "-rn", "git_test", "-aname", "pull_request", "-aparameter", "{\"head\":\"Rabbit\", \"base\":\"master\", \"title\":\"title\", \"comment\":\"comment\"}"};
        PrevService prevService = new PrevService();
        Object requestId = prevService.action(args);
        System.out.print(requestId);

    }

    @Test
    public void do_merge_test() {
        String[] args = {"-u", "446983327@qq.com", "-p", "wangyikan900301", "-ro", "ruoruoWong", "-rn", "git_test", "-aname", "approve_merge", "-aparameter", "{\"pullRequestId\":290217708, \"comment\":\"comment\"}"};
        PrevService prevService = new PrevService();
        Object requestId = prevService.action(args);
        System.out.print(requestId);

    }

    @Test
    public void submit_merge_and_merge_test() {
        String[] args = {"-u", "446983327@qq.com", "-p", "wangyikan900301", "-ro", "ruoruoWong", "-rn", "git_test", "-aname", "pull_request -> pull_request", "-aparameter", "{\n" +
                "\t\"submitMergeRequest\": {\n" +
                "\t\t\"head\": \"Rabbit\",\n" +
                "\t\t\"base\": \"master\",\n" +
                "\t\t\"title\": \"title\",\n" +
                "\t\t\"comment\": \"comment\"\n" +
                "\t},\n" +
                "\t\"mergeRequest\": {\n" +
                "\t\t\"comment\": \"comment\"\n" +
                "\t}\n" +
                "}"};
        PrevService prevService = new PrevService();
        Object requestId = prevService.action(args);
        System.out.print(requestId);
    }
}

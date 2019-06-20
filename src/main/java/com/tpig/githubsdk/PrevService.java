package com.tpig.githubsdk;

import com.tpig.githubsdk.client.GitClient;
import com.tpig.githubsdk.enums.GitRequestEnum;
import org.apache.commons.cli.*;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public class PrevService {

    private static final String USERNAME = "u";

    private static final String PASSWORD = "p";

    private static final String ENTERPRISE_URL = "e";

    private static final String REPO_OWNER = "ro";

    private static final String REPO_NAME = "rn";

    private static final String ACTION_NAME = "aname";

    private static final String ACTION_PARAMETER = "aparameter";

    public Object action(String[] args) {
        // create Options object
        Options options = new Options();
        options.addOption(USERNAME, true, "github username");
        options.addOption(PASSWORD, true, "github password");
        options.addOption(ENTERPRISE_URL, false, "enterprise url");
        options.addOption(REPO_OWNER, true, "repository owner");
        options.addOption(REPO_NAME, true, "repository name");
        options.addOption(ACTION_NAME, true, "action name[pull_request][approve_merge]");
        options.addOption(ACTION_PARAMETER, true, "action parameter");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            GitClient gitClient = GitClient.builder().username(cmd.getOptionValue(USERNAME)).password(cmd.getOptionValue(PASSWORD))
                    .enterpriseUrl(cmd.getOptionValue(ENTERPRISE_URL)).repoOwner(cmd.getOptionValue(REPO_OWNER))
                    .repoName(cmd.getOptionValue(REPO_NAME)).build();
            GitRequestEnum gitRequestEnum = GitRequestEnum.getRequestEnumByCode(cmd.getOptionValue(ACTION_NAME));
            Object result = gitRequestEnum.action(gitClient, cmd.getOptionValue(ACTION_PARAMETER), new GitRequestEnum.ActionCallback() {
                public void success(Object response) {
                }

                public void failed(String message) {
                }
            });
            return result;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}

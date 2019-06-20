package com.tpig.githubsdk.client;

import lombok.Builder;
import lombok.Data;

/**
 * Created by wangruoruo on 2019/6/20.
 */
@Builder
@Data
public class GitClient {

    private String username;

    private String password;

    /**
     *
     */
    private String enterpriseUrl;

    /**
     *
     */
    private String repoOwner;

    /**
     *
     */
    private String repoName;

}

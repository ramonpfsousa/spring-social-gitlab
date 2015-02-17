package org.springframework.social.gitlab.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.gitlab.api.profile.GitlabProfileOperations;
import org.springframework.social.gitlab.api.user.GitlabUserOperations;

/**
 * Top level operations for Gitlab.
 *
 * @author p.hoeffling
 */
public interface Gitlab extends ApiBinding {

    GitlabProfileOperations profileOperations();
    
    GitlabUserOperations userOperations();
    
    GitlabProjectOperations projectOperations();
    
}

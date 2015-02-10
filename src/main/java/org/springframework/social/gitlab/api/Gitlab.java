package org.springframework.social.gitlab.api;

import org.springframework.social.ApiBinding;

/**
 * Top level operations for Gitlab.
 *
 * @author p.hoeffling
 */
public interface Gitlab extends ApiBinding {

    GitlabProfileOperations profileOperations();
    
    GitlabUserOperations userOperations();
    
}

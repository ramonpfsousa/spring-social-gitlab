/*
 * Copyright 2015 p.hoeffling.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.gitlab.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.gitlab.api.Project;
import org.springframework.social.gitlab.api.ProjectBranch;

/**
 *
 * @author p.hoeffling
 */
public class GitlabModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public GitlabModule() {
        super("GitlabModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        
        // Project
        context.setMixInAnnotations(Project.class, ProjectMixin.class);
        context.setMixInAnnotations(Project.Permissions.class, ProjectMixin.PermissionsMixin.class);
        context.setMixInAnnotations(Project.Permissions.Access.class, ProjectMixin.PermissionsMixin.AccessMixin.class);
        context.setMixInAnnotations(Project.Namespace.class, ProjectMixin.NamespaceMixin.class);
        context.setMixInAnnotations(Project.Owner.class, ProjectMixin.OwnerMixin.class);

        // ProjectBranch
        context.setMixInAnnotations(ProjectBranch.class, ProjectBranchMixin.class);
        context.setMixInAnnotations(ProjectBranch.Commit.class, ProjectBranchMixin.CommitMixin.class);
        context.setMixInAnnotations(ProjectBranch.Commit.Author.class, ProjectBranchMixin.CommitMixin.AuthorMixin.class);
        context.setMixInAnnotations(ProjectBranch.Commit.Committer.class, ProjectBranchMixin.CommitMixin.CommitterMixin.class);
        context.setMixInAnnotations(ProjectBranch.Commit.Parent.class, ProjectBranchMixin.CommitMixin.ParentMixin.class);
    }

}

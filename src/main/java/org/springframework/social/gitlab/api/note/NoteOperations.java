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
package org.springframework.social.gitlab.api.note;

import java.util.List;

/**
 *
 * @author p.hoeffling
 */
public interface NoteOperations {

    List<Note> getProjectIssueNotes(long projectId, long issueId);
    
    Note getProjectIssueNote(long projectId, long issueId, long noteId);
    
    List<Note> getProjectSnippetNotes(long projectId, long snippetId);
    
    Note getProjectSnippetNote(long projectId, long snippetId);
}

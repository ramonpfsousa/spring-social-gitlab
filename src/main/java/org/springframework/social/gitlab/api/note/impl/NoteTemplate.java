package org.springframework.social.gitlab.api.note.impl;

import org.springframework.social.gitlab.api.GitlabApiBinding;
import org.springframework.social.gitlab.api.core.impl.AbstractGitlabOperations;
import org.springframework.social.gitlab.api.note.Note;
import org.springframework.social.gitlab.api.note.NoteOperations;

import java.net.URI;
import java.util.List;

/**
 * @author p.hoeffling
 */
public class NoteTemplate extends AbstractGitlabOperations implements NoteOperations {

    public static final String PROJECTS = "projects";
    public static final String PROJECT_ID = "{projectId}";
    public static final String ISSUES = "issues";
    public static final String ISSUE_ID = "{issueId}";
    public static final String NOTES = "notes";
    public static final String NOTE_ID = "{noteId}";
    public static final String SNIPPETS = "snippets";
    public static final String SNIPPET_ID = "{snippetId}";

    public NoteTemplate(final GitlabApiBinding gitlabApiBinding) {
        super(gitlabApiBinding);
    }

    /**
     * GET /projects/:id/issues/:issue_id/notes
     *
     * @param projectId The project id.
     * @param issueId The issue id.
     * @return A list of notes.
     */
    @Override
    public List<Note> getProjectIssueNotes(final long projectId, final long issueId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, ISSUES, ISSUE_ID, NOTES)
                .buildAndExpand(projectId, issueId)
                .toUri();

        return gitlabApiBinding.getForList(uri, Note.class);
    }

    @Override
    public Note getProjectIssueNote(final long projectId, final long issueId, final long noteId) {
        // GET /projects/:id/issues/:issue_id/notes/:note_id
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, ISSUES, ISSUE_ID, NOTES, NOTE_ID)
                .buildAndExpand(projectId, issueId, noteId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, Note.class);
    }

    @Override
    public List<Note> getProjectSnippetNotes(final long projectId, final long snippetId) {
        // GET /projects/:id/snippets/:snippet_id/notes
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, SNIPPETS, SNIPPET_ID, NOTES)
                .buildAndExpand(projectId, snippetId)
                .toUri();

        return gitlabApiBinding.getForList(uri, Note.class);
    }

    @Override
    public Note getProjectSnippetNote(final long projectId, final long snippetId, final long noteId) {
        // GET /projects/:id/snippets/:snippet_id/notes/:note_id
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, SNIPPETS, SNIPPET_ID, NOTES, NOTE_ID)
                .buildAndExpand(projectId, snippetId, noteId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, Note.class);
    }
}

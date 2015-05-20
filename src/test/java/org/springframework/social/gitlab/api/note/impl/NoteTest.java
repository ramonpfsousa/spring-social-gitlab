package org.springframework.social.gitlab.api.note.impl;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.note.Note;
import org.springframework.social.gitlab.api.note.Note.Author;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 * @author p.hoeffling
 */
public class NoteTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectIssueNotes() {
        String url = uriBuilder.api()
                .path("/projects/1/issues/2/notes")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("note-list"));

        List<Note> notes = gitlab.noteOperations().getProjectIssueNotes(1, 2);

        assertThat(notes, hasSize(2));
        assertThat(notes.get(0), instanceOf(Note.class));
    }

    @Test
    public void testGetProjectIssueNote() {
        String url = uriBuilder.api()
                .path("/projects/1/issues/2/notes/3")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("note"));

        Note note = gitlab.noteOperations().getProjectIssueNote(1, 2, 3);

        assertThat(note, is(notNullValue()));
        assertThat(note, instanceOf(Note.class));
    }

    @Test
    public void testGetProjectSnippetNotes() {
        String url = uriBuilder.api()
                .path("/projects/1/snippets/2/notes")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("note-list"));

        List<Note> notes = gitlab.noteOperations().getProjectSnippetNotes(1, 2);

        assertThat(notes, hasSize(2));
        assertThat(notes.get(0), instanceOf(Note.class));

    }

    @Test
    public void testGetProjectSnippetNote() {
        String url = uriBuilder.api()
                .path("/projects/1/snippets/2/notes/3")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("note"));

        Note note = gitlab.noteOperations().getProjectSnippetNote(1, 2, 3);

        assertThat(note, is(notNullValue()));
        assertThat(note, instanceOf(Note.class));

    }

    @Test
    public void testNoteMapping() {

        Note note = fetchSingleNote();

        assertThat(note, is(notNullValue()));
        assertThat(note.getId(),is(305L));
        assertThat(note.getBody(), is("Text of comment"));
        verifyUtcDate(note.getCreatedAt(), 2013, 10, 2, 9, 56, 3);

    }

    @Test
    public void testNestedAuthorMapping() {
        Author author = fetchSingleNote().getAuthor();

        assertThat(author, is(notNullValue()));
        assertThat(author.getId(), is(1L));
        assertThat(author.getUsername(), is("pipin"));
        assertThat(author.getEmail(), is("admin@axample.com"));
        assertThat(author.getState(), is("active"));
        assertThat(author.getName(), is("Pip"));
        verifyUtcDate(author.getCreatedAt(), 2013, 9, 30, 13, 46, 1);
    }


    private Note fetchSingleNote() {
        String url = uriBuilder.api()
                .path("/projects/1/issues/2/notes/3")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("note"));

        return gitlab.noteOperations().getProjectIssueNote(1, 2, 3);
    }
}
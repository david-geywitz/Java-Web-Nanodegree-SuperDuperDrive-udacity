package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
    }

    public int addNotes(Notes notes, Integer loggedUserId){
        if(null != loggedUserId) {
            notes.setUserId(loggedUserId);
            return notesMapper.addNotes(notes);
        }
        return 0;
    }

    public List<Notes> getNotes(Integer userId) {
        if(null != userId){
            return notesMapper.getUserNotes(userId);
        }
        return new ArrayList<Notes>();
    }

    public Notes getNote(Integer noteId){
        if(null != noteId){
            return notesMapper.getNote(noteId);
        }
        return null;
    }

    public boolean deleteNote(Integer noteId) {
        if(null != getNote(noteId)){
            try {
                notesMapper.deleteNote(noteId);
                return true;
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public int updateNotes(Notes notes, Integer loggedUserId) {
        if(null != loggedUserId){
            return notesMapper.updateNotes(notes);
        }
        return 0;
    }
}

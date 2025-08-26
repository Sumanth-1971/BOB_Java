package com.bob.masterdata.Service;

import com.bob.db.entity.StateEntity;
import com.bob.db.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    public StateEntity createState(StateEntity state) {
        try {
            state.setCreatedDate(LocalDateTime.now());
            return state;
        } catch (Exception e) {
            return null;
        }
    }

    public List<StateEntity> getAllStates() throws Exception {
        try {
            return stateRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch state");
        }
    }

    public StateEntity updateState(Long id, StateEntity state) {
        try {
            StateEntity state1=state;
            Optional<StateEntity> existingState = stateRepository.findById(id);
            if (existingState.isPresent()) {
                state.setStateId(id);
                stateRepository.save(state);
                return state;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public StateEntity deleteState(Long id) {
        try {
            if (stateRepository.existsById(id)) {
                StateEntity state=stateRepository.findById(id).get();
                stateRepository.deleteById(id);
                return state;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}

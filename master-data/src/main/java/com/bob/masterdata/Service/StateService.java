package com.bob.masterdata.Service;

import com.bob.db.entity.State;
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
    public State createState(State state) {
        try {
            state.setCreated_date(LocalDateTime.now());
            return state;
        } catch (Exception e) {
            return null;
        }
    }

    public List<State> getAllStates() throws Exception {
        try {
            return stateRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch state");
        }
    }

    public State updateState(Long id,State state) {
        try {
            State state1=state;
            Optional<State> existingState = stateRepository.findById(id);
            if (existingState.isPresent()) {
                state.setState_id(id);
                stateRepository.save(state);
                return state;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public State deleteState(Long id) {
        try {
            if (stateRepository.existsById(id)) {
                State state=stateRepository.findById(id).get();
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

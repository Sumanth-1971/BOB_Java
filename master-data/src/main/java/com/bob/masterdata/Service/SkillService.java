package com.bob.masterdata.Service;

import com.bob.db.entity.Skill;
import com.bob.db.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public Skill createSkill(Skill skill) {
        try {
            skill.setCreated_date(LocalDateTime.now());
            skillRepository.save(skill);
            return skill;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Skill> getAllSkills() throws Exception {
        try {
            return skillRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch Skills");
        }
    }

    public Skill updateSKill(Long id,Skill skill) {
        try {
            Skill skill1=skill;
            Optional<Skill> existingSkill = skillRepository.findById(id);
            if (existingSkill.isPresent()) {
                skill.setSkill_id(id);
                skillRepository.save(skill);
                return skill1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Skill deleteSkill(Long id) {
        try {
            if (skillRepository.existsById(id)) {
                Skill skill=skillRepository.findById(id).get();
                skillRepository.deleteById(id);
                return skill;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}

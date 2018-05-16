package skills;

import levels.Levels;

import java.util.Optional;
import java.util.Set;

public interface Tree {

    void updateSkillTree();

    Skill getLastSkill();

    Optional<Skill> getUnlockedSkill(String name);

    Set<Skill> getUnlockedSkills();

    void onSkillUpdate();

    void onSkillTreeRestart();

    Levels getCurrentLevel();

    void enableSkill(String skill);

    void enableAll();

}

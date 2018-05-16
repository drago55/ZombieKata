package skills;

import levels.Levels;

import java.util.Optional;
import java.util.Set;

public interface Tree {

    Set<SkillType> getListOfSkills();

    void updateSkillTree();

    Skill getLastSkill();

    Optional<Skill> getUnlockedSkill(String name);

    Set<Skill> getUnlockedSkills();

    int getNumberOfUnlockedSkills();

    void onSkillUpdate();

    void onSkillTreeRestart();

    Levels getCurrentLevel();

    void enableSkill(String skill);

    void enableAll();

}

package skills;

import levels.Levels;

import java.util.Set;

public interface Tree {

    Set<SkillType> getListOfSkills();

    void updateSkillTree();

    Skill getLastSkill();

    Set<Skill> getUnlockedSkills();

    void onSkillUpdate();

    void onSkillTreeRestart();

    Levels getCurrentLevel();

    void enableSkill(Skill skill);

    void enableAll();

}

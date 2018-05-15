package skills;

import levels.Levels;

import java.util.Set;

public interface Skill {

    Set<SkillAttributes> getListOfSkills();

    void updateSkillTree();

    SkillAttributes getLastSkill();

    Set<SkillAttributes> getUnlockedSkills();

    void onSkillUpdate();

    void onSkillTreeRestart();

    Levels getCurrentLevel();
}

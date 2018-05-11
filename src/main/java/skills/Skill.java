package skills;

public interface Skill {
    short getPotentialSkills();

    Skills getUnlockedSkills();

    void updateSkillTree(int experience);

    Skills[] getSkillTree();
}

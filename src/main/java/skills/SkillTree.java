package skills;

public class SkillTree implements Skill {
    @Override
    public short getPotentialSkills() {
        return 0;
    }

    @Override
    public Skills getUnlockedSkills() {
        return null;
    }

    @Override
    public void updateSkillTree(int experience) {

    }

    @Override
    public Skills[] getSkillTree() {
        return new Skills[0];
    }
}

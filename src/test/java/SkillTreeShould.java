import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skills.Skill;
import skills.SkillTree;
import skills.Skills;

public class SkillTreeShould {

    private Skill skill;

    @BeforeEach
    public void init() {
        Skill skill = new SkillTree();
    }

    @Test
    public void have_potential_skills() {

        Assertions.assertEquals(Skills.values(), skill.getPotentialSkills());
    }

    @Test
    public void have_unlocked_skill() {

        for (int i = 0; i < 7; i++) {
            skill.updateSkillTree(i);
        }
        Assertions.assertEquals(Skills.ACTION, skill.getUnlockedSkills());
    }

    @Test
    public void have_one_potential_skill_at_level_yellow() {

    }

    @Test
    public void have_two_potential_skill_at_level_orange() {

    }

    @Test
    public void have_tree_potential_skill_at_level_red() {

    }

    @Test
    public void have_same_skill_at_level_yellow_() {

    }

    @Test
    public void have_list_of_skills() {
        Assertions.assertEquals(Skills.values(),skill.getSkillTree());
    }
}

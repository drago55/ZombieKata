package skills;

import game.Game;
import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import survivors.Survivor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static names.StringConstants.NEW_SKILL;

public class SkillTree implements Skill {

    private Levels currentLevel;
    private Level levelSystem;
    private SkillLevel skillLevel;
    private int experience = 0;
    private Survivor survivor;
    private SkillAttributes skillAttributes;
    private int skillTreeLevelExperience = 0;
    private Set<SkillAttributes> unlockedSkills;
    private Game game;

    public SkillTree(Survivor survivor, Game game) {
        this.game = game;
        this.currentLevel = Levels.BLUE;
        this.survivor = survivor;
        this.unlockedSkills = new HashSet<>();
        levelSystem = new LevelSystem();
    }

    @Override
    public Set<SkillAttributes> getListOfSkills() {
        return Stream.of(SkillAttributes.values()).collect(Collectors.toSet());
    }

    @Override
    public void updateSkillTree() {
        this.experience++;
        this.skillTreeLevelExperience++;
        this.onSkillUpdate();
    }

    @Override
    public SkillAttributes getLastSkill() {
        long count = unlockedSkills.stream().count();
        long toLastSkill = count - 1 < 0 ? 0 : count - 1;
        return unlockedSkills.stream().skip(toLastSkill).findAny().orElse(SkillAttributes.EMPTY);
    }

    @Override
    public Set<SkillAttributes> getUnlockedSkills() {
        return this.unlockedSkills;
    }

    @Override
    public void onSkillUpdate() {
        currentLevel = levelSystem.getLevel(skillTreeLevelExperience);
        System.out.println("Total experience " + experience);
        System.out.println("Experience of skillTree " + skillTreeLevelExperience);
        System.out.println("Current level of SkillTree is  " + currentLevel);
        switch (currentLevel) {
            case YELLOW:
                unlockAndApplyAction();
                break;
            case ORANGE:
                unlockAndApplyHoard();
                unlockAndApplyFreeMove();
                break;
            case RED:
                unlockAndApplyOneMelee();
                unlockAndApplySniper();
                unlockAndApplyTough();
                break;
            default:
                break;
        }

    }

    private void unlockAndApplyTough() {
        if (experience == 129) {
            skillAttributes = SkillAttributes.TOUGH;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
            this.onSkillTreeRestart();
        }
    }

    private void unlockAndApplySniper() {
        if (experience == 86) {
            skillAttributes = SkillAttributes.SNIPER;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
            this.onSkillTreeRestart();
        }
    }

    private void unlockAndApplyOneMelee() {
        if (experience == 43) {
            skillAttributes = SkillAttributes.ONE_MELEE;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
            this.onSkillTreeRestart();
        }
    }

    private void unlockAndApplyFreeMove() {
        if (experience == 62) {
            skillAttributes = SkillAttributes.FREE_MOVE;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
        }
    }

    private void unlockAndApplyHoard() {
        if (experience == 19) {
            skillAttributes = SkillAttributes.HOARD;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
        }
    }

    private void unlockAndApplyAction() {
        if (experience == 7) {
            skillAttributes = SkillAttributes.ACTION;
            unlockedSkills.add(skillAttributes);
            skillAttributes.updateSurvivor(survivor);
            this.game.notify(survivor.getName() + NEW_SKILL + skillAttributes);
        }
    }

    @Override
    public void onSkillTreeRestart() {
        this.skillTreeLevelExperience = 0;
        this.game.notify(survivor.getName() + " has restarted skill tree");
    }

    @Override
    public Levels getCurrentLevel() {
        return this.currentLevel;
    }
}

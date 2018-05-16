package skills;

import game.Game;
import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import survivors.Survivor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static names.StringConstants.NEW_SKILL;

public class SkillTree implements Tree {

    private Levels currentLevel;
    private Level levelSystem;
    private int experience = 0;
    private Survivor survivor;
    private Skill skill;
    private int skillTreeLevelExperience = 0;
    private Set<Skill> unlockedSkills;
    private Game game;

    public SkillTree(Survivor survivor, Game game) {
        this.game = game;
        this.currentLevel = Levels.BLUE;
        this.survivor = survivor;
        this.unlockedSkills = new HashSet<>();
        levelSystem = new LevelSystem();
    }

    @Override
    public Set<SkillType> getListOfSkills() {
        return Stream.of(SkillType.values()).collect(Collectors.toSet());
    }

    @Override
    public void updateSkillTree() {
        this.experience++;
        this.skillTreeLevelExperience++;
        this.onSkillUpdate();
    }

    @Override
    public Skill getLastSkill() {
        long count = unlockedSkills.stream().count();
        long toLastSkill = count - 1 < 0 ? 0 : count - 1;
        return unlockedSkills.stream().skip(toLastSkill).findAny().orElse(new ActionSkill());
    }

    @Override
    public Optional<Skill> getUnlockedSkill(String name) {
        return unlockedSkills.stream().filter(skill1 -> skill1.getName().equalsIgnoreCase(name)).findAny();
    }

    @Override
    public Set<Skill> getUnlockedSkills() {
        return this.unlockedSkills;
    }

    @Override
    public int getNumberOfUnlockedSkills() {
        return unlockedSkills.size();
    }

    @Override
    public void onSkillUpdate() {
        currentLevel = levelSystem.getLevel(skillTreeLevelExperience);
        System.out.println("Total experience " + experience);
        System.out.println("Experience of skillTree " + skillTreeLevelExperience);
        System.out.println("Current level of SkillTree is  " + currentLevel);
        switch (currentLevel) {
            case YELLOW:
                unlockAndNotify();
                break;
            case ORANGE:
                unlockAndNotify();
                break;
            case RED:
                unlockAndNotify();
                this.onSkillTreeRestart();
                break;
            default:
                break;
        }
    }

    private void unlockAndNotify() {
        Optional<Skill> optionalSkill = Skill.getSkill(experience);
        if (optionalSkill.isPresent()) {
            skill = optionalSkill.get();
            unlockedSkills.add(skill);
            this.game.notify(survivor.getName() + NEW_SKILL + skill.toString());
        }
    }

    @Override
    public void onSkillTreeRestart() {
        this.skillTreeLevelExperience = 0;
        this.game.notify(survivor.getName() + " has restarted skill tree.");
    }

    @Override
    public Levels getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void enableSkill(String skill) {
        Optional<Skill> optionalOfSkill = getUnlockedSkill(skill);
         if (optionalOfSkill.isPresent()) {
            if (optionalOfSkill.get().isEnabled()) {
                throw new IllegalStateException("This skill is already enabled!");
            }
            optionalOfSkill.get().updateSurvivor(survivor);
            return;
        }
        throw new IllegalArgumentException("Skill is not unlocked!");
    }

    @Override
    public void enableAll() {
        unlockedSkills.stream().filter(filterSkill -> !filterSkill.isEnabled()).forEach(skill -> skill.updateSurvivor(survivor));
    }
}

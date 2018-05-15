package skills;

import survivors.Survivor;

public class YellowSkill implements SkillLevel {

    private SkillAttributes attribute;

    @Override
    public void onEnabledAttribute(Survivor survivor) {
        attribute = SkillAttributes.ACTION;
        attribute.updateSurvivor(survivor);
    }
}

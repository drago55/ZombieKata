package skills;

import survivors.Survivor;

public class BlueSkill implements SkillLevel {
    SkillAttributes attributes;

    @Override
    public void onEnabledAttribute(Survivor survivor) {
        attributes = SkillAttributes.EMPTY;
        attributes.updateSurvivor(survivor);
    }
}

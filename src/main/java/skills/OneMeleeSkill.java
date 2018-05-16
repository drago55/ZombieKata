package skills;

import survivors.Survivor;

public class OneMeleeSkill extends Skill {

    private String name = "OneMelee";
    private boolean isEnabled = false;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void updateSurvivor(Survivor survivor) {
        this.isEnabled = true;
    }
}

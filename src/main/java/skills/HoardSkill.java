package skills;

import survivors.Survivor;

public class HoardSkill extends Skill {

    private String name = "Hoard";
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
        survivor.getBag().addExtraSlot();
        this.isEnabled = true;
    }
}

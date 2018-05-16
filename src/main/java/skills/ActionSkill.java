package skills;

import survivors.Survivor;

public class ActionSkill extends Skill {

    private String name = "Action";
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
        survivor.getAction().addAdditionalAction();
        this.isEnabled = true;
    }
}

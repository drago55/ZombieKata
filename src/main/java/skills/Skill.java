package skills;


import survivors.Survivor;

public abstract class Skill {

    public abstract String getName();

    public abstract boolean isEnabled();

    @Override
    public String toString() {
        return getName();
    }

    public abstract void updateSurvivor(Survivor survivor);

    public static Skill getSkill(int experience) {
        switch (experience) {
            case 7:
                return new ActionSkill();
            case 19:
                return new HoardSkill();
            case 43:
                return new OneMeleeSkill();
            case 62:
                return new FreeMoveSkill();
            case 86:
                return new SniperSkill();
            case 129:
                return new ToughSkill();
        }
        return new ActionSkill();
    }
}

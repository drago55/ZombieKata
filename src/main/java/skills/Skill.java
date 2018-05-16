package skills;


import survivors.Survivor;

import java.util.Optional;

public abstract class Skill {

    public abstract String getName();

    public abstract boolean isEnabled();

    @Override
    public String toString() {
        return getName();
    }

    public abstract void updateSurvivor(Survivor survivor);

    public static Optional<Skill> getSkill(int experience) {
        switch (experience) {
            case 7:
                return Optional.of(new ActionSkill());
            case 19:
                return Optional.of(new HoardSkill());
            case 43:
                return Optional.of(new OneMeleeSkill());
            case 62:
                return Optional.of(new FreeMoveSkill());
            case 86:
                return Optional.of(new SniperSkill());
            case 129:
                return Optional.of(new ToughSkill());
        }
        return Optional.empty();
    }
}

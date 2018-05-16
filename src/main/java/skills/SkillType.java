package skills;

import survivors.Survivor;

public enum SkillType {
    ONE_RANGED {
        @Override
        public void updateSurvivor(Survivor survivor) {
        }
    }, ONE_MELEE {
        @Override
        public void updateSurvivor(Survivor survivor) {
        }
    }, FREE_MOVE {
        @Override
        public void updateSurvivor(Survivor survivor) {
        }
    }, ACTION {
        @Override
        public void updateSurvivor(Survivor survivor) {
            survivor.getAction().addAdditionalAction();
        }
    }, HOARD {
        @Override
        public void updateSurvivor(Survivor survivor) {
            survivor.getBag().addExtraSlot();
        }
    }, SNIPER {
        @Override
        public void updateSurvivor(Survivor survivor) {
        }
    },
    TOUGH {
        @Override
        public void updateSurvivor(Survivor survivor) {

        }
    }, EMPTY {
        @Override
        public void updateSurvivor(Survivor survivor) {
            //Represents empty attribute doesn't improve survivor
        }
    };

    public abstract void updateSurvivor(Survivor survivor);
}

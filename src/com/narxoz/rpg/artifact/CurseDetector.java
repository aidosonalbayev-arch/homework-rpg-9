package com.narxoz.rpg.artifact;

public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;

    public CurseDetector() {
        this.cursedCount = 0;
    }

    public int getCursedCount() {
        return cursedCount;
    }

    @Override
    public void visit(Weapon weapon) {
        boolean cursed = weapon.getAttackBonus() < 0 || weapon.getValue() < 50;
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] Weapon '%s': %s%n",
                weapon.getName(), cursed ? "*** CURSED — dark runes detected ***" : "clean");
    }

    @Override
    public void visit(Potion potion) {
        boolean cursed = potion.getHealing() < 0;
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] Potion '%s': %s%n",
                potion.getName(), cursed ? "*** CURSED — poison signature detected ***" : "safe");
    }

    @Override
    public void visit(Scroll scroll) {
        boolean cursed = scroll.getSpellName().toLowerCase().contains("doom")
                || scroll.getSpellName().toLowerCase().contains("curse");
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] Scroll '%s' (spell=%s): %s%n",
                scroll.getName(), scroll.getSpellName(),
                cursed ? "*** CURSED — forbidden incantation ***" : "safe");
    }

    @Override
    public void visit(Ring ring) {
        boolean cursed = ring.getMagicBonus() < 0;
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] Ring '%s': %s%n",
                ring.getName(), cursed ? "*** CURSED — corrupted enchantment ***" : "safe");
    }

    @Override
    public void visit(Armor armor) {
        boolean cursed = armor.getDefenseBonus() < 0;
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] Armor '%s': %s%n",
                armor.getName(), cursed ? "*** CURSED — hexed plating ***" : "clean");
    }
}

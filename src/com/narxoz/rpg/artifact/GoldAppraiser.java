package com.narxoz.rpg.artifact;

public class GoldAppraiser implements ArtifactVisitor {

    private int totalValue;

    public GoldAppraiser() {
        this.totalValue = 0;
    }

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public void visit(Weapon weapon) {
        int appraisedValue = weapon.getValue() + weapon.getAttackBonus() * 10;
        totalValue += appraisedValue;
        System.out.printf("  [GoldAppraiser] Weapon '%s': base=%d + attackBonus*10=%d → %d gold%n",
                weapon.getName(), weapon.getValue(), weapon.getAttackBonus() * 10, appraisedValue);
    }

    @Override
    public void visit(Potion potion) {
        int appraisedValue = potion.getValue() + potion.getHealing() * 2;
        totalValue += appraisedValue;
        System.out.printf("  [GoldAppraiser] Potion '%s': base=%d + healing*2=%d → %d gold%n",
                potion.getName(), potion.getValue(), potion.getHealing() * 2, appraisedValue);
    }

    @Override
    public void visit(Scroll scroll) {
        int appraisedValue = scroll.getValue() * 3;
        totalValue += appraisedValue;
        System.out.printf("  [GoldAppraiser] Scroll '%s' (spell=%s): base=%d × 3 → %d gold%n",
                scroll.getName(), scroll.getSpellName(), scroll.getValue(), appraisedValue);
    }

    @Override
    public void visit(Ring ring) {
        int appraisedValue = ring.getValue() + ring.getMagicBonus() * 15;
        totalValue += appraisedValue;
        System.out.printf("  [GoldAppraiser] Ring '%s': base=%d + magicBonus*15=%d → %d gold%n",
                ring.getName(), ring.getValue(), ring.getMagicBonus() * 15, appraisedValue);
    }

    @Override
    public void visit(Armor armor) {
        int appraisedValue = armor.getValue() + armor.getDefenseBonus() * 8;
        totalValue += appraisedValue;
        System.out.printf("  [GoldAppraiser] Armor '%s': base=%d + defenseBonus*8=%d → %d gold%n",
                armor.getName(), armor.getValue(), armor.getDefenseBonus() * 8, appraisedValue);
    }
}

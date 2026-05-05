package com.narxoz.rpg.artifact;

public class EnchantmentScanner implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        System.out.printf("  [EnchantmentScanner] Weapon '%s': Attack aura detected — bonus +%d, weight %d kg%n",
                weapon.getName(), weapon.getAttackBonus(), weapon.getWeight());
    }

    @Override
    public void visit(Potion potion) {
        System.out.printf("  [EnchantmentScanner] Potion '%s': Healing enchantment +%d HP, alchemical weight %d g%n",
                potion.getName(), potion.getHealing(), potion.getWeight());
    }

    @Override
    public void visit(Scroll scroll) {
        System.out.printf("  [EnchantmentScanner] Scroll '%s': Contains spell [%s], fragile parchment %d g%n",
                scroll.getName(), scroll.getSpellName(), scroll.getWeight());
    }

    @Override
    public void visit(Ring ring) {
        String tier = ring.getMagicBonus() >= 10 ? "LEGENDARY" : ring.getMagicBonus() >= 5 ? "RARE" : "COMMON";
        System.out.printf("  [EnchantmentScanner] Ring '%s': Magic resonance +%d → tier [%s]%n",
                ring.getName(), ring.getMagicBonus(), tier);
    }

    @Override
    public void visit(Armor armor) {
        System.out.printf("  [EnchantmentScanner] Armor '%s': Warding sigil defense +%d, bulk %d kg%n",
                armor.getName(), armor.getDefenseBonus(), armor.getWeight());
    }
}

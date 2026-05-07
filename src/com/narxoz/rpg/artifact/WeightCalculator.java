package com.narxoz.rpg.artifact;

public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight;

    public WeightCalculator() {
        this.totalWeight = 0;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.printf("  [WeightCalculator] Weapon '%s': %d kg (running total: %d kg)%n",
                weapon.getName(), weapon.getWeight(), totalWeight);
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.printf("  [WeightCalculator] Potion '%s': %d g (running total: %d kg)%n",
                potion.getName(), potion.getWeight(), totalWeight);
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.printf("  [WeightCalculator] Scroll '%s': %d g — negligible (running total: %d kg)%n",
                scroll.getName(), scroll.getWeight(), totalWeight);
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.printf("  [WeightCalculator] Ring '%s': %d g — tiny (running total: %d kg)%n",
                ring.getName(), ring.getWeight(), totalWeight);
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.printf("  [WeightCalculator] Armor '%s': %d kg — HEAVY (running total: %d kg)%n",
                armor.getName(), armor.getWeight(), totalWeight);
    }
}

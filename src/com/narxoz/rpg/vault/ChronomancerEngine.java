package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;

import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        Inventory vaultInventory = new Inventory();
        vaultInventory.addArtifact(new Weapon("Shadowfang Blade", 120, 8, 15));
        vaultInventory.addArtifact(new Potion("Grand Healing Elixir", 80, 1, 60));
        vaultInventory.addArtifact(new Scroll("Scroll of Doom", 200, 0, "doom"));
        vaultInventory.addArtifact(new Ring("Ring of Arcane Might", 350, 0, 12));
        vaultInventory.addArtifact(new Armor("Chrono Plate", 500, 35, 20));
        vaultInventory.addArtifact(new Potion("Mana Draught", 60, 1, 0));

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        CHRONOMANCER'S VAULT OPENED       ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("Vault contains " + vaultInventory.size() + " artifacts.\n");

        // --- VISITOR: GoldAppraiser ---
        System.out.println("── Appraisal #1: GoldAppraiser ──");
        GoldAppraiser goldAppraiser = new GoldAppraiser();
        vaultInventory.accept(goldAppraiser);
        System.out.println("  Total estimated value: " + goldAppraiser.getTotalValue() + " gold\n");
        artifactsAppraised += vaultInventory.size();

        // --- VISITOR: EnchantmentScanner ---
        System.out.println("── Appraisal #2: EnchantmentScanner ──");
        EnchantmentScanner scanner = new EnchantmentScanner();
        vaultInventory.accept(scanner);
        System.out.println();
        artifactsAppraised += vaultInventory.size();

        // --- VISITOR: CurseDetector ---
        System.out.println("── Appraisal #3: CurseDetector ──");
        CurseDetector curseDetector = new CurseDetector();
        vaultInventory.accept(curseDetector);
        System.out.println("  Cursed artifacts found: " + curseDetector.getCursedCount() + "\n");
        artifactsAppraised += vaultInventory.size();

        // --- VISITOR: WeightCalculator (4th visitor - open/closed proof) ---
        System.out.println("── Appraisal #4: WeightCalculator (open/closed extension) ──");
        WeightCalculator weightCalculator = new WeightCalculator();
        vaultInventory.accept(weightCalculator);
        System.out.println("  Total carry weight: " + weightCalculator.getTotalWeight() + " kg\n");
        artifactsAppraised += vaultInventory.size();

        // --- MEMENTO: Save and restore hero states ---
        for (Hero hero : party) {
            System.out.println("══ Hero: " + hero.getName() + " ══");
            System.out.println("  BEFORE vault trap: " + hero);

            Caretaker caretaker = new Caretaker();
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;
            System.out.println("  [Memento] Snapshot saved. Caretaker holds " + caretaker.size() + " snapshot(s).");

            // Vault trap fires — hero takes heavy damage and loses gold
            System.out.println("  *** VAULT TRAP TRIGGERED — chrono shockwave hits! ***");
            hero.takeDamage(60);
            hero.spendGold(hero.getGold() / 2);

            System.out.println("  AFTER trap:  " + hero);

            // Rewind via memento
            System.out.println("  [Memento] Chronomancer rewinds time...");
            hero.restoreFromMemento(caretaker.undo());
            restoredCount++;

            System.out.println("  AFTER rewind: " + hero);
            System.out.println("  [Memento] Caretaker now holds " + caretaker.size() + " snapshot(s).\n");
        }

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}

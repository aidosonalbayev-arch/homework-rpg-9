package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory heroInventory = new Inventory();
        heroInventory.addArtifact(new Weapon("Iron Sword", 80, 5, 8));
        heroInventory.addArtifact(new Potion("Minor Healing Potion", 30, 1, 20));

        Hero aria = new Hero("Aria the Mage", 100, 80, 12, 5, 200, heroInventory);
        Hero brom = new Hero("Brom the Warrior", 180, 20, 25, 15, 350, new Inventory());

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(aria, brom));

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            VAULT RUN COMPLETE            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("Final result: " + result);
    }
}

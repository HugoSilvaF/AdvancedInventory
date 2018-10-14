/*
 * Copyright (C) 2018 Hugo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gmail.hugosilvaf2.gui;

import com.gmail.hugosilvaf2.gui.lib.GUI;
import com.gmail.hugosilvaf2.gui.lib.GUIObject;
import com.gmail.hugosilvaf2.gui.lib.Result;
import com.gmail.hugosilvaf2.gui.lib.Source;
import com.gmail.hugosilvaf2.gui.lib.sections.Section;
import com.gmail.hugosilvaf2.gui.lib.sections.Sections;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIManager
        extends ArrayList<GUI>
        implements Listener {

    private Plugin plugin;
    private Sections sections;

    public GUIManager(Plugin plugin) {
        this.plugin = plugin;
        sections = new Sections();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        final Player whoClicked = (Player) event.getWhoClicked();

        if ((sections.hasSection(whoClicked)) && (event.getCurrentItem() != null) && (!event.getCurrentItem().getType().equals(Material.AIR))) {
            Section section = sections.getSection(whoClicked);
            ItemStack currentItem = event.getCurrentItem();

            final GUIObject guio = section.getNowPage().get(event.getSlot());
            if (event.getInventory().equals(section.getInventory())) {
                event.setCancelled(true);
            }
            if ((guio != null) && (guio.getIcon().equals(currentItem))) {
                event.setCancelled(guio.isCancelClick());
                Result result = guio.onClick(new Source(whoClicked, section, event.getClick(), new boolean[]{event.isLeftClick(), event.isRightClick(), event.isShiftClick()}));
                if (result.equals(Result.NEXT_PAGE)) {
                    section.nextPage();
                    return;
                }
                if (result.equals(Result.PREVIOUS_PAGE)) {
                    section.previousPage();
                    return;
                }
                if (result.equals(Result.OPEN_NEW)) {
                    whoClicked.closeInventory();
                    BukkitRunnable runnable = new BukkitRunnable() {
                        public void run() {
                            openGUI(whoClicked, guio.getOpenNewGUI());
                        }
                    };
                    runnable.runTaskLater(plugin, 5L);
                    return;
                }
                if (result.equals(Result.NOTHING)) {
                    return;
                }
                if (result.equals(Result.CLOSE)) {
                    whoClicked.closeInventory();
                    sections.remove(section);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (((event.getPlayer() instanceof Player))
                && (sections.hasSection((Player) event.getPlayer()))) {
            sections.remove(sections.getSection((Player) event.getPlayer()));
        }
    }

    public void openGUI(Player player, String name) {
        openGUI(player, getGUI(name));
    }

    public void openGUI(Player player, GUI gui) {
        Section section = new Section(gui, player, Bukkit.createInventory(player, gui.getSize(), gui.getTitle()));
        section.updateInventory();
        sections.add(section);
        player.openInventory(section.getInventory());
    }

    public GUI getGUI(String name) {
        for (GUI gui : this) {
            if (gui.getName().equals(name)) {
                return gui;
            }
        }
        return null;
    }

    public boolean hasGUI(String name) {
        return getGUI(name) != null;
    }
}

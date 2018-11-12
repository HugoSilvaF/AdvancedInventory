/*
 * Copyright (C) 2018 Hugo Silva <hugosilvaf2@gmail.com>
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
package com.gmail.hugosilvaf2.gui.lib.listener;

import com.gmail.hugosilvaf2.gui.GUIManager;
import com.gmail.hugosilvaf2.gui.lib.GUIObject;
import com.gmail.hugosilvaf2.gui.lib.Result;
import com.gmail.hugosilvaf2.gui.lib.Source;
import com.gmail.hugosilvaf2.gui.lib.sections.Section;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;


/**
 *
 * @author Hugo Silva <hugosilvaf2@gmail.com>
 */
public class InventoryListener implements Listener {
    
    private final Plugin plugin;

    public InventoryListener(Plugin plugin) {
        this.plugin = plugin;
    }
    
    

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        final Player whoClicked = (Player) event.getWhoClicked();

        if (!(GUIManager.getSections().hasSection(whoClicked))) {
            return;
        }
        Section section = GUIManager.getSections().getSection(whoClicked);
        if ((event.getCurrentItem() != null) && (!event.getCurrentItem().getType().equals(Material.AIR))) {
            ItemStack currentItem = event.getCurrentItem();
            final GUIObject guiObj = section.getNowPage().get(event.getSlot());

            if ((guiObj != null) && (guiObj.getIcon().equals(currentItem))) {
                event.setCancelled(guiObj.cancelClick());
                if(guiObj.getOnClick() == null) {
                    return;
                }
                Result result = guiObj.getOnClick().click(new Source(whoClicked, section, event.getClick(), event.getAction(), event.getSlot(), event.getRawSlot(), event.getSlotType(), event.getCurrentItem(), event.getCursor()));
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
                            GUIManager.openToPlayer(whoClicked, guiObj.getOpenNewGUI());
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
                    GUIManager.getSections().remove(section);
                    return;
                }
            }
        }
        // to cancel move and place items for GUI

        if (!section.getNowPage().moveItems()) {
            if (section.compareTo(event.getInventory())) {
                if (event.getRawSlot() >= (section.getInventory().getSize() - 1) && event.getRawSlot() <= (section.getInventory().getSize() - 1) + 36) {
                    if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                        event.setCancelled(true);
                    }
                }

                if (event.getRawSlot() >= 0 && event.getRawSlot() <= (section.getInventory().getSize() - 1)) {
                    if (event.getAction() == InventoryAction.PLACE_ALL || event.getAction() == InventoryAction.PLACE_ONE) {
                        event.setCancelled(true);
                    }
                }
            }
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (((event.getPlayer() instanceof Player))
                && (GUIManager.getSections().hasSection((Player) event.getPlayer()))) {
            GUIManager.getSections().remove(GUIManager.getSections().getSection((Player) event.getPlayer()));
        }
    }

}

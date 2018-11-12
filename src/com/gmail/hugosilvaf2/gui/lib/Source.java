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
package com.gmail.hugosilvaf2.gui.lib;

import com.gmail.hugosilvaf2.gui.lib.sections.Section;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Source {

    private final Player player;
    private final GUI gui;
    private final ClickType clickType;
    private final InventoryAction action;

    private final int slot;
    private final int rawSlot;
    private final InventoryType.SlotType slotType;

    private final ItemStack currentItem;
    private final ItemStack cursorItem;

    public Source(Player player, Section section, ClickType clickType, InventoryAction action, int slot, int rawSlot, InventoryType.SlotType slotType, ItemStack currentItem, ItemStack cursorItem) {
        this.player = player;
        this.gui = section.getGui();
        this.clickType = clickType;
        this.action = action;

        this.slot = slot;
        this.rawSlot = rawSlot;
        this.slotType = slotType;

        this.currentItem = currentItem;
        this.cursorItem = cursorItem;
    }

    /**
     * Obtém o jogador que executou a ação
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Obtém o GUI, lembre-se que o GUI não é a página atual
     *
     * @return
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Obtém o tipo de click
     *
     * @return
     */
    public ClickType getClickType() {
        return clickType;
    }

    /**
     * Retorna o InventoryAction do evento InventoryClick
     *
     * @return
     */
    public InventoryAction getInventoryAction() {
        return action;
    }

    /**
     * Obtém o inventário do jogador
     *
     * @return
     */
    public Inventory getPlayerInventory() {
        return getPlayer().getInventory();
    }

    /**
     * Retorna o tipo do slot
     *
     * @return
     */
    public InventoryType.SlotType getSlotType() {
        return slotType;
    }

    /**
     * Retorna o item atual
     *
     * @return
     */
    public ItemStack getCurrentItem() {
        return currentItem;
    }

    /**
     * Retorna o Item do cursor
     *
     * @return
     */
    public ItemStack getCursorItem() {
        return cursorItem;
    }

    /**
     * Retorna o Slot do evento InventoryClickEvent
     *
     * @return
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Retorna o RawSlot do evento InventoryClickEvent
     *
     * @return
     */
    public int getRawSlot() {
        return rawSlot;
    }

    /**
     * Envia um array ou lista de mensagem mensagens ao jogador
     *
     * @param t
     * @return
     */
    public <T> Source sendMessage(T t) {
        if(t instanceof String []) {
            for(String a : (String[])t) {
                getPlayer().sendMessage(a);
            }
            return this;
        }
        if(t instanceof List ) {
            for(String a : (List<String>)t) {
                getPlayer().sendMessage(a);
            }
            return this;
        }
        if(t instanceof String) {
            getPlayer().sendMessage((String) t);
        }
        return this;
    }

    /**
     * Envia ItemStack ao jogador, se o inventário estiver cheio o item será
     * jogado no chão
     *
     * @param itemStack
     * @return
     */
    public Source sendItemStack(ItemStack itemStack) {
        if ((itemStack != null) && (!itemStack.getType().equals(Material.AIR))) {
            PlayerInventory inventory = getPlayer().getInventory();
            int firstEmpty = inventory.firstEmpty();
            if (firstEmpty != -1) {
                inventory.setItem(firstEmpty, itemStack);
            } else {
                Location location = getPlayer().getLocation();
                location.getWorld().dropItem(location, itemStack);
            }
        }
        return this;
    }

}

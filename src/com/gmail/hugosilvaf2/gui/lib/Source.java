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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Source {

    private final Player player;
    private final GUI gui;
    private final ClickType clickType;
    private final boolean[] clicks;

    public Source(Player player, Section section, ClickType clickType, boolean[] clicks) {
        this.player = player;
        gui = section.getGui();
        this.clickType = clickType;
        this.clicks = clicks;
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
     * Obtém o inventário do jogador
     *
     * @return
     */
    public Inventory getPlayerInventory() {
        return getPlayer().getInventory();
    }

    /**
     * Envia mensagem ao jogador
     *
     * @param message
     * @return
     */
    public Source sendMessage(String... message) {
        getPlayer().sendMessage(message);
        return this;
    }

    /**
     * Envia ItemStack ao jogador
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

    /**
     * Obtém a boolean se o jogador clicou com o botão esquerdo do mouse
     *
     * @return
     */
    public boolean clickedLeft() {
        return clicks[0];
    }

    /**
     * Obtém a boolean se o jogador clicou com o botão direit do mouse
     *
     * @return
     */
    public boolean clickedRight() {
        return clicks[1];
    }

    /**
     * Obtém a boolean se o jogador clicou com o shift pressionado
     *
     * @return
     */
    public boolean clickedShift() {
        return clicks[2];
    }
}

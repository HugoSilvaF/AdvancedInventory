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

import org.bukkit.inventory.ItemStack;

public abstract class GUIObject {

    private String name;
    private ItemStack icon;
    private GUI openNewGUI;
    private boolean cancelClick;

    public GUIObject(String name, ItemStack icon, GUI gui, boolean cancelClick) {
        this.name = name;
        this.icon = icon;
        this.openNewGUI = gui;
        this.cancelClick = cancelClick;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public GUI getOpenNewGUI() {
        return openNewGUI;
    }

    public boolean isCancelClick() {
        return cancelClick;
    }

    public void setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
    }

    public abstract Result onClick(Source paramSource);
}

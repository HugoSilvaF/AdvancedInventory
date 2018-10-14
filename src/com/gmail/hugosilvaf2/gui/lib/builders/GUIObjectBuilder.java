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
package com.gmail.hugosilvaf2.gui.lib.builders;

import com.gmail.hugosilvaf2.gui.lib.GUI;
import com.gmail.hugosilvaf2.gui.lib.GUIObject;
import com.gmail.hugosilvaf2.gui.lib.Result;
import com.gmail.hugosilvaf2.gui.lib.Source;
import org.bukkit.inventory.ItemStack;

public class GUIObjectBuilder {

    private String name;
    private ItemStack icon;
    private Result result;
    private GUI openNewGUI;
    private int location;
    private boolean cancelClick;

    public GUIObjectBuilder() {
        result = Result.NOTHING;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }

    public GUI getOpenNewGUI() {
        return openNewGUI;
    }

    public int getLocation() {
        return location;
    }

    public boolean isCancelClick() {
        return cancelClick;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public GUIObjectBuilder setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
        return this;
    }

    public GUIObjectBuilder setOpenNewGUI(GUI openNewGUI) {
        this.openNewGUI = openNewGUI;
        return this;
    }

    public GUIObjectBuilder setIcon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public GUIObjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GUIObjectBuilder setResult(Result result) {
        this.result = result;
        return this;
    }

    public static GUIObjectBuilder instance() {
        return new GUIObjectBuilder();
    }

    public GUIObject build() {
        return new GUIObject(name, icon, openNewGUI, cancelClick) {
            public Result onClick(Source source) {
                return result;
            }
        };
    }
}

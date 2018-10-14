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

public class Page {

    final GUIObject[] guios;

    public Page(int index) {
        guios = new GUIObject[index];
    }

    public Page addGUIObject(int index, GUIObject guio) {
        guios[index] = guio;
        return this;
    }

    public Page addGUIObject(GUIObject guio) {
        addGUIObject(firstEmpty(), guio);
        return this;
    }

    public GUIObject get(int index) {
        return guios[index];
    }

    public GUIObject getGUIObject(ItemStack is) {
        for (GUIObject object : guios) {
            if (is.equals(object.getIcon())) {
                return object;
            }
        }
        return null;
    }

    public int size() {
        int counter = 0;
        for (GUIObject guio : guios) {
            if (guio != null) {
                counter++;
            }
        }
        return counter;
    }

    public int firstEmpty() {
        for (int i = 0; i < guios.length; i++) {
            if (guios[i] == null) {
                return i;
            }
        }
        return -1;
    }
}

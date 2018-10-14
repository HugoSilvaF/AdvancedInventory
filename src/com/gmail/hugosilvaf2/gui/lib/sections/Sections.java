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
package com.gmail.hugosilvaf2.gui.lib.sections;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class Sections
        extends ArrayList<Section> {

    public Sections() {
    }

    public Section getSection(Player player) {
        return getSection(player.getName());
    }

    public Section getSection(String name) {
        for (Section section : this) {
            if (section.getViewer().getName().equals(name)) {
                return section;
            }
        }
        return null;
    }

    public boolean hasSection(Player player) {
        return getSection(player) != null;
    }

    public boolean hasSection(String name) {
        return getSection(name) != null;
    }
}

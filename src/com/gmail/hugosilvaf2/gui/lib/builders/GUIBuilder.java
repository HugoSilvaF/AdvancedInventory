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
import com.gmail.hugosilvaf2.gui.lib.Page;
import java.util.LinkedList;

public class GUIBuilder {

    private GUI gui;
    private String name;
    private String title;
    int size;
    private LinkedList<Page> pages;

    public GUIBuilder() {
        size = 54;
        name = "Default Name";
        title = "Default Title";
        pages = new LinkedList();
    }

    public GUIBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GUIBuilder setTitlte(String title) {
        this.title = title;
        return this;
    }

    public GUIBuilder setSize(int size) {
        this.size = size;

        return this;
    }

    public GUIBuilder addPage(Page page) {
        pages.add(page);
        return this;
    }

    public GUI build() {
        gui = new GUI(name, title, size);
        pages.stream().forEach(a -> gui.add(a));
        return gui;
    }

    public static GUIBuilder instance() {
        return new GUIBuilder();
    }
}

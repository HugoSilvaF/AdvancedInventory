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
import com.gmail.hugosilvaf2.gui.lib.Pages.Page;
import java.util.LinkedList;

public class GUIBuilder {

    private GUI gui;
    private String name;
    private String title;
    private LinkedList<Page> pages;

    public GUIBuilder() {
        name = "Default Name";
        title = "Default Title";
        pages = new LinkedList();
    }

    /**
     * Seta o nome do GUI
     *
     * @param name
     * @return
     */
    public GUIBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Seta o titulo do GUI, este será exibido no inventário
     *
     * @param title
     * @return
     */
    public GUIBuilder setTitlte(String title) {
        this.title = title;
        return this;
    }

    /**
     * Adiciona uma página no GUI
     *
     * @param page
     * @return
     */
    public GUIBuilder addPage(Page page) {
        pages.add(page);
        return this;
    }

    /**
     * Constroi o GUI
     *
     * @return
     */
    public GUI build() {
        return GUI.newInstance().setName(name).setTitle(title).addPageAll(pages);
    }

    public static GUIBuilder newInstance() {
        return new GUIBuilder();
    }
}

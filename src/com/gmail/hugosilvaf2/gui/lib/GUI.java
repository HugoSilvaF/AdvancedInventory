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

public class GUI
        extends Pages {

    private String name;

    private String title;

    private int size;

    public GUI(String name, String title, int size) {
        this.name = name;
        this.title = title;
        this.size = size;
    }

    /**
     * Obtém o nome do GUI por exemplo "Loja"
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o título do GUI, este ficará visível no inventário
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtém o tamnho do GUI, que deverá ser o mesmo tamanho das páginas
     *
     * @return
     */
    public int getSize() {
        return size;
    }
}

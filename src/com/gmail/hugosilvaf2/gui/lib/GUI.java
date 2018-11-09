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

import java.util.Collection;

public abstract class GUI extends Pages {

    private String name, title;

    /**
     * Obtém o nome do GUI por exemplo "Loja"
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém o título do GUI, este ficará visível no inventário
     *
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Altera o nome do GUI, é para uso interno
     *
     * @param name
     * @return
     */
    public GUI setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Altera o titulo do GUI, sera visualizado para os jogadores
     *
     * @param title
     * @return
     */
    public GUI setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Adiciona a página
     *
     * @param page
     * @return
     */
    public GUI addPage(Page page) {
        add(page);
        return this;
    }

    /**
     * Adiciona todoas páginas da Collection c
     *
     * @param c
     * @return
     */
    public GUI addPageAll(Collection<? extends Page> c) {
        addAll(c);
        return this;
    }

    /**
     * Adiciona todoas páginas da Collection c a partir do index
     *
     * @param index
     * @param c
     * @return
     */
    public GUI addPageAll(int index, Collection<? extends Page> c) {
        addAll(index, c);
        return this;
    }

    /**
     * Obtéma instancia desta classe
     *
     * @return
     */
    public static GUI newInstance() {
        return new GUI() {
        };
    }

}

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

    boolean moveItems = false;

    public Page(int index) {
        guios = new GUIObject[index];
    }

    /**
     * Adiciona o GUIObject atraves do index
     *
     * @param index
     * @param guio
     * @return
     */
    public Page addGUIObject(int index, GUIObject guio) {
        guios[index] = guio;
        return this;
    }

    /**
     * Adiciona o GUIObject no primeiro slot vazio
     *
     * @param guio
     * @return
     */
    public Page addGUIObject(GUIObject guio) {
        addGUIObject(firstEmpty(), guio);
        return this;
    }

    /**
     * Obtém o GUIObject através do index
     *
     * @param index
     * @return
     */
    public GUIObject get(int index) {
        return guios[index];
    }

    /**
     * Obtém o GUIObject através do item
     *
     * @param is
     * @return
     */
    public GUIObject getGUIObject(ItemStack is) {
        for (GUIObject object : guios) {
            if (is.equals(object.getIcon())) {
                return object;
            }
        }
        return null;
    }

    /**
     * Setar se pode mover os items no inventário True para sim, e false para
     * não
     *
     * Retorna a classe
     *
     * @param b
     * @return Page
     */
    public Page setMoveItems(boolean b) {
        this.moveItems = b;
        return this;
    }

    /**
     * Obtém a boolean se pode mover items, true se sim, false se não
     *
     * @return
     */
    public boolean getMoveItems() {
        return this.moveItems;
    }

    /**
     * Retorna o tamanho items que está na página
     *
     * @return int
     */
    public int size() {
        int counter = 0;
        for (GUIObject guio : guios) {
            if (guio != null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Obtém o primeiro slot vazio.
     *
     * @return int
     */
    public int firstEmpty() {
        for (int i = 0; i < guios.length; i++) {
            if (guios[i] == null) {
                return i;
            }
        }
        return -1;
    }
}

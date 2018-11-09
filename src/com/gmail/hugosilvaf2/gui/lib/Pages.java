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

import com.gmail.hugosilvaf2.gui.lib.Pages.Page;
import java.util.LinkedList;
import org.bukkit.inventory.ItemStack;

public class Pages
        extends LinkedList<Page> {

    public static abstract class Page {

        private GUIObject[] guios = new GUIObject[9];

        boolean moveItems = false;

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
         * Setar se pode mover os items no inventário True para sim, e false
         * para não
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
         * Seta o tamanho da pagina, lembre-se que deverá ser multiplo de 9, e
         * se houver algum objeto ele será apagado ao ser setado outro tamanho
         *
         * @param i
         * @return
         */
        public Page setSize(int i) {
            this.guios = new GUIObject[i];
            return this;
        }

        /**
         * Obtém a boolean se pode mover items, true se sim, false se não
         *
         * @return
         */
        public boolean moveItems() {
            return this.moveItems;
        }

        /**
         * Retorna o tamanho da página
         *
         * @return int
         */
        public int size() {
            return guios.length;
        }

        /**
         * Obtém o primeiro slot vazio., se não houver nenhum slot vazio retorna
         * -1
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

        public static Page newInstance() {
            return new Page() {
            };
        }

    }

}

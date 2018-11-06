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

    /**
     * Obtém o ícone do GUIObject, este será visto no invetário.
     *
     * @return
     */
    public ItemStack getIcon() {
        return icon;
    }

    /**
     * Obtém o nome do GUIObject, para fazer um botão de próxima página é bom
     * colocar "Próxima Página"
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o novo GUI que será aberto com este GUIObject, lembre-se que só irá
     * abrir um novo GUI se tiver um para abrir
     *
     * @return
     */
    public GUI getOpenNewGUI() {
        return openNewGUI;
    }

    /**
     * Obtém a boolean se o click foi cancelado, se sim true, se não false
     *
     * @return
     */
    public boolean isCancelClick() {
        return cancelClick;
    }

    /**
     * Seta se o ao clicar no item o click será cancelado, se não for cancelado
     * o jogador pode retirar o item
     *
     * @param cancelClick
     */
    public void setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
    }

    /**
     * Este método é chamado ao jogador clicar no item. Retorna o resultado,
     * próxima página ou nada, o dev decide.
     *
     * @param paramSource
     * @return
     */
    public abstract Result onClick(Source paramSource);
}

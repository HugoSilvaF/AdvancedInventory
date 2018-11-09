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

    public interface OnClick {

        Result click(Source scr);
    }

    private String name;
    private ItemStack icon;
    private GUI openNewGUI;
    private OnClick OnClick;
    private boolean cancelClick;

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
     * Obtém a classe do OnClick onde tem o método que será chamado ao jogador
     * clicar no objeto
     *
     * @return
     */
    public OnClick getOnClick() {
        return OnClick;
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
    public GUIObject setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
        return this;
    }

    /**
     * Seta o OnClick método que será chamado ao jogador clicar no objeto
     *
     * @param OnClick
     * @return
     */
    public GUIObject setOnClick(OnClick OnClick) {
        this.OnClick = OnClick;
        return this;
    }

    /**
     * Seta o nome do GUIObject, é para diferenciar dos objetos para botões,
     * como botões de próximas páginas ou abrir novos inventários
     *
     * @param name
     * @return
     */
    public GUIObject setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Seta o ícone do GUIObject
     *
     * @param icon
     * @return
     */
    public GUIObject setIcon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Seta o novo GUI que será aberto, caso o resultado do método onClick seje
     * OPEN_NEW
     *
     * @param openNewGUI
     * @return
     */
    public GUIObject setOpenNewGUI(GUI openNewGUI) {
        this.openNewGUI = openNewGUI;
        return this;
    }

    /**
     * Obtém a instancia desta classe
     *
     * @return
     */
    public static GUIObject newInstance() {
        return new GUIObject() {
        };
    }

}

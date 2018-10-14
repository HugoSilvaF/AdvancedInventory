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

import com.gmail.hugosilvaf2.gui.lib.GUI;
import com.gmail.hugosilvaf2.gui.lib.Page;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Section {

    private GUI gui;
    private Player viewer;
    private Inventory inventory;
    private int nowPage;

    public Section(GUI gui, Player viewer, Inventory inventory) {
        this.gui = gui;
        this.viewer = viewer;
        this.inventory = inventory;

        nowPage = 0;
    }

    public GUI getGui() {
        return gui;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Page getNowPage() {
        return (Page) gui.get(nowPage);
    }

    public Page getLastPage() {
        return (Page) gui.get(getLastPageInt());
    }

    public int getNowPageInt() {
        return nowPage;
    }

    public int getLastPageInt() {
        return nowPage >= 0 ? nowPage - 1 : 0;
    }

    public Player getViewer() {
        return viewer;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public void nextPage() {
        if (gui.size() > nowPage + 1) {
            nowPage += 1;
            updateInventory();
        }
    }

    public void previousPage() {
        if (nowPage - 1 >= 0) {
            nowPage -= 1;
            updateInventory();
        }
    }

    public void updateInventory() {
        inventory.clear();
        Page page = (Page) getGui().get(nowPage);
        for (int i = 0; i < page.size(); i++) {
            inventory.setItem(i, page.get(i).getIcon());
        }
        viewer.updateInventory();
    }
}

/*
 * Copyright (C) 2018 Hugo Silva <hugosilvaf2@gmail.com>
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
package com.gmail.hugosilvaf2.gui.lib.updater;

import com.gmail.hugosilvaf2.gui.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hugo Silva <hugosilvaf2@gmail.com>
 */
public class Updater extends Thread {

    private final Plugin plugin;

    private boolean destroy = false;
    private boolean started = false;

    public Updater(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!destroy) {
            started = true;
            GUIManager.getSections().cache.forEach(n -> {
                if (n.getNowPage().getUpdater() != null) {
                    n.getNowPage().getUpdater().update();
                    n.updateInventory();
                }
            });
            Bukkit.getScheduler().runTaskLater(plugin, this, 10L);
        }
    }

    @Override
    public synchronized void start() {
        if (!started) {
            super.start();
        }
    }

}

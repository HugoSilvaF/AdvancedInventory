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
package com.gmail.hugosilvaf2.gui;

import com.gmail.hugosilvaf2.gui.lib.GUI;
import com.gmail.hugosilvaf2.gui.lib.listener.InventoryListener;
import com.gmail.hugosilvaf2.gui.lib.sections.Section;
import com.gmail.hugosilvaf2.gui.lib.sections.Sections;
import com.gmail.hugosilvaf2.gui.lib.updater.Updater;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GUIManager {

    private static ConcurrentLinkedQueue<GUI> cache;

    private static Plugin plugin;
    private static Sections sections;
    private static Updater updater;

    private static InventoryListener listener;

    /**
     * Inicia as configurações da lib
     * @param p 
     */
    public static void start(Plugin p) {
        plugin      = plugin    != null ? plugin    : p;
        cache       = cache     != null ? cache     : new ConcurrentLinkedQueue<>();
        sections    = sections  != null ? sections  : new Sections();
        updater     = updater   != null ? updater   : new Updater(plugin);
        listener    = listener  != null ? listener  : new InventoryListener(plugin);

        Bukkit.getPluginManager().registerEvents(listener, plugin);

        updater.start();
    }
    
    /**
     * Obtém as seções
     * 
     * @return 
     */
    public static Sections getSections(){
        return sections;
    }

    /**
     * Obtém o updater
     *
     * @return
     */
    public static Updater getUpdater() {
        return updater;
    }

    /**
     * Registra um GUI
     *
     * @param gui
     * @return
     */
    public static void register(GUI gui) {
        cache.add(gui);
    }

    /**
     * Remove o registro
     *
     * @param gui
     * @return
     */
    public static void remove(GUI gui) {
        cache.remove(gui);
    }

    /**
     * Abrirá o GUI através do nome do GUI
     *
     * @param player
     * @param name
     */
    public static void openToPlayer(Player player, String name) {
        openToPlayer(player, getGUI(name));
    }

    /**
     * Abrirá o GUI
     *
     * @param player
     * @param gui
     */
    public static void openToPlayer(Player player, GUI gui) {
        Section section = new Section(gui, player, Bukkit.createInventory(player, gui.getFirst().size(), gui.getTitle()));
        section.updateInventory();
        sections.add(section);
        player.openInventory(section.getInventory());
    }

    /**
     * Obtém o GUI caso exista, se não retornará nulo
     *
     * @param name
     * @return
     */
    public static GUI getGUI(String name) {
        return getOptionalGUI(name).get();
    }


    /**
     * Checa através do nome se o GUI existe, se sim retorna true, se não
     * retorna false.
     *
     * @param name
     * @return
     */
    public static boolean existGUI(String name) {
        return getOptionalGUI(name).isPresent();
    }
    
    /**
     * Obtém o Optional<GUI>
     *
     * @param name
     * @return
     */
    public static Optional<GUI> getOptionalGUI(String name) {
        return cache.stream().filter(n -> n.getName().equals(name)).findFirst();
    }
}

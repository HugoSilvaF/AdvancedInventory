
# GUI
A simple GUI lib of inventory for minecraft plugins.

[![Build Status](https://travis-ci.org/CrazyDev/GUI.svg?branch=wolfgang)](https://travis-ci.org/CrazyDev/GUI)

## Version 1.1

### OBSERVATION:
This lib version can have bugs, wait for more updates.

## Usage
  first create GUIManager.

  ```java
  GUIManager manager = new GUIManager(plugin);
  ```
  after, you can create a new class extends GUI or use GUIBuilder.
  
  ```java
 GUIBuilder builder = GUIBuilder.instance()
                .setName("LOJA")
                .setTitlte("My GUI")
                .addPage(
                        new Page(54)
                                .addGUIObject(
                                        GUIObjectBuilder.instance()
                                                .setCancelClick(true)
                                                .setIcon(new ItemStack(Material.STONE))
                                                .setName("NexPage")
                                                .setResult(Result.NEXT_PAGE).build()));
                 
  ```
  
  after, you need register your GUI.
  
  ```java
  manager.add(builder.build());
  ```
  to open your GUI you can use:
  ```
  manager.openGUI(player, nameGUI);
  ```


# GUI
A simple GUI lib of inventory for minecraft plugins.

# Version 1.2

# OBSERVATION:
This lib version can have bugs, wait for more updates.

# Usando

Primeiro crie o GUIManager

```java
 GUIManager manager = new GUIManager(plugin);
 ```
Depois você Cria uma classe para ser seu GUI, exemplo Loja, e extenda a classe GUI

```java
public class Loja extends GUI {

    public Loja(String name, String title, int size) {
        super(name, title, size);
    }

}
```

Agora vamos criar o seu objeto do gui, os items que estarão a venda na loja.
```java
public class LojaObjetos extends GUIObject {

    public LojaObjetos(String name, ItemStack icon, GUI gui, boolean cancelClick) {
        super(name, icon, gui, cancelClick);
    }

    @Override
    public Result onClick(Source paramSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
```

Agora vamos criar seu botao para próxima página,
e deixo-o sempre em um local fixo, e não coloque nenhum objeto por cima dele.
```java
public class ProximaPagina extends GUIObject {

    public ProximaPagina(String name, ItemStack icon, GUI gui, boolean cancelClick) {
        super(name, icon, gui, cancelClick);
    }

    @Override
    public Result onClick(Source paramSource) {

        return Result.NEXT_PAGE;
    }

}
```
Uma maneira mais prática, é utilizar o builder do GUI
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
 Depois vamos registar o seu GUI
  ```java
  manager.add(builder.build());
  manager.add(new Loja);
  ```
 Para abrir o GUI, você pode utilizar:
  ```java
  manager.openGUI(player, nameGUI);
  ```

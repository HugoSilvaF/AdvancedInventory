
# GUI
A simple GUI lib of inventory for minecraft plugins.
Uma simples lib de GUI de inventários para minecraft.

# Version 1.2 | Versão 1.2

# OBSERVATION: | Observação:
This lib version can have bugs, wait for more updates. any bug or suggestion just create a issue.
Esta versão da lib pode haver bugs, aguarde por mais updates, qualquer bug ou susgestão crie um Issue

# Use | Usando
First, create GUIManager
Primeiro crie o GUIManager

```java
 GUIManager manager = new GUIManager(plugin);
 ```
After, create a class for is your GUI, exemplo "Loja", and extends the class GUI
Depois você Cria uma classe para ser seu GUI, exemplo Loja, e extenda a classe GUI

```java
public class Loja extends GUI {

    public Loja(String name, String title, int size) {
        super(name, title, size);
    }

}
```
Now, we go create your object of gui, where is the item that sell in your shop
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
Now, we go to create your bottom to the next page, stay ever in fixed place, and dont put anything on top.
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

  Now, we go register your GUI
 Depois vamos registar o seu GUI
  ```java
  manager.add(new Loja);
  ```
  For your open the GUI, you can use:
 Para abrir o GUI, você pode utilizar:
  ```java
  manager.openGUI(player, nameGUI);
  ```

A fast way to do, is use a Builder of GUI
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
  Now, we go register your GUI
 Depois vamos registar o seu GUI
  ```java
  manager.add(builder.build());
  ```
 For you open the GUI, you can use:
 Para abrir o GUI, você pode utilizar:
  ```java
  manager.openGUI(player, nameGUI);
  ```

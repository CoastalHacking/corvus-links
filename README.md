
## Projects

### prototype.link

* General project, displaying in hierarchical view

### prototype.link.ui

* Created using new plug-in project, makes UI contributions
* New other -> Eclipse 4 -> Model -> New Model Fragment (defaults)
* Created the handler by clicking on the handler "Class URI" link

The E4 context only supports IEditorPart, not ITextEditor

### prototype.link.api

* Contains DTOs and interfaces used by UI
* Using an ITextSelection DTO since its JavaDoc contract stipulates the
  values change with the text selection, so we lock in those values via
  the DTO. 

### prototype.link.provider

* Has context function for LinkController for depedency injection 

[What are context functions?](http://www.vogella.com/tutorials/Eclipse4ContextFunctions/article.html#what-are-context-functions)

> The context function registers itself for a certain key, for example a class
> name. Whenever the Eclipse dependency injection does not find an existing
> object under this key, it calls the compute() method of the context function.


[Creation of a context function](http://www.vogella.com/tutorials/Eclipse4ContextFunctions/article.html#creation-of-a-context-function):

> If the key is a class you have to point to the fully qualified class. This key
> can be used for dependency injection. If you register a key which is not a
> class name, a consumer of the injection can use the @Named annotation to
> specify the key.

* [Eclipse Dependency Injection](https://wiki.eclipse.org/Eclipse4/RCP/Dependency_Injection)

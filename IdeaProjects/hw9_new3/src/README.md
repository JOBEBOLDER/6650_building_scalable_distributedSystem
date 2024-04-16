# Photo Album Shapes Controller - Assignment 9 Submission

## Overview

Building on the foundations of Assignment 8, this project introduces a controller layer to manage the interactions between the model and view within a virtual photo album application. 
In addition to shape manipulation, this version emphasizes the integration of command-line processing, enhancing the application's interaction and testing.

## Design Overview

The application adopts the Model-View-Controller (MVC) architecture, encapsulating the logic into distinct layers:

- `model`: Contains the `PhotoAlbumModel` class, `IShape` interface, shape classes (`Rectangle`, `Ovals`, etc.), and transformation classes (`Resize`, `Rotate`, etc.).
- `controller`: Features the `PhotoAlbumController`, coordinating the flow of data between the `model` and `views`.
- `utility`: Hosts `CommandLineProcessor` and `FileCommandProcessor` classes, handling command-line argument parsing and file command processing.
- `views`: Manages the user interface with `GraphicalView` and `WebView` classes for different representations of the album.

## Key Changes

1. **Controller Package**: Manages the flow of information and commands between the model and view.
2. **Utility Package**: New classes like `CommandLineProcessor` and `FileCommandProcessor` handle the execution of the file-based commands.
3. **View Package Enhancements**: Introduction of `GraphicalView` and `WebView` for different representations of the photo album, ensuring flexibility and a seamless user experience.
4. **Main Package Setup**: Serves as the program's entry point, orchestrating the entire application by processing command-line arguments and initiating the respective views.
5. **MVC structure** : Delete some unnecessary classes and adjust the overall project to better align with the MVC structure.
## Usage

To operate the application:

1. Compile the source code into a `.jar` file.
2. Run the jar file with the appropriate command-line arguments to specify the command file path and the desired view type (`graphical` or `web`).

Example command:
1. For graphical view:


```bash
java -jar hw9_final.jar -in buildings.txt -view graphical
java -jar hw9_final.jar -in hoops.txt -view graphical
java -jar hw9_final.jar -in demo_input.txt -view graphical
java -jar hw9_final.jar -in teris_wallpaper.txt -view graphical
```

2. For web view:
```bash
java -jar hw9_final.jar -in buildings.txt -view web -out buildings.html
java -jar hw9_final.jar -in hoops.txt -view web -out hoops.html
java -jar hw9_final.jar -in demo_input.txt -view web -out demo.html
java -jar hw9_final.jar -in teris_wallpaper.txt -view web -out teris.html
```

## Testing
The project includes comprehensive tests covering various scenarios and edge cases to ensure robustness and reliability for the webview

## Documentation
Complete Javadoc documentation is provided for each class, interface, and method to ensure clarity and ease of use. Check the source files for detailed documentation.


## Additional Notes
- Ensure that the paths and commands in the "Example Commands" section are accurate for your environment.
**To exectute the commandline above,first make sure open terminal through these paths: out-> artifacts -> hw9_final_jar**
- Adjust the project name (`hw9_final.jar`) if different when you create your JAR file.
- Update the "Contributing" section with specific guidelines as needed, especially if you're using a public repository.





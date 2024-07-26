

### Overview

PathFinder is a Bukkit/Spigot plugin designed to help with navigation in Minecraft. It assists players by dynamically calculating and visualizing the most efficient paths to significant locations within the game world. Whether returning to a personally set 'home' location or navigating towards friends or points of interest, PathFinder aims to simplify and enrich the player experience with advanced pathfinding capabilities.

### Features

- **Home Navigation**: Automatically calculates and visually indicates the fastest route back to a player-defined 'home' location.
- **Player Tracking**: *Planned Feature* — Facilitates meeting up with friends by finding and showing the quickest path to them in the game world.
- **Safe Path Considerations**: Avoids dangers like lava and ensures that the path is passable, enhancing safety during navigation.

### Motivation

This project was initiated as a practical learning exercise to improve Java programming skills, deepen understanding of object-oriented programming principles, and explore algorithms in a real-world application.

### Installation

1. **Download the Plugin**:
    - Download the `PathFinder.jar` file from the repository.
    - !!THIS WILL BECOME AVAILABLE WHEN PLUGIN IS CONSIDERED FINISHED!! in the meanwhile it's necessary to download the source code and build the .jar yourself. 

2. **Server Setup**:
    - Place the downloaded `.jar` file into the `plugins` directory of your Minecraft server.

3. **Restart Server**:
    - Restart your server to enable the new plugin.

### Usage

- **Setting a Home Location**:
    - Use the command `/sethome` to set your current location as your home.
- **Navigating Home**:
    - Use the command `/findwayhome` to initiate pathfinding to your home. The path will be visually indicated in the game.

### Things to be look out for

- The best use case for this plugin is using inside cave-systems. Using the plugin on the surface over longer distances will take a lot of time. The plugin runs asynchronously so the server won't bork.     
- Reloading the minecraft server using /reload won't stop the pathfinding if activated. Until further development there are no way to stop the pathfinding unless you actually stop the server, and start it again.

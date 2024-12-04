# GameBase

GameBase is a foundational Java library developed to support game development projects. It provides essential utilities and structures, designed as a base framework for personal game projects.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Dependencies](#dependencies)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

This library was created as a reusable foundation for Java-based games. It has been utilized exclusively in personal projects to simplify the implementation of common game functionalities, allowing a focus on game-specific features.

## Features

- **Utility Classes**: Provides commonly used utilities for game development.
- **Modular Design**: Built for easy integration into game projects.
- **Extensible Framework**: Allows for extension and customization to fit specific requirements.

## Dependencies

- **[Slick2D](http://slick.ninjacave.com/)**: A 2D game library for Java used for rendering and game mechanics.

## Installation

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed on your system.
- **Git**: Required to clone the repository.
- **Slick2D**: Download the library and set it up in your development environment.

### Setup

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Horeak/GameBase.git
   cd GameBase
   ```

2. **Set Up Slick2D**:

   Download the [Slick2D library](http://slick.ninjacave.com/) and include the JAR file in your project's classpath.

3. **Compile the Code**:

   Navigate to the `src` directory and compile all Java source files with Slick2D included in the classpath:

   ```bash
   javac -cp "path_to_slick2d/slick.jar" src/**/*.java
   ```

## Usage

Integrate the GameBase library into your Java game projects by including the compiled classes in your classpath alongside Slick2D. Use the provided utility classes and structures to build and manage game components effectively.

## Contributing

As this repository is archived and read-only, contributions are no longer accepted. However, feel free to fork the repository for personal exploration and learning.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

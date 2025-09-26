# ⭕ Circle Drawing Algorithms (Java AWT)

This repository hosts a Java AWT application that visually compares three classic computer graphics algorithms for drawing a circle: **Midpoint**, **Bresenham**, and **Digital Differential Analyzer (DDA)**. The application allows users to select both the algorithm and the line style (Solid, Dotted, or Dashed) via a simple graphical user interface (GUI).

## 🚀 Key Features

* **Three Algorithms Implemented:**
    * **Midpoint** (Optimized for performance)
    * **Bresenham** (Integer arithmetic only)
    * **DDA (Digital Differential Analyzer)** (Parametric/trigonometric approach)
* **Line Styles:** Choose between **Solid** (default), **Dotted**, and **Dashed** output to observe the pixel-by-pixel rendering process.
* **Java AWT GUI:** Utilizes the lightweight Abstract Window Toolkit for a simple, self-contained demonstration.
* **Symmetry Optimization:** All algorithms utilize **8-way symmetry** to calculate only the points in the first octant ($0^\circ$ to $45^\circ$) and reflect them across the plane.

***

## ⚙️ Getting Started

### Prerequisites

You need the **Java Development Kit (JDK)** (version 8 or later) installed on your system.

### Installation and Execution

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/YourUsername/YourRepoName.git](https://github.com/YourUsername/YourRepoName.git)
    cd YourRepoName
    ```
    *(Replace `YourUsername/YourRepoName` with your actual GitHub path.)*

2.  **Compile the Java Code:**
    ```bash
    javac CircleDrawingAWT.java
    ```

3.  **Run the Application:**
    ```bash
    java CircleDrawingAWT
    ```

***

## 💻 How to Use

Once the application launches, a window titled "Circle Drawing AWT" will appear.

1.  **Select Algorithm:** Use the **Algorithm** dropdown (`Midpoint`, `Bresenham`, or `DDA`) to choose the drawing method.
2.  **Select Style:** Use the **Style** dropdown (`Solid`, `Dotted`, or `Dashed`) to change the appearance of the line.
3.  **Draw:** Click the **Draw Circle** button. The circle is rendered instantly on the canvas.

### Current Circle Parameters

The circle is currently drawn with fixed parameters:
* **Center:** $$(150, 150)$$
* **Radius:** $$75$$ pixels

***

## 💡 Algorithm Details

The application implements standard computer graphics methods:

| Algorithm | Method Summary | Optimization |
| :--- | :--- | :--- |
| **Midpoint** | Uses a decision parameter ($P$) to select the next pixel based on which point is closer to the true circle. | Avoids square roots and most floating-point math. |
| **Bresenham** | Similar to Midpoint, but strictly uses integer arithmetic for high efficiency. | Highly optimized for integer operations and minimal calculations. |
| **DDA** | Uses a parametric form, iterating the angle in small steps and calculating $(x, y)$ using trigonometry ($\sin$, $\cos$). | Iterates only from $0^\circ$ to $45^\circ$ ( $\pi/4$ radians) and uses symmetry. |

***

## 🎨 Line Style Implementation

Custom line styles are handled by the low-level `plot` method:

| Style | Condition (`plot` method) | Description |
| :--- | :--- | :--- |
| **Solid** | No condition; plots every pixel. | Standard line. |
| **Dotted** | `if (count % 4 != 0) return;` | Plots only every 4th pixel, creating dots. |
| **Dashed** | `if ((count / 5) % 2 == 1) return;` | Creates a pattern of 5 pixels on, 5 pixels off. |

***

## 👋 Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests for enhancements like allowing user input for circle parameters or exploring other curve generation algorithms.

***

## 📄 License

This project is licensed under the MIT License.

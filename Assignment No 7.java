import java.awt.*;
import java.awt.event.*;

public class CircleDrawingAWT extends Frame implements ActionListener {
    // Defaults: Midpoint, Solid
    int algo = 1, style = 1;

    public CircleDrawingAWT() {
        setTitle("Circle Drawing AWT");
        // Adjusted size slightly for the new center
        setSize(400, 400); 
        setLayout(new FlowLayout());

        String[] algos = { "Midpoint", "Bresenham", "DDA" };
        String[] styles = { "Solid", "Dotted", "Dashed" };

        Choice algoChoice = new Choice(), styleChoice = new Choice();
        for (String a : algos) algoChoice.add(a);
        for (String s : styles) styleChoice.add(s);

        Button drawBtn = new Button("Draw Circle");

        // Use lambda expressions for listeners
        algoChoice.addItemListener(e -> algo = algoChoice.getSelectedIndex() + 1);
        styleChoice.addItemListener(e -> style = styleChoice.getSelectedIndex() + 1);
        drawBtn.addActionListener(this);

        add(new Label("Algorithm:"));
        add(algoChoice);
        add(new Label("Style:"));
        add(styleChoice);
        add(drawBtn);

        setVisible(true);

        // Standard window closing handler
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        // Redraw the circle when the button is pressed
        repaint();
    }

    public void paint(Graphics g) {
        // --- CIRCLE PARAMETERS (Center: 150, 150, Radius: 75) ---
        int xc = 150, yc = 150, r = 75; 
        // -----------------------------
        
        // Clear the drawing area for a clean redraw
        g.clearRect(0, 0, getWidth(), getHeight()); 

        switch (algo) {
            case 1: drawMidpoint(g, xc, yc, r); break;
            case 2: drawBresenham(g, xc, yc, r); break;
            case 3: drawDDA(g, xc, yc, r); break;
        }
    }

    void plot(Graphics g, int x, int y, int count) {
        // Style 2: Dotted (e.g., plot every 4th point)
        if (style == 2 && count % 4 != 0) return; 
        // Style 3: Dashed (e.g., 5 plotted, 5 skipped)
        if (style == 3 && (count / 5) % 2 == 1) return; 
        
        // g.drawLine(x, y, x, y) plots a single pixel
        g.drawLine(x, y, x, y);
    }

    // Plots 8-way symmetric points using a single (x, y) from the first octant
    void drawSymmetricPoints(Graphics g, int xc, int yc, int x, int y, int count) {
        plot(g, xc + x, yc + y, count);
        plot(g, xc - x, yc + y, count);
        plot(g, xc + x, yc - y, count);
        plot(g, xc - x, yc - y, count);
        plot(g, xc + y, yc + x, count);
        plot(g, xc - y, yc + x, count);
        plot(g, xc + y, yc - x, count);
        plot(g, xc - y, yc - x, count);
    }

    // Midpoint Circle Algorithm
    void drawMidpoint(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r, p = 1 - r, count = 0;
        
        // Plot starting point (0, R) and its 8 symmetric partners
        drawSymmetricPoints(g, xc, yc, x, y, count++); 

        while (x < y) {
            x++;
            // Update decision parameter 'p' based on its sign
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
            drawSymmetricPoints(g, xc, yc, x, y, count++);
        }
    }

    // Bresenham's Circle Algorithm
    void drawBresenham(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r, d = 3 - 2 * r, count = 0;
        
        while (x <= y) {
            drawSymmetricPoints(g, xc, yc, x, y, count++);
            
            if (d < 0) {
                // Choose E (East)
                d += 4 * x + 6;
            } else {
                // Choose SE (South-East)
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    // DDA Circle Algorithm (Uses 0 to 45 degree symmetry)
    void drawDDA(Graphics g, int xc, int yc, int r) {
        double step = 1.0 / r; // Angle increment
        double angle = 0, count = 0;
        
        // Loop only up to 45 degrees (pi/4 radians) and rely on symmetry
        while (angle <= Math.PI / 4) { 
            int x = (int) Math.round(r * Math.cos(angle));
            int y = (int) Math.round(r * Math.sin(angle));
            
            drawSymmetricPoints(g, xc, yc, x, y, (int)count++);
            
            angle += step; 
        }
    }

    public static void main(String[] args) {
        new CircleDrawingAWT();
    }
}
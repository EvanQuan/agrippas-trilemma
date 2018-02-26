package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * Uses GridBagLayout and GridBagConstraints to add components to panel
 *
 * @author Evan Quan
 */
public abstract class GridBagPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PADDING = 0;
    private GridBagLayout layout;
    private GridBagConstraints constraint;

    public GridBagPanel() {
        layout = new GridBagLayout();
        constraint = new GridBagConstraints();
        setLayout(layout);
        constraint.insets = new Insets(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
    }

    public void addComponent(Component aComponent) {
        layout.setConstraints(aComponent, constraint);
        add(aComponent, constraint);
    }

    /**
     * Add a component to inputed coordinates with inputed dimensions using
     * GridBagLayout and GridBagConstraints
     *
     * @param Component
     *            aComponent to add to frame
     * @param int
     *            xCoordinate of aComponent
     * @param int
     *            yCoordinate of aComponent
     * @param int
     *            width of aComponent
     * @param int
     *            height of aComponent
     */
    public void addComponent(Component aComponent, int xCoordinate, int yCoordinate, int width, int height) {
        constraint.gridx = xCoordinate;
        constraint.gridy = yCoordinate;
        constraint.gridwidth = width;
        constraint.gridheight = height;
        addComponent(aComponent);
    }

    public int getAnchor(int anchor) {
        return constraint.anchor;
    }

    public int getFill(int fill) {
        return constraint.fill;
    }

    public int getInternalPaddingX(int ipadx) {
        return constraint.ipadx;
    }

    public int getInternalPaddingY(int ipady) {
        return constraint.ipady;
    }

    public double getWeightX(double weightx) {
        return constraint.weightx;
    }

    public double getWeightY(double weighty) {
        return constraint.weighty;
    }

    public void setAnchor(int anchor) {
        constraint.anchor = anchor;
    }

    public void setFill(int fill) {
        constraint.fill = fill;
    }

    public void setHeight(int gridheight) {
        constraint.gridheight = gridheight;
    }

    public void setInsets(int padding) {
        constraint.insets.set(padding, padding, padding, padding);
    }

    public void setInsets(int top, int left, int bottom, int right) {
        constraint.insets.set(top, left, bottom, right);
    }

    public void setInternalPaddingX(int ipadx) {
        constraint.ipadx = ipadx;
    }

    public void setInternalPaddingY(int ipady) {
        constraint.ipady = ipady;
    }

    public void setWeightX(double weightx) {
        constraint.weightx = weightx;
    }

    public void setWeightY(double weighty) {
        constraint.weighty = weighty;
    }

    public void setWidth(int gridwidth) {
        constraint.gridwidth = gridwidth;
    }

    public void setX(int gridx) {
        constraint.gridx = gridx;
    }

    public void setY(int gridy) {
        constraint.gridy = gridy;
    }
}

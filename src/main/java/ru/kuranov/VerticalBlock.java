package ru.kuranov;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Optional;

public class VerticalBlock extends VerticalLayout implements Block {

    float height;
    float width;
    float size = 3.0f;

    NativeButton center = new NativeButton();
    NativeButton left = new NativeButton();
    NativeButton leftUp = new NativeButton();
    NativeButton up = new NativeButton();
    NativeButton rightUp = new NativeButton();
    NativeButton right = new NativeButton();
    NativeButton rightBottom = new NativeButton();
    NativeButton bottom = new NativeButton();
    NativeButton leftBottom = new NativeButton();
    DropTarget<NativeButton> leftTarget = DropTarget.create(left);
    DropTarget<NativeButton> upTarget = DropTarget.create(up);
    DropTarget<NativeButton> rightTarget = DropTarget.create(right);
    DropTarget<NativeButton> bottomTarget = DropTarget.create(bottom);
    HorizontalLayout hl1 = new HorizontalLayout();
    HorizontalLayout hl2 = new HorizontalLayout();
    HorizontalLayout hl3 = new HorizontalLayout();

    public VerticalBlock(String stringHeight, String stringWidth, String name) {

        setId("VERTICAL");
        height = Float.parseFloat(stringHeight.replace("px", ""));
        width = Float.parseFloat(stringWidth.replace("px", ""));

        center.setId("CENTER");
        center.setText(name);
        center.setHeight(height - size * 2, Unit.PIXELS);
        center.setWidth(width - size * 2, Unit.PIXELS);
        hl2.setSpacing(false);
        hl1.setSpacing(false);
        hl3.setSpacing(false);
        hl1.setPadding(false);
        hl2.setPadding(false);
        hl3.setPadding(false);
        setSpacing(false);
        setPadding(false);

        left.setId("DROP-LEFT");
//        left.setHeight(height - size * 2, Unit.PIXELS);
//        left.setWidth(size, Unit.PIXELS);

        leftUp.setId("LEFT-UP");
        leftUp.setHeight(0, Unit.PIXELS);
        leftUp.setWidth(0, Unit.PIXELS);
        leftUp.setVisible(false);

        up.setId("DROP-UP");
//        up.setHeight(size, Unit.PIXELS);
//        up.setWidth(width - size * 2, Unit.PIXELS);

        rightUp.setId("RIGHT-UP");
//        rightUp.setHeight(size, Unit.PIXELS);
//        rightUp.setWidth(size, Unit.PIXELS);
        rightUp.setVisible(false);

        right.setId("DROP-RIGHT");
//        right.setHeight(height - size * 2, Unit.PIXELS);
//        right.setWidth(size, Unit.PIXELS);

        rightBottom.setId("RIGHT-BOTTOM");
//        rightBottom.setHeight(size, Unit.PIXELS);
//        rightBottom.setWidth(size, Unit.PIXELS);
        rightBottom.setVisible(false);

        bottom.setId("DROP-BOTTOM");
//        bottom.setHeight(size, Unit.PIXELS);
//        bottom.setWidth(width - size * 2, Unit.PIXELS);

        leftBottom.setId("LEFT-BOTTOM");
//        leftBottom.setHeight(size, Unit.PIXELS);
//        leftBottom.setWidth(size, Unit.PIXELS);
        leftBottom.setVisible(false);

        hideTarget();

        hl1.add(leftUp, up, rightUp);
        hl2.add(left, center, right);
        hl3.add(leftBottom, bottom, rightBottom);

        upTarget.addDropListener(e -> {
            NativeButton component = e.getComponent();
            Optional<Component> parent = component.getParent();
            VerticalBlock block = (VerticalBlock) parent.get().getParent().get();
            NativeButton center = (NativeButton) block.getCenter();
            String newHeight = String.valueOf(Float.parseFloat(center.getHeight().replace("px", "")) / 2);
//            String newWidth = String.valueOf(Float.parseFloat(center.getWidth().replace("px","")) / 2);
            String newName = String.valueOf(Integer.parseInt(center.getText()) + 1);
            VerticalBlock verticalBlock1 = new VerticalBlock(newHeight, center.getWidth(), newName);
            verticalBlock1.setSpacing(false);
            verticalBlock1.setPadding(false);
            VerticalBlock verticalBlock2 = new VerticalBlock(newHeight, center.getWidth(), center.getText());
            verticalBlock2.setSpacing(false);
            verticalBlock2.setPadding(false);
            block.replaceCenter(verticalBlock1, verticalBlock2);
        });


        add(hl1, hl2, hl3);
    }

    public void showTarget() {

        left.setHeight(height - size * 2, Unit.PIXELS);
        left.setWidth(size, Unit.PIXELS);

        up.setHeight(size, Unit.PIXELS);
        up.setWidth(width - size * 2, Unit.PIXELS);

        right.setHeight(height - size * 2, Unit.PIXELS);
        right.setWidth(size, Unit.PIXELS);

        bottom.setHeight(size, Unit.PIXELS);
        bottom.setWidth(width - size * 2, Unit.PIXELS);

        left.setVisible(true);
        right.setVisible(true);
        up.setVisible(true);
        bottom.setVisible(true);
    }

    public void hideTarget() {
        left.setHeight(0, Unit.PIXELS);
        left.setWidth(0, Unit.PIXELS);

        up.setHeight(0, Unit.PIXELS);
        up.setWidth(0, Unit.PIXELS);

        right.setHeight(0, Unit.PIXELS);
        right.setWidth(0, Unit.PIXELS);

        bottom.setHeight(0, Unit.PIXELS);
        bottom.setWidth(0, Unit.PIXELS);

        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        bottom.setVisible(false);
    }

    //TODO сделать красиво размеры
    private void replaceCenter(VerticalBlock verticalBlock1, VerticalBlock verticalBlock2) {
        VerticalLayout vert = new VerticalLayout();
        vert.setSpacing(false);
        vert.setPadding(false);
        vert.add(verticalBlock1, verticalBlock2);
        hl2.removeAll();
        hl2.add(left, vert, right);
    }

    public void setTargetVisible() {
        left.setVisible(true);
        right.setVisible(true);
        up.setVisible(true);
        bottom.setVisible(true);
    }

    public void setTargetInVisible() {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        bottom.setVisible(false);
    }

    public Component getCenter() {
        return center;
    }
}

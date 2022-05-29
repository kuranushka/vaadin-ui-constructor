package ru.kuranov;


import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class OldBlock extends VerticalLayout {

    int name = 1;
    float size = 3.0f;
    HorizontalLayout hl1 = new HorizontalLayout();
    HorizontalLayout hl2 = new HorizontalLayout();
    HorizontalLayout hl3 = new HorizontalLayout();

    Div container = new Div();

    NativeButton center = new NativeButton();
    NativeButton left = new NativeButton();
    NativeButton right = new NativeButton();
    NativeButton up = new NativeButton();
    NativeButton bottom = new NativeButton();

    NativeButton leftUp = new NativeButton();
    NativeButton rightUp = new NativeButton();
    NativeButton leftBottom = new NativeButton();
    NativeButton rightBottom = new NativeButton();

    DragSource<NativeButton> dragSource = DragSource.create(center);

    DropTarget<NativeButton> leftTarget = DropTarget.create(left);
    DropTarget<NativeButton> rightTarget = DropTarget.create(right);
    DropTarget<NativeButton> upTarget = DropTarget.create(up);
    DropTarget<NativeButton> bottomTarget = DropTarget.create(bottom);

    public OldBlock() {

        setMainSize();
        setDummiesSize();
        center.setText(String.valueOf(name));
        container.add(center);

        hl1.add(leftUp, up, rightUp);
        hl2.add(left, container, right);
        hl3.add(leftBottom, bottom, rightBottom);


        leftTarget.addDropListener(e -> {
            HorizontalLayout newHl = new HorizontalLayout();
            Div newDiv = new Div();
            NativeButton nativeButton = new NativeButton(String.valueOf(++name));
            newDiv.add(nativeButton);
            newHl.add(newDiv, container);
            hl2.replace(container, newHl);
        });


        add(hl1, hl2, hl3);
    }

    private void setMainSize() {
        left.setHeight(100, Unit.PERCENTAGE);
        left.setWidth(100, Unit.PERCENTAGE);
        left.setVisible(false);

        right.setHeight(100, Unit.PERCENTAGE);
        right.setWidth(100, Unit.PERCENTAGE);
        right.setVisible(false);

        up.setHeight(100, Unit.PERCENTAGE);
        up.setWidth(100, Unit.PERCENTAGE);
        up.setVisible(false);

        bottom.setHeight(100, Unit.PERCENTAGE);
        bottom.setWidth(100, Unit.PERCENTAGE);
        bottom.setVisible(false);
    }

    private void setDummiesSize() {
        leftBottom.setWidth(size, Unit.PIXELS);
        leftBottom.setHeight(size, Unit.PIXELS);
        rightBottom.setHeight(size, Unit.PIXELS);
        rightBottom.setWidth(size, Unit.PIXELS);
        leftUp.setHeight(size, Unit.PIXELS);
        leftUp.setWidth(size, Unit.PIXELS);
        rightUp.setHeight(size, Unit.PIXELS);
        rightUp.setWidth(size, Unit.PIXELS);
        rightBottom.setHeight(size, Unit.PIXELS);
        rightBottom.setWidth(size, Unit.PIXELS);
        leftBottom.setVisible(false);
        leftUp.setVisible(false);
        rightBottom.setVisible(false);
        rightUp.setVisible(false);
    }

    public void setName(String name) {
        center.setText(name);
    }

    public void setSize(String height, String width) {
        double h = Double.parseDouble(height.replace("px", ""));
        double w = Double.parseDouble(width.replace("px", ""));

        center.setHeight(h + "px");
        center.setWidth(w + "px");

        left.setHeight((h - size) + "px");
        left.setWidth(size, Unit.PIXELS);

        right.setHeight((h - size) + "px");
        right.setWidth(size, Unit.PIXELS);

        up.setHeight(size, Unit.PIXELS);
        up.setWidth((w - size) + "px");

        bottom.setHeight(size, Unit.PIXELS);
        bottom.setWidth((w - size) + "px");
    }

    public void setVisibleTarget() {
        left.setVisible(true);
        right.setVisible(true);
        up.setVisible(true);
        bottom.setVisible(true);

        leftUp.setVisible(true);
        leftBottom.setVisible(true);
        rightUp.setVisible(true);
        rightBottom.setVisible(true);
    }

    public void setInVisibleTarget() {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        bottom.setVisible(false);

        leftUp.setVisible(false);
        leftBottom.setVisible(false);
        rightUp.setVisible(false);
        rightBottom.setVisible(false);
    }
}

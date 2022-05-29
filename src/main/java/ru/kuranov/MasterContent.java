package ru.kuranov;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("5")
public class MasterContent extends VerticalLayout {

    public MasterContent() {

        Button buttonC = new Button("MAIN");
        Button buttonCC = new Button("MAIN");
        NativeButton buttonL = new NativeButton();
        NativeButton buttonU = new NativeButton();
        NativeButton buttonR = new NativeButton();
        NativeButton buttonB = new NativeButton();



        HorizontalLayout hl = new HorizontalLayout();

        hl.add(buttonL, buttonC, buttonR);

        setAlignItems(Alignment.CENTER);
        add(buttonU, hl, buttonB);
        int componentCount = this.getComponentCount();
        System.out.println(componentCount);
    }
}

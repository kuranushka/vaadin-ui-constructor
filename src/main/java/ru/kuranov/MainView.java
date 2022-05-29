package ru.kuranov;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Route("")
public class MainView extends VerticalLayout {
//    private Person draggedItem;
//    private List<Person> gridItems;

    List<NativeButton> buttonList = new ArrayList<>();

    public MainView() {


        NativeButton element = new NativeButton("ELEMENT");
        element.setHeight(100, Unit.PIXELS);
        element.setWidth(100, Unit.PIXELS);
        DragSource<NativeButton> dragElement = DragSource.create(element);


        Div panel = new Div();
        panel.setWidth(110, Unit.PIXELS);
        panel.setHeight(800, Unit.PIXELS);
        panel.add(element);

        Div field = new Div();
        field.setSizeFull();
        field.setWidth(1200, Unit.PIXELS);
        field.setHeight(800, Unit.PIXELS);
        DropTarget<Div> fieldTarget = DropTarget.create(field);

        fieldTarget.addDropListener(e -> {
            Optional<Object> dragData = e.getDragData();
            Optional<Component> dragSourceComponent = e.getDragSourceComponent();

            VerticalBlock verticalBlock = new VerticalBlock(field.getHeight(), field.getWidth(), "1");
            field.add(verticalBlock);
            field.setSizeFull();
            fieldTarget.setActive(false);
        });

        dragElement.addDragStartListener(e -> {
            showTargets(field.getChildren().collect(Collectors.toList()));
        });

        dragElement.addDragEndListener(e -> {
            hideTargets(field.getChildren().collect(Collectors.toList()));
        });
//        VerticalLayout vl = new VerticalLayout();
//        HorizontalLayout hl = new HorizontalLayout();
//        Block block = new Block();

//        block.setSize(field.getHeight(),field.getWidth());
//
//        vl.add(block);
//        hl.add(block);
//        field.add(block);

//        dragElement.addDragStartListener(e -> {
//            block.setVisibleTarget();
//            field.setSizeFull();
//        });
//        dragElement.addDragEndListener(e -> block.setInVisibleTarget());


//        NativeButton nativeButton = new NativeButton("1");
//        nativeButton.setHeight(100, Unit.PERCENTAGE);
//        nativeButton.setWidth(100, Unit.PERCENTAGE);
//
//        NativeButton nativeButton0 = new NativeButton();
//        nativeButton0.setHeight(3, Unit.PIXELS);
//        nativeButton0.setWidth(100, Unit.PERCENTAGE);
//        nativeButton0.setVisible(false);
//        NativeButton nativeButton2 = new NativeButton();
//        nativeButton2.setHeight(3, Unit.PIXELS);
//        nativeButton2.setWidth(100, Unit.PERCENTAGE);
//        nativeButton2.setVisible(false);
//        DropTarget<NativeButton> target0 = DropTarget.create(nativeButton0);
//        DropTarget<NativeButton> target2 = DropTarget.create(nativeButton2);
//
//
//        VerticalLayout vl = new VerticalLayout();
//        vl.add(nativeButton0, nativeButton, nativeButton2);
//        vl.setSizeFull();
//        field.add(vl);
//
//        dragElement.addDragStartListener(e -> {
//            nativeButton0.setVisible(true);
//            nativeButton2.setVisible(true);
//        });
//        dragElement.addDragEndListener(e -> {
//            nativeButton0.setVisible(false);
//            nativeButton2.setVisible(false);
//        });


        SplitLayout splitLayout = new SplitLayout(panel, field);
        add(splitLayout);


//        gridItems = getTestData();
//        Grid<Person> grid = new Grid<>(Person.class);
//        grid.setColumns("firstName", "lastName");
//        grid.setItems(gridItems);
//        grid.setSortableColumns();
//        grid.setSelectionMode(Grid.SelectionMode.NONE);
//        grid.setRowsDraggable(true);
//
//        grid.addDragStartListener(
//                event -> {
//                    // store current dragged item so we know what to drop
//                    draggedItem = event.getDraggedItems().get(0);
//                    grid.setDropMode(GridDropMode.BETWEEN);
//                }
//        );
//
//        grid.addDragEndListener(
//                event -> {
//                    draggedItem = null;
//                    // Once dragging has ended, disable drop mode so that
//                    // it won't look like other dragged items can be dropped
//                    grid.setDropMode(null);
//                }
//        );
//
//        grid.addDropListener(
//                event -> {
//                    Person dropOverItem = event.getDropTargetItem().get();
//                    if (!dropOverItem.equals(draggedItem)) {
//                        // reorder dragged item the backing gridItems container
//                        gridItems.remove(draggedItem);
//                        // calculate drop index based on the dropOverItem
//                        int dropIndex =
//                                gridItems.indexOf(dropOverItem) + (event.getDropLocation() == GridDropLocation.BELOW ? 1 : 0);
//                        gridItems.add(dropIndex, draggedItem);
//                        grid.getDataProvider().refreshAll();
//                    }
//                }
//        );
//        add(grid);
//    }
//
//    private List<Person> getTestData() {
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("Jake", "Peralta"));
//        persons.add(new Person("Rosa", "Diaz"));
//        persons.add(new Person("Terry", "Jeffords"));
//        persons.add(new Person("Amy", "Santiago"));
//        persons.add(new Person("Charles", "Boyle"));
//        persons.add(new Person("Raymond", "Holt"));
//        return persons;
//    }
//
//    public static class Person {
//        private String firstName;
//        private String lastName;
//
//        public Person(String firstName, String lastName) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//    }
    }

    private void hideTargets(List<Component> collect) {
        for (Component component : collect) {
            if (!component.getId().get().contains("VERTICAL")) {
                hideTargets(component.getChildren().collect(Collectors.toList()));
            } else {
                VerticalBlock component1 = (VerticalBlock) component;
                component1.hideTarget();
            }
        }
    }

    private void showTargets(List<Component> collect) {
        for (Component component : collect) {
            if (!component.getId().get().contains("VERTICAL")) {
                showTargets(component.getChildren().collect(Collectors.toList()));
            } else {
                VerticalBlock component1 = (VerticalBlock) component;
                component1.showTarget();
            }
        }
    }


}


//        Text text1 = new Text("TEXT1");
//        Text text2 = new Text("TEXT2");
//        Text text3 = new Text("TEXT3");
//        DragSource<Text> dragSource1 = DragSource.create(text1);
//        DragSource<Text> dragSource2 = DragSource.create(text2);
//        DragSource<Text> dragSource3 = DragSource.create(text3);


//        Button element= new Button("ELEMENT");
//        element.setHeight(100, Unit.PIXELS);
//        element.setWidth(100, Unit.PIXELS);
//        DragSource<Button> dragElement = DragSource.create(element);
//
//
//        Div left = new Div();
//        left.setWidth(110,Unit.PIXELS);
//        left.setHeight(600, Unit.PIXELS);
//        left.add(element);
//
//        Div right  = new Div();
//        DragSource<Div> divDragSource = DragSource.configure(right);
//        divDragSource.setDraggable(true);
//        divDragSource.addDragEndListener(event ->{
//            right.add(new Text("HHHHHHH"));
//        });
//        dragElement.addDragStartListener(event ->{
//            element.setText("JJJJJJJJJJJJJJJJJJ");
//            right.add(new Text("HHHHHHH"));
//        });
//
//        SplitLayout splitLayout = new SplitLayout(left,right);
//        add(splitLayout);

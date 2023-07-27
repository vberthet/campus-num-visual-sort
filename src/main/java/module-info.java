module edu.campusnum.visualsort {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.campusnum.visualsort to javafx.fxml;
    exports edu.campusnum.visualsort;
}
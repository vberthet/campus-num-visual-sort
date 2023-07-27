module edu.campusnum.visualsort {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.campusnum.visualsort to javafx.fxml;
    exports edu.campusnum.visualsort;
}
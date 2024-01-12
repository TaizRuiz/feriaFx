module com.mycompany.feria_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    opens clases to javafx.base;
    opens com.mycompany.feria_poo to javafx.base, javafx.fxml;
    exports com.mycompany.feria_poo;
}

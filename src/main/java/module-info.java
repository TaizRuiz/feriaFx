module com.mycompany.feria_poo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.feria_poo to javafx.fxml;
    exports com.mycompany.feria_poo;
}

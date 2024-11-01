module com.mutere.sufeeds.sufeedsproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.mutere.sufeeds.sufeedsproject to javafx.fxml;
    exports com.mutere.sufeeds.sufeedsproject;
}
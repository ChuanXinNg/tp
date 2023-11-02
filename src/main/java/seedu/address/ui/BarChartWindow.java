package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.barchartresults.EnrolDateBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.GenderBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SecLevelBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SubjectBarChartCommandResult;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.Subject;

/**
 * Controller of a table page.
 */
public class BarChartWindow extends UiPart<Stage> {
    public static final String FXML = "BarChartWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(BarChartWindow.class);

    @FXML
    private BarChart<String, Number> barChart;

    /**
     * Constructor for creating LineChartWindow instance.
     * @param commandResult BarChart command result instance containing column titles and values.
     */
    public BarChartWindow(CommandResult commandResult) {
        super(FXML, new Stage());
        barChart = createBarChart(commandResult);
        ModelManager.getBarChart(this);
        initialize();
    }

    /**
     * Creates a bar chart with table command result instance containing
     * given column titles and values.
     */
    public static BarChart<String, Number> createBarChart(CommandResult commandResult) {
        if (commandResult instanceof GenderBarChartCommandResult) {
            GenderBarChartCommandResult genderBarChartCommandResult = (GenderBarChartCommandResult) commandResult;
            return createGenderBarChart(genderBarChartCommandResult);
        } else if (commandResult instanceof SecLevelBarChartCommandResult) {
            SecLevelBarChartCommandResult secLevelBarChartCommandResult = (SecLevelBarChartCommandResult) commandResult;
            return createSecLevelBarChart(secLevelBarChartCommandResult);
        } else if (commandResult instanceof SubjectBarChartCommandResult) {
            SubjectBarChartCommandResult subjectBarChartCommandResult = (SubjectBarChartCommandResult) commandResult;
            return createSubjectBarChart(subjectBarChartCommandResult);
        } else {
            EnrolDateBarChartCommandResult enrolDateBarChartCommandResult
                    = (EnrolDateBarChartCommandResult) commandResult;
            return createEnrolDateBarChart(enrolDateBarChartCommandResult);
        }
    }

    /**
     * Create a table with GenderTableCommandResult instance containing counts for each gender.
     * @param commandResult GenderTableCommandResult containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createGenderBarChart(GenderBarChartCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Male", "Female")));
        xAxis.setLabel("Gender");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between genders");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Male", commandResult.getMaleCount()));
        series.getData().add(new XYChart.Data<>("Female", commandResult.getFemaleCount()));

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Create a table with SecLevelTableCommandResult instance containing counts for each sec level.
     * @param commandResult SecLevelTableCommand instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createSecLevelBarChart(SecLevelBarChartCommandResult commandResult) {

        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Sec 1", "Sec 2", "Sec 3", "Sec 4")));
        xAxis.setLabel("Sec Level");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between Sec Levels");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Sec 1", commandResult.getSec1Count()));
        series.getData().add(new XYChart.Data<>("Sec 2", commandResult.getSec2Count()));
        series.getData().add(new XYChart.Data<>("Sec 3", commandResult.getSec3Count()));
        series.getData().add(new XYChart.Data<>("Sec 4", commandResult.getSec4Count()));

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Create a table with SubjectTableCommandResult instance containing counts for each subject
     * @param commandResult SubjectTableCommandResult instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createSubjectBarChart(SubjectBarChartCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                Subject.ENG, Subject.CHI, Subject.EMATH, Subject.AMATH, Subject.PHY, Subject.CHEMI,
                Subject.BIO, Subject.GEOG, Subject.HIST, Subject.SOC)));
        xAxis.setLabel("Subjects");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between Subjects");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(Subject.ENG, commandResult.getEngCount()));
        series.getData().add(new XYChart.Data<>(Subject.CHI, commandResult.getChiCount()));
        series.getData().add(new XYChart.Data<>(Subject.EMATH, commandResult.getEmathCount()));
        series.getData().add(new XYChart.Data<>(Subject.AMATH, commandResult.getAmathCount()));
        series.getData().add(new XYChart.Data<>(Subject.PHY, commandResult.getPhyCount()));
        series.getData().add(new XYChart.Data<>(Subject.CHEMI, commandResult.getChemiCount()));
        series.getData().add(new XYChart.Data<>(Subject.BIO, commandResult.getBioCount()));
        series.getData().add(new XYChart.Data<>(Subject.GEOG, commandResult.getGeogCount()));
        series.getData().add(new XYChart.Data<>(Subject.HIST, commandResult.getHistCount()));
        series.getData().add(new XYChart.Data<>(Subject.SOC, commandResult.getSocCount()));

        barChart.getData().add(series);

        return barChart;

    }

    /**
     * Create a table with EnrolDateBarChartCommandResult instance containing counts for each subject
     * @param commandResult EnrolDateBarChartCommandResult instance containing column titles and counts mapping.
     * @return a BarChartView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createEnrolDateBarChart(EnrolDateBarChartCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")));
        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Bar Chart for student enrolment in different month");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Jan", commandResult.getJanCount()));
        series.getData().add(new XYChart.Data<>("Feb", commandResult.getFebCount()));
        series.getData().add(new XYChart.Data<>("Mar", commandResult.getMarCount()));
        series.getData().add(new XYChart.Data<>("Apr", commandResult.getAprCount()));
        series.getData().add(new XYChart.Data<>("May", commandResult.getMayCount()));
        series.getData().add(new XYChart.Data<>("Jun", commandResult.getJunCount()));
        series.getData().add(new XYChart.Data<>("Jul", commandResult.getJulCount()));
        series.getData().add(new XYChart.Data<>("Aug", commandResult.getAugCount()));
        series.getData().add(new XYChart.Data<>("Sep", commandResult.getSepCount()));
        series.getData().add(new XYChart.Data<>("Oct", commandResult.getOctCount()));
        series.getData().add(new XYChart.Data<>("Nov", commandResult.getNovCount()));
        series.getData().add(new XYChart.Data<>("Dec", commandResult.getDecCount()));

        barChart.getData().add(series);

        return barChart;

    }

    /**
     * Set up the root control, scene and css for the table window.
     */
    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(barChart);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/LineChartWindow.css");
        super.getRoot().setScene(scene);
    }

    /**
     * Shows the bar chart window.
     */
    public void show() {
        logger.fine("Showing bar chart in another window.");
        super.getRoot().show();
        super.getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the table window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Displays a file chooser dialog for saving the current bar chart as a PNG image.
     * If the selected file already exists, prompts the user for confirmation before overwriting.
     * The image is saved after a short delay to allow JavaFX to properly render the chart.
     */
    public void exportAsPng() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("bar_chart.png");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(super.getRoot());

        if (file != null) {
            if (file.exists()) {
                // File already exists, prompt user to confirm overwrite
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("File Already Exists");
                alert.setContentText("The file already exists. Do you want to overwrite it?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User confirmed, proceed with saving the image after a delay
                    Platform.runLater(() -> saveImage(file));
                }
            } else {
                // File doesn't exist, directly save the image after a delay
                Platform.runLater(() -> saveImage(file));
            }
        }
    }

    /**
     * Saves the current state of the bar chart as a PNG image to the specified file.
     * The method captures a snapshot of the bar chart after a delay and writes it to the file.
     *
     * @param file The file where the bar chart image will be saved.
     */
    private void saveImage(File file) {
        PauseTransition pause = new PauseTransition(Duration.millis(600));
        pause.setOnFinished(event -> {
            WritableImage image = barChart.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                System.out.println("Image saved successfully.");
            } catch (IOException e) {
                // Handle IO exception, e.g., show an error dialog or log the exception
                e.printStackTrace();
                // Display an error dialog to the user
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Error Saving Image");
                errorAlert.setContentText("An error occurred while saving the image. Please try again.");
                errorAlert.showAndWait();
            }
        });
        pause.play();
    }


}
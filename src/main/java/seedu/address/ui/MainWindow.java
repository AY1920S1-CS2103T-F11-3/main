package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private FileListPanel fileListPanel;
    private CardListPanel cardListPanel;
    private NoteListPanel noteListPanel;
    private PasswordListPanel passwordListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private EditObjectWindow editObjectWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane objectListPanelPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    @FXML
    private StackPane noteListPanelPlaceholder;

    @FXML
    private StackPane passwordListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
        editObjectWindow = new EditObjectWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        fillInnerPartsWithMode();
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Fills up all the placeholders of this window using the current mode.
     */
    void fillInnerPartsWithMode() {
        switch (logic.getMode()) {
        case "file":
            fileListPanel = new FileListPanel(logic.getFilteredFileList());
            objectListPanelPlaceholder.getChildren().add(fileListPanel.getRoot());
            break;
        case "card":
            cardListPanel = new CardListPanel(logic.getFilteredCardList());
            objectListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());
            break;
        case "note":
            noteListPanel = new NoteListPanel(logic.getFilteredNoteList());
            objectListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());
            break;
        case "password":
            passwordListPanel = new PasswordListPanel(logic.getFilteredPasswordList());
            objectListPanelPlaceholder.getChildren().add(passwordListPanel.getRoot());
            break;
        default:
            personListPanel = new PersonListPanel(logic.getFilteredPersonList());
            objectListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
            break;
        }
    }

    /**
     * Handle UI changes on mode change.
     */
    void handleModeChange() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getPasswordBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        objectListPanelPlaceholder.getChildren().clear();
        fillInnerPartsWithMode();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the window or focuses on it if it's already opened.
     */
    @FXML
    public void handleShowWindow() {
        if (!editObjectWindow.isShowing()) {
            editObjectWindow.show();
        } else {
            editObjectWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public NoteListPanel getNoteListPanel() {
        return noteListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isExit()) {
                handleExit();
            }
            if (commandResult.isGoTo()) {
                switch (commandResult.getModeToGoTo()) {
                case "password":
                    logic.setMode("password");
                    break;
                case "file":
                    logic.setMode("file");
                    break;
                case "note":
                    logic.setMode("note");
                    break;
                case "card":
                    logic.setMode("card");
                    break;
                default:
                    logic.setMode("home");
                    break;
                }
                handleModeChange();
            }
            if (commandResult.isShowWindow()) {
                //TODO optimise this
                String[] tempFeedBack = commandResult.getFeedbackToUser().split("//");
                editObjectWindow.setTitle(tempFeedBack[0]);
                editObjectWindow.setContent(tempFeedBack[1]);
                editObjectWindow.setLogic(logic);
                editObjectWindow.setIndex(tempFeedBack[2]);
                handleShowWindow();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

package application.view

import javafx.application.Application
import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.util.function.Consumer


class CurrencyConverterView : Application() {
    private var controller: application.controller.CurrencyConverterController? = null

    private val choiceBoxSource: ChoiceBox<String> = ChoiceBox<String>()
    private val choiceBoxTarget: ChoiceBox<String> = ChoiceBox<String>()
    private val source = TextField()
    private val target = TextField()

    private val alert = Alert(Alert.AlertType.ERROR, "Please select currency or fill the number field.")

    private val SQLAlert = Alert(Alert.AlertType.ERROR)

    private var stage: Stage? = null

    override fun start(primaryStage: Stage?) {
        stage = Stage()
        stage!!.setTitle("Currency Converter")
        stage!!.setResizable(false)
        target.setEditable(false)


        val hBox = HBox()
        hBox.setAlignment(Pos.BOTTOM_CENTER)

        source.setMinWidth(200.0)
        hBox.children.add(source)
        HBox.setMargin(source, Insets(10.0, 10.0, 10.0, 10.0))

        val vBox1 = VBox()
        vBox1.children.add(Label("Select Currency"))
        vBox1.children.add(choiceBoxSource)
        vBox1.setAlignment(Pos.CENTER)
        VBox.setMargin(choiceBoxSource, Insets(2.0, 0.0, 0.0, 0.0))

        hBox.children.add(vBox1)
        HBox.setMargin(vBox1, Insets(5.0, 10.0, 10.0, 0.0))

        val convertButton = Button("Convert")
        convertButton.styleClass.add("convert-button")
        hBox.children.add(convertButton)
        HBox.setMargin(convertButton, Insets(10.0, 10.0, 10.0, 0.0))

        choiceBoxSource.setOnAction(EventHandler { event: Event? ->
            controller!!.onSourceChoiceBoxChange()
            choiceBoxSource.getItems().remove("Select")
        })

        target.minWidth = 200.0
        hBox.children.add(target)
        HBox.setMargin(target, Insets(10.0, 10.0, 10.0, 0.0))

        val vBox2 = VBox()
        vBox2.children.add(Label("Select Currency"))
        vBox2.children.add(choiceBoxTarget)
        vBox2.setAlignment(Pos.CENTER)
        VBox.setMargin(choiceBoxTarget, Insets(2.0, 0.0, 0.0, 0.0))
        hBox.children.add(vBox2)
        HBox.setMargin(vBox2, Insets(5.0, 10.0, 10.0, 0.0))

        source.setOnKeyTyped(EventHandler { event: KeyEvent? ->
            target.setText("")
        })

        // input filtering
        source.textProperty()
            .addListener(ChangeListener { observable: ObservableValue<out String?>?, oldValue: String?, newValue: String? ->
                // NOTE: ChatGPT generated regex
                if (!newValue!!.matches("\\d*([.,]\\d{0,2})?".toRegex())) {
                    source.text = oldValue
                }
            })

        choiceBoxTarget.onAction = EventHandler { event: Event? ->
            controller!!.onTargetChoiceBoxChange()
            choiceBoxTarget.getItems().remove("Select")
        }

        convertButton.onAction = EventHandler { event: ActionEvent? ->
            controller!!.convertButtonPressed()
        }

        hBox.styleClass.add("hBox")

        val scene = Scene(hBox)

        scene.stylesheets.add(javaClass.getResource("/style.css")?.toExternalForm())

        stage!!.setScene(scene)

        if (!SQLAlert.isShowing) {
            stage!!.show()
        }
    }

    override fun init() {
        controller = application.controller.CurrencyConverterController(this)
    }

    fun setChoiceBoxSource(list: MutableList<String?>) {
        choiceBoxSource.items = FXCollections.observableList(list)
    }

    fun setChoiceBoxTarget(list: MutableList<String?>) {
        choiceBoxTarget.items = FXCollections.observableList(list)
    }

    fun setChoiceBoxSourceValue(s: String?) {
        choiceBoxSource.value = s
    }

    fun setChoiceBoxTargetValue(s: String?) {
        choiceBoxTarget.value = s
    }

    fun getSource(): String? {
        return source.text
    }

    fun getTarget(): String? {
        return target.text
    }

    fun setSource(s: String?) {
        source.setText(s)
    }

    fun setSourcePlaceholder(s: String?) {
        source.setPromptText(s)
    }

    fun setTarget(s: String?) {
        target.setText(s)
    }

    fun setTargetPlaceholder(s: String?) {
        target.setPromptText(s)
    }

    fun showAlert() {
        Platform.runLater(Runnable {
            alert.show()
        })
    }

    val choiceBoxes: Array<String?>
        get() = arrayOf<String?>(choiceBoxSource.getValue() as String?, choiceBoxTarget.getValue() as String?)

    fun showSQLError() {
        println("AHHHH")
        Platform.runLater(Runnable {
            SQLAlert.setTitle("Database Error")
            SQLAlert.setHeaderText("Cannot retrieve currency data")
            SQLAlert.setContentText("Database not found or no currency data")
            SQLAlert.showAndWait().ifPresent { buttonType: ButtonType? ->
                Platform.exit()
            }
        })
    }
}
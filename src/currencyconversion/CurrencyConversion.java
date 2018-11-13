
package currencyconversion;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CurrencyConversion extends Application 
{
    private Scene scene;//For the scence
    private Button convert; //Button for conversion
    
    private TextField poundsValue; //Pounds to be entered or converted
    private TextField dollarsValue; //Dollars to be entered or converted
    
    private RadioButton poundsChoice; 
    //For instantiation for the setUpCurrencyChoices();
    
    private final int sceneWidth = 300; //for the scence width
    private final int sceneHeight = 200; //for the scene height
    
    private final String poundsString = "1"; 
    private final String dollarsString = "1.31"; 
    //This is for conversion purposes.
    //When we run the application, these numbers will appear in the text box already.
    //These numbers will be used in the actually conversion method.
    
    @Override
    public void start(Stage primaryStage)
    {
        //For formula purposes.
      final double dollarsPerPound = 1.31; 
      final double poundsPerDollars = .76;
      
      drawVisualInterface();
      //Sets up the appearance for application.
      
      //The bottom is the action that will happen when you hit the
      //conversion button.
      convert.setOnAction(new EventHandler<ActionEvent>()
      {
          //BUTTON HANDLING
          @Override
          public void handle (ActionEvent event)
          {
              //When you hit convert, the conversion happens. 
              convertCurrency(dollarsPerPound, poundsPerDollars);
          }
      
      });
      
      primaryStage.setTitle("Currency Conversion"); //Window title.
      primaryStage.setScene(scene); //Visual Interface
      
      primaryStage.show();
      
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
    private void drawVisualInterface()
    {
        BorderPane borderPane = new BorderPane();
        
        borderPane.setTop(setUpCurrencyChoices());//Sets the HBox - The Radio Buttons
        borderPane.setCenter(setUpCurrencyInformation()); //Sets the VBox - For Text Fields
        
        convert = new Button("Convert");
        //instantiate button.
        
        borderPane.setBottom(convert);
        
        scene = new Scene(borderPane, sceneWidth, sceneHeight);
    
    
    }
    private HBox setUpCurrencyChoices() //For currency selection and returns HBOX
    {
        poundsChoice = new RadioButton("Pounds");
        //pounds selection button instantiated

        RadioButton dollarsChoice = new RadioButton("Dollars");
        //dollars selection button instantiated
        
        poundsChoice.setSelected(true);//pounds will be preselected.
        
        ToggleGroup tg = new ToggleGroup();
        //Allows you to switch from pounds and dollars
        
        
        poundsChoice.setToggleGroup(tg);
        dollarsChoice.setToggleGroup(tg);
        //Adds two radio buttons to the toggle group.
        
        HBox currencyChoices = new HBox(); //Horizontal Box
        
        //Now we will add the two buttons to display
        currencyChoices.getChildren().add(poundsChoice);
        currencyChoices.getChildren().add(dollarsChoice);
        
        
        return currencyChoices; //Returns the display.
    }
    
    private VBox setUpCurrencyInformation()
    {
        VBox currencyInformation = new VBox(); //For the text fields.
        
        
        currencyInformation.getChildren().add(setUpPoundsInformation());
        currencyInformation.getChildren().add(setUpDollarsInformation());
        
        //Pounds: [         ]
        //Dollars: [         ]
        
        //This is what you want to display.
        return currencyInformation;
    }
    
    private HBox setUpPoundsInformation() //For Pounds TEXTBOX
    {
        Label poundsName = new Label("Pounds: "); //For labeling the text field
        poundsValue = new TextField(poundsString);
        //Set default pounds in textbox.
        
        HBox poundsInformation = new HBox(); //For setting up text field.
        
        poundsInformation.getChildren().add(poundsName); //Displays Name
        poundsInformation.getChildren().add(poundsValue); //Displays TextField.
        
        //Pounds: [         ]
        //When you return this hbox thats what you want to be displayed.
    
        return poundsInformation;
    }
    
    private HBox setUpDollarsInformation()//For dollars textbox
    {
        Label dollarsName = new Label("Dollars: ");//For labeling the text field
        dollarsValue = new TextField(dollarsString);
        //Sets default dollars in textbox.
        
        HBox dollarsInformation = new HBox(); //For setting up text field.
        
        dollarsInformation.getChildren().add(dollarsName); //Displays Name
        dollarsInformation.getChildren().add(dollarsValue); //Displays TextField.
        
        //Dollars: [         ]
        //When you return this hbox thats what you want to be displayed.
    
        return dollarsInformation;
        
    }
    
    private void convertCurrency(double dollarsPerPound, double poundsPerDollars)
            //The action that will perform when you hit convert. This handles the conversion.
    {
        if(poundsChoice.isSelected())//If the button is on pounds.
            //Dollars will be converted to pounds.
        {
            //this will convert the dollars from string to a double.
            double dollars = Double.parseDouble(dollarsValue.getText());
            //Pounds = .76 * dollars;
            double pounds = poundsPerDollars * dollars;
            
            //converts the pounds from double back to string for the text field.
            poundsValue.setText(String.valueOf(pounds));
        }
        else
            //Pounds will be converted to dollars.
        {
            //this will convert the pounds from string to a double.
            double pounds = Double.parseDouble(poundsValue.getText());
            //Dollars = 1.31 * pounds;
            double dollars = dollarsPerPound * pounds;
            
            //converts the dollars from double back to string for the text field.
            dollarsValue.setText(String.valueOf(dollars));
        
        }
    }
    
}

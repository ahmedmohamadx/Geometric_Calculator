/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*********shapes**************************************/
class GeometricObject {

    double calculateArea() {
        return 0.0;
    }
 

    double calculatePerimeter() {
        return 0.0;
    }
}

class Rectangle extends GeometricObject {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double calculateArea() {
        return width * height;
    }

    double calculatePerimeter() {
        return 2 * (width + height);
    }
}

class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}

class Circle extends GeometricObject {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }

    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
/********* GUI Implementation **************************************/
public class Project {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometric Calculator");
        frame.setBounds(500,200,400, 300);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        frame.setVisible(true);
    }
 private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel shapeLabel = new JLabel("Select Shape:");
        shapeLabel.setBounds(10, 20, 80, 25);
        panel.add(shapeLabel);

        String[] shapes = {"Rectangle", "Square", "Circle"};
        JComboBox<String> shapeComboBox = new JComboBox<>(shapes);
        shapeComboBox.setBounds(100, 20, 120, 25);
        panel.add(shapeComboBox);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(10, 50, 80, 25);
        panel.add(widthLabel);
        
        JTextField widthField = new JTextField();
        widthField.setBounds(100, 50, 120, 25);
        panel.add(widthField);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(10, 80, 80, 25);
        panel.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(100, 80, 120, 25);
        panel.add(heightField);

        JLabel radiusLabel = new JLabel("Radius:");
        radiusLabel.setBounds(10, 50, 80, 25);
        panel.add(radiusLabel);

        JTextField radiusField = new JTextField();
        radiusField.setBounds(100, 50, 120, 25);
        panel.add(radiusField);
        
  JLabel sideLabel=new JLabel("Side: ");
  sideLabel.setBounds(10, 50, 80, 25);
  panel.add(sideLabel);
  JTextField sideField = new JTextField();
        sideField.setBounds(100, 50, 120, 25);
        panel.add(sideField);
        JButton calculateButton = new JButton("Calculate and Display");
        calculateButton.setBounds(100, 140, 180, 25);
        panel.add(calculateButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 170, 80, 25);
        panel.add(resultLabel);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(100, 170, 250, 80);
        panel.add(resultArea);
 widthLabel.setVisible(true);
        widthField.setVisible(true);
        heightLabel.setVisible(true);
        heightField.setVisible(true);
        radiusLabel.setVisible(false);
        radiusField.setVisible(false);
         sideLabel.setVisible(false);
         sideField.setVisible(false);
         shapeComboBox.addActionListener(new ActionListener() {
             
            @Override
              
            public void actionPerformed(ActionEvent e) {
                 sideField.setText("");
              widthField.setText("");
               heightField.setText("");
              radiusField.setText("");
              resultArea.setText("");
                String selectedShape = (String) shapeComboBox.getSelectedItem();
              
      if ("Rectangle".equals(selectedShape)) {
           widthLabel.setVisible(true);
        widthField.setVisible(true);
             widthField.setVisible(true);
        heightLabel.setVisible(true);
        heightField.setVisible(true);
        radiusLabel.setVisible(false);
        radiusField.setVisible(false);
         sideLabel.setVisible(false);
         sideField.setVisible(false);
        } else if ("Circle".equals(selectedShape)) {
                  widthField.setVisible(false);
                    widthLabel.setVisible(false);
        heightLabel.setVisible(false);
        heightField.setVisible(false);
        radiusLabel.setVisible(true);
        radiusField.setVisible(true);
         sideLabel.setVisible(false);
         sideField.setVisible(false);
        } else if ("Square".equals(selectedShape)) {
                       widthField.setVisible(false);
        heightLabel.setVisible(false);
        heightField.setVisible(false);
        widthLabel.setVisible(false);
        widthField.setVisible(false);
        radiusLabel.setVisible(false);
        radiusField.setVisible(false);
         sideLabel.setVisible(true);
         sideField.setVisible(true);

        }
            }
        });
 class NegativeLengthException extends Exception {
    public NegativeLengthException() {
        super("Dimension can't be negative");
    }
}
        calculateButton.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeComboBox.getSelectedItem();
                double inputValue;
                 
           try {
            switch (selectedShape) {
                case "Rectangle":
                    double widthR = Double.parseDouble(widthField.getText());
                    double heightR =  Double.parseDouble(heightField.getText());
                    
                    checkNonNegative(widthR, heightR);
                   
                    inputValue = widthR * heightR;
                    break;
                case "Square":                  
                    inputValue = Double.parseDouble(sideField.getText());
                   
                    checkNonNegative(inputValue);               
                    break;
                case "Circle":
                    inputValue = Double.parseDouble(radiusField.getText());
                    checkNonNegative(inputValue);
                    break;
                default:
                    resultArea.setText("Invalid shape selection.");
                    return;
            }
            handleCalculation(selectedShape, inputValue);
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter valid numbers.");
        }  catch (NegativeLengthException ex) {
            resultArea.setText(ex.getMessage());
        }
            
            
    }

  private void checkNonNegative(double... values) throws NegativeLengthException {
        for (double value : values) {
            if (value < 0) {
                throw new NegativeLengthException();
            }
        }
    }
  
    private void handleCalculation(String selectedShape, double inputValue) {
        GeometricObject geometricObject;
               
      
                switch (selectedShape) {
                    case "Rectangle":
                        geometricObject = new Rectangle(Double.parseDouble(widthField.getText()), Double.parseDouble(heightField.getText()));
                        break;
                    case "Square":
                        geometricObject = new Square(Double.parseDouble(sideField.getText()));
                        break;
                    case "Circle":
                        geometricObject = new Circle(Double.parseDouble(radiusField.getText()));
                        break;
                    default:
                        resultArea.setText("Invalid shape selection.");
                        return;
                }

                double area = geometricObject.calculateArea();
                double perimeter = geometricObject.calculatePerimeter();

                resultArea.setText("Area: " + area + "\nPerimeter: " + perimeter);
            }
   
     
        });
    }
}   

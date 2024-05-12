package src.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.AlgoritmoGenetico;
import src.cruce.CruceMonopunto;
import src.cruce.ICruce;
import src.individuo.Individuo;
import src.individuo.IndividuoFuncion1;
import src.mutacion.IMutacion;
import src.mutacion.MutacionBoolean;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.seleccion.SeleccionTruncamiento;

/* 
 * Class where all the options will be added to a panel. Will be later added to the main frame.
 */

public class OptionsView {
  // grid baglayout variables
  final boolean shouldFill = false;
  
  double precision = 0.001;

  AlgoritmoGenetico ag;


  ArrayList<Individuo> funcionList = new ArrayList<>();
  String[] funcionNames;
  ArrayList<ISeleccion> seleccionList = new ArrayList<>();
  String[] seleccionNames;
  ArrayList<ICruce> cruceList = new ArrayList<>();
  String[] cruceNames;
  ArrayList<IMutacion> mutacionList = new ArrayList<>();
  String[] mutacionNames;

  JPanel optionsPanel;

  public OptionsView() {

    initArrayLists();

    optionsPanel = new JPanel();
    optionsPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    if (shouldFill) {
      c.fill = GridBagConstraints.HORIZONTAL;
    }

    // Tam Poblacion --------------------------------------------------------------

    JLabel tamPobLabel = new JLabel("Tamaño de Poblacion:");
    c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
    c.ipady = 10; // padding
    c.ipadx = 10;
    c.weightx = 0.0; // Don't fill the available horizontal space
    c.weighty = 0.0; // Don't fill the available vertical space
    c.gridwidth = 1; // 1 column wide
    c.gridheight = 1; // 1 row tall
    c.gridx = 0; // leftmost column
    c.gridy = 0; // top row
    optionsPanel.add(tamPobLabel, c);

    JTextField tamPobText = new JTextField();
    tamPobText.setText("100");
    c.gridx = 1; // rightmost column
    c.gridy = 0; // top row
    optionsPanel.add(tamPobText, c);

    // Num Generaciones
    // --------------------------------------------------------------
    JLabel numGenLabel = new JLabel("Numero de Generaciones:");
    c.gridx = 0; // leftmost column
    c.gridy = 1; // second row
    optionsPanel.add(numGenLabel, c);

    JTextField numGenText = new JTextField();
    numGenText.setText("100");
    c.gridx = 1; // rightmost column
    c.gridy = 1; // second row
    optionsPanel.add(numGenText, c);

    // Funcion a optimizar
    // ----------------------------------------------------------
    JLabel funcionLabel = new JLabel("Funcion a Optimizar:");
    c.gridx = 0; // leftmost column
    c.gridy = 2; // third row
    optionsPanel.add(funcionLabel, c);

    JComboBox<String> funcionCombo = new JComboBox<>(funcionNames);
    funcionCombo.setSelectedIndex(0);
    c.gridx = 1; // rightmost column
    c.gridy = 2; // third row
    optionsPanel.add(funcionCombo, c);

    // Precision de la representacion ----------------------------------------------
    JLabel precisionLabel = new JLabel("Precision:");
    c.gridx = 0; // leftmost column
    c.gridy = 3; // fourth row
    optionsPanel.add(precisionLabel, c);

    JTextField precisionText = new JTextField();
    precisionText.setText("0.001");
    c.gridx = 1; // rightmost column
    c.gridy = 3; // fourth row
    optionsPanel.add(precisionText, c);

    // Tipo de seleccion -----------------------------------------------------------
    JLabel seleccionLabel = new JLabel("Metodo de Seleccion:");
    c.gridx = 0; // leftmost column
    c.gridy = 4; // fifth row
    optionsPanel.add(seleccionLabel, c);

    JComboBox<String> seleccionCombo = new JComboBox<>(seleccionNames);
    seleccionCombo.setSelectedIndex(0);
    c.gridx = 1; // rightmost column
    c.gridy = 4; // fifth row
    optionsPanel.add(seleccionCombo, c);

    // Tipo de cruce ---------------------------------------------------------------
    JLabel cruceLabel = new JLabel("Metodo de Cruce:");
    c.gridx = 0; // leftmost column
    c.gridy = 5; // sixth row
    optionsPanel.add(cruceLabel, c);

    JComboBox<String> cruceCombo = new JComboBox<>(cruceNames);
    cruceCombo.setSelectedIndex(0);
    c.gridx = 1; // rightmost column
    c.gridy = 5; // sixth row
    optionsPanel.add(cruceCombo, c);

    // Probabilidad de cruce -------------------------------------------------------
    JLabel probCruceLabel = new JLabel("Probabilidad Cruce:");
    c.gridx = 0; // leftmost column
    c.gridy = 6; // seventh row
    optionsPanel.add(probCruceLabel, c);

    JTextField probCruceText = new JTextField();
    probCruceText.setText("60");
    c.gridx = 1; // rightmost column
    c.gridy = 6; // seventh row
    optionsPanel.add(probCruceText, c);

    // Tipo de mutacion -----------------------------------------------------------
    JLabel mutacionLabel = new JLabel("Metodo de Mutacion:");
    c.gridx = 0; // leftmost column
    c.gridy = 7; // eighth row
    optionsPanel.add(mutacionLabel, c);

    JComboBox<String> mutacionCombo = new JComboBox<>(mutacionNames);
    mutacionCombo.setSelectedIndex(0);
    c.gridx = 1; // rightmost column
    c.gridy = 7; // eighth row
    optionsPanel.add(mutacionCombo, c);

    // Probabilidad de mutacion ---------------------------------------------------
    JLabel probMutLabel = new JLabel("Probabilidad Mutacion:");
    c.gridx = 0; // leftmost column
    c.gridy = 8; // ninth row
    optionsPanel.add(probMutLabel, c);

    JTextField probMutText = new JTextField();
    probMutText.setText("5");
    c.gridx = 1; // rightmost column
    c.gridy = 8; // ninth row
    optionsPanel.add(probMutText, c);

    // Elitismo -------------------------------------------------------------------
    JLabel elitismoLabel = new JLabel("Elitismo:");
    c.gridx = 0; // leftmost column
    c.gridy = 9; // tenth row
    optionsPanel.add(elitismoLabel, c);

    JTextField elitismoText = new JTextField();
    elitismoText.setText("0");
    c.gridx = 1; // rightmost column
    c.gridy = 9; // tenth row
    optionsPanel.add(elitismoText, c);

    // Dimensiones del problema ---------------------------------------------------
    JLabel dimensionesLabel = new JLabel("Dimensiones:");
    c.gridx = 0; // leftmost column
    c.gridy = 10; // eleventh row
    optionsPanel.add(dimensionesLabel, c);

    JTextField dimensionesText = new JTextField();
    dimensionesText.setText("2");
    c.gridx = 1; // rightmost column
    c.gridy = 10; // eleventh row
    optionsPanel.add(dimensionesText, c);

    // Run button ------------------------------------------------------------------
    JButton runButton = new JButton("Run");
    c.gridx = 0; // leftmost column
    c.gridy = 11; // twelfth row
    c.gridwidth = 2; // 2 columns wide
    optionsPanel.add(runButton, c);

    // Mejor Historico Panel -------------------------------------------------------
    JLabel mejorHistoricoLabel = new JLabel("Mejor Historico:");
    c.gridx = 0; // leftmost column
    c.gridy = 12; // thirteenth row
    c.gridwidth = 1; // 1 column wide
    optionsPanel.add(mejorHistoricoLabel, c);

    JLabel mejorHistoricoText = new JLabel("");
    c.gridx = 1; // rightmost column
    c.gridy = 12; // thirteenth row
    optionsPanel.add(mejorHistoricoText, c);
    
    runButton.addActionListener(e -> {
      System.out.println("Run button pressed");

      ag = new AlgoritmoGenetico(Integer.parseInt(tamPobText.getText()),
                                Integer.parseInt(numGenText.getText()),
                                Double.parseDouble(probCruceText.getText()) / 100, 
                                Double.parseDouble(probMutText.getText()) / 100,
                                Double.parseDouble(elitismoText.getText()) / 100, 
                                Double.parseDouble(precisionText.getText()),
                                funcionList.get(funcionCombo.getSelectedIndex()), 
                                cruceList.get(cruceCombo.getSelectedIndex()),
                                mutacionList.get(mutacionCombo.getSelectedIndex()), 
                                seleccionList.get(seleccionCombo.getSelectedIndex()));

      ag.run();

      Individuo mejor = ag.getMejorHistorico();
			double[] mejorHistoricoArray = ag.getMejorHistoricoArray();
			double[] mejorGeneracionArray = ag.getMejorGeneracionArray();
			double[] mediaFitnessArray = ag.getMediaFitnessArray();
			double[] numGeneraciones = new double[ag.getNumGen()];
			for (int i = 0; i < ag.getNumGen(); ++i) {
				numGeneraciones[i] = i;
			}
      GraphView.plotLines(numGeneraciones, mejorHistoricoArray, mejorGeneracionArray, mediaFitnessArray);

      String mejorHistorico = "";
      mejorHistorico = mejor.showable();

      mejorHistoricoText.setText(mejorHistorico);
    });
  
  }



  // TODO Add all the implemented classes
  private void initArrayLists() {
    funcionList.add(new IndividuoFuncion1(precision));
    funcionNames = new String[funcionList.size()];
    for (Individuo individuo : funcionList) {
      funcionNames[funcionList.indexOf(individuo)] = individuo.toString();
    }
    
    seleccionList.add(new SeleccionRuleta());
    seleccionList.add(new SeleccionTruncamiento());
    seleccionNames = new String[seleccionList.size()];
    for (ISeleccion seleccion : seleccionList) {
      seleccionNames[seleccionList.indexOf(seleccion)] = seleccion.toString();
    }

    cruceList.add(new CruceMonopunto());
    cruceNames = new String[cruceList.size()];
    for (ICruce cruce : cruceList) {
      cruceNames[cruceList.indexOf(cruce)] = cruce.toString();
    }

    mutacionList.add(new MutacionBoolean());
    mutacionNames = new String[mutacionList.size()];
    for (IMutacion mutacion : mutacionList) {
      mutacionNames[mutacionList.indexOf(mutacion)] = mutacion.toString();
    }
  }

  public JPanel getOptionsPanel() {
    return optionsPanel;
  }
}

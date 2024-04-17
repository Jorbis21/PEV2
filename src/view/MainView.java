package src.view;

import java.awt.Frame;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.math.plot.Plot2DPanel;

import src.aeropuerto.Aeropuerto;
import src.cruce.ICruce;
import src.individuo.Individuo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;
import src.utils.Pair;
import src.utils.Solution;
import src.AlgoritmoGenetico;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.custom.ScrolledComposite;

public class MainView {

	private static AlgoritmoGenetico ag;
	protected Shell shell;
	private String numIndv;
	private Text numGeneraciones;
	private String numGen;
	private Text mutacion;
	private String mut;
	private Text probCruce;
	private String pCruce;
	private Text numIndividuos;
	private Text elitismo;

	private String elite;
	
	private ArrayList<ISeleccion> selecciones = new ArrayList<ISeleccion>();
	private ArrayList<String> seleccionesString = new ArrayList<String>();
	private ArrayList<ICruce> cruces = new ArrayList<ICruce>();
	private ArrayList<String> crucesString = new ArrayList<String>();
	private ArrayList<IMutacion> mutaciones = new ArrayList<IMutacion>();
	private ArrayList<String> mutacionesString = new ArrayList<String>();
	private ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
	private ArrayList<String> aeropuertosString = new ArrayList<String>();


	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
			ag = new AlgoritmoGenetico();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(898, 551);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(5, false));
		new Label(shell, SWT.NONE);

		initArrayLists();
		

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Grafica
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */
		 new Label(shell, SWT.NONE);
		 new Label(shell, SWT.NONE);
		 new Label(shell, SWT.NONE);
 
		 Composite graph = new Composite(shell, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		 graph.setToolTipText("Grafica que muestra la evolucion de poblacion");
		 graph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 17));
		 
		 double[] generaciones = new double[ag.getNumGeneraciones()];
		 for(int i = 0; i < ag.getNumGeneraciones(); i++){
			 generaciones[i] = i;
		 }

		 double[] mediaFitness = ag.getMediaFitness();
		 double[] mejorHistorico = ag.getMejorHistorico();
		 double[] mejorGeneracion = ag.getMejorGeneracion();

		 // create your PlotPanel (you can use it as a JPanel)
		 Plot2DPanel initialPlot = new Plot2DPanel();
		 // define the legend position
		 initialPlot.addLegend("SOUTH");
		 // add a line plot to the PlotPanel
		 initialPlot.addLinePlot("MEDIA FITNESS", generaciones, mediaFitness);
		 initialPlot.addLinePlot("MEJOR ABSOLUTO", generaciones, mejorHistorico);
		 initialPlot.addLinePlot("MEJOR DE GENERACION", generaciones, mejorGeneracion);
		 // put the PlotPanel in the graph composite
		 Frame graphFrame = SWT_AWT.new_Frame(graph);
		 graphFrame.add(initialPlot);

		

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Tam poblacion
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblNumIndividuos = new Label(shell, SWT.NONE);
		lblNumIndividuos.setText("Num. Individuos");

		numIndividuos = new Text(shell, SWT.BORDER);
		numIndividuos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					numIndv = numIndividuos.getText();
					ag.setTamPoblacion(Integer.parseInt(numIndv));
				}
			}
		});
		numIndividuos.setToolTipText("Seleccionar el numero de Individuos (2 <= Num. Generaciones <= 10000)");
		numIndividuos.setText("100");
		GridData gd_numIndividuos = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_numIndividuos.widthHint = 75;
		numIndividuos.setLayoutData(gd_numIndividuos);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Numero Generaciones
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblNumGeneraciones = new Label(shell, SWT.NONE);
		lblNumGeneraciones.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNumGeneraciones.setText("Num. Generaciones");

		numGeneraciones = new Text(shell, SWT.BORDER);
		numGeneraciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					numGen = numGeneraciones.getText();
					ag.setNumGeneraciones(Integer.parseInt(numGen));
				}
			}
		});
		numGeneraciones.setToolTipText("Seleccionar el numero de generaciones (1 <= Num. Generaciones <= 10000)");
		numGeneraciones.setText("100");
		GridData gd_numGeneraciones = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_numGeneraciones.widthHint = 112;
		numGeneraciones.setLayoutData(gd_numGeneraciones);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Aeropuerto
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblAeropuerto = new Label(shell, SWT.NONE);
		lblAeropuerto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblAeropuerto.setToolTipText("");
		lblAeropuerto.setText("Aeropuerto");

		Combo aeropuerto = new Combo(shell, SWT.NONE);
		aeropuerto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = aeropuerto.getSelectionIndex();
				ag.setAeropuerto(aeropuertos.get(id));
			}
		});

		aeropuerto.setToolTipText("Dropdown para elegir el aeropuerto");
		String [] aeropuertosArray = new String[aeropuertosString.size()];
		aeropuertosArray = aeropuertosString.toArray(aeropuertosArray);
		aeropuerto.setItems(aeropuertosArray);
		GridData gd_aeropuerto = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_aeropuerto.widthHint = 145;
		aeropuerto.setLayoutData(gd_aeropuerto);
		aeropuerto.setText("Aeropuerto 1");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Probabilidad de cruce
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblProbCruce = new Label(shell, SWT.NONE);
		lblProbCruce.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblProbCruce.setText("Prob. Cruce");

		probCruce = new Text(shell, SWT.BORDER);
		probCruce.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					pCruce = probCruce.getText();
					ag.setProbCruce(Double.parseDouble(pCruce));
				}
			}
		});
		probCruce.setToolTipText(
				"Seleccionar la probabilidad de que los individuos se crucen (0.1 = 10% de probabilidad de cruce) (0.0 <= Prob. Cruce <= 1)");
		probCruce.setText("0.6");
		GridData gd_probCruce = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_probCruce.widthHint = 116;
		probCruce.setLayoutData(gd_probCruce);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Probabilidad de mutacion
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblProbMutacion = new Label(shell, SWT.NONE);
		lblProbMutacion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblProbMutacion.setText("Prob. Mutacion");

		mutacion = new Text(shell, SWT.BORDER);
		mutacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					mut = mutacion.getText();
					ag.setProbMutacion(Double.parseDouble(mut));
				}	
			}
		});
		mutacion.setToolTipText(
				"Seleccionar la probabilidad de que los individuos sufran mutaciones (0.1 = 10% de probabilidad de mutacion) (0.0 <= Prob. Mutacion <= 1)");
		mutacion.setText("0.1");
		GridData gd_mutacion = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_mutacion.widthHint = 116;
		mutacion.setLayoutData(gd_mutacion);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Metodo de Seleccion
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblMetodoSeleccion = new Label(shell, SWT.NONE);
		lblMetodoSeleccion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblMetodoSeleccion.setText("Metodo Seleccion");

		Combo metodoSeleccion = new Combo(shell, SWT.NONE);
		metodoSeleccion.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = metodoSeleccion.getSelectionIndex();
				ag.setSeleccion(selecciones.get(id));
			}
		});

		metodoSeleccion.setToolTipText("Drop down para elegir el metodo de Seleccion");
		String [] seleccionesArray = new String[seleccionesString.size()];
		seleccionesArray = seleccionesString.toArray(seleccionesArray);
		metodoSeleccion.setItems(seleccionesArray);
		GridData gd_metodoSeleccion = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_metodoSeleccion.widthHint = 132;
		metodoSeleccion.setLayoutData(gd_metodoSeleccion);
		metodoSeleccion.setText("Ruleta");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Metodo de Cruce
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblMetodoCruce = new Label(shell, SWT.NONE);
		lblMetodoCruce.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblMetodoCruce.setText("Metodo Cruce");

		Combo metodoCruce = new Combo(shell, SWT.NONE);
		metodoCruce.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = metodoCruce.getSelectionIndex();
				ag.setCruce(cruces.get(id));
			}
		});

		metodoCruce.setToolTipText("Drop down para elegir el modo de cruce");
		String [] crucesArray = new String[crucesString.size()];
		crucesArray = crucesString.toArray(crucesArray);
		metodoCruce.setItems(crucesArray);
		GridData gd_metodoCruce = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_metodoCruce.widthHint = 139;
		metodoCruce.setLayoutData(gd_metodoCruce);
		metodoCruce.setText("COUniforme");

		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Metodo de Mutacion
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblMetodoMutacion = new Label(shell, SWT.NONE);
		lblMetodoMutacion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblMetodoMutacion.setText("Metodo Mutacion");

		Combo metodoMutacion = new Combo(shell, SWT.NONE);
		metodoMutacion.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = metodoMutacion.getSelectionIndex();
				ag.setMutacion(mutaciones.get(id));
			}
		});
		metodoMutacion.setToolTipText("Drop down para elgir el metodo de mutacion");
		String [] mutacionesArray = new String[mutacionesString.size()];
		mutacionesArray = mutacionesString.toArray(mutacionesArray);
		metodoMutacion.setItems(mutacionesArray);
		GridData gd_metodoMutacion = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_metodoMutacion.widthHint = 122;
		metodoMutacion.setLayoutData(gd_metodoMutacion);
		metodoMutacion.setText("Intercambio");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Elitismo
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */

		Label lblElitismo = new Label(shell, SWT.NONE);
		lblElitismo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblElitismo.setText("Proporcion de Elitismo");

		elitismo = new Text(shell, SWT.BORDER);
		elitismo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR){ // if enter is pressed we get the value
					elite = elitismo.getText();
					ag.setPropElite(Double.parseDouble(elite));
				}
			}
		});
		elitismo.setText("0.0");
		elitismo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Scrolled composite
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */
		// aqui mostraremos resultados para que sea mas facil ver la solucion
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 5));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		/*
		 * -----------------------------------------------------------------------------
		 * -------------- Boton Run
		 * -----------------------------------------------------------------------------
		 * ---------------
		 */
		Button btnRun = new Button(shell, SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ag.run();
				// update graph
				double[] generaciones = new double[ag.getNumGeneraciones()];
				for(int i = 0; i < ag.getNumGeneraciones(); i++){
					generaciones[i] = i;
				}

				double[] mediaFitness = ag.getMediaFitness();
				double[] mejorHistorico = ag.getMejorHistorico();
				double[] mejorGeneracion = ag.getMejorGeneracion();

				// create your PlotPanel (you can use it as a JPanel)
				Plot2DPanel plot = new Plot2DPanel();
				// define the legend position
				plot.addLegend("SOUTH");
				// add a line plot to the PlotPanel
				plot.addLinePlot("MEDIA FITNESS", generaciones, mediaFitness);
				plot.addLinePlot("MEJOR DE GENERACION", generaciones, mejorGeneracion);
				plot.addLinePlot("MEJOR ABSOLUTO", generaciones, mejorHistorico);
				// put the PlotPanel in the graph composite
				graphFrame.removeAll();
				graphFrame.add(plot);
				graphFrame.validate();
				
				Pair<Individuo, ArrayList<Solution>> resultado = ag.getResults();
				String data = ag.getTableAeropuerto(resultado.getFirst());
				Text text = new Text(scrolledComposite, SWT.MULTI | SWT.BORDER | SWT.WRAP);
				text.setText(data);

				scrolledComposite.setContent(text);
				scrolledComposite.setMinSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));

				text.append("El mejor individuo es: " + resultado.getFirst().getGenotipo() + "\n\t\t con un fitness de: " + resultado.getFirst().getFitness() + "\n\n");
			}
		});
		GridData gd_btnRun = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
		gd_btnRun.widthHint = 113;
		btnRun.setLayoutData(gd_btnRun);
		btnRun.setText("Run");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
	}


	private void initArrayLists(){
		selecciones.add(new src.seleccion.SeleccionRuleta());
		selecciones.add(new src.seleccion.SeleccionTorneoDet());
		selecciones.add(new src.seleccion.SeleccionTorneoPro());
		selecciones.add(new src.seleccion.SeleccionUniEstocastica());
		selecciones.add(new src.seleccion.SeleccionRanking());
		selecciones.add(new src.seleccion.SeleccionRestos());
		selecciones.add(new src.seleccion.SeleccionTruncamiento());

		for (ISeleccion s : selecciones)
			seleccionesString.add(s.toString());

		cruces.add(new src.cruce.COUniforme());
		cruces.add(new src.cruce.CO());
		cruces.add(new src.cruce.CX());
		cruces.add(new src.cruce.OX());
		cruces.add(new src.cruce.OXPP());
		cruces.add(new src.cruce.PMX());

		for (ICruce c : cruces)
			crucesString.add(c.toString());

		mutaciones.add(new src.mutacion.Intercambio());
		mutaciones.add(new src.mutacion.Insercion());
		mutaciones.add(new src.mutacion.Inversion());
		mutaciones.add(new src.mutacion.Heuristica());
		
		for (IMutacion m : mutaciones)
			mutacionesString.add(m.toString());
		
		aeropuertos.add(new src.aeropuerto.Aeropuerto1());
		aeropuertos.add(new src.aeropuerto.Aeropuerto2());

		for (Aeropuerto a : aeropuertos)
			aeropuertosString.add(a.toString());
	}
}
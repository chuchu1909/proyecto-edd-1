/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import EDD.Lista;
import Funciones.BFS;
import Funciones.DFS;
import Funciones.GuardarCSV;
import static Interfaces.Inicio.buscaMinaApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Miguel
 */
public class Tablero extends javax.swing.JFrame {
    

    /**
     * Creates new form Tablero
     */
    
    int numFilas = buscaMinaApp.getN();
    int numColumnas = buscaMinaApp.getN();
    int numMinas = buscaMinaApp.getCantidadMinas();
    private Lista letras = new Lista();
    JButton[][] botonesTablero;
    JButton[][] btnBarrido;
    boolean bandera = false;
    int numBanderasMinas = 0;
    private Lista jugadas = new Lista();

    private JLabel lblModo; // Label para mostrar el modo actual

    public Tablero() {
        //coordenadasLabel=new JLabel("Coordenadas");
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        cargarControles();
        System.out.println(buscaMinaApp.getGrafo());
        lblModo = new JLabel("Modo: Barrer");
        lblModo.setBounds(25, 10, 200, 30); // Posición y tamaño
        getContentPane().add(lblModo);
        actualizarTablero();
    }

    private void cargarControles() {
        letras.nombrarColumnas();

        int anchoControl = 30;
        int altoControl = 30;
        int espacio = 5; // Espaciado entre botones

        int anchoTablero = numColumnas * (anchoControl + espacio) - espacio;
        int altoTablero = numFilas * (altoControl + espacio) - espacio;

        // Centrar el tablero en la ventana
        int posXReferencia = (getWidth() - anchoTablero) / 2;
        int posYReferencia = 40; // Espacio desde la parte superior

        botonesTablero = new JButton[numFilas][numColumnas];
        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + letras.getValor(j));

                botonesTablero[i][j].setBackground(Color.GRAY);
                botonesTablero[i][j].setForeground(Color.BLACK);
                botonesTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (i == 0 && j == 0) {
                    botonesTablero[i][j].setBounds(posXReferencia, posYReferencia, anchoControl, altoControl);
                } else if (i == 0 && j != 0) {
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i][j - 1].getX() + botonesTablero[i][j - 1].getWidth() + espacio,
                            posYReferencia, anchoControl, altoControl);
                } else {
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i - 1][j].getX(),
                            botonesTablero[i - 1][j].getY() + botonesTablero[i - 1][j].getHeight() + espacio,
                            anchoControl, altoControl);
                }

                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }
                });

                getContentPane().add(botonesTablero[i][j]);
            }
        }

        // Ajustar tamaño de la ventana para los botones adicionales
        int alturaExtra = 150; // Espacio extra para los botones
        int nuevaAltura = posYReferencia + altoTablero + alturaExtra;
        int nuevaAncho = Math.max(getWidth(), anchoTablero + 50);

        this.setSize(nuevaAncho, nuevaAltura);

        // Agregar botones extras
        agregarBotonesExtras();
        agregarBotonesDeBusqueda();
        revalidate();
        repaint(); 
       
    }
    /*Registra las fugadas hechas por el usuario y las agrea a una lista*/
    private void registrarJugada(String posColumna,int posFila,String nombreCasilla){
        String jugada= "Columna:"+posColumna+"  "+"Fila:"+posFila+"  "+
                "Nombre de la casilla es:"+nombreCasilla;
                jugadas.InsertarFinal(jugada);
                
    }
    
    //Crear los botones de tipo de b&uacutesqueda
    private void agregarBotonesDeBusqueda(){
     /**/
        setLayout(new BorderLayout());  
        
    /*Se crea un panel para que permite posci&oacute 
     componentes dentro del panel*/
    JPanel panelPrincipal= new JPanel(null);
     getContentPane().add(panelPrincipal,BorderLayout.CENTER);
     
    //Se crea el panel de b&uacutesqueda y se alinea a la izquierda
    JPanel panelOpciones= new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
    /*Se pone en el norte del tablero principal*/
    getContentPane().add(panelOpciones,BorderLayout.NORTH);
    
    //Crea los botones para seleccionar la b&uacutesqueda 
    boton_DFS=new JRadioButton("DFS");
    boton_BFS=new JRadioButton("BFS");
    /*ButtonGroup() verifica que solo uno de ellos este activo*/
    tipoBusqueda=new ButtonGroup();
    tipoBusqueda.add(boton_BFS);
    tipoBusqueda.add(boton_DFS);
    
    //agrega los botones al panel de opci&oacuten
    panelOpciones.add(new JLabel("Selecciona el tipo de Búsqueda"));
    panelOpciones.add(boton_DFS);
    panelOpciones.add(boton_BFS);
    
    /*Tamaño para el panel de opci&oacuten*/
    panelOpciones.setPreferredSize(new Dimension (300,60));
    /*Se agrega un borde gris alrededor del panel de opci&oacuten */
    panelOpciones.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    //getContentPane().add(panelOpciones);
    
    
    //Opci&oacuten con la que empiezas
    boton_BFS.setSelected(true);
    
    /*Cuando se presiona un boton imprime el tipo de b&uacutesqueda  */
    boton_BFS.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Búsqueda BFS seleccionada");
        }
    });
        boton_DFS.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("Búsqueda DFS seleccionada");
       }
    });
    
    }
    
    /*Imprime la lista de jugadas y las enumera*/
    private void imprimirJugadas(){
    int N=1;
        System.out.println("Jugadas realizadas:");
        for(int i=0;i<jugadas.getSize();i++){
            String jugada=(String) jugadas.get(i);
            System.out.println("Jugada numero:"+N+"     "+jugada);
            N++;            
        }System.out.println("");
    }
    
    /*Actualiza el tablero para mostrar el barrido*/
    private void actualizarTablero(){
        /*Recorrer las casillas del tablero*/
        for (int i = 0; i <numFilas ; i++) {
            for (int j = 0; j < numColumnas; j++) {
                /*se convierte las columnas de letras a numeros*/
                String nombreCasilla=(char)('A'+j)+String.valueOf(i);
                /*Se busca la casilla en el grafo*/
                Casilla casilla = buscaMinaApp.getGrafo().buscar(nombreCasilla);
                /*Se obtiene las variables finales */
                final int fi=i;
                final int fj=j;
                /*Se verifica que la casilla exita y que no este revelada */
                if(casilla!=null){
                if(casilla.estaRevelada()){
                    /*Si la casilla tiene una mina se pone una bomba y 
                    se pone de color rojo la casilla */
                    if(casilla.isMina()){
                        /*Runnable este m&eacutetodo asegura que la intefaz se
                        actualice correectamente y de manera segura y el run 
                        se encarga de actualizar las componentes*/
                        SwingUtilities.invokeLater(new Runnable(){
                              @Override
                              public void run(){    
                        botonesTablero[fi][fj].setText("💣");
                        botonesTablero[fi][fj].setBackground(Color.RED);
                        
                       //estas funciones sirven para actualizar la interfaz 
                        botonesTablero[fi][fj].revalidate();
                        botonesTablero[fi][fj].repaint();      
                              }
                    });}else{
                        /*Se obtiene la minas adyacentes deesa casilla*/
                        int minasAdyacentes=casilla.cantidadMinasAdy();
                            SwingUtilities.invokeLater(new Runnable(){
                              @Override
                              public void run(){
                        /*Si hay mas de una mina cerca la casilla se pone 
                        de color amarilla y muestra el numero de minas cerca*/
                        if(minasAdyacentes>0 ){
                            botonesTablero[fi][fj].setText(String.valueOf(minasAdyacentes));
                            botonesTablero[fi][fj].setBackground(Color.YELLOW);}
                        else{
                            /*Si no hay minas cerca las casilla se cambia 
                            a color azul*/
                            botonesTablero[fi][fj].setText("");
                            botonesTablero[fi][fj].setBackground(Color.BLUE);
                            }
                        //Desabilita el boton si la casilla ya fue revelada
                        botonesTablero[fi][fj].setEnabled(false);
                        //estas funciones sirven para actualizar la interfaz 
                        botonesTablero[fi][fj].revalidate();
                        botonesTablero[fi][fj].repaint();}
                              });
           
                        }
                    }
                    
                }else{
                SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                /*Si la casilla no ha sido revelada entonces
                la casilla sera de color gris */
                botonesTablero[fi][fj].setText("");
                botonesTablero[fi][fj].setBackground(Color.GRAY);
                botonesTablero[fi][fj].setEnabled(true);
                 //estas funciones sirven para actualizar la interfaz 
                botonesTablero[fi][fj].revalidate();
                botonesTablero[fi][fj].repaint();}
                }
                );
            }
        }
        }
    }
    
    private void btnClick(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila = Integer.parseInt(coordenada[0]);
        String posColumna = coordenada[1];
        String nombreCasilla = posColumna + posFila; // Nombre del vértice en el grafo

        // Buscar la casilla en el grafo
        Casilla casilla = buscaMinaApp.getGrafo().buscar(nombreCasilla);

        if (casilla != null) {
            if (!bandera) {
                if (casilla.isMina()) {
                    btn.setText("💣");
                    JOptionPane.showMessageDialog(null, "Haz Perdido");
                    accionSalir();
                } else {
                      /*Envia las posici&oacuten de la casilla 
                    que le dieron click*/
                      registrarJugada(posColumna,posFila,nombreCasilla);
                      /*Imprime esa casilla*/
                      imprimirJugadas();
                      /*Obtener el grafo del tablero*/
                      Grafo g=buscaMinaApp.getGrafo();
                      /*Utiliza el m&eacutetodo para realizar la b&uacutesqueda
                      de forma DFS*/
                      if(boton_DFS.isSelected()){
                            DFS dfs=new DFS(g);
                      /*Barre todos los 0 alrededor de la casilla con DFS*/
                      dfs.realizarDFS(nombreCasilla);
                      }
                      /*Utiliza el m&eacutetodo para realizar la b&uacutesqueda
                      de forma BFS*/
                      else if(boton_BFS.isSelected()){
                        BFS bfs=new BFS(g);
                        /*Barre todos los 0 alrededor de la casilla con BFS*/
                        bfs.Barrer(posFila, posColumna);
                      }
                    actualizarTablero();     
                }
            } else {
                if (buscaMinaApp.getGrafo().verticesMarcados() < this.numMinas) {
                    if (!casilla.isEstaMarcada()) {
                        btn.setBackground(Color.RED);
                        casilla.setEstaMarcada(true);
                        if (casilla.isMina()) {
                            numBanderasMinas++;
                        }

                        if (numBanderasMinas == buscaMinaApp.getCantidadMinas()) {
                            int confirmacion = JOptionPane.showConfirmDialog(this, "Haz Ganado", "Volver al Menu", JOptionPane.YES_NO_OPTION);
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                Inicio inicio = new Inicio();
                                this.dispose();
                            }
                        }
                    } else {
                        btn.setBackground(Color.GRAY);
                        casilla.setEstaMarcada(false);
                        if (casilla.isMina()) {
                            numBanderasMinas--;
                        }
                    }
                } else {
                    if (casilla.isEstaMarcada()) {
                        btn.setBackground(Color.GRAY);
                        casilla.setEstaMarcada(false);
                        if (casilla.isMina()) {
                            numBanderasMinas--;
                        }

                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Error: No se encontró la casilla en el grafo.");
        }
        
    }
    //Agrega botones extra para el usuario,
    private void agregarBotonesExtras() {
        int posXBotones = 25;
        int posYBotones = botonesTablero[numFilas - 1][0].getY() + botonesTablero[numFilas - 1][0].getHeight() + 20;
        int anchoBoton = 100;
        int altoBoton = 30;
        int espacioBoton = 20;

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(posXBotones, posYBotones, anchoBoton, altoBoton);
        btnGuardar.addActionListener(e -> accionGuardar());
        getContentPane().add(btnGuardar);

        // Botón Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(posXBotones + anchoBoton + espacioBoton, posYBotones, anchoBoton, altoBoton);
        btnSalir.addActionListener(e -> accionSalir());
        getContentPane().add(btnSalir);

        // Botón Marcar
        JButton btnMarcar = new JButton("Marcar");
        btnMarcar.setBounds(posXBotones + 2 * (anchoBoton + espacioBoton), posYBotones, anchoBoton, altoBoton);
        btnMarcar.addActionListener(e -> accionMarcar());
        getContentPane().add(btnMarcar);
    }
    private void accionGuardar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación para guardar la partida");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv")); // Filtrar solo archivos .csv

        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado por el usuario
            File archivo = fileChooser.getSelectedFile();

            // Si el archivo no tiene la extensión ".csv", agregarla automáticamente
            if (!archivo.getName().endsWith(".csv")) {
                archivo = new File(archivo.getAbsolutePath() + ".csv");
            }

            // Llamar a la función de guardado pasándole el archivo elegido
            GuardarCSV.guardarPartida(botonesTablero, archivo);
        }

    }

// Acción para el botón "Salir"
    private void accionSalir() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            Inicio inicio = new Inicio();
            this.dispose();
        }
    }

// Acción para el botón "Marcar"
    private void accionMarcar() {

        bandera = !bandera;
        lblModo.setText(bandera ? "Modo: Marcar" : "Modo: Barrer"); // Actualiza el label

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

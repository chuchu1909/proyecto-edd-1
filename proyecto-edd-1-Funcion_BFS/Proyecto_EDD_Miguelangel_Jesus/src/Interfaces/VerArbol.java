/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;
import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;


import javax.swing.*;
import java.awt.*;
public class VerArbol extends JFrame {

    private Grafo grafo;
    private Viewer visor;
    private ViewPanel panelVista;
    public VerArbol(Grafo grafo) {
        this.grafo = grafo;
        configurarInterfaz();
        inicializarVisor();
        agregarBotonCerrar();
    }

    private void configurarInterfaz() {
        setTitle("Visualización del Grafo");
        setSize(800, 600); // Aumentar el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }
    private void inicializarVisor() {
        Graph grafoVisual = new SingleGraph("Grafo de Casillas");
        construirGrafo(grafoVisual);

        visor = grafoVisual.display(false);
        visor.enableAutoLayout();
        if (panelVista == null) {
            panelVista = (ViewPanel) visor.getDefaultView();
            add(panelVista, BorderLayout.CENTER);
        }
    }

    private void construirGrafo(Graph grafoVisual) {
        Nodo aux = grafo.getVertices().getpFirst();
        while (aux != null) {
            Casilla casilla = (Casilla) aux.getDato();
            String nombreCasilla = casilla.getNombre();

            // Agregar nodo al grafo visual
            if (grafoVisual.getNode(nombreCasilla) == null) {
                Node nodo = grafoVisual.addNode(nombreCasilla);
                nodo.setAttribute("ui.label", nombreCasilla);
                nodo.setAttribute("ui.style", "fill-color: blue; size: 30px; text-size: 16px;"); // Cambiar color y tamaño
            }
            // Agregar aristas (adyacencias)
            for (int i = 0; i < casilla.getAdyacentes().getSize(); i++) {
                Casilla adyacente = (Casilla) casilla.getAdyacentes().getValor(i);
                String nombreAdyacente = adyacente.getNombre();

                if (grafoVisual.getEdge(nombreCasilla + "-" + nombreAdyacente) == null) {
                    grafoVisual.addEdge(nombreCasilla + "-" + nombreAdyacente, nombreCasilla, nombreAdyacente, true)
                            .setAttribute("ui.style", "fill-color: orange; size: 2px;"); // Cambiar color y tamaño
                }
            }
            aux = aux.getPnext();
        }

        // Configuración del estilo del grafo
        grafoVisual.setAttribute("ui.stylesheet",
                "node { text-size: 16px; size: 30px; text-alignment: under; }" +
                        "edge { size: 2px; fill-color: orange; }");
    }
    private void agregarBotonCerrar() {
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> {
            cerrarVisor();
            dispose();
        });
        add(botonCerrar, BorderLayout.SOUTH);
    }

    private void cerrarVisor() {
        if (visor != null) {
            visor.disableAutoLayout();
            visor.close();
        }
        if (panelVista != null) {
            remove(panelVista);
            panelVista = null;
        }
    }
}

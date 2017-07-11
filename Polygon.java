/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Polygon implements Serializable{
    ArrayList<Edge> Edges;
    int numEdges;
    Color edgesColor;
    
    Polygon( ArrayList<Edge> EdgeBuffer ){
        this.Edges = EdgeBuffer;
        numEdges = EdgeBuffer.size();
        edgesColor = Color.black;
    }
    
    Polygon( ){
        Edges = new ArrayList<Edge>( );
        numEdges = 0;
        edgesColor = Color.black;
    }
    
    Polygon( ArrayList<Node> pontos, Node origem, Color c, boolean complete ){
        Edges = new ArrayList<Edge>( );
        numEdges = 0;
        edgesColor = c;
        
        AddEdge( origem, pontos.get( 0 ) );
        for( int i = 1; i < pontos.size(); i++ ){
            AddEdge(pontos.get(i), pontos.get(i-1) );
        }
        
        if( complete ) AddEdge( pontos.get( pontos.size()-1 ), origem );

    }
    
    public void AddEdge( Node a, Node b ){
        Edge temp = new Edge(a, b);
        this.Edges.add(temp);
        this.numEdges++;
    }
    
    public Node nodeCoordinatesA( int index ){
        return Edges.get(index).a;
    }
    
    public Node nodeCoordinatesB( int index ){
        return Edges.get(index).b;
    }
    
    public void setColor( Color c ){
        this.edgesColor = c;
    }
    
}

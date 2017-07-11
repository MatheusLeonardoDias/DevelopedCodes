/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class Edge implements Serializable{
    Node a, b;
    
    Edge( Node a, Node b ){
        this.a = a;
        this.b = b;
    }
    
    public Node getNodeA(){
        return this.a;
    }
    
    public Node getNodeB(){
        return this.b;
    }
    
}

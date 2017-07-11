/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 *
 * @author root
 */
public class Node implements Serializable{

    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX( int nx ){
        this.x = nx;
    }

    public void setY( int ny ){
        this.y = ny;
    }

    public int normalizedCoordinateX( int NDHML ) {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double ndcx = (this.x) / (screensize.getWidth()-1);
        
        return (int) Math.round( ndcx*NDHML );
    }

    public int normalizedCoordinateY( int NDVML ) {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double ndcy = (this.y) / (screensize.getHeight()-1);

        return (int) Math.round( ndcy*NDVML );
    }
}

/*
Bugs:

    throw exception ao desenhar com mao livre, na selecao a referencia de uma aresta e nula.
    icones dos botoes


 */
package cg;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import sun.java2d.loops.DrawLine;

public class InterfaceCG extends JFrame implements Serializable{

    ArrayList<Polygon> PolyList;
    MouseListener MaoLivreL, TamanhoDefinidoL, SelecionarPoligonoL;
    MouseMotionListener SelecionarPoligonoML;

    public InterfaceCG() throws IOException {
        initComponents();
        //super.addMouseListener(this);
        PolyList = new ArrayList();
        
        spinnerRaio.setVisible(false);
        labelRaio.setVisible(false);
        spinnerVertices.setVisible(false);
        labelVertices.setVisible(false);
        spinnerRaio.setValue(50);
        spinnerVertices.setValue(4);
        initializeMouseListeners();
        this.setTitle("PolyDraw");
        Image WindowIcon = ImageIO.read(new File("WindowIcon.png"));
        this.setIconImage( WindowIcon );
        this.setLocationRelativeTo(null);
        
//        this.setSize(700, 600);
//        jDesktopPane1.setSize(100, 600 );
//        jDesktopPane1.setLocation(0, 0);
//        QuadroDesenho.setSize(600, 600);
        
        ActionListener updateTask;
        updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                repaint();  // Refresh the JFrame, callback paintComponent()
            }
        };
        // Allocate a Timer to run updateTask's actionPerformed() after every delay msec
        new Timer(500, updateTask).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuadroDesenho = new javax.swing.JPanel();
        spinnerVertices = new javax.swing.JSpinner();
        spinnerRaio = new javax.swing.JSpinner();
        MaoLivre = new javax.swing.JButton();
        labelVertices = new javax.swing.JLabel();
        labelRaio = new javax.swing.JLabel();
        TamanhoDefinido = new javax.swing.JButton();
        SelecionarPoligono = new javax.swing.JButton();
        ExcluirPoligonos = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        Carregar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(600, 600));
        setSize(new java.awt.Dimension(600, 600));

        QuadroDesenho.setBackground(new java.awt.Color(102, 102, 102));
        QuadroDesenho.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout QuadroDesenhoLayout = new javax.swing.GroupLayout(QuadroDesenho);
        QuadroDesenho.setLayout(QuadroDesenhoLayout);
        QuadroDesenhoLayout.setHorizontalGroup(
            QuadroDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );
        QuadroDesenhoLayout.setVerticalGroup(
            QuadroDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        MaoLivre.setFont(new java.awt.Font("Dialog", 1, 5)); // NOI18N
        MaoLivre.setText("Mao Livre");
        MaoLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaoLivreActionPerformed(evt);
            }
        });

        labelVertices.setText("Vertices");

        labelRaio.setText("Raio");

        TamanhoDefinido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TamanhoDefinidoActionPerformed(evt);
            }
        });

        SelecionarPoligono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecionarPoligonoActionPerformed(evt);
            }
        });

        ExcluirPoligonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirPoligonosActionPerformed(evt);
            }
        });

        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        Carregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarregarActionPerformed(evt);
            }
        });

        Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelVertices)
                    .addComponent(MaoLivre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TamanhoDefinido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRaio)
                    .addComponent(spinnerVertices, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerRaio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SelecionarPoligono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExcluirPoligonos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Carregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QuadroDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MaoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TamanhoDefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SelecionarPoligono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ExcluirPoligonos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(labelVertices)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerVertices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRaio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerRaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Carregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(QuadroDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TamanhoDefinidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TamanhoDefinidoActionPerformed

        cleanSection();
        spinnerRaio.setVisible(true);
        labelRaio.setVisible(true);
        spinnerVertices.setVisible(true);
        labelVertices.setVisible(true);


        QuadroDesenho.setCursor( new Cursor( Cursor.CROSSHAIR_CURSOR));
        
        QuadroDesenho.addMouseListener( TamanhoDefinidoL );
    }//GEN-LAST:event_TamanhoDefinidoActionPerformed

    private void MaoLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaoLivreActionPerformed
        cleanSection();

        
        // Colocar numa funcao plmds
        this.MaoLivreL = new MouseAdapter() {
            boolean check = false;
            int erro = 10;
            ArrayList<Node> Pontos = new ArrayList();
            Node Origem = new Node(-1, -1);
            public void mouseClicked(MouseEvent evt) {
                if( !PolyList.isEmpty() && check )
                    PolyList.remove(PolyList.size()-1);
                if( evt.getButton() == MouseEvent.BUTTON3 ){
                    QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
                    QuadroDesenho.removeMouseListener(this);
                }else{

                    if( Origem.x == -1 && Origem.y == -1 ){
                        Origem.setX( evt.getX() );
                        Origem.setY( evt.getY() );
                    }else{

                        if( Math.hypot( Math.abs(Origem.x-evt.getX()), Math.abs(Origem.y-evt.getY()) ) < erro ){
                            PolyList.add( new Polygon( Pontos, Origem, Color.black, true ) ); check = true;
                            QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
                            QuadroDesenho.removeMouseListener(this);
                        }else{
                            Node ponto = new Node( evt.getX(), evt.getY() );
                            Pontos.add(ponto);
                            PolyList.add( new Polygon( Pontos, Origem, Color.blue, false ) ); check = true;

                        }
                    }
                }
            }

        };
        
        QuadroDesenho.setCursor( new Cursor(Cursor.CROSSHAIR_CURSOR) );

        QuadroDesenho.addMouseListener( MaoLivreL );
    }//GEN-LAST:event_MaoLivreActionPerformed

    private void SelecionarPoligonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecionarPoligonoActionPerformed
        cleanSection();

        QuadroDesenho.addMouseListener( SelecionarPoligonoL );
        QuadroDesenho.addMouseMotionListener( SelecionarPoligonoML ); 
        
    }//GEN-LAST:event_SelecionarPoligonoActionPerformed

    private void ExcluirPoligonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirPoligonosActionPerformed
        for( int i = 0; i < PolyList.size(); i++ ){
            if( PolyList.get(i).edgesColor == red ){
                PolyList.remove(i);
                i = -1;
            }
        }
    }//GEN-LAST:event_ExcluirPoligonosActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        cleanSection();
        JFileChooser j = new JFileChooser();
        if( j.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
            File f = j.getSelectedFile();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                oos.writeObject(PolyList);
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_SalvarActionPerformed

    private void CarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarregarActionPerformed
        cleanSection();
        JFileChooser j = new JFileChooser();
        if( j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
            File f = j.getSelectedFile();
            FileInputStream fos = null;
            try {
                fos = new FileInputStream(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectInputStream oos = null;
            try {
                oos = new ObjectInputStream(fos);
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                try {
                    this.PolyList = (ArrayList<Polygon>) oos.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_CarregarActionPerformed

    private void NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoActionPerformed
        cleanSection();
        PolyList.clear();
    }//GEN-LAST:event_NovoActionPerformed

    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = QuadroDesenho.getGraphics();
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint
          (RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke( new BasicStroke( (float) 2.0) );
        g.setColor(Color.black);

        for (int i = 0; i < PolyList.size(); i++) {
            
            for (int k = 0; k < PolyList.get(i).Edges.size(); k++) {
                g.setColor( PolyList.get(i).edgesColor );
//                g.drawLine(PolyList.get(i).nodeCoordinatesA(k).x, PolyList.get(i).nodeCoordinatesA(k).y, PolyList.get(i).nodeCoordinatesB(k).x, PolyList.get(i).nodeCoordinatesB(k).y);
                g2.draw(new Line2D.Double(PolyList.get(i).nodeCoordinatesA(k).x, PolyList.get(i).nodeCoordinatesA(k).y, PolyList.get(i).nodeCoordinatesB(k).x, PolyList.get(i).nodeCoordinatesB(k).y));
            }
        }
    }
    
    public int mouseUnderEdge(){
//        int r, s, t;
//        double dist;
        int erro = 7;
        for (int i = 0; i < PolyList.size(); i++) {
            
            if( !(PolyList.get(i).edgesColor == red) )
                for (int j = 0; j < PolyList.get(i).numEdges; j++) {

                    double ax = PolyList.get(i).nodeCoordinatesA(j).x;
                    double ay = PolyList.get(i).nodeCoordinatesA(j).y;
                    double bx = PolyList.get(i).nodeCoordinatesB(j).x;
                    double by = PolyList.get(i).nodeCoordinatesB(j).y;

                    double cx = QuadroDesenho.getMousePosition().x;
                    double cy = QuadroDesenho.getMousePosition().y;
                    double r_numerator = (cx-ax)*(bx-ax) + (cy-ay)*(by-ay);
                    double r_denomenator = (bx-ax)*(bx-ax) + (by-ay)*(by-ay);
                    double r = r_numerator / r_denomenator;

                    double px = ax + r*(bx-ax);
                    double py = ay + r*(by-ay);

                    double s =  ((ay-cy)*(bx-ax)-(ax-cx)*(by-ay) ) / r_denomenator;

                    double distanceLine = Math.abs(s)*Math.sqrt(r_denomenator);
                    double distanceSegment;
                   
                    double xx = px;
                    double yy = py;

                    if ( (r >= 0) && (r <= 1) )
                    {
                            distanceSegment = distanceLine;
                    }
                    else
                    {
                        double dist1 = (cx-ax)*(cx-ax) + (cy-ay)*(cy-ay);
                        double dist2 = (cx-bx)*(cx-bx) + (cy-by)*(cy-by);
                        if (dist1 < dist2)
                        {
                                xx = ax;
                                yy = ay;
                                distanceSegment = Math.sqrt(dist1);
                        }
                        else
                        {
                                xx = bx;
                                yy = by;
                                distanceSegment = Math.sqrt(dist2);
                        }

                    }
                    
                    if( distanceSegment <= erro )
                        return i;
                    
                }
        }
        
        return -1;
    }
    
    public void deleteIncompletePolygons(){
       for (int i = 0; i < PolyList.size(); i++) {
           if( PolyList.get(i).edgesColor == blue ){
               PolyList.remove(i); i--;
               continue;
           }
           PolyList.get(i).setColor(black);
       }
        
    }
    
    public void cleanSection(){
        deleteIncompletePolygons();
        QuadroDesenho.removeMouseListener(MaoLivreL);
        QuadroDesenho.removeMouseListener(SelecionarPoligonoL);
        QuadroDesenho.removeMouseMotionListener(SelecionarPoligonoML);
        QuadroDesenho.removeMouseListener(TamanhoDefinidoL);
        spinnerRaio.setVisible(false);
        labelRaio.setVisible(false);
        spinnerVertices.setVisible(false);
        labelVertices.setVisible(false);
        QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
    }
    
    public void initializeMouseListeners( ){
        
        this.TamanhoDefinidoL = new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                
                int raio = (Integer) spinnerRaio.getValue();
                int vertices = (Integer) spinnerVertices.getValue();
                Node Origem = new Node(evt.getX(), evt.getY());
                double teta = (2 * Math.PI / vertices );

                Point PontoInicial = new Point(0, raio);
                if (PontoInicial.y % 2 == 0) {
                    PontoInicial.x = (int) Math.round((PontoInicial.y * ((double) Math.sin(Math.PI / 4))));
                    PontoInicial.y = (int) Math.round((PontoInicial.y * ((double) Math.cos(Math.PI / 4))));

                }
                Node anterior = new Node(PontoInicial.x, PontoInicial.y);

                /**/
                ArrayList<Edge> teste = new ArrayList();
                /**/
                for (int i = 1; i <= vertices; i++) {
                    Node proximo = new Node(
                        (int) Math.round((anterior.x * ((double) Math.cos(teta))) - (anterior.y * ((double) Math.sin(teta)))),
                        (int) Math.round((anterior.x * ((double) Math.sin(teta))) + (anterior.y * ((double) Math.cos(teta))))
                    );
                    anterior.x += Origem.x;
                    anterior.y += Origem.y;
                    proximo.x += Origem.x;
                    proximo.y += Origem.y;

                    Edge ui = new Edge(new Node(anterior.x, anterior.y), proximo);
                    teste.add(ui);
                    anterior.x = proximo.x - Origem.x;
                    anterior.y = proximo.y - Origem.y;
                }

                PolyList.add(new Polygon(teste));

            }
        };
        
//        this.MaoLivreL = new MouseAdapter() {
//            boolean check = false;
//            int erro = 10;
//            ArrayList<Node> Pontos = new ArrayList();
//            Node Origem = new Node(-1, -1);
//            public void mouseClicked(MouseEvent evt) {
//                if( !PolyList.isEmpty() && check )
//                    PolyList.remove(PolyList.size()-1);
//                if( evt.getButton() == MouseEvent.BUTTON3 ){
//                    QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
//                    QuadroDesenho.removeMouseListener(this);
//                }else{
//
//                    System.out.println(evt.getX()+" "+evt.getY());
//                    if( Origem.x == -1 && Origem.y == -1 ){
//                        System.out.println("Origem: "+evt.getX()+" "+evt.getY());
//                        Origem.setX( evt.getX() );
//                        Origem.setY( evt.getY() );
//                    }else{
//
//                        if( Math.hypot( Math.abs(Origem.x-evt.getX()), Math.abs(Origem.y-evt.getY()) ) < erro ){
//                            PolyList.add( new Polygon( Pontos, Origem, Color.black, true ) ); check = true;
//                            QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
//                            QuadroDesenho.removeMouseListener(this);
//                        }else{
//                            Node ponto = new Node( evt.getX(), evt.getY() );
//                            Pontos.add(ponto);
//                            PolyList.add( new Polygon( Pontos, Origem, Color.blue, false ) ); check = true;
//
//                        }
//                    }
//                }
//            }
//
//        };
        
        this.SelecionarPoligonoL = new MouseAdapter(){
            
            public void mouseClicked( MouseEvent evt ){
                int index = mouseUnderEdge();
                if( index != -1 ){
                    PolyList.get(index).setColor(red);
                }else{
                    for( int i = 0; i < PolyList.size(); i++ ){
                        PolyList.get(i).setColor(black);
                    }
                }
            }
            
        };
        
        this.SelecionarPoligonoML = new MouseMotionAdapter() {
            
            public void mouseMoved( MouseEvent evt ){
                if( mouseUnderEdge() != -1 )
                    QuadroDesenho.setCursor( new Cursor(Cursor.HAND_CURSOR) );
                else
                    QuadroDesenho.setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
            }
            
            public void mouseClicked( MouseEvent evt ){
                
            }
            
        };
        
        
    }

   

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
            java.util.logging.Logger.getLogger(InterfaceCG.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 



catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCG.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 



catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCG.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 



catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCG.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new InterfaceCG().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCG.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Carregar;
    private javax.swing.JButton ExcluirPoligonos;
    private javax.swing.JButton MaoLivre;
    private javax.swing.JButton Novo;
    private javax.swing.JPanel QuadroDesenho;
    private javax.swing.JButton Salvar;
    private javax.swing.JButton SelecionarPoligono;
    private javax.swing.JButton TamanhoDefinido;
    private javax.swing.JLabel labelRaio;
    private javax.swing.JLabel labelVertices;
    private javax.swing.JSpinner spinnerRaio;
    private javax.swing.JSpinner spinnerVertices;
    // End of variables declaration//GEN-END:variables
}

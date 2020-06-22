/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Musica;
import controller.MusicaDAO;
import static view.TelaLogin.telaPrincipal;

/**
 * Classe para excluir musicas
 *
 * @author Jhennifer
 */
public class ExcluirMusica extends javax.swing.JFrame {

    /**
     * Creates new form ExcluirMusica
     */
    public ExcluirMusica() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) this.jTableMusicas.getModel();
        this.jTableMusicas.setRowSorter(new TableRowSorter(modelo));
        readJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMusicas = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setResizable(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_size_lado-removebg-preview.png"))); // NOI18N

        jTableMusicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Artista", "Genero", "Album", "Duracao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableMusicas);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonExcluir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
        boolean sucesso = false;

        //quantidade de itens selecionados
        int rowIndex = jTableMusicas.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a musica a ser excluida!");
            return;
        }

        //conteudo dos itens selecionados
        String nome = (String) this.jTableMusicas.getModel().getValueAt(rowIndex, 0);
        String artista = (String) this.jTableMusicas.getModel().getValueAt(rowIndex, 1);

        MusicaDAO musicaDAO = new MusicaDAO();

        sucesso = musicaDAO.deletarMusica(nome, artista);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Musica excluida com sucesso");
            ((DefaultTableModel) jTableMusicas.getModel()).removeRow(jTableMusicas.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Tente novamente!");
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    /**
     * Método para exibir a classe TelaPrincipal
     */
    private void chamarTelaPrincipal() {
        if (telaPrincipal == null) {
            telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
        }
        this.setVisible(false);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        chamarTelaPrincipal();
    }//GEN-LAST:event_jButton1ActionPerformed

    public JTable getjTableMusicas() {
        return jTableMusicas;
    }

    /**
     * Método para ler e salvar dados na tabela referente as musicas
     */
    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableMusicas.getModel();
        MusicaDAO musicaDAO = new MusicaDAO();
        for (Musica m : musicaDAO.buscarMusicaSemFiltro()) {
            modelo.addRow(new Object[]{
                m.getNome(),
                m.getArtista(),
                m.getGenero(),
                m.getAlbum(),
                m.getDuracao()
            });

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMusicas;
    // End of variables declaration//GEN-END:variables
}

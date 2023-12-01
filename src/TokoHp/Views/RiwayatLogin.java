package TokoHp.Views;

import TokoHp.Objects.Variable;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class RiwayatLogin extends javax.swing.JInternalFrame {

    Connection connection;
    Object[] tableHead = {"ID SESSION", "ID USER", "NAMA LENGKAP", "WAKTU LOGIN"};
    DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);

    public RiwayatLogin() {
        initComponents();
        init();
    }

    private void init() {
        connection = Variable.koneksi();
        read();
        Variable.setLabelFont(jLabel1);
        Variable.setPlaceholderTextfield(tfPencarian, "Cari");
    }

    private void read() {
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            sql = "SELECT ID_SESSION, ID_USER, NAMA_LENGKAP, WAKTU_LOGIN FROM USERS JOIN SESSIONS USING(ID_USER) WHERE ID_USER LIKE ? OR LOWER(NAMA_LENGKAP) LIKE ? ORDER BY ID_SESSION DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + tfPencarian.getText() + "%");
            preparedStatement.setString(2, "%" + tfPencarian.getText() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] data = {
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getTimestamp(4)
                };
                tableModel.addRow(data);
            }
            table.setModel(tableModel);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tfPencarian = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1200, 740));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RIWAYAT LOGIN");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        tfPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPencarianKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tfPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(tfPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPencarianKeyReleased
        read();
    }//GEN-LAST:event_tfPencarianKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField tfPencarian;
    // End of variables declaration//GEN-END:variables
}